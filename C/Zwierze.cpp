#include "Zwierze.h"
#include "Wilk.h"
#include "Owca.h"
#include "Lis.h"
#include "Zolw.h"
#include "Antylopa.h"
#include <cstdlib>
#define LEWO 0 
#define GORA 1
#define PRAWO 2
#define DOL 3
int Zwierze::Get_wiek()const
{
	return this->wiek;
}
int Zwierze::Get_inicjatywa()const
{
	return this->inicjatywa;
}
char Zwierze::Get_znak()const
{
	return this->znak;
}
int Zwierze::Get_sila()const
{
	return this->sila;
}
int Zwierze::Get_x()const
{
	return this->x;
}
int Zwierze::Get_y()const
{
	return this->y;
}
void Zwierze::Set_inicjatywa(int inicjatywa)
{
	this->inicjatywa = inicjatywa;
}
void Zwierze::Set_sila(int sila)
{
	this->sila = sila;
}
void Zwierze::Set_x(int x)
{
	this->x = x;
}
void Zwierze::Set_y(int y)
{
	this->y = y;
}
void Zwierze::Set_znak(int znak)
{
	this->znak = znak;
}
void Zwierze::Set_wiek(int wiek)
{
	this->wiek = wiek;
}
void Zwierze::akcja()
{
	int old_x = this->Get_x();
	int old_y = this->Get_y();
	bool poza = false;
	int kierunek = rand() % 4;
	switch (kierunek)
	{
	case LEWO:
		if (this->x - 1 >= 0)
		{
			this->x -= 1;
		}
		else poza = true;
		break;
	case GORA:
		if (this->y - 1 >= 0)
		{
			this->y -= 1;
		}
		else poza = true;
		break;
	case PRAWO:
		if (this->x + 1 < this->swiat->Get_szerokosc())
		{
			this->x += 1;
		}
		else poza = true;
		break;
	case DOL:
		if (this->y + 1 < this->swiat->Get_wysokosc())
		{
			this->y += 1;
		}
		else poza = true;
		break;
	}
	Organizm* kolidujacy = NULL;
	if (this->swiat->GetOrganizm(this->x, this->y)!=NULL && poza == false)
	{
		kolidujacy = this->swiat->GetOrganizm(this->x, this->y);
		kolidujacy->kolizja(this, old_x, old_y);
	}
	if (poza == false)
	{
		
		Organizm* stary = this->swiat->GetOrganizm(old_x, old_y);
		if (stary != kolidujacy)//przypadek kiedy Antylopa zamienia sie miejscami z atakujacym
		{
			this->swiat->UsunOrganizm(old_x, old_y);
			if (this->swiat->CzyNaLiscie(this->Get_x(), this->Get_y()))
			{
				this->swiat->UstawOrganizm(this->Get_x(), this->Get_y(), stary);
			}
		}
	}
}
void Zwierze::rysowanie()
{
	cout << this->znak;
}
void Zwierze::kolizja(Organizm* napastnik, int x, int y)
{
	bool czy_urodzony = false;
	czy_urodzony = this->rozmnazanie(napastnik, x, y);
	
	if(czy_urodzony == false)
	{
		if (this->Get_sila() > napastnik->Get_sila())
		{
			cout << this->Get_znak() << " zabija  napastnika " << napastnik->Get_znak() << endl;
			napastnik->Set_x(x);
			napastnik->Set_y(y);
			this->swiat->UsunOrganizm(x, y);
			this->swiat->UsunzListy(x, y);
		}
		else
		{
			cout << napastnik->Get_znak() << " zabija " << this->Get_znak() << endl;
			napastnik->Set_x(x);
			napastnik->Set_y(y);
			int nowe_x = this->Get_x();
			int nowe_y = this->Get_y();
			this->swiat->UsunOrganizm(nowe_x, nowe_y);
			this->swiat->UsunzListy(nowe_x, nowe_y);
			napastnik->Set_x(nowe_x);
			napastnik->Set_y(nowe_y);

		}
	}

}
bool Zwierze::rozmnazanie(Organizm* napastnik, int x, int y)
{
	if (dynamic_cast<Wilk*>(this->swiat->GetOrganizm(this->Get_x(), this->Get_y())) != nullptr && dynamic_cast<Wilk*>(this->swiat->GetOrganizm(x, y)) != nullptr)
	{
		napastnik->Set_x(x);
		napastnik->Set_y(y);
		Organizm* nowe_zycie;
		int x_nowego_zycia = -1;
		int y_nowego_zycia = -1;
		if (this->Get_wiek() >= 3 && napastnik->Get_wiek() >= 3)
		{
			this->szukanie_miejsca_zycia(x, y, &x_nowego_zycia, &y_nowego_zycia);
			if (x_nowego_zycia != -1 && y_nowego_zycia != -1)
			{
				nowe_zycie = new Wilk(x_nowego_zycia, y_nowego_zycia, this->swiat);
				this->swiat->DodajOrganizm(x_nowego_zycia, y_nowego_zycia, nowe_zycie);
				cout << "Nowy Wilk urodzil sie na polu " << x_nowego_zycia << " " << y_nowego_zycia << endl;
			}
			else cout << "Nie ma miejsca na urodziny Wilka" << endl;
		}
		else cout << "Jeden z Wilkow jest za m這dy by sie rozmnozyc" << endl;
		return true;

	}
	else if (dynamic_cast<Owca*>(this->swiat->GetOrganizm(this->Get_x(), this->Get_y())) != nullptr && dynamic_cast<Owca*>(this->swiat->GetOrganizm(x, y)) != nullptr)
	{
		napastnik->Set_x(x);
		napastnik->Set_y(y);
		Organizm* nowe_zycie;
		int x_nowego_zycia = -1;
		int y_nowego_zycia = -1;
		if (this->Get_wiek() >= 3 && napastnik->Get_wiek() >= 3)
		{
			this->szukanie_miejsca_zycia(x, y, &x_nowego_zycia, &y_nowego_zycia);
			if (x_nowego_zycia != -1 && y_nowego_zycia != -1)
			{
				nowe_zycie = new Owca(x_nowego_zycia, y_nowego_zycia, this->swiat);
				this->swiat->DodajOrganizm(x_nowego_zycia, y_nowego_zycia, nowe_zycie);
				cout << "Nowa Owca urodzila sie na polu " << x_nowego_zycia << " " << y_nowego_zycia << endl;
			}
			else cout << "Nie ma miejsca na urodziny Owcy" << endl;
		}
		else cout << "Jedna z Owiec jest za m這da by sie rozmnozyc" << endl;
		return true;
	}
	else if (dynamic_cast<Lis*>(this->swiat->GetOrganizm(this->Get_x(), this->Get_y())) != nullptr && dynamic_cast<Lis*>(this->swiat->GetOrganizm(x, y)) != nullptr)
	{
		napastnik->Set_x(x);
		napastnik->Set_y(y);
		Organizm* nowe_zycie;
		int x_nowego_zycia = -1;
		int y_nowego_zycia = -1;
		if (this->Get_wiek() >= 3 && napastnik->Get_wiek() >= 3)
		{
			this->szukanie_miejsca_zycia(x, y, &x_nowego_zycia, &y_nowego_zycia);
			if (x_nowego_zycia != -1 && y_nowego_zycia != -1)
			{
				nowe_zycie = new Lis(x_nowego_zycia, y_nowego_zycia, this->swiat);
				this->swiat->DodajOrganizm(x_nowego_zycia, y_nowego_zycia, nowe_zycie);
				cout << "Nowy Lis urodzil sie na polu " << x_nowego_zycia << " " << y_nowego_zycia << endl;
			}
			else cout << "Nie ma miejsca na urodziny Lisa" << endl;
		}
		else cout << "Jeden z Lisow jest za m這dy by sie rozmnozyc" << endl;
		return true;
	}
	else if (dynamic_cast<Zolw*>(this->swiat->GetOrganizm(this->Get_x(), this->Get_y())) != nullptr && dynamic_cast<Zolw*>(this->swiat->GetOrganizm(x, y)) != nullptr)
	{
		napastnik->Set_x(x);
		napastnik->Set_y(y);
		Organizm* nowe_zycie;
		int x_nowego_zycia = -1;
		int y_nowego_zycia = -1;
		if (this->Get_wiek() >= 3 && napastnik->Get_wiek() >= 3)
		{
			this->szukanie_miejsca_zycia(x, y, &x_nowego_zycia, &y_nowego_zycia);
			if (x_nowego_zycia != -1 && y_nowego_zycia != -1)
			{
				nowe_zycie = new Zolw(x_nowego_zycia, y_nowego_zycia, this->swiat);
				this->swiat->DodajOrganizm(x_nowego_zycia, y_nowego_zycia, nowe_zycie);
				cout << "Nowy Zolw urodzil sie na polu " << x_nowego_zycia << " " << y_nowego_zycia << endl;
			}
			else cout << "Nie ma miejsca na urodziny Zolwia" << endl;
		}
		else cout << "Jeden z Zolwi jest za m這dy by sie rozmnozyc" << endl;
		return true;
	}
	return false;
}
void Zwierze::szukanie_miejsca_zycia(int x_napastnika, int y_napastnika , int* x_nowego_zycia, int* y_nowego_zycia )
{
	if (this->Get_x() - 1 >= 0 && this->swiat->GetOrganizm(this->Get_x() - 1, this->Get_y()) == NULL)
	{
		*x_nowego_zycia = this->Get_x() - 1;
		*y_nowego_zycia = this->Get_y();
	}
	else if (this->Get_y() - 1 >= 0 && this->swiat->GetOrganizm(this->Get_x(), this->Get_y() - 1) == NULL)
	{
		*x_nowego_zycia = this->Get_x();
		*y_nowego_zycia = this->Get_y() - 1;
	}
	else if (this->Get_x() + 1 < this->swiat->Get_szerokosc() && this->swiat->GetOrganizm(this->Get_x() + 1, this->Get_y()) == NULL)
	{
		*x_nowego_zycia = this->Get_x() + 1;
		*y_nowego_zycia = this->Get_y();
	}
	else if (this->Get_y() + 1 < this->swiat->Get_wysokosc() && this->swiat->GetOrganizm(this->Get_x(), this->Get_y() + 1) == NULL)
	{
		*x_nowego_zycia = this->Get_x();
		*y_nowego_zycia = this->Get_y() - 1;
	}
	else if (x_napastnika - 1 >= 0 && this->swiat->GetOrganizm(x_napastnika - 1, y_napastnika) == NULL)
	{
		*x_nowego_zycia = x_napastnika - 1;
		*y_nowego_zycia = y_napastnika;
	}
	else if (y_napastnika - 1 >= 0 && this->swiat->GetOrganizm(x_napastnika, y_napastnika - 1) == NULL)
	{
		*x_nowego_zycia = x_napastnika;
		*y_nowego_zycia = y_napastnika - 1;
	}
	else if (x_napastnika + 1 < this->swiat->Get_szerokosc() && this->swiat->GetOrganizm(x_napastnika + 1, y_napastnika) == NULL)
	{
		*x_nowego_zycia = x_napastnika + 1;
		*y_nowego_zycia = y_napastnika;
	}
	else if (y_napastnika + 1 < this->swiat->Get_wysokosc() && this->swiat->GetOrganizm(x_napastnika, y_napastnika + 1) == NULL)
	{
		*x_nowego_zycia = x_napastnika;
		*y_nowego_zycia = y_napastnika + 1;
	}
}
Zwierze::~Zwierze()
{
}
