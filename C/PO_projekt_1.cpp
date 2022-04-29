#include <iostream>
#include <ctime>
using namespace std;
#include "Świat.h"
#include "Wilk.h"
#include "Lis.h"
#include "Zolw.h"
#include "Owca.h"
#include "Antylopa.h"
#include "Człowiek.h"
#include "Trawa.h"
#include "WilczeJagody.h"
#include "Guarana.h"
#include "Barszcz.h"

int main()
{
	srand(time(NULL));
	Swiat swiat(20,20);
	swiat.Gra();


return 0;
}

