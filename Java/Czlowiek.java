import java.util.Random;
public class Czlowiek extends Zwierze 
{
    private int czas_mocy;
    

    public Czlowiek(int x, int y, Swiat swiat)
    {
        Set_x(x);
        Set_y(y);
        Set_inicjatywa(4);
        Set_sila(5);
        Set_znak('C');
        Set_wiek(0);
        SetCzasmocy(0);
        this.swiat = swiat;
    }
    public void SetCzasmocy(int czas)
    {
        this.czas_mocy = czas;
    }
    public int GetCzasmocy()
    {
        return this.czas_mocy;
    }
    @Override
    public void akcja()
    {
        int old_x = this.Get_x();
        int old_y = this.Get_y();
        this.swiat.komentarz("Ruch człowieka!");
        int ruch = this.swiat.Get_kierunek();
        int moc = this.swiat.Get_moc();
        boolean poza = false;
        if (moc == 1)
        {
            if (this.GetCzasmocy() == 0)
            {
                SetCzasmocy(11);
                this.swiat.komentarz("Tarcza Alzura aktywowana!");
            }
            else if (this.GetCzasmocy() > 5)
            {
                this.swiat.komentarz("Tarcza Alzura jest aktywna");
            }
            else if (this.GetCzasmocy() <= 5 && this.GetCzasmocy() > 0)
            {
                this.swiat.komentarz("Nie mozna uzyc mocy specjalnej, trwa cooldown" );
            }
           
        }
        switch (ruch)
        {
            case 2:
                if (this.x - 1 >= 0)
                {
                    this.x -= 1;
                }
                else poza = true;
                break;
            case 0:
                if (this.y - 1 >= 0)
                {
                    this.y -= 1;
                }
                else poza = true;
                break;
            case 3:
                if (this.x + 1 < this.swiat.GetSzerokosc())
                {
                    this.x += 1;
                }
                else poza = true;
                break;
            case 1:
                if (this.y + 1 < this.swiat.GetWysokosc())
                {
                    this.y += 1;
                }
                else poza = true;
                break;
            default:
                poza = true;
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
        if (this.GetCzasmocy() > 0)
        {
            this.SetCzasmocy(this.GetCzasmocy() - 1);
        }
    
    }
    @Override
    public void kolizja(Organizm napastnik, int x, int y)
    {
        if (this.GetCzasmocy() > 5)
        {
            boolean poza;
            int losowe_pole;
            do
            {
                poza = false;
                Random ran1 = new Random();
                losowe_pole = ran1.nextInt(4);
                this.swiat.komentarz("Atak " + napastnik.Get_znak() + " odbity na :");
                switch (losowe_pole)
                {
                case 0:
                    if (this.Get_x() - 1 >= 0)
                    {
                        this.swiat.komentarz("lewe pole");
                        napastnik.Set_x(this.Get_x() - 1);
                        napastnik.Set_y(this.Get_y());
                        if (this.swiat.Get_Organizm(this.Get_x() - 1, this.Get_y()) != null && napastnik != this.swiat.Get_Organizm(this.Get_x() - 1, this.Get_y()))
                        {
                            this.swiat.Get_Organizm(this.Get_x() - 1, this.Get_y()).kolizja(napastnik, x, y);
                        }
                        else
                        {
                            this.swiat.UsunOrganizm(x, y);
                            this.swiat.UstawOrganizm(napastnik.Get_x(), napastnik.Get_y(), napastnik);
                        }
                    }
                    else poza = true;
                    break;
                case 1:
                    if (this.Get_y() - 1 >= 0)
                    {
                        this.swiat.komentarz("gorne pole");
                        napastnik.Set_x(this.Get_x());
                        napastnik.Set_y(this.Get_y() - 1);
                        if (this.swiat.Get_Organizm(this.Get_x(), this.Get_y() - 1) != null && napastnik != this.swiat.Get_Organizm(this.Get_x(), this.Get_y() - 1))
                        {
                            this.swiat.Get_Organizm(this.Get_x(), this.Get_y() - 1).kolizja(napastnik, x, y);
                        }
                        else
                        {
                            this.swiat.UsunOrganizm(x, y);
                            this.swiat.UstawOrganizm(napastnik.Get_x(), napastnik.Get_y(), napastnik);
                        }
                    }
                    else poza = true;
                    break;
                case 2:
                    if (this.Get_x() + 1 < this.swiat.GetSzerokosc())
                    {
                        this.swiat.komentarz("prawe pole");
                        napastnik.Set_x(this.Get_x() + 1);
                        napastnik.Set_y(this.Get_y());
                        if (this.swiat.Get_Organizm(this.Get_x() + 1, this.Get_y()) != null && napastnik != this.swiat.Get_Organizm(this.Get_x() + 1, this.Get_y()))
                        {
                            this.swiat.Get_Organizm(this.Get_x() + 1, this.Get_y()).kolizja(napastnik, x, y);
                        }
                        else
                        {
                            this.swiat.UsunOrganizm(x, y);
                            this.swiat.UstawOrganizm(napastnik.Get_x(), napastnik.Get_y(), napastnik);
                        }
                    }
                    else poza = true;
                    break;
                case 3:
                    if (this.Get_y() + 1 < this.swiat.GetWysokosc())
                    {
                        this.swiat.komentarz("dolne pole");
                        napastnik.Set_x(this.Get_x());
                        napastnik.Set_y(this.Get_y() + 1);
                        if (this.swiat.Get_Organizm(this.Get_x(), this.Get_y() + 1) != null && napastnik != this.swiat.Get_Organizm(this.Get_x(), this.Get_y() + 1))
                        {
                            this.swiat.Get_Organizm(this.Get_x(), this.Get_y() + 1).kolizja(napastnik, x, y);
                        }
                        else
                        {
                            this.swiat.UsunOrganizm(x, y);
                            this.swiat.UstawOrganizm(napastnik.Get_x(), napastnik.Get_y(), napastnik);
                        }
                    }
                    else poza = true;
                    break;
                }
            } while (poza == true);
        }
        else
        {
           super.kolizja(napastnik, x, y);
        }
    }
}