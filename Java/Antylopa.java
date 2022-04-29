import java.util.Random;
public class Antylopa extends Zwierze
{
    public Antylopa(int x, int y, Swiat swiat)
    {
        Set_x(x);
        Set_y(y);
        Set_inicjatywa(4);
        Set_sila(4);
        Set_znak('A');
        Set_wiek(0);
        this.swiat = swiat;
    }
    @Override
    public void akcja()
    {
        int old_x = this.Get_x();
        int old_y = this.Get_y();
        boolean poza = false;
        Random ran = new Random();
        int kierunek = ran.nextInt(4);
        switch(kierunek)
        {
            case 0:
                if (this.x - 2 >= 0)
                {
                    this.x -= 2;
                }
            else poza = true;
            break;
            case 1:
            if (this.y - 2 >= 0)
            {
                this.y -= 2;
            }
            else poza = true;
            break;
            case 2:
            if (this.x + 2 < this.swiat.GetSzerokosc())
            {
                this.x += 2;
            }
            else poza = true;
            break;
            case 3:
            if (this.y + 2 < this.swiat.GetWysokosc())
            {
                this.y += 2;
            }
            else poza = true;
            break;
        }
        if(this.swiat.Get_Organizm(this.x, this.y) != null && poza == false)
        {
            this.swiat.Get_Organizm(this.x, this.y).kolizja(this, old_x, old_y);
        }
        else if(poza == false)
        {
            Organizm stary = this.swiat.Get_Organizm(old_x, old_y);
            this.swiat.UsunOrganizm(old_x, old_y);
            this.swiat.UstawOrganizm(this.x, this.y, stary);
        }
        this.swiat.komentarz(this.Get_znak()+" x:"+old_x+ " y:"+old_y+" ->  x:"+this.Get_x()+ " y:"+this.Get_y());
    }
    @Override
    public void kolizja(Organizm napastnik, int old_x, int old_y)
    {
        Random ran = new Random();
        int szansa = ran.nextInt(4);
        if(napastnik instanceof Antylopa)
        {
            napastnik.Set_x(old_x);
            napastnik.Set_y(old_y);
            Organizm nowe_zycie = null;
            int nowe_x = -1;
            int nowe_y = -1;
            if (this.Get_wiek() >= 3 && napastnik.Get_wiek() >= 3)
            {
                this.swiat.komentarz(napastnik.Get_znak()+"o ("+old_x +" "+old_y +") i " +this.Get_znak()+"o ("+this.Get_x()+" "+Get_y()+") probują się rozmnożyć");
                int cos = this.szukanie_miejsca_zycia(old_x,old_y);
                if (cos != -1)
                {
                    switch (cos) {
                        case 0:
                            nowe_x = this.Get_x();
                            nowe_y = this.Get_y() - 1;
                               break;
                        case 1:
                            nowe_x = this.Get_x();
                            nowe_y= this.Get_y() + 1;
                            break;
                        case 2:
                            nowe_x = this.Get_x() - 1;
                            nowe_y= this.Get_y();
                            break;
                        case 3:
                            nowe_x = this.Get_x();
                            nowe_y= this.Get_y() + 1;
                            break;
                        default:
                            break;
                    }
                    nowe_zycie = new Antylopa(nowe_x, nowe_y, this.swiat);
                    this.swiat.DodajOrganizm(nowe_x, nowe_y, nowe_zycie);
                    this.swiat.komentarz("Nowy Organizm "+this.Get_znak()+" urodzi sie na polu x:"+nowe_x+" y:"+nowe_y);
                }
                else
                {
                    this.swiat.komentarz("Nie ma miejsca na urodziny "+this.Get_znak());
                }
            }
            else
            {
                this.swiat.komentarz(this.Get_znak()+" jest za młoda by sie rozmnożyć");
            }
        }
        else if(szansa > 1)
        {  
                this.swiat.komentarz("Proba ucieczki Antylopy przed " + napastnik.Get_znak());
				if (this.Get_y() + 1 < this.swiat.GetWysokosc() && this.swiat.Get_Organizm(this.Get_x(), this.Get_y() + 1) == null)
				{
                    int nowe_x = this.Get_x();
                    int nowe_y = this.Get_y();

                    this.swiat.komentarz("Ucieczka do "+nowe_x+" "+(nowe_y+1));

                    this.swiat.UsunOrganizm(nowe_x, nowe_y);
                    this.swiat.UstawOrganizm(nowe_x, nowe_y, napastnik);

                    this.swiat.UsunOrganizm(old_x, old_y);

                    this.Set_y(this.Get_y() + 1);
                    this.swiat.UstawOrganizm(this.Get_x(), this.Get_y(),this);
				}
				else if (this.Get_x() - 1 >= 0 && this.swiat.Get_Organizm(this.Get_x() - 1, this.Get_y()) == null)
				{
					int nowe_x = this.Get_x();
                    int nowe_y = this.Get_y();

                    this.swiat.komentarz("Ucieczka do "+(nowe_x - 1)+" "+nowe_y);

                    this.swiat.UsunOrganizm(nowe_x, nowe_y);
                    this.swiat.UstawOrganizm(nowe_x, nowe_y, napastnik);

                    this.swiat.UsunOrganizm(old_x, old_y);

                    this.Set_x(this.Get_x() - 1);
                    this.swiat.UstawOrganizm(this.Get_x(), this.Get_y(),this);
				}
				else if (this.Get_x() + 1 < this.swiat.GetSzerokosc() && this.swiat.Get_Organizm(this.Get_x() + 1, this.Get_y()) == null)
				{
					int nowe_x = this.Get_x();
                    int nowe_y = this.Get_y();

                    this.swiat.komentarz("Ucieczka do "+(nowe_x +1)+" "+nowe_y);

                    this.swiat.UsunOrganizm(nowe_x, nowe_y);
                    this.swiat.UstawOrganizm(nowe_x, nowe_y, napastnik);

                    this.swiat.UsunOrganizm(old_x, old_y);

                    this.Set_x(this.Get_x() + 1);
                    this.swiat.UstawOrganizm(this.Get_x(), this.Get_y(),this);
                }
                else if (this.Get_y() - 1 >= 0 && this.swiat.Get_Organizm(this.Get_x(), this.Get_y()- 1) == null)
				{
					int nowe_x = this.Get_x();
                    int nowe_y = this.Get_y();

                    this.swiat.komentarz("Ucieczka do "+nowe_x+" "+(nowe_y-1));

                    this.swiat.UsunOrganizm(nowe_x, nowe_y);
                    this.swiat.UstawOrganizm(nowe_x, nowe_y, napastnik);

                    this.swiat.UsunOrganizm(old_x, old_y);

                    this.Set_y(this.Get_y() - 1);
                    this.swiat.UstawOrganizm(this.Get_x(), this.Get_y(),this);
				}
				else
				{
                    Organizm zmiennik;
                    zmiennik = napastnik;
                    int x_antylopy = this.Get_x();
                    int y_antylopy = this.Get_y();
                    
                    this.swiat.komentarz("Ucieczka do "+old_x+" "+old_y);

                    this.Set_x(old_x);
                    this.Set_y(old_y);

                    this.swiat.UsunOrganizm(old_x, old_y);
                    this.swiat.UstawOrganizm(old_x, old_y, this);

                    this.swiat.UsunOrganizm(x_antylopy, y_antylopy);
                    this.swiat.UstawOrganizm(x_antylopy, y_antylopy, zmiennik);

				}
        }
        else
        {
            super.kolizja(napastnik, old_x, old_y);
        }
    }
    @Override
    public int szukanie_miejsca_zycia(int old_x, int old_y)
    {
            if (this.Get_x() - old_x == 0)
            {
                if (this.Get_y() - old_y > 0)
                {
                    if (this.swiat.Get_Organizm(this.Get_x(), this.Get_y() - 1) == null)
                    {
                        return 0;
                    }
                }
                else if (this.Get_y() - old_y < 0)
                {
                    if (this.swiat.Get_Organizm(this.Get_x(), this.Get_y() + 1) == null)
                    {
                        return 1;
                    }
                }
            }
            else if (this.Get_y() - old_y == 0)
            {
                if (this.Get_x() - old_x > 0)
                {
                    if (this.swiat.Get_Organizm(this.Get_x() - 1, this.Get_y()) == null)
                    {
                        return 2;
                    }
                }
                else if (this.Get_y() - old_y < 0)
                {
                    if (this.swiat.Get_Organizm(this.Get_x() + 1, this.Get_y()) == null)
                    {
                        return 3;
                    }
                }
            }
            return -1;
    }

}
