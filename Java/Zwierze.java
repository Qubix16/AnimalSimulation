import java.util.Random;

//import java.util.Scanner;
public class Zwierze extends Organizm
{

    @Override
    public void Set_znak(char znak)
    {
        this.znak = znak;
    };
    @Override
    public void Set_x(int x)
    {
        this.x = x;
    };
    @Override
    public void Set_y(int y)
    {
        this.y = y;
    };
    @Override
    public void Set_inicjatywa(int inicjatywa)
    {
        this.inicjatywa = inicjatywa;
    };
    @Override
    public void Set_sila(int sila)
    {
        this.sila = sila;
    };
    @Override
    public void Set_wiek(int wiek)
    {
        this.wiek = wiek;
    };
    @Override
    public int Get_x()
    {
         return this.x;
    };
    @Override
    public int Get_y()
    {
         return this.y;
    };
    @Override
    public char Get_znak()
    {
         return this.znak;
    };
    @Override
    public int Get_inicjatywa()
    {
         return this.inicjatywa;
    };
    @Override
    public int Get_sila()
    {
         return this.sila;
    };
    @Override
    public int Get_wiek()
    {
         return this.wiek;
    };
    @Override
    public void rysowanie()
    {
         System.out.print(this.znak);
    };
    @Override
    public void akcja()
    {
        int old_x = this.Get_x();
        int old_y = this.Get_y();
        boolean poza = false;
        Random ran = new Random();
        int kierunek = ran.nextInt(4);
        //System.out.println(kierunek);
        switch(kierunek)
        {
            case 0:
                if (this.x - 1 >= 0)
                {
                    this.x -= 1;
                }
            else poza = true;
            break;
            case 1:
            if (this.y - 1 >= 0)
            {
                this.y -= 1;
            }
            else poza = true;
            break;
            case 2:
            if (this.x + 1 < this.swiat.GetSzerokosc())
            {
                this.x += 1;
            }
            else poza = true;
            break;
            case 3:
            if (this.y + 1 < this.swiat.GetWysokosc())
            {
                this.y += 1;
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
        
        
    };
    @Override
    public void kolizja(Organizm napastnik, int old_x, int old_y)
    {
        boolean czy_urodzony = false;
        czy_urodzony = this.rozmanzanie(napastnik, old_x,old_y);
        if(czy_urodzony == false)
        {
            if(this.Get_sila() > napastnik.Get_sila())
            {
                this.swiat.komentarz(this.Get_znak() + " zabija  napastnika " + napastnik.Get_znak());
                napastnik.Set_x(old_x);
			    napastnik.Set_y(old_y);
			    this.swiat.UsunOrganizm(old_x, old_y);
			    this.swiat.UsunzListy(old_x, old_y);
            }
            else
            {
                this.swiat.komentarz(napastnik.Get_znak() + " zabija " + this.Get_znak());
                napastnik.Set_x(old_x);
                napastnik.Set_y(old_y);
			    int nowe_x = this.Get_x();
			    int nowe_y = this.Get_y();
			    this.swiat.UsunOrganizm(nowe_x, nowe_y);
			    this.swiat.UsunzListy(nowe_x, nowe_y);
			    napastnik.Set_x(nowe_x);
                napastnik.Set_y(nowe_y);
                this.swiat.UstawOrganizm(nowe_x, nowe_y, napastnik);
                this.swiat.UsunOrganizm(old_x, old_y);
            }
        }
    }
    public boolean rozmanzanie(Organizm napastnik, int old_x, int old_y)
    {
        boolean takie_same = false;
        if(napastnik instanceof Wilk && this instanceof Wilk)takie_same= true;
        else if(napastnik instanceof Owca && this instanceof Owca)takie_same= true;
        else if(napastnik instanceof Lis && this instanceof Lis)takie_same= true;
        else if(napastnik instanceof Zolw && this instanceof Zolw)takie_same= true;

        if(takie_same == true)
        {
            this.swiat.komentarz(napastnik.Get_znak()+"o ("+old_x +" "+old_y +") i " +this.Get_znak()+"o ("+this.Get_x()+" "+Get_y()+") probują się rozmnożyć");
            napastnik.Set_x(old_x);
		    napastnik.Set_y(old_y);
		    Organizm nowe_zycie = null;
		    int x_nowego_zycia = -1;
		    int y_nowego_zycia = -1;
		    if (this.Get_wiek() >= 3 && napastnik.Get_wiek() >= 3)
		    {
			    int cos = this.szukanie_miejsca_zycia(old_x,old_y);
			    if (cos != -1)
			    {
                    switch (cos) {
                        case 0:
                            x_nowego_zycia = this.Get_x() - 1;
                            y_nowego_zycia = this.Get_y();
                            break;
                        case 1:
                            x_nowego_zycia = this.Get_x();
                            y_nowego_zycia = this.Get_y() - 1;
                            break;
                        case 2:
                            x_nowego_zycia = this.Get_x() + 1;
                            y_nowego_zycia = this.Get_y();
                            break;
                        case 3:
                            x_nowego_zycia = this.Get_x();
                            y_nowego_zycia = this.Get_y() + 1;
                            break;
                        case 4:
                            x_nowego_zycia = old_x - 1;
                            y_nowego_zycia = old_y;
                            break;
                        case 5:
                            x_nowego_zycia = old_x;
                            y_nowego_zycia = old_y - 1;
                            break;
                        case 6:
                            x_nowego_zycia = old_x + 1;
                            y_nowego_zycia = old_y;
                            break;
                        case 7:
                            x_nowego_zycia = old_x;
                            y_nowego_zycia = old_y + 1;
                            break;
                        default:
                            break;
                    }
                    if(napastnik instanceof Wilk && this instanceof Wilk)nowe_zycie = new Wilk(x_nowego_zycia, y_nowego_zycia, this.swiat);
                    else if(napastnik instanceof Owca && this instanceof Owca)nowe_zycie = new Owca(x_nowego_zycia, y_nowego_zycia, this.swiat);
                    else if(napastnik instanceof Lis && this instanceof Lis)nowe_zycie = new Lis(x_nowego_zycia, y_nowego_zycia, this.swiat);
                    else if(napastnik instanceof Zolw && this instanceof Zolw)nowe_zycie = new Zolw(x_nowego_zycia, y_nowego_zycia, this.swiat);
				    this.swiat.DodajOrganizm(x_nowego_zycia, y_nowego_zycia, nowe_zycie);
				    this.swiat.komentarz("Nowy Organizm "+this.Get_znak()+" urodzi sie na polu x:"+x_nowego_zycia+" y:"+y_nowego_zycia);
			    }
			    else
                {
                    this.swiat.komentarz("Nie ma miejsca na urodziny "+this.Get_znak());
                }
		    }
		    else
            {
                this.swiat.komentarz("Organizm "+this.Get_znak()+" jest za młody by moc sie rozmnożyć");
            }
		return true;
        }
    return false;
    }
    public int szukanie_miejsca_zycia(int old_x, int old_y)
    {
        
        if (this.Get_x() - 1 >= 0 && this.swiat.Get_Organizm(this.Get_x() - 1, this.Get_y()) == null)
        {
            return 0;
        }
        else if (this.Get_y() - 1 >= 0 && this.swiat.Get_Organizm(this.Get_x(), this.Get_y() - 1) == null)
        {
            return 1;
        }
        else if (this.Get_x() + 1 < this.swiat.GetSzerokosc() && this.swiat.Get_Organizm(this.Get_x() + 1, this.Get_y()) == null)
        {
            return 2;
        }
        else if (this.Get_y() + 1 < this.swiat.GetWysokosc() && this.swiat.Get_Organizm(this.Get_x(), this.Get_y() + 1) == null)
        {
            return 3;
        }
        else if (old_x - 1 >= 0 && this.swiat.Get_Organizm(old_x - 1, old_y) == null)
        {
            return 4;
        }
        else if (old_y - 1 >= 0 && this.swiat.Get_Organizm(old_x, old_y - 1) == null)
        {
            return 5;
        }
        else if (old_x + 1 < this.swiat.GetSzerokosc() && this.swiat.Get_Organizm(old_x + 1, old_y) == null)
        {
            return 6;
        }
        else if (old_y + 1 < this.swiat.GetWysokosc() && this.swiat.Get_Organizm(old_x, old_y + 1) == null)
        {
            return 7;
        }
        else return -1;
    }
}