#pragma once
#include "Zwierze.h"
class Czlowiek : public Zwierze
{
private:
	int czas_mocy;
public:
	Czlowiek(int x, int y, Swiat*swait);
	void akcja() override;
	void SetCzasmocy(int czas_mocy);
	int GetCzasmocy();
	void kolizja(Organizm* napastnik, int x, int y) override;
};
