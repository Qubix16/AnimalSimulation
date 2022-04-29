import java.util.Random;
public class Zolw extends Zwierze 
{
    public Zolw(int x, int y, Swiat swiat)
    {
        Set_x(x);
        Set_y(y);
        Set_inicjatywa(1);
        Set_sila(2);
        Set_znak('Z');
        Set_wiek(0);
        this.swiat = swiat;
    }
    @Override
    public void akcja()
    {
        Random ran1 = new Random();
        int los = ran1.nextInt(4);
        if (los == 3)
        {
            super.akcja();
        }
    }
    @Override
    public void kolizja(Organizm napastnik, int x, int y)
    {
                if (napastnik instanceof Zolw)
                {
                    super.kolizja(napastnik, x, y);
                }
                else if (napastnik.Get_sila() >= 5)
                {
                    super.kolizja(napastnik, x, y);
                }
                else
                {
                    this.swiat.komentarz(this.Get_znak()+ " odparl atak "+napastnik.Get_znak());
                    napastnik.Set_x(x);
                    napastnik.Set_y(y);
                }
    }
}