#include "Mlecz.h"
Mlecz::Mlecz( int x, int y, Swiat *swait)
{
	Set_x(x);
	Set_y(y);
	Set_sila(0);
	Set_znak('-');
	Set_inicjatywa(0);
	Set_wiek(0);
	this->swiat = swait;
}
void Mlecz::akcja()
{
	for (int i = 0; i < 3; i++)
	{
		this->Roslina::akcja();
	}
}