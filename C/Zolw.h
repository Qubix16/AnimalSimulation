#pragma once
#include "Zwierze.h"
class Zolw :public Zwierze
{
public:
	Zolw(int x, int y, Swiat* swiat);
	void akcja()override;
	void kolizja(Organizm*napsatnik, int x,int y)override;
};
