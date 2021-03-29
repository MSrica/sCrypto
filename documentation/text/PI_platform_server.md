# Odlučivanje o platformi za izradu bota za trgovinu kriptovalutama

U današnje vrijeme postoji mnogo stranica za trgovanje kriptovalutama. Neke od najpoznatijih su: 

* Coinbase
* Binance 
* Kraken
* Crypto.com
* Gemini

Čimbenici o kojima ovisi izbor platforme su:

* sigurnost fonda
* naknada prilikom izvršavanja transakcijom
* dostupnost i lakoća korištenja
* ukupni promet (dostupnost valuta)
* API dokumentacija


### Sigurnost fonda 

Kod svih navedenih platformi sigurnost je na izvrsnom nivou i to čak preko 95%. Sigurnost nema nikakvu ključnu ulogu unutar izbora među navedenim platformama.  

### Dostupnost i prilagođenje korisnicima 

Kada uspoređujemo dostupnost korisnicima, sve navedene platforme su jako pristupačne i intuitivne, tako da ni to nije imalo ključnu ulogu kod odabira. 

### Naknade

* Coinbase 
	- 1.5% standardna kupnja
	- 4% instantna kupnja
	- transfer na karticu €0.15
* Binance 
	* uglavnom ispod 0.1% na sve valute, ali ovisno o valutama (što je veća razina manja je naknada)
* Kraken 
	* funkcionira na mjesečnim (30 dnevnim) naknadama 
	* najveća je od 0.2% za iznose do $50,000
* Crypto.com 
	* što je viša razina, to je manja naknada 
	* najveća je 0.16% 
* Gemini 
	* 0.5% na tržišnu vrijednost kod razmjene
	* 10% na prebacivanje s platforme na račun

### Ukupni promet

Sa stranice [CoinGecko](https://www.coingecko.com/en/exchanges) saznaje se promet koji se odvio na platformi u zadnjih 24 sata. Iz navedene stranice može se zaključiti kako Binance uvjerljivo stoji na prvome mjestu, zatim slijedi Coinbase i na trećem mjestu se nalazi Kraken.  

### API Dokumentacija

API-jev limit zahtjeva po satu:

* Coinbase - 10000
* Binance - 72000
* Kraken - 180000
* Crypto - 360000
* Gemini - 7200

Također važno je napomenuti da su API javne dokumentacije dostupne svima, međutim Binance API je najlakše dostupna za dobiti te implementirati.

## Odabir platforme

Uzimajući sve faktore u obzir, odabrani je rad na platformi Binance. Dostupnost i dokumentacija kod API - REST je najbolje objašnjena. Binance ima najveći promet u danu te ima najširu ponudu kriptovaluta. Osim toga prilikom testiranja ponuđen je "demo" prividni novac s kojim se može testirati rad trading bota bez stvarnih ulaganja, što uvelike olakšava pristup i testiranje daljnjih algoritama. 

# Odabir poslužitelja

Pri odabiru poslužitelja sagledano je više opcija među kojima je bila i izrada vlastitog poslužitelja, Raspberry Pi poslužitelja itd. Tražene su karakteristike pouzdanosti, niske cijene i brige o komponentama. Iz tih razloga izabran je rad na Cloud poslužitelju i to Microsoft Azure radi najveće dostupnosti RAM i ROM memorije. 
