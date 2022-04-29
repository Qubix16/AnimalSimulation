#include "Wilk.h"
Wilk::Wilk( int x, int y, Swiat*swiat)
{
	Set_x(x);
	Set_y(y);
	Set_inicjatywa(5);
	Set_sila(9);
	Set_znak('W');
	Set_wiek(0);
	this->swiat = swiat;
}
void Wilk::rysowanie()
{
	cout << this->znak;
}
