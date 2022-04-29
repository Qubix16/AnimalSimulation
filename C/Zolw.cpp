#include "Zolw.h"
Zolw::Zolw(int x, int y, Swiat*swiat)
{
	Set_x(x);
	Set_y(y);
	Set_inicjatywa(1);
	Set_sila(2);
	Set_znak('Z');
	Set_wiek(0);
	this->swiat = swiat;
}
void Zolw::akcja()
{

	int los = 3;
	los = rand() % 4;
	if (los == 3)
	{
		this->Zwierze::akcja();
	}
}
void Zolw::kolizja(Organizm* napastnik, int x, int y)
{
	if (dynamic_cast<Zolw*>(this->swiat->GetOrganizm(this->Get_x(), this->Get_y())) != nullptr && dynamic_cast<Zolw*>(this->swiat->GetOrganizm(x, y)) != nullptr)
	{
		Zwierze::kolizja(napastnik, x, y);
	}
	else if (napastnik->Get_sila() >= 5)
	{
		Zwierze::kolizja(napastnik, x, y);
	}
	else
	{
		cout <<this->Get_znak()<< " odparl atak "<<napastnik->Get_znak()<<endl;
		napastnik->Set_x(x);
		napastnik->Set_y(y);
	}
}