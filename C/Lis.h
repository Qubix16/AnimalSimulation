#pragma once
#include "Zwierze.h"
class Lis : public Zwierze
{
public:
	Lis( int x, int y, Swiat* swiat);
	void akcja()override;
};