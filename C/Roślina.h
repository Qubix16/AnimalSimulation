#pragma once
#include "Organizm.h"

class Roslina : public Organizm
{
public:
	//Roslina(int sila, int x, int y, char znak);
	int Get_wiek() const override;
	int Get_x() const override;
	int Get_y()const override;
	int Get_inicjatywa()const override;
	int Get_sila()const override;
	char Get_znak()const override;
	void Set_wiek(int wiek) override;
	void Set_znak(int znak) override;
	void Set_x(int x) override;
	void Set_y(int y) override;
	void Set_inicjatywa(int inicjatywa) override;
	void Set_sila(int sila) override;
	void akcja()override;
	void kolizja(Organizm* napastnik, int x, int y)override;
	void rysowanie() override;
};
