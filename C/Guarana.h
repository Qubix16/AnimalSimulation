#pragma once
#include "Ro�lina.h"

class Guarana : public Roslina
{
public:
	Guarana(int x, int y,Swiat*swiat);
	void kolizja(Organizm*napsatnik, int x,int y)override;
};
