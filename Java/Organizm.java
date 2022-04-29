
abstract class Organizm 
{
    
    protected int sila;
    protected int inicjatywa;
    protected int wiek;
    protected int x,y;
    protected char znak;
    protected Swiat swiat;
    
    public void akcja(){};
    public void kolizja(Organizm napastnik, int old_x, int old_y){};
    public void rysowanie(){};
    public void Set_znak(char znak){};
	public void Set_x(int x){};
	public void Set_y(int y){};
	public void Set_inicjatywa(int inicjatywa){};
	public void Set_sila(int sila){};
    public void Set_wiek(int wiek){};
    public int Get_x(){ return 0;};
    public int Get_y(){ return 0;};
	public char Get_znak(){ return 0;};
	public int Get_inicjatywa(){ return 0;};
	public int Get_sila(){ return 0;};
	public int Get_wiek(){ return 0;};
}