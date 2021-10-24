# treenipaivakirja
Palvelinohjelmointi-kurssin harjoitustyö

##Sovellus Herokussa
https://sannaworkoutdiary.herokuapp.com/
Herokuun on asennettu vanhempi versio sovelluksesta, joka käyttää H2 tietokantaa

##Sovelluksen viimeisin GitHub versio
Sovelluksen viimeisin palautettu versio käyttää MariaDB tietokantaa
Sovellus sisältää 2 käyttäjää user ja admin. 
Adminilla on kaikki oikeudet eli kaikki CRUD oikeudet kaikkiin toimintoihin (treenisuoritus Entry, treeniohjelma Workout ja treeniliike Movement).
User pääsee tekemään CRUD operaatioita suoritusmerkintöihin (Entry), mutta vain selailemaan treenien sisältöjä (Workout) ja treenien liikkeitä (Movement).
Sisäänkirjautumisessa salasana on sama kuin tunnus.
Sisäänkirjautumisen jälkeen käyttäjä näkee vain omat suoritusmerkinnät (Entry).

Entry - User (ManyToOne, yhdellä käyttäjällä voi olla useita suoritusmerkintöjä)
Entry - Workout (ManyToOne, yhdellä treenillä voi olla useita suoritusmerkintöjä)
Workout - Movement (ManyToMany, yhdellä treenillä voi olla useita liikkeitä ja toisaalta yhdellä liikkeellä useita treenejä)
