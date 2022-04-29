#include <iostream>
#include "Organizm.h"
#include <vector>
#pragma once
using namespace std;

class Swiat
{
private:
	vector <Organizm*>lista;
	Organizm ***pola;
	int szerokosc;
	int wysokosc;
public:
	Swiat(int szerokosc, int wyskosc);
	int Get_wysokosc() const;
	int Get_szerokosc() const;
	void Set_szerokosc(int szerokosc);
	void Set_wysokosc(int wysokosc);
	void WykonajTure();
	void Rysujåwiat();
	Organizm* GetOrganizm(int x, int y);
	void UtworzSwiat();
	void DodajOrganizm(int x, int y, Organizm* nowy);
	void UsunOrganizm(int x, int y);
	void Sort();
	void UsunzListy(int x,int y);
	bool CzyNaLiscie(int x,int y);
	void UstawStatyNaLiscie(int x, int y, int inicjatywa, int sila, int wiek);
	void infoListy();
	void Zapis();
	void Wczytywanie();
	void WyczyscListe();
	void UstawOrganizm(int x,int y, Organizm* nowy);
	void Gra();

};