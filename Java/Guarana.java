public class Guarana extends Roslina
{
    public Guarana(int x, int y, Swiat swiat)
    {
        Set_x(x);
        Set_y(y);
        Set_sila(0);
        Set_inicjatywa(0);
        Set_wiek(0);
        Set_znak(',');
        this.swiat = swiat;
    }
    @Override
    public void kolizja(Organizm napastnik,int x,int y)
    {
        napastnik.Set_sila(napastnik.Get_sila() + 3);
        this.swiat.komentarz(napastnik.Get_znak() + " posiada sile "+ napastnik.Get_sila()+" bo zjadl " + this.Get_znak());
        super.kolizja(napastnik,x,y);
    }
}