#include "Roœlina.h"
#include "Œwiat.h"
#include "Mlecz.h"
#include "Trawa.h"
#include "Guarana.h"
#include "Barszcz.h"
#include "WilczeJagody.h"

char Roslina::Get_znak()const
{
	return this->znak;
}
int Roslina::Get_sila()const
{
	return this->sila;
}
int Roslina::Get_x()const
{
	return this->x;
}
int Roslina::Get_y()const
{
	return this->y;
}
void Roslina::Set_sila(int sila)
{
	this->sila = sila;
}
void Roslina::Set_x(int x)
{
	this->x = x;
}
int Roslina::Get_inicjatywa()const
{
	return this->inicjatywa;
}
void Roslina::Set_inicjatywa(int inicjatywa)
{
	this->inicjatywa = inicjatywa;
}
void Roslina::Set_y(int y)
{
	this->y = y;
}
void Roslina::Set_znak(int znak)
{
	this->znak = znak;
}
void Roslina::rysowanie()
{
	cout << this->znak;
}
void Roslina::kolizja(Organizm* napastnik, int x, int y)
{
	cout << napastnik->Get_znak() << " je " << this->Get_znak() << endl;
	napastnik->Set_x(x);
	napastnik->Set_y(y);
	int nowe_x = this->Get_x();
	int nowe_y = this->Get_y();


	this->swiat->UsunOrganizm(nowe_x, nowe_y);
	this->swiat->UsunzListy(nowe_x, nowe_y);
	napastnik->Set_x(nowe_x);
	napastnik->Set_y(nowe_y);
}
void Roslina::akcja()
{
	int szansa = rand() % 5;
	int nowy_x;
	int nowy_y;
	bool nowy = false;
	if (szansa == 4)
	{
		int nowe_pole = rand() % 4;
		switch (nowe_pole)
		{
		case 0:
			if (this->Get_x() - 1 >= 0 && this->swiat->GetOrganizm(this->Get_x() - 1, this->Get_y()) == NULL)
			{
				nowy = true;
				nowy_x = this->Get_x() - 1;
				nowy_y = this->Get_y();
			}
			break;
		case 1:
			if (this->Get_y() - 1 >= 0 && this->swiat->GetOrganizm(this->Get_x(), this->Get_y() - 1) == NULL)
			{
				nowy = true;
				nowy_x = this->Get_x();
				nowy_y = this->Get_y() - 1;
			}
			break;
		case 2:
			if (this->Get_x() + 1 < this->swiat->Get_szerokosc() && this->swiat->GetOrganizm(this->Get_x() + 1, this->Get_y()) == NULL)
			{
				nowy = true;
				nowy_x = this->Get_x() + 1;
				nowy_y = this->Get_y();
			}
			break;
		case 3:
			if (this->Get_y() + 1 < this->swiat->Get_wysokosc() && this->swiat->GetOrganizm(this->Get_x(), this->Get_y() + 1) == NULL)
			{
				nowy = true;
				nowy_x = this->Get_x();
				nowy_y = this->Get_y() + 1;
			}
			break;
		default:
			break;
		}
		if (nowy == true && this->Get_wiek()>2)
		{
			cout << "Udalo sie, bedzie nowa roslina na polu "<<nowy_x<<" "<<nowy_y<< endl;
			Organizm* nowa_roslina = NULL;
			if (dynamic_cast<Mlecz*>(this->swiat->GetOrganizm(this->Get_x(), this->Get_y())) != nullptr)
			{
				nowa_roslina = new Mlecz(nowy_x, nowy_y, this->swiat);
			}
			else if (dynamic_cast<Trawa*>(this->swiat->GetOrganizm(this->Get_x(), this->Get_y())) != nullptr)
			{
				nowa_roslina = new Trawa(nowy_x, nowy_y, this->swiat);
			}
			else if (dynamic_cast<Guarana*>(this->swiat->GetOrganizm(this->Get_x(), this->Get_y())) != nullptr)
			{
				nowa_roslina = new Guarana(nowy_x, nowy_y, this->swiat);
			}
			else if (dynamic_cast<Barszcz*>(this->swiat->GetOrganizm(this->Get_x(), this->Get_y())) != nullptr)
			{
				nowa_roslina = new Barszcz(nowy_x, nowy_y, this->swiat);
			}
			else if (dynamic_cast<WilczeJagody*>(this->swiat->GetOrganizm(this->Get_x(), this->Get_y())) != nullptr)
			{
				nowa_roslina = new WilczeJagody(nowy_x, nowy_y, this->swiat);
			}
			this->swiat->DodajOrganizm(nowy_x, nowy_y, nowa_roslina);
		}
	}

}
void Roslina::Set_wiek(int wiek)
{
	this->wiek = wiek;
}
int Roslina::Get_wiek()const
{
	return this->wiek;
}