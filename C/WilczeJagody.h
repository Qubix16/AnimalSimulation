#pragma once
#include "Ro�lina.h"
class WilczeJagody :public Roslina
{
public:
	WilczeJagody(int x, int y, Swiat *swiat);
	void kolizja(Organizm* napastnik, int x, int y);
	
};