#include "åwiat.h"
#include "Wilk.h"
#include "Lis.h"
#include "Zolw.h"
#include "Owca.h"
#include "Antylopa.h"
#include "Cz≥owiek.h"
#include "Trawa.h"
#include "WilczeJagody.h"
#include "Guarana.h"
#include "Barszcz.h"
#include "Mlecz.h"
#include <fstream>
#include <string>
#include <conio.h>


Swiat::Swiat(int szerokosc, int wysokosc)
{
	this->szerokosc = szerokosc;
	this->wysokosc = wysokosc;
	
	pola = new Organizm**[wysokosc];
	for (int i = 0; i < wysokosc; i++)
	{
		pola[i] = new Organizm * [szerokosc];
	}
	for (int i = 0; i < wysokosc; i++)
	{
		for (int j = 0; j < szerokosc; j++)
		{
			pola[i][j] = NULL;
		}
	}
}
void Swiat::Rysujåwiat()
{
	for (int j = 0; j < this->szerokosc+ 2; j++)cout << "#";
	cout << endl;
	for (int j = 0; j < this->wysokosc; j++)
	{
		cout << "#";
		for (int i = 0; i < this->szerokosc; i++)
		{
			if (pola[j][i] == NULL)
			{
				cout << " ";
			}
			else
			pola[j][i]->rysowanie();
		}
		cout << "#";
		cout << endl;
	}
	for (int j = 0; j < this->szerokosc+ 2; j++)cout << "#";
	cout << endl;
}
void Swiat::Set_wysokosc(int wysokosc)
{
	this->wysokosc = wysokosc;
}
void Swiat::Set_szerokosc(int szerokosc)
{
	this->szerokosc =szerokosc;
}
int Swiat::Get_wysokosc()const
{
	return this->wysokosc;
}
int Swiat::Get_szerokosc()const
{
	return this->szerokosc;
}
void Swiat::DodajOrganizm(int x, int y, Organizm* nowy)
{
	this->lista.push_back(nowy);
	this->UstawOrganizm(x, y, nowy);
}
void Swiat::UstawOrganizm(int x, int y, Organizm* nowy)
{
	this->pola[y][x] = nowy;
}
void Swiat::UsunOrganizm(int x, int y)
{
	//swiat->UsunzListy(x, y);
	//swiat->Sort();
	this->pola[y][x] = NULL;
}
void Swiat::WykonajTure()
{
	
	//this->infoListy();
	cout << "Wykonywanie akcji" << endl;
	for (int i = 0; i < this->lista.size(); i++)
	{
		if (this->lista[i] != NULL)
		{
			if (this->lista[i]->Get_wiek() >= 1)
			{
				this->lista[i]->akcja();
			}
		}
	}
	this->Sort();
	for (int i = 0; i < this->lista.size(); i++)
	{
		this->lista[i]->Set_wiek(this->lista[i]->Get_wiek()+1);
	}
}
void Swiat::infoListy()
{
	cout << this->lista.size()<<endl;
	for (int i = 0; i < this->lista.size(); i++)
	{
		cout<<this->lista[i]->Get_znak()<<" ";
		cout<<this->lista[i]->Get_x()<<" ";
		cout<<this->lista[i]->Get_y();
		cout << endl;
	}
}
Organizm* Swiat::GetOrganizm(int x, int y)
{
	return this->pola[y][x];
}
void Swiat::UtworzSwiat()
{
	int tab_x[21];
	int tab_y[21];
	Organizm* W;
	bool same ;
	for (int i = 0; i < 21; i++)
	{
		same = false;
		tab_x[i] = rand() % this->szerokosc;
		tab_y[i] = rand() % this->wysokosc;
		for (int j = 0; j < i; j++)
		{
			if (tab_x[j] == tab_x[i] && tab_y[j] == tab_y[i])
			{
				same = true;
				i--;
				break;
			}
		}
		if (same == false)
		{
			//cout << i << endl;
			if (i < 2)
			{
				W = new Wilk(tab_x[i], tab_y[i], this);
				this->DodajOrganizm(tab_x[i], tab_y[i], W);
			}
			else if (i < 4)
			{
				W = new Owca(tab_x[i], tab_y[i], this);
				this->DodajOrganizm(tab_x[i], tab_y[i], W);
			}
			else if (i < 6)
			{
				W = new Lis(tab_x[i], tab_y[i], this);
				this->DodajOrganizm(tab_x[i], tab_y[i], W);
			}
			else if (i < 8)
			{
				W = new Antylopa(tab_x[i], tab_y[i], this);
				this->DodajOrganizm(tab_x[i], tab_y[i], W);
			}
			else if (i < 10)
			{
				W = new Zolw(tab_x[i], tab_y[i], this);
				this->DodajOrganizm(tab_x[i], tab_y[i], W);
			}
			else if (i < 12)
			{
				W = new Trawa(tab_x[i], tab_y[i], this);
				this->DodajOrganizm(tab_x[i], tab_y[i], W);
			}
			else if (i < 14)
			{
				W = new Mlecz(tab_x[i], tab_y[i], this);
				this->DodajOrganizm(tab_x[i], tab_y[i], W);
			}
			else if (i < 16)
			{
				W = new Barszcz(tab_x[i], tab_y[i], this);
				this->DodajOrganizm(tab_x[i], tab_y[i], W);
			}
			else if (i < 18)
			{
				W = new WilczeJagody(tab_x[i], tab_y[i], this);
				this->DodajOrganizm(tab_x[i], tab_y[i], W);
			}
			else if (i < 20)
			{
				W = new Guarana(tab_x[i], tab_y[i], this);
				this->DodajOrganizm(tab_x[i], tab_y[i], W);
			}
			else
			{
				W = new Czlowiek(tab_x[i], tab_y[i], this);
				this->DodajOrganizm(tab_x[i], tab_y[i], W);
			}
		}
	}
}
void Swiat::Sort()
{
	this->WyczyscListe();
	for (int i = 0; i < this->lista.size(); i++)
	{
		for (int j = 0; j < this->lista.size() - 1; j++)
		{
			if (this->lista[j]->Get_inicjatywa() < this->lista[j+1]->Get_inicjatywa())
			{
				swap(this->lista[j], this->lista[j + 1]);
			}
			else if (this->lista[j]->Get_inicjatywa() == this->lista[j + 1]->Get_inicjatywa())
			{
				if (this->lista[j]->Get_wiek() < this->lista[j + 1]->Get_wiek())
				{
					swap(this->lista[j], this->lista[j + 1]);
				}
			}

		}
	}
}
void Swiat::UsunzListy(int x, int y)
{
	
	for (int i = 0; i < this->lista.size(); i++)
	{
		if (this->lista[i] != NULL)
		{
			if (x == this->lista[i]->Get_x() && y == this->lista[i]->Get_y())
			{
				this->lista[i] = NULL;
				break;
			}
		}
	}
}
void Swiat::WyczyscListe()
{
	for (int i = 0; i < this->lista.size(); i++)
	{
		if (this->lista[i] == NULL)
		{
			this->lista.erase(this->lista.begin() + i);
		}
	}
}
bool Swiat::CzyNaLiscie(int x, int y)
{

	for (int i = 0; i < this->lista.size(); i++)
	{
		if (this->lista[i] != NULL)
		{
			if (x == this->lista[i]->Get_x() && y == this->lista[i]->Get_y())
			{
				return true;
			}
		}
	}
	return false;
}
void Swiat::Zapis()
{
	fstream plik;
	plik.open("zapis.txt", ios::out);
	plik  << this->Get_szerokosc() << endl;
	plik  << this->Get_wysokosc() << endl;
	//cout <<"Wysokosc "<<this->Get_wysokosc();
	for (int i = 0; i < this->lista.size(); i++)
	{
		plik << this->lista[i]->Get_znak()<<" ";
		plik << this->lista[i]->Get_sila()<<" ";
		plik << this->lista[i]->Get_wiek()<<" ";
		plik << this->lista[i]->Get_inicjatywa()<<" ";
		plik << this->lista[i]->Get_x()<<" ";
		plik << this->lista[i]->Get_y()<<" ";
		plik << endl;
	}
	plik.close();
}
void Swiat::Wczytywanie()
{
	fstream plik;
	plik.open("zapis.txt", ios::in);
	string linia;
	for (int i = 0; i < this->Get_wysokosc(); i++)
	{
		delete[] this->pola[i];
	}
	delete[] this->pola;
	this->lista.erase(this->lista.begin(), this->lista.end());
	
	int szerokosc;
	int wysokosc;
	int x;
	int y;
	int sila;
	int wiek;
	int inicjatywa;
	char znak;
	getline(plik, linia);
	szerokosc = atoi(linia.c_str());
	getline(plik, linia);
	wysokosc = atoi(linia.c_str());

	this->Set_szerokosc(szerokosc);
	this->Set_wysokosc(wysokosc);
	this->pola = new Organizm **[wysokosc];
	for (int i = 0; i < wysokosc; i++)
	{
		this->pola[i] = new Organizm * [szerokosc];
	}
	for (int i = 0; i < wysokosc; i++)
	{
		for (int j = 0; j < szerokosc; j++)
		{
			this->pola[i][j] = NULL;
		}
	}
	while (true)
	{
		if (!plik.fail())
		{
			plik >> znak >> sila >> wiek >> inicjatywa >> x >> y;
			if (this->CzyNaLiscie(x, y) == false)
			{
				Organizm* nowy = NULL;
				switch (znak)
				{
				case'L':
					nowy = new Lis(x, y, this);
					break;
				case'W':
					nowy = new Wilk(x, y, this);
					break;
				case'O':
					nowy = new Owca(x, y, this);
					break;
				case'A':
					nowy = new Antylopa(x, y, this);
					break;
				case'C':
					nowy = new Czlowiek(x, y, this);
					break;
				case'Z':
					nowy = new Zolw(x, y, this);
					break;
				case'&':
					nowy = new Barszcz(x, y, this);
					break;
				case'.':
					nowy = new Trawa(x, y, this);
					break;
				case',':
					nowy = new Guarana(x, y, this);
					break;
				case'-':
					nowy = new Mlecz(x, y, this);
					break;
				case'*':
					nowy = new WilczeJagody(x, y, this);
					break;
				}

				this->DodajOrganizm(x, y, nowy);
				this->UstawStatyNaLiscie(x, y, inicjatywa, sila, wiek);
				//cout << znak << sila << wiek << inicjatywa << x << y << endl;

			}
		}
		else break;
	}
	//this->infoListy();
	this->Rysujåwiat();
	plik.close();
}
void Swiat::UstawStatyNaLiscie(int x, int y ,int inicjatywa,int sila, int wiek)
{

	for (int i = 0; i < this->lista.size(); i++)
	{
		if (x == this->lista[i]->Get_x() && y == this->lista[i]->Get_y())
		{
			this->lista[i]->Set_inicjatywa(inicjatywa);
			this->lista[i]->Set_sila(sila);
			this->lista[i]->Set_wiek(wiek);
		}
	}
	
}
void Swiat::Gra()
{
	this->UtworzSwiat();
	char x = 'z';
	while (x != 'q')
	{	
		cout << "Jakub Nowak nr.180503" << endl;
		this->Rysujåwiat();
		 do
		 {
			 cout << "Podejmij decyzje" << endl;
			 x = _getch();
			 if (x == 'z')
			 {
				 this->Zapis();
				 cout << "Swiat zapisany!" << endl;
			 }
			 else if (x == 'w')
			 {
				 this->Wczytywanie();
				 cout << "Swiat wczytany!" << endl;
			 }
		 } while (x == 'z' || x == 'w');
		 this->WykonajTure();
		 cout << endl;
	}
}