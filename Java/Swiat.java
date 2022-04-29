import java.util.Vector;
import java.util.Random;
import java.io.File;
import java.util.Scanner;
import java.io.IOException; 
import java.io.FileWriter;

public class Swiat 
{
    private GUI obraz;
    private Vector <Organizm> lista;
    private Organizm[][] tablica;
    private int szerokosc;
    private int wysokosc;

    public Swiat(int wysokosc, int szerokosc)
    {
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        this.tablica = new Organizm [wysokosc][szerokosc];
        this.lista = new Vector<Organizm>();
        for(int i = 0; i < wysokosc ;i++)
        {
            for(int j = 0; j < szerokosc ;j++)
            {
                this.tablica[i][j] = null;
            }
        }
        this.obraz = new GUI(this);

    }
    public int GetSzerokosc()
    {
        return this.szerokosc;
    }
    public int GetWysokosc()
    {
        return this.wysokosc;
    }
    public void SetSzerokosc(int szerokosc)
    {
        this.szerokosc = szerokosc;
    }
    public void SetWysokosc(int wysokosc)
    {
        this.wysokosc = wysokosc;
    }
    public int Get_kierunek()
    {
        return this.obraz.Getkierunek();
    }
    public int Get_moc()
    {
        return this.obraz.Getmoc();
    }
    public void Gra()
    {
        this.Wykonaj_ture();
        this.obraz.update(this.tablica);
    }
    public void Utworz_Organizmy()
    {
        int[] tab_x = new int[22];
        int[] tab_y = new int [22];
        Random rand = new Random();
        Organizm W = null;
        boolean same;
        for (int i = 0; i < 22; i++)
        {
            same = false;
            tab_x[i] = rand.nextInt(this.szerokosc);
            tab_y[i] = rand.nextInt(this.wysokosc);
            for (int j = 0; j < i; j++)
            {
                if (tab_x[j] == tab_x[i] && tab_y[j] == tab_y[i])
                {
                    same = true;
                    i--;
                    break;
                }
            }
            if (same == false)
            {
                
                if (i < 2)
                {
                    W = new Wilk(tab_x[i], tab_y[i], this);
                    this.DodajOrganizm(tab_x[i], tab_y[i], W);
                }
                else if (i < 4)
                {
                    W = new Owca(tab_x[i], tab_y[i], this);
                    this.DodajOrganizm(tab_x[i], tab_y[i], W);
                }
                else if (i < 6)
                {
                    W = new Lis(tab_x[i], tab_y[i], this);
                    this.DodajOrganizm(tab_x[i], tab_y[i], W);
                }
                else if (i < 8)
                {
                    W = new Antylopa(tab_x[i], tab_y[i], this);
                    this.DodajOrganizm(tab_x[i], tab_y[i], W);
                }
                else if (i < 10)
                {
                    W = new Zolw(tab_x[i], tab_y[i], this);
                    this.DodajOrganizm(tab_x[i], tab_y[i], W);
                }
                else if (i < 12)
                {
                    W = new Trawa(tab_x[i], tab_y[i], this);
                    this.DodajOrganizm(tab_x[i], tab_y[i], W);
                }
                else if (i < 14)
                {
                    W = new Mlecz(tab_x[i], tab_y[i], this);
                    this.DodajOrganizm(tab_x[i], tab_y[i], W);
                }
                else if (i < 16)
                {
                    W = new Barszcz(tab_x[i], tab_y[i], this);
                    this.DodajOrganizm(tab_x[i], tab_y[i], W);
                }
                else if (i < 18)
                {
                    W = new WilczeJagody(tab_x[i], tab_y[i], this);
                    this.DodajOrganizm(tab_x[i], tab_y[i], W);
                }
                else if (i < 20)
                {
                    W = new Guarana(tab_x[i], tab_y[i], this);
                    this.DodajOrganizm(tab_x[i], tab_y[i], W);
                }
                else if(i<21)
                {
                    W = new CyberOwca(tab_x[i], tab_y[i], this);
                    this.DodajOrganizm(tab_x[i], tab_y[i], W);
                }
                else 
                {
                    W = new Czlowiek(tab_x[i], tab_y[i], this);
                    this.DodajOrganizm(tab_x[i], tab_y[i], W);
                }
            }
        }
        this.obraz.update(this.tablica);
    }
    
