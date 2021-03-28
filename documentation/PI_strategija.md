# Odlučivanje o strategiji trgovanja kriptovalutama

### Odabir kriptovalute

Prije odlučivanja samih metoda (u kontekstu bot-a, algoritama) mora doći odluka kojom kriptovalutom točno se želi trgovati. Postoji mnogo jedinstvenih čimbenika za svaku od kriptovaluta koji pozitivno ili negativno utječu na njezinu cijenu, zbog čega je potrebno odabrati ono za koju će biti osiguran najveći dobitak u danom trenutku.

Odabir kriptovalute kod jednostavnog bota će se riješiti ručnim odabirom kriptovalute kojom će se trgovati, dok će se kod složenijeg bota uvesti odabir kriptovalute pomoću prepoznavanja određenih trendova (sa twittera, te raznih stranica koje donose novosti o kriptovalutama, ili pomoću određenih uzoraka) pomoću kojih će bot sam odlučiti kojom od kriptovaluta želi trgovati.

### Spot vs. leverage trgovanje

* Spot trgovanje
	* manje rizična metoda trgovanja, kupuju se kriptovalute posuđenim novcima
	* za početni ulog od 56.020,10 USD može se kupiti 1 BTC (28.3.2021.)
	* u trenutku pada ili rasta cijene i dalje je u posjedovanju 1 BTC neovisno o cijeni
	* u bilo kojem trenutku može se prodati samo taj 1 BTC koji je u posjedovanju
* Leverage trgovanje
	* više rizična metoda trgovanja, kupuju se kriptovalute posuđenm novcima
	* ako je leverage jednak faktoru deset i početni ulog je 56.020,10 USD -> ulog se uvećava za onoliko koliko je leverage, te se može kupiti 10 BTC
	  * na isti način mogu se i prodavati kriptovalute koje nisu u trenutnom posjedovanju 
	* u trenutku pada (ili porasta cijene, ovisno o tome je li riječ o kupnji ili prodaji kriptovaluta) postoji šansa za gubitak cijelog početnog uloga u slucaju pada/porasta cijene ispod/iznad određene cijene (eng. "liquidation price")

### Indikatori tehničke analize

Za predviđanje cijene, tj. trenda u kojem je cijena, postoje mnogi indikatori kojima se uspješno ili manje uspješno, može predvidjeti rast ili pad cijene kriptovaluta.

Neki od njih su:

* MFI - indeks protoka novca (eng. money flow index)
* MA - pomični prosjek (eng. moving average)
* WMA - težinski pomični prosjek (eng. weighted moving average)
* SMMA - zaglađeni pomični prosjek (eng. smoothed moving average)
* RSI - indeks relativne snage (eng. relative strength index)
* MACD - divergencija konvergencije pomičnog prosjeka (eng. moving average convergence divergence)

##### Candlestick uzorci

Osim pomoću tehničkih indikatora, određene trendove se može predvidjeti pomoću uzoraka u grafovima

* Three line strike - predviđa povećanje cijena sa 83% sigurnošću
* Abandoned baby - nalazi se na dnu padajućeg trenda, predviđa povećanje cijena sa 49.73% sigurnošću
* Hammer - označuje preokret trenda iz padajućeg u rastući
* Shooting star - označuje preokret trenda iz rastućeg u padajući
* Doji - označuje preokret iz trenutnog trenda u suprotni sa manjom sigurnošću nego "Hammer" i "Shooting star" uzorci
* Double top/bottom- također označuje preokret iz trenutnog trenda u suprotni

### Strategije trgovanja temeljene na indikatorima tehničke analize

* MFI
	* zamišljeni interval candlestick-a bi bio 5 minuta
	* potrebno je čekati da MFI dosegne razinu 100 dva puta zaredom kako bi trend bio osiguran
	* ako je MFI dosegnuo razinu 100 dva puta, te je cijena i dalje u rastućem trendu, postavlja se nalog za kupnju u trenutku kada MFI treći puta dosegne razinu 100 (nije nužno potrebno čekati za treću potvrdu, no na taj način je sigurniji profit)


Ostali od gore navedenih indikatora ne koriste previše sami za sebe već ih je potrebno koristiti u paru zbog bolje efikasnosti. 


* Golden/Death cross (WMA/SMMA ili 50MA/200MA) (prijelaz pomičnog prosjeka)
	* metoda koja koristi dva pomična prosjeka 
		* WMA/SMMA
		* 50MA/200MA (50-odnevni/200-dnevni)
	* traži se presjek dva pomična prosjeka iz čega se dobiva
		* konvergencija ili "Golden cross" - 50MA(SMMA) prelazi iznad 200MA(WMA) 
		* divergencija ili "Death cross" - 50MA(SMMA) prelazi ispod 200MA(WMA)
	* konvergencija - postavlja se nalog za kupnju
	* divergencija - postavlja se nalog za prodaju

* RSI + MACD
	* RSI pokazuje je li određena kriptovaluta prekupovana ili preprodana u odnosu na nedavne cijene, dok pomoću MACD-a je vidljiva snaga kretanja cijene na osnovu divergencije dvaju eksponencijalnih pomičnih prosjeka (EMA12/EMA26)
	* ako postoji konvergencija u MACD-u te ako je RSI rastuć i iznad određene razine (točna razina odrediti će se pomoću backtesting-a), postavlja se nalog za kupnju
	* u trenutku divergencije postavlja se nalog za prodaju

##### Strategije isključivo za leverage trgovanje

* HFT - trgovanje visoke frekvencije (eng. high-frequency trading)
	* koristi se povrh svih prijašnje navedenih
	* u vrlo malom vremenskom periodu (nekoliko milisekundi) se izvrši mnogo kupovanja/prodavanja te instantnih prodavanja/kupovanja kriptovaluta
	* velik rizik -> velika nagrada

### Odabir

Od navedenih strategija teško je izabrati najbolju; sve od njih su dobre, te imaju velik potencijal. Zbog toga je cilj projekta redom implementirati MFI, Golden/Death cross te RSI/MACD strategiju kojima će se dodavati ili oduzimati ostale indikatore trenda ovisno o efikasnosti. O dodavanju indikatora koji će se dodati/maknuti u određene strategije, odlučivati će se nakon provedenog backtestinga.  