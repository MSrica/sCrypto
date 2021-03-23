# Odlučivanje o platformi za izradu bota za trgovinu kriptovalutama

U današnje vrijeme postoji mnogo stranica za trgovanje kriptovalutama. Neke od njih su: 

* Coinbase
* Binance 
* Kraken
* Crypto.com
* Gemini

Od navedenih platformi odlučili smo se za Binance. Zašto baš Binance? Objasniti ćemo u kratkoj usporedbi između odabranih 5 platformi. 

Čimbenici o kojima je ovisio izbor platforme su:

* sigurnost fonda
* naknada prilikom izvršaanja transakcijom
* dostupnost i lakoća korištenja
* ukupni promet (dostupnost valuta)
* API dokumentacija


### Sigurnost fonda 

Kod svih navedenih platformi sigurnost je na izvrsnom nivou i to čak preko 95%. Sigurnost nema nikakvu ključnu ulogu unutar izbora među navedenim platformama.  


### Naknade

* Coinbase 
	- 1.5% standardna kupnja
	- 4% instantna kupnja
	- transfer na karticu 0.15euro
* Binance 
	* uglavnom ispod 0.1% na sve valute ali ovisno o valutama (što je veći level računa manja naknada)
* Kraken 
	* funkcionira na mjesečnim (30 dnevnim naknadama) najveća je od 0.20% za iznose do 50000USD
* crypto 
	* po levelima potrošnje što je veći level to je manja naknada 
najveća je 0.16% 
* gemini 
	* 0.50% na tržišnu vrijednost kod razmjene
	* 	 10% na prebacivanje s platforme na račun
	
### Dostupnost i prilagođenje korinsicima 

Kada uspoređujemo dostupnost korisnicima, sve navedene platforme su jako pristupačne i intuitivne, tako da nam i to nije imalo ključnu ulogu kod odabira. 

### Ukupni promet

Prema stranici [coingecko](https://www.coingecko.com/en/exchanges) saznajemo promet koji se odvio na platformi u zadnjih 24 sata. Iz navedene stranice možemo zaključiti kako Binance uvjerljivo stoji na prvome mjestu, zatim slijedi Coinbase i na trećem mjestu se nalazi Kraken.  

### API Dokumentacija

* Coinbase API ima limit na 10000 zahtjeva po satu. 
* Binance API ima limit na 72000 zahtjeva u satu 
* Kraken API ima limit na 180000 zahtjeva svakih u stau
* Crypto API ima limit 360000 zahtjeva po satu
* Gemini API ima limit od 7200 zahtjeva po satu

Također važno je napomenuti da su API public dokumentacije dostupne svima, međutim Binance API je najlakše dostupna za dobiti te implemenitirati.

## Odabir

Uzimajući sve faktore u obzir, odlučili smo se za rad na platformi Binance. Dostupnost i dokumentacija kod API - REST je bila najbolje objašnjena. Binance ima najveći promet u danu te ima najširu ponudu kriptovaluta. Osim toga prilikom testiranja ponuđeno nam je "demo" prividni novac s kojim možemo testirati rad našeg trading bota, što nam uvelike olakšava pristup i testiranje daljnih algoritama. 

# Odabir servera za rad

Kao odabir servera za rad promatrali smo bili više opcija, među njima je bilo čak i opcije izrade vlastitog servera, raspberry pie servera itd. Međutim odlučili smo se ipak za korisštenje Cloud servera. Zašto smo odabrali tu opciju, jeftinija je, pouzdanija, ne trebamo brinuti o komponentama itd. Za rad smo se dvoumili između par cloud based servera, ali na kraju smo se odlučili za Microsoft Azure radi najveće dostupnosti RAM i ROM memorije. 