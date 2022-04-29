#pragma once
#include "Zwierze.h"
class Wilk : public Zwierze
{
public:
	Wilk( int x, int y, Swiat*swiat);
	void rysowanie()override;
};
