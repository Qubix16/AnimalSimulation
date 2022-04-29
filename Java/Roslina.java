import java.util.Random;
public class Roslina extends Organizm
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
    public void kolizja(Organizm napastnik, int old_x, int old_y)
    {
        
        this.swiat.komentarz(napastnik.Get_znak() +" je " + this.Get_znak());
	    napastnik.Set_x(old_x);
        napastnik.Set_y(old_y);
        
	    int nowe_x = this.Get_x();
        int nowe_y = this.Get_y();
        
	    this.swiat.UsunOrganizm(nowe_x, nowe_y);
        this.swiat.UsunzListy(nowe_x, nowe_y);
        
	    napastnik.Set_x(nowe_x);
        napastnik.Set_y(nowe_y);
        this.swiat.UsunOrganizm(old_x, old_y);
        this.swiat.UstawOrganizm(nowe_x, nowe_y, napastnik);
    }
    @Override
    public void akcja()
    {
        if(this.Get_wiek() > 2)
        {

            Random ran = new Random();
            int szansa = ran.nextInt(6);
	        int nowy_x = -1;
	        int nowy_y = -1;
	        boolean nowy = false;
	        if (szansa == 0)
	        {
                Random ran1 = new Random();
                int nowe_pole = ran1.nextInt(4);
		        switch (nowe_pole)
		        {
		            case 0:
			            if (this.Get_x() - 1 >= 0 && this.swiat.Get_Organizm(this.Get_x() - 1, this.Get_y()) == null)
			            {
				        nowy = true;
				        nowy_x = this.Get_x() - 1;
				        nowy_y = this.Get_y();
			            }
			        break;
		            case 1:
			            if (this.Get_y() - 1 >= 0 && this.swiat.Get_Organizm(this.Get_x(), this.Get_y() - 1) == null)
			            {
				            nowy = true;
				            nowy_x = this.Get_x();
				            nowy_y = this.Get_y() - 1;
			            }
			            break;
		            case 2:
		            	if (this.Get_x() + 1 < this.swiat.GetSzerokosc() && this.swiat.Get_Organizm(this.Get_x() + 1, this.Get_y()) == null)
		            	{
		            		nowy = true;
			            	nowy_x = this.Get_x() + 1;
			            	nowy_y = this.Get_y();
		            	}
		            	break;
	            	case 3:
		            	if (this.Get_y() + 1 < this.swiat.GetWysokosc() && this.swiat.Get_Organizm(this.Get_x(), this.Get_y() + 1) == null)
		            	{
		            		nowy = true;
		            		nowy_x = this.Get_x();
		        		    nowy_y = this.Get_y() + 1;
		            	}
			            break;
		            default:
		            	break;
	            	}
		            if (nowy == true && this.Get_wiek()>2)
		            {
		            	this.swiat.komentarz("Udalo sie, bedzie nowa roslina na polu "+nowy_x+" "+nowy_y);
		            	Organizm nowa_roslina = null;
		            	if (this instanceof Mlecz)
			            {
			            	nowa_roslina = new Mlecz(nowy_x, nowy_y, this.swiat);
			            }
			            else if (this instanceof Trawa)
		            	{
			            	nowa_roslina = new Trawa(nowy_x, nowy_y, this.swiat);
		            	}
	            		else if (this instanceof Guarana)
		            	{
		            		nowa_roslina = new Guarana(nowy_x, nowy_y, this.swiat);
	            		}
	            		else if (this instanceof Barszcz)
		            	{
		            		nowa_roslina = new Barszcz(nowy_x, nowy_y, this.swiat);
		            	}
		            	else if (this instanceof WilczeJagody)
		            	{
		              		nowa_roslina = new WilczeJagody(nowy_x, nowy_y, this.swiat);
	            		}
		            	this.swiat.DodajOrganizm(nowy_x, nowy_y, nowa_roslina);
	            	}
	            }
        }
    }
}