    public void Wykonaj_ture()
    {
        this.komentarz("Nowa Tura\n");
        for(int i = 0;i< this.lista.size();i++)
        {
            if(this.lista.get(i) != null)
            {
                this.lista.get(i).akcja();
            }
        }
        this.Sort();
        for(int i = 0;i< this.lista.size();i++)
        {
            this.lista.get(i).Set_wiek(this.lista.get(i).Get_wiek()+1);
        }
        //this.info();
    }
    public void Rysuj_Swiat()
    {
        for (int j = 0; j < this.szerokosc+ 2; j++)System.out.print("#");
        System.out.print("\n");
        for (int j = 0; j < this.wysokosc; j++)
        {
            System.out.print("#");
            for (int i = 0; i < this.szerokosc; i++)
            {
                if (tablica[j][i] == null)
                {
                    System.out.print(" ");
                }
                else tablica[j][i].rysowanie();
            }
            System.out.print("#");
            System.out.print("\n");
        }
        for (int j = 0; j < this.szerokosc+ 2; j++)System.out.print("#");
        System.out.print("\n");  
    }

    public void UstawOrganizm(int x, int y, Organizm nowy)
    {
        this.tablica[y][x] = nowy;
    }
    public void DodajOrganizm(int x, int y, Organizm nowy)
    {
	    this.lista.addElement(nowy);
	    this.UstawOrganizm(x, y, nowy);
    }
    public void UsunOrganizm(int x, int y)
    {
	    this.tablica[y][x] = null;
    }
    public Organizm Get_Organizm(int x,int y)
    {
        return this.tablica[y][x];
    }

