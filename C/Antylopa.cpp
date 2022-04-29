#include "Antylopa.h"
#define LEWO 0
#define GORA 1
#define PRAWO 2
#define DOL 3
Antylopa::Antylopa(int x, int y, Swiat*swiat)
{
	Set_x(x);
	Set_y(y);
	Set_inicjatywa(4);
	Set_sila(4);
	Set_znak('A');
	Set_wiek(0);
	this->swiat = swiat;
}
void Antylopa::akcja()
{
	int old_x = this->Get_x();
	int old_y = this->Get_y();
	int kierunek = rand() % 4;
	bool poza = false;
	switch (kierunek)
	{
	case LEWO:
		if (this->x - 2 >= 0)
		{
			this->x -= 2;
		}
		else poza = true;
		break;
	case GORA:
		if (this->y - 2 >= 0)
		{
			this->y -= 2;
		}
		else poza = true;
		break;
	case PRAWO:
		if (this->x + 2 < this->swiat->Get_szerokosc())
		{
			this->x += 2;
		}
		else poza = true;
		break;
	case DOL:
		if (this->y + 2 < this->swiat->Get_wysokosc())
		{
			this->y += 2;
		}
		else poza = true;
		break;
	}
	if (this->swiat->GetOrganizm(this->Get_x(), this->Get_y()) != NULL && poza == false)
	{
		Organizm* kolidujacy = this->swiat->GetOrganizm(this->Get_x(), this->Get_y());
		kolidujacy->kolizja(this, old_x, old_y);
	}
	if (poza == false)
	{
		Organizm* stary = this->swiat->GetOrganizm(old_x, old_y);
		this->swiat->UsunOrganizm(old_x, old_y);
		if (this->swiat->CzyNaLiscie(this->Get_x(), this->Get_y()))
		{
			this->swiat->UstawOrganizm(this->Get_x(), this->Get_y(), stary);
		}
	
	}

}
void Antylopa::kolizja(Organizm* napastnik, int x, int y)
{
	int szansa;
	Organizm* zmiennik = NULL;
	szansa = rand() % 4;
	if (dynamic_cast<Antylopa*>(this->swiat->GetOrganizm(this->Get_x(), this->Get_y())) != nullptr && dynamic_cast<Antylopa*>(this->swiat->GetOrganizm(x, y)) != nullptr)
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
				nowe_zycie = new Antylopa(x_nowego_zycia, y_nowego_zycia, this->swiat);
				this->swiat->DodajOrganizm(x_nowego_zycia, y_nowego_zycia, nowe_zycie);
				cout << "Nowa Antylopa urodzila sie na polu " << x_nowego_zycia << " " << y_nowego_zycia << endl;
			}
			else cout << "Nie ma miejsca na urodziny Antylopy" << endl;
		}
		else cout << "Jedna z Antylop jest za m³oda by sie rozmnozyc" << endl;
	}
	else if (szansa > 1)
	{
		cout << "Proba ucieczki Antylopy przed " << napastnik->Get_znak()<<endl;
		if (this->Get_x() - x == 0)
		{
			if (this->Get_y() - y > 0)
			{
				if (this->Get_y() + 1 < this->swiat->Get_wysokosc() && this->swiat->GetOrganizm(this->Get_x(), this->Get_y() + 1) == NULL)
				{
					this->Ucieczka(this->Get_x(), this->Get_y() + 1);
				}
				else if (this->Get_x() - 1 >= 0 && this->swiat->GetOrganizm(this->Get_x() - 1, this->Get_y()) == NULL)
				{
					this->Ucieczka(this->Get_x() - 1, this->Get_y());
				}
				else if (this->Get_x() + 1 < this->swiat->Get_szerokosc() && this->swiat->GetOrganizm(this->Get_x() + 1, this->Get_y()) == NULL)
				{
					this->Ucieczka(this->Get_x() + 1, this->Get_y());
				}
				else
				{
					zmiennik = this->swiat->GetOrganizm(x, y);
					int x_zmiennika = this->Get_x();
					int y_zmiennika = this->Get_y();
					this->Ucieczka(x, y);
					this->swiat->UstawOrganizm(x_zmiennika, y_zmiennika, zmiennik);
				}
			}
			else if (this->Get_y() - y < 0)
			{
				if (this->Get_y() - 1 >= 0 && this->swiat->GetOrganizm(this->Get_x(), this->Get_y() - 1) == NULL)
				{
					this->Ucieczka(this->Get_x(), this->Get_y() - 1);
				}
				else if (this->Get_x() + 1 < this->swiat->Get_szerokosc() && this->swiat->GetOrganizm(this->Get_x() + 1, this->Get_y()) == NULL )
				{
					this->Ucieczka(this->Get_x() + 1, this->Get_y());
				}
				else if (this->Get_x() - 1 >= 0 && this->swiat->GetOrganizm(this->Get_x() - 1, this->Get_y()) == NULL)
				{
					this->Ucieczka(this->Get_x() - 1, this->Get_y());
				}
				else
				{
					zmiennik = this->swiat->GetOrganizm(x, y);
					int x_zmiennika = this->Get_x();
					int y_zmiennika = this->Get_y();
					this->Ucieczka(x, y);
					this->swiat->UstawOrganizm(x_zmiennika, y_zmiennika, zmiennik);
				}
			}
		}
		else if (this->Get_y() - y == 0)
		{
			if (this->Get_x() - x > 0)
			{
				if (this->Get_x() + 1 < this->swiat->Get_wysokosc() && this->swiat->GetOrganizm(this->Get_x() + 1, this->Get_y()) == NULL )
				{
					this->Ucieczka(this->Get_x() + 1, this->Get_y());
				}
				else if (this->Get_y() - 1 >= 0 && this->swiat->GetOrganizm(this->Get_x(), this->Get_y()- 1) == NULL )
				{
					this->Ucieczka(this->Get_x() , this->Get_y() - 1);
				}
				else if (this->Get_y() + 1 < this->swiat->Get_szerokosc() && this->swiat->GetOrganizm(this->Get_x(), this->Get_y()+1) == NULL)
				{
					this->Ucieczka(this->Get_x(), this->Get_y()+1);
				}
				else
				{
					zmiennik = this->swiat->GetOrganizm(x, y);
					int x_zmiennika = this->Get_x();
					int y_zmiennika = this->Get_y();
					this->Ucieczka(x, y);
					this->swiat->UstawOrganizm(x_zmiennika, y_zmiennika, zmiennik);
				}
			}
			else if (this->Get_x() - x < 0)
			{
				if (this->Get_x() - 1 >= 0 && this->swiat->GetOrganizm(this->Get_x() -1 , this->Get_y()) == NULL)
				{
					this->Ucieczka(this->Get_x() - 1, this->Get_y());
				}
				else if (this->Get_y() + 1 < this->swiat->Get_szerokosc() && this->swiat->GetOrganizm(this->Get_x(), this->Get_y() + 1) == NULL )
				{
					this->Ucieczka(this->Get_x(), this->Get_y() + 1);
				}
				else if (this->Get_y() - 1 >= 0 && this->swiat->GetOrganizm(this->Get_x(), this->Get_y() - 1) == NULL)
				{
					this->Ucieczka(this->Get_x(), this->Get_y() - 1);
				}
				else
				{
					zmiennik = this->swiat->GetOrganizm(x, y);
					int x_zmiennika = this->Get_x();
					int y_zmiennika = this->Get_y();
					this->Ucieczka(x, y);
					this->swiat->UstawOrganizm(x_zmiennika, y_zmiennika, zmiennik);
				}
			}
			
		}
		else
		{
			if (this->Get_x() - 1 >= 0 && this->swiat->GetOrganizm(this->Get_x() + 1, this->Get_y()) == NULL)
			{
				this->Ucieczka(this->Get_x() - 1, this->Get_y());
			}
			else if (this->Get_x() + 1 < this->swiat->Get_wysokosc() && this->swiat->GetOrganizm(this->Get_x() + 1, this->Get_y()) == NULL)
			{
				this->Ucieczka(this->Get_x() + 1, this->Get_y());
			}
			else if (this->Get_y() - 1 >= 0 && this->swiat->GetOrganizm(this->Get_x(), this->Get_y() - 1) == NULL)
			{
				this->Ucieczka(this->Get_x(), this->Get_y() - 1);
			}
			else if (this->Get_y() + 1 < this->swiat->Get_szerokosc() && this->swiat->GetOrganizm(this->Get_x(), this->Get_y() + 1) == NULL)
			{
				this->Ucieczka(this->Get_x(), this->Get_y() + 1);
			}
			else
			{
				this->Zwierze::kolizja(napastnik, x, y);
			}
		}
	}
	else
	{
		this->Zwierze::kolizja(napastnik,x,y);
	}
}
void Antylopa::szukanie_miejsca_zycia(int x_napastnika, int y_napastnika, int* x_nowego_zycia, int* y_nowego_zycia)
{
	if (this->Get_x() - x_napastnika == 0)
	{
		if (this->Get_y() - y_napastnika > 0)
		{
			if (this->swiat->GetOrganizm(this->Get_x(), this->Get_y() - 1) == NULL)
			{
				*x_nowego_zycia = this->Get_x();
				*y_nowego_zycia = this->Get_y() - 1;
			}
		}
		else if (this->Get_y() - y_napastnika < 0)
		{
			if (this->swiat->GetOrganizm(this->Get_x(), this->Get_y() + 1) == NULL)
			{
				*x_nowego_zycia = this->Get_x();
				*y_nowego_zycia = this->Get_y() + 1;
			}
		}
	}
	else if (this->Get_y() - y_napastnika == 0)
	{
		if (this->Get_x() - x_napastnika > 0)
		{
			if (this->swiat->GetOrganizm(this->Get_x() - 1, this->Get_y()) == NULL)
			{
				*x_nowego_zycia = this->Get_x() - 1;
				*y_nowego_zycia = this->Get_y();
			}
		}
		else if (this->Get_y() - y_napastnika < 0)
		{
			if (this->swiat->GetOrganizm(this->Get_x() + 1, this->Get_y()) == NULL)
			{
				*x_nowego_zycia = this->Get_x() + 1;
				*y_nowego_zycia = this->Get_y();
			}
		}
	}
}
void Antylopa::Ucieczka(int x, int y)
{
	
	this->swiat->UsunOrganizm(this->Get_x(), this->Get_y());
	this->Set_x(x);
	this->Set_y(y);
	this->swiat->UstawOrganizm(x, y,this);
	cout << "Ucieczka do " << x << " " << y << endl;

}