#pragma once
#include "Roœlina.h"
class Barszcz :public Roslina
{
public:
	Barszcz(int x, int y, Swiat *swiat);
	void akcja()override;
	void kolizja(Organizm* napastnik ,int x,int y)override;
};