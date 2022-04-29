public class Barszcz extends Roslina
{
    public Barszcz(int x, int y, Swiat swiat)
    {
        Set_x(x);
        Set_y(y);
        Set_sila(10);
        Set_wiek(0);
        Set_znak('&');
        Set_inicjatywa(0);
        this.swiat = swiat;
    }
    @Override
    public void akcja()
    {
        if (this.Get_x() + 1 < this.swiat.GetSzerokosc() )
        {
            if (this.swiat.Get_Organizm(this.Get_x() + 1, this.Get_y()) instanceof Zwierze)
            {
                if(this.swiat.Get_Organizm(this.Get_x() + 1, this.Get_y()) instanceof CyberOwca);
                else
                {
                    this.swiat.komentarz("Barszcz zabija "+ this.swiat.Get_Organizm(this.Get_x() + 1, this.Get_y()).Get_znak()+" na sasiednim polu");
                    this.swiat.UsunOrganizm(this.Get_x() + 1, this.Get_y());
                    this.swiat.UsunzListy(this.Get_x() + 1, this.Get_y());
                }
            }
        }
        if (this.Get_x() - 1 >= 0)
        {
            if (this.swiat.Get_Organizm(this.Get_x() -1, this.Get_y()) instanceof Zwierze)
            {
                if(this.swiat.Get_Organizm(this.Get_x() - 1, this.Get_y()) instanceof CyberOwca);
                else
                {
                    this.swiat.komentarz("Barszcz zabija "+ this.swiat.Get_Organizm(this.Get_x() - 1, this.Get_y()).Get_znak()+" na sasiednim polu");
                    this.swiat.UsunOrganizm(this.Get_x() - 1, this.Get_y());
                    this.swiat.UsunzListy(this.Get_x() - 1, this.Get_y());
                }
            }
        }
        if (this.Get_y() + 1 < this.swiat.GetWysokosc())
        {
            if (this.swiat.Get_Organizm(this.Get_x(), this.Get_y() + 1) instanceof Zwierze)
            {
                if(this.swiat.Get_Organizm(this.Get_x(), this.Get_y()+1) instanceof CyberOwca);
                else
                {
                    this.swiat.komentarz("Barszcz zabija "+ this.swiat.Get_Organizm(this.Get_x(), this.Get_y()+1).Get_znak()+" na sasiednim polu");
                    this.swiat.UsunOrganizm(this.Get_x(), this.Get_y() + 1);
                    this.swiat.UsunzListy(this.Get_x(), this.Get_y() + 1);
                }
            }
        }
        if (this.Get_y() - 1 >= 0)
        {
            if (this.swiat.Get_Organizm(this.Get_x(), this.Get_y()- 1)instanceof Zwierze)
            {
                if(this.swiat.Get_Organizm(this.Get_x(), this.Get_y()-1) instanceof CyberOwca);
                else
                {
                    this.swiat.komentarz("Barszcz zabija "+ this.swiat.Get_Organizm(this.Get_x(), this.Get_y() -1).Get_znak()+" na sasiednim polu");
                    this.swiat.UsunOrganizm(this.Get_x(), this.Get_y() - 1);
                    this.swiat.UsunzListy(this.Get_x(), this.Get_y() - 1);
                }
            }
        }

        super.akcja();
    }
    @Override
    public void kolizja(Organizm napastnik, int old_x, int old_y)
    {
        napastnik.Set_x(old_x);
        napastnik.Set_y(old_y);
        int nowe_x = this.Get_x();
        int nowe_y = this.Get_y();
        this.swiat.UsunOrganizm(nowe_x, nowe_y);
        this.swiat.UsunzListy(nowe_x, nowe_y);

        napastnik.Set_x(nowe_x);
        napastnik.Set_y(nowe_y);
        if(napastnik instanceof CyberOwca)
        {
            this.swiat.UsunOrganizm(old_x, old_y);
            this.swiat.UstawOrganizm(nowe_x, nowe_y, napastnik);
        }
        else
        {
            this.swiat.UsunOrganizm(old_x, old_y);
            this.swiat.UsunzListy(nowe_x, nowe_y);
            this.swiat.komentarz(napastnik.Get_znak() + " umarl bo zjadl Barszcz");
        }
    }
}