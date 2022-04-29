#include "Trawa.h"

Trawa::Trawa( int x, int y,Swiat*swiat)
{
	Set_inicjatywa(0);
	Set_x(x);
	Set_y(y);
	Set_sila(0);
	Set_wiek(0);
	Set_znak('.');
	this->swiat = swiat;
}
