public class CyberOwca extends Zwierze
{
    public CyberOwca(int x, int y, Swiat swiat)
    {
        Set_x(x);
        Set_y(y);
        Set_inicjatywa(4);
        Set_sila(11);
        Set_znak('S');
        Set_wiek(0);
        this.swiat = swiat;
    }
    @Override
    public void akcja()
    {
        Organizm barszcz = this.swiat.szukaj_barszcz(this.Get_x(), this.Get_y());
        if(barszcz != null)
        {
            this.swiat.komentarz("Cyber Owca idzie do Barszczu ("+barszcz.Get_x()+" "+barszcz.Get_y()+")");
            int old_x = this.Get_x();
            int old_y = this.Get_y();
            if(this.Get_x() != barszcz.Get_x())
            {
                if(this.Get_x() > barszcz.Get_x())
                {
                    this.x -=1;
                }
                else if(this.Get_x() < barszcz.Get_x())
                {
                    this.x +=1;
                }
            } 
            else if(this.Get_y() != barszcz.Get_y())
            {
                if(this.Get_y() > barszcz.Get_y())
                {
                    this.y -=1;
                }
                else if(this.Get_y() < barszcz.Get_y())
                {
                    this.y +=1;
                }
            } 
            if(this.swiat.Get_Organizm(this.x, this.y) != null)
            {
                this.swiat.Get_Organizm(this.x, this.y).kolizja(this, old_x, old_y);
            }
            else
            {
                Organizm stary = this.swiat.Get_Organizm(old_x, old_y);
                this.swiat.UsunOrganizm(old_x, old_y);
                this.swiat.UstawOrganizm(this.x, this.y, stary);
            }
            this.swiat.komentarz(this.Get_znak()+" x:"+old_x+ " y:"+old_y+" ->  x:"+this.Get_x()+ " y:"+this.Get_y());
            
        }
        else
        {
            super.akcja();
        }
    }
}