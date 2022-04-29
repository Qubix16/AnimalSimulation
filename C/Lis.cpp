#include "Lis.h"
#define LEWO 0
#define GORA 1
#define PRAWO 2
#define DOL 3
Lis::Lis( int x, int y, Swiat* swiat)
{
	Set_x(x);
	Set_y(y);
	Set_inicjatywa(7);
	Set_sila(3);
	Set_znak('L');
	Set_wiek(0);
	this->swiat = swiat;
}
void Lis::akcja()
{
	int new_x;
	int new_y;
	int old_x = this->Get_x();
	int old_y = this->Get_y();
	bool zajate[4];
	for(int j = 0; j < 4; j++)zajate[j] = false;
	bool brak_ruchu = false;
	bool poza = false;
	Organizm* nowy = NULL;
	do
	{
		new_x = this->Get_x();
		new_y = this->Get_y();
		int kierunek = rand() % 4;
		switch (kierunek)
		{
		case LEWO:
			if (new_x - 1 >= 0)
			{
				new_x -= 1;
			}
			else poza = true;
			break;
		case GORA:
			if (new_y - 1 >= 0)
			{
				new_y -= 1;
			}
			else poza = true;
			break;
		case PRAWO:
			if (new_x + 1 < this->swiat->Get_szerokosc())
			{
				new_x += 1;
			}
			else poza = true;
			break;
		case DOL:
			if (new_y + 1 < this->swiat->Get_wysokosc())
			{
				new_y += 1;
			}
			else poza = true;
			break;
		}
		if (poza == true)break;
		nowy = this->swiat->GetOrganizm(new_x, new_y);
		if (nowy != NULL)
		{
			if (nowy->Get_sila() > this->Get_sila())
			{
				zajate[kierunek] = true;
			}
			else
			{
				nowy = NULL;
			}
		}

		for (int i = 0; i < 4; i++)
		{
			if (zajate[i] == false)
			{
				brak_ruchu = false;
				break;
			}
			else if(i == 3)
			{
				brak_ruchu = true;
			}
		}
	} while (nowy != NULL && brak_ruchu != true);

	if (this->swiat->GetOrganizm(new_x, new_y) != NULL && brak_ruchu == false && poza == false)
	{
		Organizm* kolidujacy = this->swiat->GetOrganizm(new_x, new_y);
		kolidujacy->kolizja(this, this->x, this->y);
	}
	if (brak_ruchu == false && poza == false )
	{
		//cout << "Lis sie poruszyl" << endl;
		Organizm* stary = this->swiat->GetOrganizm(old_x, old_y);
		this->swiat->UsunOrganizm(old_x, old_y);
		if (this->swiat->CzyNaLiscie(this->Get_x(), this->Get_y()))
		{
			this->swiat->UstawOrganizm(new_x, new_y, stary);
			this->Set_x(new_x);
			this->Set_y(new_y);
		}
	}
	else
	{
		//cout << "brak ruchu"<<endl;
	}
}