# LanguageHelperAPI

Egy nyelvtanulást segítő, szótárfüzet. 

Miben más egy jegyzetfüzettől?

    Idegen szavakat lehet felvenni, jelentésekkel együtt.
    Felvételkor ellenőrzi hogy az adott szó szerepel e már a listában.
        - ha szerepel a listában akkor előhívja azt a szót és lehet szerkeszteni 
        (pl.: új jelentés hozzáírása, vagy a meglévőeket módosítani)
    Szavakat lehet törölni, listázni, keresni.
    Kifejezés/Mondatokat lehet felvenni, törölni, listázni, módosítani.

Az elmentett szavakat egy H2 embedded adatbázisba tárolja.

Több platformos!
    Gépen a megjelenítés JAVAFX-el lesz megvalósítva.
    Böngészőben - HTML/CSS/JS + BackEnd: SpringBoot.
    Telefonon ?

Ez a Model + Service része az egész programnak.
