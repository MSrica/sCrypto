# Odlučivanje o strategiji trgovanja kriptovalutama

### Odabir kriptovalute

Prije odlučivanja samih metoda (u kontekstu bot-a, algoritama) moramo odlučiti kojom kriptovalutom točno želimo trgovati. Postoji mnogo jedinstvenih čimbenika za svaku od kriptovaluta koji pozitivno ili negativno utječu na njezinu cijenu, zbog čega je potrebno odabrati ono za koju ćemo imati najveći dobitak u danom trenutku.

Odabir kriptovalute kod jednostavnog bota ćemo riješiti ručnim odabirom kriptovalute kojom će se trgovati, dok ćemo kod složenijeg bota uvesti odabir kriptovalute pomoću prepoznavanje određenih trendova (sa twittera, te raznih stranica koje donose novosti o kriptovalutama) pomoću kojih će bot sam odlučiti kojom od kriptovaluta želi trgovati.

### Spot vs. leverage trgovanje
* Spot trading
	* manje rizična metoda trgovanja, kupujemo kriptovalute novcima koje posjedujemo
	* za početni ulog od 54.603,70 USD možemo kupiti 1 BTC
	* u trenutku pada cijene i dalje posjedujemo 1 BTC koliko god da vrijedio
* Leverage trading
	* više rizična metoda trgovanja, kupujemo kriptovalute posuđenm novcima
	* leverage = x10, početni ulog 54.603,70 USD -> ulog se uvećava za onoliko koliko je leverage, te možemo kupiti 10 BTC
	* u trenutku pada (ili porasta cijene, ovisno o tome je li riječ o kupnji ili prodaji kriptovaluta) postoji šansa da izgubimo cijeli početni ulog u slucaju pada(porasta) cijene ispod(iznad) određene cijene (eng. "liquidation price")

### Indikatori tehničke analiza

Za predviđanje cijene, tj. trenda u kojem je cijena, postoje mnogi indikatori kojima se uspiješno, ili manje uspješno, može predvidjeti rast ili pad cijene kriptovaluta.

Neki od njih su:

* MFI (Indeks protoka novca)
* MA (pomični prosjek)
* WMA (težinski(?) pomični prosjek)
* SMMA (zaglađeni pomični prosjek)
* RSI (indeks relativne snage)
* MACD (Divergencija konvergencije pomičnog prosjeka)

##### Uzorci svijećnjaka(?)

Osim pomoću tehničkih indikatora, određene trendove možemo predvidjeti pomoću uzoraka u grafikonima (grafovima?)

* Three Line Strike - predviđa povećanje cijena sa 83% sigurnošću
* Abandoned baby - nalazi se na dnu padajućeg trenda, predviđa povećanje cijena sa 49.73% sigurnošću
* Hammer - označuje preokret trenda iz padajućeg u rastući
* Shooting star - označuje preokret trenda iz rastućeg u padajući
* Doji - označuje preokret iz trenutnog trenda u suprotni sa manjom sigurnošću nego "Hammer" i "Shooting star" uzorci

### Strategije trgovanja temeljene na indikatorima tehničke analize

* MFI
	* za ovu metodu zamišljeni interval svijećnjaka (?) bi bio 5 minuta
	* potrebno je čekati da MFI dosegne level 100 dva puta za redom kako bismo bili sigurniji u trend
	* ako je MFI dosegnuo level 100 dva puta, te je cijena i dalje u rastućem trendu, postavlja se nalog za kupnju u trenutku kada MFI treći puta dosegne level 100 (nije nužno potrebno čekati za treću potvrdu, no na taj način je sigurniji profit)


Ostali od gore navedenih indikatora nam ne govore previše sami za sebe već ih je potrebno koristiti u paru zbog bolje efikasnosti. 


* Golden cross/death cross (WMA/SMMA ili 50MA/200MA) (prijelaz pomičnog prosjeka)
	* metoda koja koristi dva pomična prosjeka 
		* WMA/SMMA
		* 50MA/200MA (50-odnevni/200-dnevni)
	* traži se presjek dva pomična prosjeka iz čega dobivamo
		* Konvergenciju ili "Golden cross" - 50MA(SMMA) prelazi iznad 200MA(WMA) 
		* Divergenciju ili "Death cross" - 50MA(SMMA) prelazi ispod 200MA(WMA)
	* konvergencija - postavlja se nalog za kupnju
	* divergencija - postavlja se nalog za prodaju

* RSI + MACD
	* RSI nam pokazuje je li određena kriptovaluta prekupovana ili preprodana u odnosu na nedavne cijene, dok pomoću MACD-a vidimo snagu kretanja cijene na osnovu divergencije dvaju eksponencijalnih pomičnih prosjeka (EMA12/EMA26)
	* postoji li konvergencija u MACD-u, te je RSI rastuć i iznad određenog levela (točni level odrediti će se pomoću backtesting-a) postavlja se nalog za kupnju
	* u trenutku divergencije postavlja se nalog za prodaju

##### Strategije isključivo za leverage trgovanje

* High-frequency trading (HFT)
	* ova strategija se koristi povrh svih prijašnje navedenih
	* poanta je u tome da se u vrlo malom vremenskom periodu (nekoliko milisekundi) izvrši mnogo kupovanja(prodavanja), te instantnih prodavanja(kupovanja) kriptovaluta
	* velik rizik -> velika nagrada

### Odabir

Od navedenih strategija teško je izabrati najbolju; sve od njih su dobre, te imaju velik potencijal. Zbog toga je naš cilj implementirati MFI, Golden cross/death cross, te RSI/MACD strategiju kojima ćemo dodavati ili oduzimati ostale indikatore trenda ovisno o efikasnosti. O tome koje dodatne indikatore ćemo dodati u određene strategije, odlučiti ćemo nakon provedenog backtestinga.  