    public void UsunzListy(int x, int y)
    {
        
        for (int i = 0; i < this.lista.size(); i++)
        {
            if (this.lista.get(i) != null)
            {
                if (x == this.lista.get(i).Get_x() && y == this.lista.get(i).Get_y())
                {
                    this.lista.setElementAt(null, i);
                    break;
                }
            }
        }
    }
    public void WyczyscListe()
    {
        for (int i = 0; i < this.lista.size(); i++)
        {
            if (this.lista.get(i) == null)
            {
                this.lista.removeElementAt(i);
                i-=1;
            }
        }
    }
    public void Sort()
    {
        //this.info();
        this.WyczyscListe();
        //this.info();
        for (int i = 0; i < this.lista.size(); i++)
        {
            for (int j = 0; j < this.lista.size() - 1; j++)
            {
                if (this.lista.get(j).Get_inicjatywa() < this.lista.get(j+1).Get_inicjatywa())
                {
                    Organizm zmiennik = this.lista.get(j);
                    this.lista.set(j,this.lista.get(j+1));
                    this.lista.set(j+ 1,zmiennik);
                    
                }
                else if (this.lista.get(j).Get_inicjatywa() == this.lista.get(j+1).Get_inicjatywa())
                {
                    if (this.lista.get(j).Get_wiek() < this.lista.get(j+1).Get_wiek())
                    {
                        Organizm zmiennik = this.lista.get(j);
                        this.lista.set(j,this.lista.get(j+1));
                        this.lista.set(j+ 1,zmiennik);
                    }
                }
    
            }
        }
    }
    public void info()
    {
        System.out.println(this.lista.size());
	    for (int i = 0; i < this.lista.size(); i++)
	    {
            
            if(this.lista.get(i) == null)
            {
                System.out.println("null");
            }
            else
            {
		    System.out.print(this.lista.get(i).Get_znak() + " ");
		    System.out.print(this.lista.get(i).Get_x() + " ");
		    System.out.print(this.lista.get(i).Get_y() + " ");
            System.out.print("\n");
            }
	    }
    }
    public Organizm szukaj_barszcz(int x, int y)
    {
        
        int droga = 20;
        Organizm barszcz = null;
        for (int i = 0; i < this.lista.size(); i++)
        {
            
            if (this.lista.get(i) instanceof Barszcz)
            {
                
                if(this.lista.get(i).Get_x() >= x && this.lista.get(i).Get_y() >= y)
                {
                    if((this.lista.get(i).Get_x() - x) +(this.lista.get(i).Get_y() - y) < droga)
                    {
                        droga = (this.lista.get(i).Get_x() - x) +(this.lista.get(i).Get_y() - y);
                        barszcz  = this.lista.get(i);
                    }
                }
                else if(this.lista.get(i).Get_x() <= x && this.lista.get(i).Get_y() <= y)
                {
                    if((x - this.lista.get(i).Get_x() ) +(y - this.lista.get(i).Get_y()) < droga)
                    {
                        droga = (x - this.lista.get(i).Get_x() ) +(y - this.lista.get(i).Get_y());
                        barszcz  = this.lista.get(i);
                    }
                }
                else if(this.lista.get(i).Get_x() <= x && this.lista.get(i).Get_y() >= y)
                {
                    if((x - this.lista.get(i).Get_x() ) +(this.lista.get(i).Get_y() - y) < droga)
                    {
                        droga = (x - this.lista.get(i).Get_x() ) +(this.lista.get(i).Get_y() - y);
                        barszcz  = this.lista.get(i);
                    }
                }
                else if(this.lista.get(i).Get_x() >= x && this.lista.get(i).Get_y() <= y)
                {
                    if((this.lista.get(i).Get_x() - x) +(y - this.lista.get(i).Get_y()) < droga)
                    {
                        droga = (this.lista.get(i).Get_x() - x) +(y - this.lista.get(i).Get_y());
                        barszcz  = this.lista.get(i);
                    }
                }
                
            }
        }
        
        return barszcz;
    }
    public void komentarz(String text)
    {
        this.obraz.dodaj_komentarz(text);
    }
    public void zapisz()
    {
        try 
        {
            File zapis = new File("zapis.txt");
            if (zapis.createNewFile()) 
            {
              System.out.println("Plik stworzony: " + zapis.getName());
            } 
            else 
            {
              System.out.println("Plik juz istnieje.");
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error");
            e.printStackTrace();
        }

        try 
        {
            FileWriter myWriter = new FileWriter("zapis.txt");
            myWriter.write(this.GetSzerokosc() +"\n");
            myWriter.write(this.GetWysokosc()+"\n");
            for(int i = 0;i<this.lista.size();i++)
            {
                myWriter.write(this.lista.get(i).Get_znak()+"\n");
                myWriter.write(this.lista.get(i).Get_sila()+"\n");
                myWriter.write(this.lista.get(i).Get_wiek()+"\n");
                myWriter.write(this.lista.get(i).Get_inicjatywa()+"\n");
                myWriter.write(this.lista.get(i).Get_x()+"\n");
                myWriter.write(this.lista.get(i).Get_y()+"\n");
               
            }
            myWriter.close();
            System.out.println("Udany zapis do pliku.");
        }
        catch (IOException e) 
        {
            System.out.println("Error.");
            e.printStackTrace();
        }
    }
    public void wczytaj()
    {
        try 
        {
            File file = new File("zapis.txt");
            Scanner in = new Scanner(file);
            
            this.lista.clear();
            int nowa_szerokosc;
            int nowa_wysokosc;
            int x;
            int y;
            int sila;
            int wiek;
            int inicjatywa;
            char znak;


            String linia = in.nextLine();
            nowa_szerokosc = Integer.parseInt(linia);
            linia = in.nextLine();
            nowa_wysokosc = Integer.parseInt(linia);
            this.SetSzerokosc(nowa_szerokosc);
            this.SetWysokosc(nowa_wysokosc);
            Organizm [][]nowa_tab = new Organizm [nowa_wysokosc][nowa_szerokosc];
            for(int i = 0; i < nowa_wysokosc ;i++)
            {
                for(int j = 0; j < nowa_szerokosc ;j++)
                {
                    nowa_tab[i][j] = null;
                }
            }
            this.tablica = nowa_tab;
            while(in.hasNextLine())
            {
                linia = in.nextLine();
                znak = linia.charAt(0);
                linia = in.nextLine();
                sila = Integer.parseInt(linia);
                linia = in.nextLine();
                wiek = Integer.parseInt(linia);
                linia = in.nextLine();
                inicjatywa = Integer.parseInt(linia);
                linia = in.nextLine();
                x = Integer.parseInt(linia);
                linia = in.nextLine();
                y = Integer.parseInt(linia);
                Organizm nowy = null;
				switch (znak)
				{
				case'L':
					nowy = new Lis(x, y, this);
					break;
				case'W':
					nowy = new Wilk(x, y, this);
					break;
				case'O':
					nowy = new Owca(x, y, this);
					break;
				case'A':
					nowy = new Antylopa(x, y, this);
					break;
				case'C':
					nowy = new Czlowiek(x, y, this);
					break;
				case'Z':
					nowy = new Zolw(x, y, this);
					break;
				case'&':
					nowy = new Barszcz(x, y, this);
					break;
				case'.':
					nowy = new Trawa(x, y, this);
					break;
				case',':
					nowy = new Guarana(x, y, this);
					break;
				case'-':
					nowy = new Mlecz(x, y, this);
					break;
				case'*':
					nowy = new WilczeJagody(x, y, this);
                    break;
                case'S':
					nowy = new CyberOwca(x, y, this);
					break;
				}

                this.DodajOrganizm(x, y, nowy);
                this.UstawStatyNaLiscie(x, y, inicjatywa, sila, wiek);
                

            }
            this.obraz.update(this.tablica);
            in.close();
        } 
        catch (IOException e) 
        {
            System.out.println("Error");
            e.printStackTrace();
        };
    }
    public void UstawStatyNaLiscie(int x, int y ,int inicjatywa,int sila, int wiek)
    {
    
        for (int i = 0; i < this.lista.size(); i++)
        {
            if (x == this.lista.get(i).Get_x() && y == this.lista.get(i).Get_y())
            {
                this.lista.get(i).Set_inicjatywa(inicjatywa);
                this.lista.get(i).Set_sila(sila);
                this.lista.get(i).Set_wiek(wiek);
            }
        }
        
    }
}