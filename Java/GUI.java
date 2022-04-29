import javax.swing.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class GUI extends JFrame implements KeyListener ,ActionListener
{
    private JFrame board;
    private JButton zapis;
    private JButton wczytaj;
    private JButton nowa_tura;
    private JLabel[][] pola;
    private Swiat swiat;
    private JTextArea komentarze;
    private JScrollPane scroll;
    private int kierunek;
    private int moc;
    
    public GUI(Swiat swiat)
    {
        this.swiat = swiat;
        this.pola = new JLabel [this.swiat.GetWysokosc()][this.swiat.GetSzerokosc()];
        this.board=new JFrame();
          
        this.zapis= new JButton("Zapisz"); 
        this.wczytaj= new JButton("Wczytaj");
        this.nowa_tura= new JButton("Następna Tura");
        this.komentarze = new JTextArea("Witaj graczu\n");
        this.scroll = new JScrollPane(this.komentarze);

        this.zapis.setBounds(0,0, 100, 40); 
        this.wczytaj.setBounds(100,0, 100, 40);
        this.nowa_tura.setBounds(200,0, 200, 40);
        //this.komentarze.setBounds(500, 100, 250 ,400);
        this.scroll.setBounds(500, 100, 300 ,450);
        

       
        this.komentarze.setEditable(false);
        this.zapis.addActionListener(this);
        this.wczytaj.addActionListener(this);
        this.nowa_tura.addActionListener(this);

        
        this.board.add(this.scroll);
        
        this.board.add(this.zapis); 
        this.board.add(this.wczytaj);
        this.board.add(this.nowa_tura);
        ImageIcon imageIcon = new ImageIcon("null.png");
        for(int i = 0;i<this.swiat.GetWysokosc();i++)
        {
            for(int j = 0;j<this.swiat.GetSzerokosc();j++)
            {
                //System.out.println("JD");
                this.pola[i][j] = new JLabel(imageIcon);
                this.pola[i][j].setLocation(0 +(j * 25), 50 + (i*25));
                this.pola[i][j].setSize(20, 20);
                this.board.add(this.pola[i][j]);
            }
        }
        this.board.setSize(850,600); 
        this.board.setLayout(null);
        this.board.setVisible(true);
        this.board.addKeyListener(this);
        this.board.setFocusable(true);

        this.zapis.setFocusable(false);
        this.wczytaj.setFocusable(false);
        this.nowa_tura.setFocusable(false);
        this.kierunek = -1;
        
    }
    public int Getkierunek()
    {
        return this.kierunek;
    }
    public int Getmoc()
    {
        return this.moc;
    }
    @Override
    public void keyPressed(KeyEvent e) 
    {
        int keyCode = e.getKeyCode();
        switch( keyCode )
        { 
            case KeyEvent.VK_UP:
                System.out.println("GORA");
                this.kierunek = 0;
                break;
            case KeyEvent.VK_DOWN:
            System.out.println("DOL");
                this.kierunek = 1;
                break;
            case KeyEvent.VK_LEFT:
            System.out.println("LWEA");
                this.kierunek = 2;
                break;
            case KeyEvent.VK_RIGHT :
            System.out.println("PRAWA");
                this.kierunek = 3;
                break;
            case KeyEvent.VK_A :
                this.moc = 1;
                break;
        }
        
    }  
    @Override
    public void keyTyped(KeyEvent e)
    {
        //System.out.println("JD");
       
    }
    @Override
    public void keyReleased(KeyEvent e)
    {
        //System.out.println("JD");
      
    }
    public void dodaj_komentarz(String text)
    {
        this.komentarze.append(text +"\n");
        this.komentarze.setCaretPosition(this.komentarze.getDocument().getLength());
    }
    public void actionPerformed(ActionEvent e)
    {
        this.board.setFocusable(true);
        if(e.getActionCommand().equals("Następna Tura"))
        {
           this.swiat.Gra();
           this.kierunek = -1;
           this.moc = 0;
        }
        else if(e.getActionCommand().equals("Zapisz"))
        {
           this.swiat.zapisz();
           this.swiat.komentarz("Świat zapisany");
        }
        else if(e.getActionCommand().equals("Wczytaj"))
        {
           this.swiat.wczytaj();
           this.swiat.komentarz("Świat wczytany");
        }
    }
    public void update(Organizm[][] tab)
    {
        for(int i = 0;i<this.swiat.GetWysokosc();i++)
        {
            for(int j = 0;j<this.swiat.GetSzerokosc();j++)
            {
                if(tab[i][j] == null)
                {
                   ImageIcon imageIcon = new ImageIcon("null.png");
                   this.pola[i][j].setIcon(imageIcon);
                }
                else if(tab[i][j] instanceof Lis)
                {
                    ImageIcon imageIcon = new ImageIcon("Lis.png");
                    this.pola[i][j].setIcon(imageIcon);
                }
                else if(tab[i][j] instanceof Wilk)
                {
                    ImageIcon imageIcon = new ImageIcon("Wilk.png");
                    this.pola[i][j].setIcon(imageIcon);
                }
                else if(tab[i][j] instanceof Zolw)
                {
                    ImageIcon imageIcon = new ImageIcon("Zolw.png");
                    this.pola[i][j].setIcon(imageIcon);
                }
                else if(tab[i][j] instanceof Antylopa)
                {
                    ImageIcon imageIcon = new ImageIcon("Antylopa.png");
                    this.pola[i][j].setIcon(imageIcon);
                }
                else if(tab[i][j] instanceof Owca)
                {
                    ImageIcon imageIcon = new ImageIcon("Owca.png");
                    this.pola[i][j].setIcon(imageIcon);
                }
                else if(tab[i][j] instanceof Trawa)
                {
                    ImageIcon imageIcon = new ImageIcon("Trawa.png");
                    this.pola[i][j].setIcon(imageIcon);
                }
                else if(tab[i][j] instanceof CyberOwca)
                {
                    ImageIcon imageIcon = new ImageIcon("CyberOwca.png");
                    this.pola[i][j].setIcon(imageIcon);
                }
                else if(tab[i][j] instanceof Mlecz)
                {
                    ImageIcon imageIcon = new ImageIcon("Mlecz.png");
                    this.pola[i][j].setIcon(imageIcon);
                }
                else if(tab[i][j] instanceof WilczeJagody)
                {
                    ImageIcon imageIcon = new ImageIcon("WilczeJagody.png");
                    this.pola[i][j].setIcon(imageIcon);
                }
                else if(tab[i][j] instanceof Guarana)
                {
                    ImageIcon imageIcon = new ImageIcon("Guarana.png");
                    this.pola[i][j].setIcon(imageIcon);
                }
                else if(tab[i][j] instanceof Barszcz)
                {
                    ImageIcon imageIcon = new ImageIcon("Barszcz.png");
                    this.pola[i][j].setIcon(imageIcon);
                }
                else if(tab[i][j] instanceof Czlowiek)
                {
                    ImageIcon imageIcon = new ImageIcon("Human.png");
                    this.pola[i][j].setIcon(imageIcon);
                }


            }
        }
    }
}