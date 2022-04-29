#include "Owca.h"
Owca::Owca( int x, int y,Swiat*swiat)
{
	Set_x(x);
	Set_y(y);
	Set_inicjatywa(4);
	Set_sila(4);
	Set_znak('O');
	Set_wiek(0);
	this->swiat = swiat;
}