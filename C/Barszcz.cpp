#include "Barszcz.h"
#include "Œwiat.h"
#include "Zwierze.h"
Barszcz::Barszcz(int x, int y, Swiat *swiat)
{
	Set_x(x);
	Set_y(y);
	Set_sila(10);
	Set_wiek(0);
	Set_znak('&');
	Set_inicjatywa(0);
	this->swiat = swiat;
}
void Barszcz::akcja()
{
	if (this->Get_x() + 1 < this->swiat->Get_szerokosc() )
	{
		if (dynamic_cast<Zwierze*>(this->swiat->GetOrganizm(this->Get_x() + 1, this->Get_y())) != nullptr)
		{
			this->swiat->UsunOrganizm(this->Get_x() + 1, this->Get_y());
			this->swiat->UsunzListy(this->Get_x() + 1, this->Get_y());
		}
	}
	if (this->Get_x() - 1 >= 0)
	{
		if (dynamic_cast<Zwierze*>(this->swiat->GetOrganizm(this->Get_x() -1, this->Get_y())) != nullptr)
		{
			this->swiat->UsunOrganizm(this->Get_x() - 1, this->Get_y());
			this->swiat->UsunzListy(this->Get_x() - 1, this->Get_y());
		}
	}
	if (this->Get_y() + 1 < this->swiat->Get_wysokosc())
	{
		if (dynamic_cast<Zwierze*>(this->swiat->GetOrganizm(this->Get_x(), this->Get_y() + 1)) != nullptr)
		{
			this->swiat->UsunOrganizm(this->Get_x(), this->Get_y() + 1);
			this->swiat->UsunzListy(this->Get_x(), this->Get_y() + 1);
		}
	}
	if (this->Get_y() - 1 >= 0)
	{
		if (dynamic_cast<Zwierze*>(this->swiat->GetOrganizm(this->Get_x(), this->Get_y()- 1)) != nullptr)
		{
			this->swiat->UsunOrganizm(this->Get_x(), this->Get_y() - 1);
			this->swiat->UsunzListy(this->Get_x(), this->Get_y() - 1);
		}
	}

	this->Roslina::akcja();
}
void Barszcz::kolizja(Organizm* napastnik, int x,int y)
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
	cout << napastnik->Get_znak() << " umarl bo zjadl Barszcz" << endl;;
}