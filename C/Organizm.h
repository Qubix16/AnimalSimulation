#pragma once
#include <iostream>
using namespace std;
class Swiat;
class Organizm
{
protected:
	int sila;
	int wiek;
	int inicjatywa;
	int x, y;
	char znak;
	Swiat* swiat;
public:
	virtual void akcja()= 0;
	virtual void kolizja(Organizm* napastnik, int x, int y) = 0;
	virtual void rysowanie()= 0;
	virtual int Get_x()const = 0;
	virtual int Get_y()const = 0;
	virtual char Get_znak()const = 0;
	virtual int Get_inicjatywa()const = 0;
	virtual int Get_sila()const = 0;
	virtual int Get_wiek()const = 0;
	virtual void Set_znak(int znak) = 0;
	virtual void Set_x(int x) = 0;
	virtual void Set_y(int y) = 0;
	virtual void Set_inicjatywa(int inicjatywa) = 0;
	virtual void Set_sila(int sila) = 0;
	virtual void Set_wiek(int sila) = 0;
};
