#pragma once
#include "Zwierze.h"
class Antylopa : public Zwierze
{
public:
	Antylopa(int x, int y, Swiat* swiat);
	void akcja()override;
	void Ucieczka(int x,int y);
	void kolizja(Organizm* napastnik,int x, int y)override;
	void szukanie_miejsca_zycia(int x_napastnika, int y_napastnika, int* x_nowego_zycia, int *y_nowego_zycia);
};
