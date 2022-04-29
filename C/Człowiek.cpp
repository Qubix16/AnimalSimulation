#include "Cz³owiek.h"
#include <conio.h>
#define KEY_UP 72
#define KEY_DOWN 80
#define KEY_LEFT 75
#define KEY_RIGHT 77
#define LEWO 0
#define GORA 1
#define PRAWO 2
#define DOL 3
Czlowiek::Czlowiek(int x, int y, Swiat* swiat)
{
	Set_x(x);
	Set_y(y);
	Set_inicjatywa(4);
	Set_sila(5);
	Set_znak('C');
	Set_wiek(0);
	SetCzasmocy(0);
	this->swiat = swiat;
}
void Czlowiek::SetCzasmocy(int czas_mocy)
{
	this->czas_mocy = czas_mocy;
}
int Czlowiek::GetCzasmocy()
{
	return this->czas_mocy;
}
void Czlowiek::akcja()
{
	int old_x = this->Get_x();
	int old_y = this->Get_y();
	cout << "Ruch czlowieka!" << endl;
	int ruch = _getch();
	bool poza = false;
	if (ruch == 'a')
	{
		if (this->GetCzasmocy() == 0)
		{
			SetCzasmocy(11);
			cout << "Tarcza Alzura aktywowana!" << endl;
		}
		else if (this->GetCzasmocy() > 5)
		{
			cout << "Tarcza Alzura jest aktywna" << endl;
		}
		else if (this->GetCzasmocy() <= 5 && this->GetCzasmocy() > 0)
		{
			cout << "Nie mozna uzyc mocy specjalnej, trwa cooldown" << endl;
		}
		ruch = _getch();
	}
	switch (ruch)
	{
	case 224:
		switch (ruch = _getch())
		{
		case KEY_LEFT:
			if (this->x - 1 >= 0)
			{
				this->x -= 1;
			}
			else poza = true;
			break;
		case KEY_UP:
			if (this->y - 1 >= 0)
			{
				this->y -= 1;
			}
			else poza = true;
			break;
		case KEY_RIGHT:
			if (this->x + 1 < this->swiat->Get_szerokosc())
			{
				this->x += 1;
			}
			else poza = true;
			break;
		case KEY_DOWN:
			if (this->y + 1 < this->swiat->Get_wysokosc())
			{
				this->y += 1;
			}
			else poza = true;
			break;
		default:
			poza = true;
			break;
		}
		break;
	default:
		poza = true;
		break;
	}
	if (this->swiat->GetOrganizm(this->x, this->y) != NULL && poza == false )
	{
		Organizm* kolidujacy = this->swiat->GetOrganizm(this->x, this->y);
		kolidujacy->kolizja(this, old_x, old_y);
	}
	if (poza == false )
	{
		Organizm* stary = this->swiat->GetOrganizm(old_x, old_y);
		this->swiat->UsunOrganizm(old_x, old_y);
		this->swiat->UstawOrganizm(this->Get_x(), this->Get_y(), stary);
	}
	if (this->GetCzasmocy() > 0)
	{
		this->SetCzasmocy(this->GetCzasmocy() - 1);
	}
}
void Czlowiek::kolizja(Organizm* napastnik, int x, int y)
{
	if (this->GetCzasmocy() > 5)
	{
		bool poza;
		int losowe_pole;
		do
		{
			poza = false;
			losowe_pole = rand() % 4;
			cout << "Atak " << napastnik->Get_znak() << " odbity na ";
			switch (losowe_pole)
			{
			case LEWO:
				if (this->Get_x() - 1 >= 0)
				{
					cout << "lewe pole" << endl;
					napastnik->Set_x(this->Get_x() - 1);
					napastnik->Set_y(this->Get_y());
					if (this->swiat->GetOrganizm(this->Get_x() - 1, this->Get_y()) != NULL && napastnik != this->swiat->GetOrganizm(this->Get_x() - 1, this->Get_y()))
					{
						this->swiat->GetOrganizm(this->Get_x() - 1, this->Get_y())->kolizja(napastnik, x, y);
					}
				}
				else poza = true;
				break;
			case GORA:
				if (this->Get_y() - 1 >= 0)
				{
					cout << "gorne pole" << endl;
					napastnik->Set_x(this->Get_x());
					napastnik->Set_y(this->Get_y() - 1);
					if (this->swiat->GetOrganizm(this->Get_x(), this->Get_y() - 1) != NULL && napastnik != this->swiat->GetOrganizm(this->Get_x(), this->Get_y() - 1))
					{
						this->swiat->GetOrganizm(this->Get_x(), this->Get_y() - 1)->kolizja(napastnik, x, y);
					}
				}
				else poza = true;
				break;
			case PRAWO:
				if (this->Get_x() + 1 < this->swiat->Get_szerokosc())
				{
					cout << "prawe pole" << endl;
					napastnik->Set_x(this->Get_x() + 1);
					napastnik->Set_y(this->Get_y());
					if (this->swiat->GetOrganizm(this->Get_x() + 1, this->Get_y()) != NULL && napastnik != this->swiat->GetOrganizm(this->Get_x() + 1, this->Get_y()))
					{
						this->swiat->GetOrganizm(this->Get_x() + 1, this->Get_y())->kolizja(napastnik, x, y);
					}
				}
				else poza = true;
				break;
			case DOL:
				if (this->Get_y() + 1 < this->swiat->Get_wysokosc())
				{
					cout << "dolne pole" << endl;
					napastnik->Set_x(this->Get_x());
					napastnik->Set_y(this->Get_y() + 1);
					if (this->swiat->GetOrganizm(this->Get_x(), this->Get_y() + 1) != NULL && napastnik != this->swiat->GetOrganizm(this->Get_x(), this->Get_y() + 1))
					{
						this->swiat->GetOrganizm(this->Get_x(), this->Get_y() + 1)->kolizja(napastnik, x, y);
					}
				}
				else poza = true;
				break;
			}
		} while (poza == true);
	}
	else
	{
		this->Zwierze::kolizja(napastnik, x, y);
	}
}