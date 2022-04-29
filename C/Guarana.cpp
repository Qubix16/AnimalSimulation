#include "Guarana.h"
Guarana::Guarana(int x, int y, Swiat*swiat)
{
	Set_x(x);
	Set_y(y);
	Set_sila(0);
	Set_inicjatywa(0);
	Set_wiek(0);
	Set_znak(',');
	this->swiat = swiat;
}
void Guarana::kolizja(Organizm* napastnik,int x,int y)
{
	napastnik->Set_sila(napastnik->Get_sila() + 3);
	cout << napastnik->Get_znak() << " posiada sile "<< napastnik->Get_sila()<<" bo zjadl " << this->Get_znak() << endl;
	this->Roslina::kolizja(napastnik,x,y);
}