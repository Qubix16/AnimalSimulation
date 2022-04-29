#include "WilczeJagody.h"
#include "Œwiat.h"
WilczeJagody::WilczeJagody(int x, int y, Swiat*swiat)
{
	Set_x(x);
	Set_y(y);
	Set_sila(99);
	Set_wiek(0);
	Set_znak('*');
	Set_inicjatywa(0);
	this->swiat = swiat;
}
void WilczeJagody::kolizja(Organizm* napastnik, int x, int y)
{
	napastnik->Set_x(x);
	napastnik->Set_y(y);
	int nowe_x = this->Get_x();
	int nowe_y = this->Get_y();
	this->swiat->UsunOrganizm(nowe_x, nowe_y);
	this->swiat->UsunzListy(nowe_x, nowe_y);
	napastnik->Set_x(nowe_x);
	napastnik->Set_y(nowe_y);
	this->swiat->UsunOrganizm(nowe_x, nowe_y);
	this->swiat->UsunzListy(nowe_x, nowe_y);
	cout << napastnik->Get_znak() << " umarl bo zjadl Wilcze Jagody" << endl;
}