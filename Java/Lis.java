import java.util.Random;
public class Lis extends Zwierze
{
    public Lis(int x,int y, Swiat swiat)
    {
        Set_x(x);
        Set_y(y);
        Set_inicjatywa(7);
        Set_sila(3);
        Set_znak('L');
        Set_wiek(0);
        this.swiat = swiat;
    }
    @Override
    public void akcja()
    {
            int new_x;
            int new_y;
            int old_x =this.Get_x();
            int old_y =this.Get_y();

            boolean[] zajate = new boolean[4];
            for(int j = 0; j < 4; j++)zajate[j] = false;
            boolean brak_ruchu = false;
            boolean poza = false;
            Organizm nowy = null;
            do
            {
                Random ran1 = new Random();
                int kierunek = ran1.nextInt(4);
                new_x = this.Get_x();
		        new_y = this.Get_y();
                switch (kierunek)
                {
                    case 0:
                        if (new_x - 1 >= 0)
                        {
                            new_x -= 1;
                        }
                        else poza = true;
                        break;
                    case 1:
                        if (new_y - 1 >= 0)
                        {
                            new_y -= 1;
                        }
                        else poza = true;
                        break;
                    case 2:
                        if (new_x + 1 < this.swiat.GetSzerokosc())
                        {
                            new_x += 1;
                        }
                        else poza = true;
                        break;
                    case 3:
                        if (new_y + 1 < this.swiat.GetWysokosc())
                        {
                            new_y += 1;
                        }
                        else poza = true;
                        break;
                }
                if (poza == true)break;
                nowy = this.swiat.Get_Organizm(new_x, new_y);
                if (nowy != null)
                {
                    if (nowy.Get_sila() > this.Get_sila())
                    {
                        zajate[kierunek] = true;
                    }
                    else
                    {
                        nowy = null;
                    }
                }

                for (int i = 0; i < 4; i++)
                {
                    if (zajate[i] == false)
                    {
                        brak_ruchu = false;
                        break;
                    }
                    else if(i == 3)
                    {
                        brak_ruchu = true;
                    }
                }
        } while (nowy != null && brak_ruchu != true);
        this.Set_x(new_x);
        this.Set_y(new_y);
        if (this.swiat.Get_Organizm(this.Get_x(), this.Get_y()) != null && brak_ruchu == false && poza == false)
        {
            this.swiat.Get_Organizm(this.Get_x(), this.Get_y()).kolizja(this, old_x, old_y);
        }
        else if (brak_ruchu == false && poza == false)
        {
                Organizm stary = this.swiat.Get_Organizm(old_x, old_y);
                this.swiat.UsunOrganizm(old_x, old_y);
                this.swiat.UstawOrganizm(this.Get_x(), this.Get_y(), stary);
        }
        else
        {
            this.Set_x(old_x);
            this.Set_y(old_y);
        }
        this.swiat.komentarz(this.Get_znak()+" x:"+old_x+ " y:"+old_y+" ->  x:"+this.Get_x()+ " y:"+this.Get_y());
    }
}