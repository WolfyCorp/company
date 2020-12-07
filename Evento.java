package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ThreadLocalRandom;

import static com.company.Status.getStatus;

/**
 * Classe che descrive un evento. Un evento è una porzione di narrativa che prevede degli input da parte del giocatore. Ad esempio,
 * "trovi un fiore, cosa fai?" con le relative scelte puo' essere un evento.
 */
public class Evento {

    String testo = "";
    String scelta = "";

    int tipo = 0;           //determina l'id dell'evento

    //costruttore
    public Evento(int tipo)
    {
        this.tipo = tipo;
        Loop.next = 0;           //reset di next

        this.testo = cerca();
        stampa(testo);
        scelta();
    }

    /**
     * Metodo che cerca il testo
     */
    private String cerca()
    {
        String str = "";

        switch(tipo)
        {
            case 1:
                str = "\nArrivi in una pianura coltivata.";
                str = str.concat("\n1. Prosegui e ignora");
                str = str.concat("\n2. Cerca cibo fra le coltivazioni");
                str = str.concat("\n3. Cerca una fattoria");
                str = str.concat(stampaOggettiCurativiUsabili());
            break;

            case 2:
                str = "\nArrivi in una foresta.";
                str = str.concat("\n1. Prosegui e ignora");
                str = str.concat("\n2. Riposa facendo un fuoco [+10 hp]");
                str = str.concat("\n3. Vai a caccia");
                str = str.concat("\n4. Cerca uno specchio d'acqua");
                str = str.concat(stampaOggettiCurativiUsabili());

                if (getStatus().oggettoUsi.get(4) > 0)      //se hai l'ascia
                {
                    str = str.concat("\nUsabile: ascia [riposa con un fuoco migliore [+30 hp], trova cibo e acqua]");
                }
            break;

            case 3:
                str = "\nTrovi una fattoria.";
                str = str.concat("\n1. Prosegui e ignora");
                str = str.concat("\n2. Depreda cibo");
                str = str.concat("\n3. Depreda acqua");
                str = str.concat("\n4. Depreda denaro");
                str = str.concat("\n5. Esplora a fondo");
                str = str.concat(stampaOggettiCurativiUsabili());
                str = str.concat(stampaAttrezziUsabili());
            break;

            case 4:
                str = "\nArrivi ad un lago. L'acqua potrebbe essere tossica.";
                str = str.concat("\n1. Passagli attorno");
                str = str.concat("\n2. Prova a pescare");
                str = str.concat("\n3. Raccogli l'acqua");
                str = str.concat("\n4. Attraversa a nuoto [alta possibilita' di ottenere oggetti]");
                str = str.concat(stampaOggettiCurativiUsabili());
                break;

            case 5:
                str = "\nC'e' uno zombie di fronte a te.";
                str = str.concat("\n1. Ignoralo e scappa");
                str = str.concat("\n2. Attaccalo a mani nude [rischioso, possibilita' di ottenere ogetti]");
                str = str.concat("\n3. Distrailo [meno rischioso, piccola possibilita' di ottenere oggetti]");
                str = str.concat(stampaOggettiCurativiUsabili());
                str = str.concat(stampaArmaUsabili());
                break;

            case 6:
                str = "\nIncontri un mercante.";
                str = str.concat("\n1. Prosegui e ignora");
                str = str.concat("\n2. $ 15 ---> Bastone");
                str = str.concat("\n3. $ 30 ---> Coltello");
                str = str.concat("\n4. $ 80 ---> Ascia");
                str = str.concat("\n5. $ 120 ---> Motosega");
                str = str.concat("\n6. $ 140 ---> Lanciafiamme");
                str = str.concat("\n7. $ 5 ---> Erbe curative");
                str = str.concat("\n8. $ 35 ---> Medikit");
                str = str.concat("\n9. $ 75 ---> Kit pronto soccorso");
                str = str.concat(stampaOggettiCurativiUsabili());
                break;

            case 7:
                str = "\nC'e' un gruppo di zombie in vicinanza.";
                str = str.concat("\n1. Ignorali e scappa");
                str = str.concat("\n2. Attaccali a mani nude [rischioso, possibilita' di ottenere ogetti]");
                str = str.concat("\n3. Distraili [meno rischioso, piccola possibilita' di ottenere oggetti]");
                str = str.concat(stampaOggettiCurativiUsabili());
                str = str.concat(stampaArmaUsabili());
                break;

            case 8:
                str = "\nC'e' un precipizio di fronte a te.";
                str = str.concat("\n1. Calati con calma");
                str = str.concat("\n2. Torna indietro [rischioso, gli zombie potrebbero averti seguito]");
                str = str.concat(stampaOggettiCurativiUsabili());
                str = str.concat(stampaAttrezziUsabili());
                break;

            case 9:
                str = "\nC'e' un villaggio nelle vicinanze.";
                str = str.concat("\n1. Prosegui e ignoralo");
                str = str.concat("\n2. Depreda il cibo");
                str = str.concat("\n3. Depreda l'acqua");
                str = str.concat("\n4. Trova le cassaforti e depredale");
                str = str.concat("\n5. Cerca un mercante");
                str = str.concat("\n6. Vai all'armeria [rischioso, alta possibilita' di trovare un oggetto]");
                str = str.concat(stampaOggettiCurativiUsabili());
                str = str.concat(stampaAttrezziUsabili());
                break;

            case 10:
                str = "\nC'e' un covo di zombie. Custodiscono qualcosa.";
                str = str.concat("\n1. Prosegui e ignoralo");
                str = str.concat("\n2. Corri e ruba quel che riesci [poco rischioso, bassa possibilita' di trovare un oggetto]");
                str = str.concat("\n3. Fai fuori gli zombie [estremamente rischioso, alta possibilita' di trovare piu' oggetti]");
                str = str.concat(stampaOggettiCurativiUsabili());
                str = str.concat(stampaArmaUsabili());
                break;
        }

        return str;
    }

    /**
     * Metodo che stampa il testo di base
     */
    private void stampa(String testo)
    {
        System.out.println(testo);
    }

    /**
     * Metodo che gestisce l'input dell'utente
     */
    private String input()
    {
        //INPUT---------------------------------------------------*
        String line = "";

        //costruzione oggetto per l'input
        BufferedReader input = new BufferedReader(
                new InputStreamReader( System.in )
        );
        try
        {
            //acquisisce input di "scelta"
            line = input.readLine();
        }
        catch (Exception e)
        {
            System.out.println("Errore in input");
        }

        return line;

        //FINE INPUT----------------------------------------------*
    }

    /**
     * Metodo che gestisce il ciclo di scelta
     */
    private void scelta()
    {
        boolean wrong = true;

        do {
            String line = input();

            wrong = parser(line);
        }
        while(wrong);
    }

    /**
     * Metodo che identifica se i comandi sono validi oppure no
     * @return valore booleano che indica se l'input e' sbagliato oppure no
     */
    private boolean parser(String line)
    {
        boolean wrong = false;

        switch(tipo) {
            case 1:     //pianura coltivabile
                if (line.contains("1")) {               //scelta 1 - niente

                    //niente

                } else if (line.contains("2")) {        //scelta 2 - cerca cibo

                    int randomNum = ThreadLocalRandom.current().nextInt(0,  10 + 1);
                    Status.setHp(Status.getHp() - randomNum);                           //fino a -10 hp

                    randomNum = ThreadLocalRandom.current().nextInt(1, 25 + 1);
                    Status.setCibo(Status.getCibo() + randomNum);                       //fino a +25 cibo

                } else if (line.contains("3")) {        //scelta 3 - cerca fattoria

                    Loop.next = 3;      //prossimo evento sarà la fattoria

                }
                else {                                //errore oppure oggetti curativi
                    if (inputOggettiCurativi(line) == false) {
                        System.out.println("Non puoi fare quello.");
                        wrong = true;
                    }
                }
            break;

            case 2:     //foresta
                if (line.contains("1")) {               //scelta 1 - niente

                    //niente

                } else if (line.contains("2")) {        //scelta 2 - riposa e fai un fuoco [+10 hp]

                    Status.setHp(Status.getHp() + 10);

                } else if (line.contains("3")) {        //scelta 3 - vai a caccia

                    int randomNum = ThreadLocalRandom.current().nextInt(0,  20 + 1);
                    Status.setHp(Status.getHp() - Math.abs(randomNum - Status.getDifesa()));       //fino a -20 hp

                    randomNum = ThreadLocalRandom.current().nextInt(0,  30 + 1);
                    Status.setCibo(Status.getCibo() + randomNum);                 //fino a +30 cibo

                } else if (line.contains("4")) {        //scelta 4 - cerca uno specchio d'acqua

                    Loop.next = 4;      //prossimo evento sarà il lago
                }
                else if ((line.contains("ascia")) && (getStatus().oggettoUsi.get(4) > 0)) {     //se hai l'ascia
                    getStatus().oggettoUsi.set(4, getStatus().oggettoUsi.get(4) - 1);

                    Status.setHp(Status.getHp() + 30);

                    int randomNum = ThreadLocalRandom.current().nextInt(0,  15 + 1);    //fino a +15 cibo
                    Status.setCibo(Status.getCibo() + randomNum);

                    randomNum = ThreadLocalRandom.current().nextInt(0,  15 + 1);    //fino a +15 cibo
                    Status.setAcqua(Status.getAcqua() + randomNum);
                }
                else {                                //errore oppure oggetti curativi oppure armi
                    if (inputArmi(line) == true)
                    {
                        int randomNum = ThreadLocalRandom.current().nextInt(0,  20 + 1);    //fino a -40 vita
                        Status.setHp(Status.getHp() - Math.abs(randomNum - Status.getDifesa()));

                        randomNum = ThreadLocalRandom.current().nextInt(0,  35 + 1);    //fino a +35 cibo
                        Status.setCibo(Status.getCibo() + randomNum);

                        randomNum = ThreadLocalRandom.current().nextInt(0,  5 + 1);    //fino a +5 acqua
                        Status.setCibo(Status.getCibo() + randomNum);

                        possibilitaTrovaOggetto(5);
                    }
                    else if (inputOggettiCurativi(line) == false) {
                        System.out.println("Non puoi fare quello.");
                        wrong = true;
                    }
                }
                break;

            case 3:     //fattoria
                if (line.contains("1")) {               //scelta 1 - niente

                    //niente

                } else if (line.contains("2")) {        //scelta 2 - depreda cibo

                    int randomNum = ThreadLocalRandom.current().nextInt(10,  30 + 1);  //fino a +30 cibo
                    Status.setCibo(Status.getCibo() + randomNum);

                } else if (line.contains("3")) {        //scelta 3 - depreda acqua

                    int randomNum = ThreadLocalRandom.current().nextInt(10,  30 + 1);    //fino a +30 acqua
                    Status.setAcqua(Status.getAcqua() + randomNum);

                } else if (line.contains("4")) {        //scelta 3 - depreda soldi

                    int randomNum = ThreadLocalRandom.current().nextInt(0,  50 + 1);    //fino a +50 soldi
                    Status.setSoldi(Status.getSoldi() + randomNum);

                 } else if (line.contains("5")) {        //scelta 3 - esplora a fondo!

                    int randomNum = ThreadLocalRandom.current().nextInt(10,  30 + 1);  //fino a +30 cibo
                    Status.setCibo(Status.getCibo() + randomNum);

                    randomNum = ThreadLocalRandom.current().nextInt(10,  30 + 1);    //fino a +30 acqua
                    Status.setAcqua(Status.getAcqua() + randomNum);

                    randomNum = ThreadLocalRandom.current().nextInt(0,  50 + 1);    //fino a +50 soldi
                    Status.setSoldi(Status.getSoldi() + randomNum);

                    randomNum = ThreadLocalRandom.current().nextInt(0,  40 + 1);    //fino a -40 vita
                    Status.setHp(Status.getHp() - Math.abs(randomNum - Status.getDifesa()));

                    possibilitaTrovaOggetto(60);

                }
                else {                                //errore oppure oggetti curativi oppure armi
                    if (inputAttrezzi(line) == true)
                    {
                        int randomNum = ThreadLocalRandom.current().nextInt(10,  30 + 1);  //fino a +30 cibo [incl. difesa]
                        Status.setCibo(Status.getCibo() + randomNum + Status.getDifesa());

                        randomNum = ThreadLocalRandom.current().nextInt(10,  30 + 1);    //fino a +30 acqua [incl. difesa]
                        Status.setAcqua(Status.getAcqua() + randomNum + Status.getDifesa());

                        randomNum = ThreadLocalRandom.current().nextInt(0,  50 + 1);    //fino a +50 soldi [incl. difesa]
                        Status.setSoldi(Status.getSoldi() + randomNum + Status.getDifesa());

                        randomNum = ThreadLocalRandom.current().nextInt(0,  25 + 1);    //fino a -25 vita [incl. difesa]
                        Status.setHp(Status.getHp() - Math.abs(randomNum - Status.getDifesa()));

                        possibilitaTrovaOggetto(75);
                    }
                    else if (inputOggettiCurativi(line) == false) {
                        System.out.println("Non puoi fare quello.");
                        wrong = true;
                    }
                }
                break;

            case 4:     //lago
                if (line.contains("1")) {               //scelta 1 - niente

                    //niente

                } else if (line.contains("2")) {        //scelta 2 - prova a pescare

                    int randomNum = ThreadLocalRandom.current().nextInt(10,  30 + 1);  //fino a +30 cibo
                    Status.setCibo(Status.getCibo() + randomNum);

                    randomNum = ThreadLocalRandom.current().nextInt(0,  5 + 1);  //fino a +5 acqua
                    Status.setAcqua(Status.getAcqua() + randomNum);

                    randomNum = ThreadLocalRandom.current().nextInt(0,  15 + 1);  //fino a -15 hp
                    Status.setHp(Status.getHp() + randomNum);

                } else if (line.contains("3")) {        //scelta 3 - raccogli l'acqua

                    int randomNum = ThreadLocalRandom.current().nextInt(20,  50 + 1);  //fino a +50 acqua
                    Status.setAcqua(Status.getAcqua() + randomNum);

                    randomNum = ThreadLocalRandom.current().nextInt(5,  20 + 1);  //fino a -20 hp
                    Status.setHp(Status.getHp() + randomNum);
                }
                else if (line.contains("4")) {        //scelta 3 - attraversa a nuoto

                    int randomNum = ThreadLocalRandom.current().nextInt(0,  50 + 1);  //fino a -50 hp
                    Status.setHp(Status.getHp() + randomNum);

                    possibilitaTrovaOggetto(80);

                }
                else {                                //errore oppure oggetti curativi
                    if (inputOggettiCurativi(line) == false) {
                        System.out.println("Non puoi fare quello.");
                        wrong = true;
                    }
                }
                break;

            case 5:     //zombie
                if (line.contains("1")) {               //scelta 1 - niente

                    int randomNum = ThreadLocalRandom.current().nextInt(0,  5 + 1);    //fino a -5 vita
                    Status.setHp(Status.getHp() - Math.abs(randomNum - Status.getDifesa()));

                } else if (line.contains("2")) {        //scelta 2 - attacca a mani nude

                    int randomNum = ThreadLocalRandom.current().nextInt(0,  50 + 1);    //fino a -50 vita
                    Status.setHp(Status.getHp() - Math.abs(randomNum - Status.getDifesa()));

                    randomNum = ThreadLocalRandom.current().nextInt(0,  20 + 1);    //fino a +20 cibo
                    Status.setCibo(Status.getCibo() + randomNum);

                    randomNum = ThreadLocalRandom.current().nextInt(0,  20 + 1);    //fino a +20 acqua
                    Status.setCibo(Status.getCibo() + randomNum);

                    randomNum = ThreadLocalRandom.current().nextInt(0,  30 + 1);    //fino a +30 soldi
                    Status.setSoldi(Status.getSoldi() + randomNum);

                    possibilitaTrovaOggetto(30);

                } else if (line.contains("3")) {        //scelta 3 - distrai

                    int randomNum = ThreadLocalRandom.current().nextInt(0,  15 + 1);    //fino a -15 vita
                    Status.setHp(Status.getHp() - Math.abs(randomNum - Status.getDifesa()));

                    randomNum = ThreadLocalRandom.current().nextInt(0,  5 + 1);    //fino a +5 cibo
                    Status.setCibo(Status.getCibo() + randomNum);

                    randomNum = ThreadLocalRandom.current().nextInt(0,  5 + 1);    //fino a +5 acqua
                    Status.setCibo(Status.getCibo() + randomNum);

                    randomNum = ThreadLocalRandom.current().nextInt(0,  10 + 1);    //fino a +10 soldi
                    Status.setSoldi(Status.getSoldi() + randomNum);

                    possibilitaTrovaOggetto(15);

                }
                else {                                //errore oppure oggetti curativi oppure armi
                    if (inputArmi(line) == true)
                    {
                        int randomNum = ThreadLocalRandom.current().nextInt(0,  40 + 1);    //fino a -40 vita
                        Status.setHp(Status.getHp() - Math.abs(randomNum - Status.getDifesa()));

                        randomNum = ThreadLocalRandom.current().nextInt(0,  20 + 1);    //fino a +20 cibo
                        Status.setCibo(Status.getCibo() + randomNum);

                        randomNum = ThreadLocalRandom.current().nextInt(0,  20 + 1);    //fino a +20 acqua
                        Status.setCibo(Status.getCibo() + randomNum);

                        randomNum = ThreadLocalRandom.current().nextInt(0,  30 + 1);    //fino a +30 soldi
                        Status.setSoldi(Status.getSoldi() + randomNum);

                        possibilitaTrovaOggetto(40);
                    }
                    else if (inputOggettiCurativi(line) == false) {
                        System.out.println("Non puoi fare quello.");
                        wrong = true;
                    }
                }
                break;

            case 6:     //mercante
                if (line.contains("1")) {               //scelta 1 - niente

                    //niente

                } else if ((line.contains("2")) && (Status.getSoldi() >= 15)) {        //scelta 2 - compra bastone

                    getStatus().oggettoUsi.set(7, getStatus().oggettoUsiMax.get(7));
                    Status.setSoldi(Status.getSoldi() - 15);
                    Loop.oggettiPosseduti += 1;
                    System.out.println("OGGETTO ACQUISTATO: bastone");

                } else if ((line.contains("3")) && (Status.getSoldi() >= 30)) {        //scelta 3 - compra coltello

                    getStatus().oggettoUsi.set(1, getStatus().oggettoUsiMax.get(1));
                    Status.setSoldi(Status.getSoldi() - 30);
                    Loop.oggettiPosseduti += 1;
                    System.out.println("OGGETTO ACQUISTATO: coltello");

                }
                else if ((line.contains("4")) && (Status.getSoldi() >= 80)) {        //scelta 4 - compra ascia

                    getStatus().oggettoUsi.set(4, getStatus().oggettoUsiMax.get(4));
                    Status.setSoldi(Status.getSoldi() - 80);
                    Loop.oggettiPosseduti += 1;
                    System.out.println("OGGETTO ACQUISTATO: ascia");

                }
                else if ((line.contains("5")) && (Status.getSoldi() >= 120)) {        //scelta 5 - compra motosega

                    getStatus().oggettoUsi.set(13, getStatus().oggettoUsiMax.get(13));
                    Status.setSoldi(Status.getSoldi() - 120);
                    Loop.oggettiPosseduti += 1;
                    System.out.println("OGGETTO ACQUISTATO: motosega");

                }
                else if ((line.contains("6")) && (Status.getSoldi() >= 140)) {        //scelta 6 - compra lanciafiamme

                    getStatus().oggettoUsi.set(14, getStatus().oggettoUsiMax.get(14));
                    Status.setSoldi(Status.getSoldi() - 140);
                    Loop.oggettiPosseduti += 1;
                    System.out.println("OGGETTO ACQUISTATO: lanciafiamme");

                }
                else if ((line.contains("7")) && (Status.getSoldi() >= 5)) {        //scelta 7 - compra erbe curative

                    getStatus().oggettoUsi.set(8, getStatus().oggettoUsiMax.get(8));
                    Status.setSoldi(Status.getSoldi() - 5);
                    Loop.oggettiPosseduti += 1;
                    System.out.println("OGGETTO ACQUISTATO: erbe curative");

                }
                else if ((line.contains("8")) && (Status.getSoldi() >= 35)) {        //scelta 8 - compra medikit

                    getStatus().oggettoUsi.set(0, getStatus().oggettoUsiMax.get(0));
                    Status.setSoldi(Status.getSoldi() - 35);
                    Loop.oggettiPosseduti += 1;
                    System.out.println("OGGETTO ACQUISTATO: medikit");

                }
                else if ((line.contains("9")) && (Status.getSoldi() >= 75)) {        //scelta 2 - compra kit pronto soccorso

                    getStatus().oggettoUsi.set(10, getStatus().oggettoUsiMax.get(10));
                    Status.setSoldi(Status.getSoldi() - 75);
                    Loop.oggettiPosseduti += 1;
                    System.out.println("OGGETTO ACQUISTATO: kit pronto soccorso");

                }
                else {                                //errore oppure oggetti curativi oppure armi
                    if (inputOggettiCurativi(line) == false) {
                        System.out.println("Non puoi fare quello.");
                        wrong = true;
                    }
                }
                break;

            case 7:     //gruppo di zombie
                if (line.contains("1")) {               //scelta 1 - niente

                    int randomNum = ThreadLocalRandom.current().nextInt(0,  10 + 1);    //fino a -10 vita
                    Status.setHp(Status.getHp() - Math.abs(randomNum - Status.getDifesa()));

                } else if (line.contains("2")) {        //scelta 2 - attacca a mani nude

                    int randomNum = ThreadLocalRandom.current().nextInt(0,  90 + 1);    //fino a -90 vita
                    Status.setHp(Status.getHp() - Math.abs(randomNum - Status.getDifesa()));

                    randomNum = ThreadLocalRandom.current().nextInt(0,  50 + 1);    //fino a +50 cibo
                    Status.setCibo(Status.getCibo() + randomNum);

                    randomNum = ThreadLocalRandom.current().nextInt(0,  50 + 1);    //fino a +50 acqua
                    Status.setCibo(Status.getCibo() + randomNum);

                    randomNum = ThreadLocalRandom.current().nextInt(0,  70 + 1);    //fino a +70 soldi
                    Status.setSoldi(Status.getSoldi() + randomNum);

                    possibilitaTrovaOggetto(60);

                } else if (line.contains("3")) {        //scelta 3 - distrai

                    int randomNum = ThreadLocalRandom.current().nextInt(0,  60 + 1);    //fino a -60 vita
                    Status.setHp(Status.getHp() - Math.abs(randomNum - Status.getDifesa()));

                    randomNum = ThreadLocalRandom.current().nextInt(0,  25 + 1);    //fino a +25 cibo
                    Status.setCibo(Status.getCibo() + randomNum);

                    randomNum = ThreadLocalRandom.current().nextInt(0,  25 + 1);    //fino a +25 acqua
                    Status.setCibo(Status.getCibo() + randomNum);

                    randomNum = ThreadLocalRandom.current().nextInt(0,  50 + 1);    //fino a +50 soldi
                    Status.setSoldi(Status.getSoldi() + randomNum);

                    possibilitaTrovaOggetto(30);

                }
                else {                                //errore oppure oggetti curativi oppure armi
                    if (inputArmi(line) == true)
                    {
                        int randomNum = ThreadLocalRandom.current().nextInt(0,  60 + 1);    //fino a -15 vita
                        Status.setHp(Status.getHp() - Math.abs(randomNum - Status.getDifesa()));

                        randomNum = ThreadLocalRandom.current().nextInt(0,  50 + 1);    //fino a +50 cibo
                        Status.setCibo(Status.getCibo() + randomNum);

                        randomNum = ThreadLocalRandom.current().nextInt(0,  50 + 1);    //fino a +50 acqua
                        Status.setCibo(Status.getCibo() + randomNum);

                        randomNum = ThreadLocalRandom.current().nextInt(0,  70 + 1);    //fino a +70 soldi
                        Status.setSoldi(Status.getSoldi() + randomNum);

                        possibilitaTrovaOggetto(65);
                    }
                    else if (inputOggettiCurativi(line) == false) {
                        System.out.println("Non puoi fare quello.");
                        wrong = true;
                    }
                }
                break;

            case 8:     //precipizio
                if (line.contains("1")) {               //scelta 1 - calati lentamente

                    int randomNum = ThreadLocalRandom.current().nextInt(0,  30 + 1);    //fino a -30 vita
                    Status.setHp(Status.getHp() - Math.abs(randomNum - Status.getDifesa()));

                } else if (line.contains("2")) {        //scelta 2 - torna indietro (dagli zombie)

                    int randomNum = ThreadLocalRandom.current().nextInt(0,  100 + 1);

                    if (randomNum >= 15)        //probabilita' di 15
                    {
                        Loop.next = 7;      //gruppo di zombie
                    }
                    else
                    if (randomNum >= 30)        //probabilita' di 30
                    {
                        Loop.next = 5;      //zombie singolo
                    }

                }
                else {                                //errore oppure oggetti curativi oppure armi
                    if (inputAttrezzi(line) == true)
                    {
                        int randomNum = ThreadLocalRandom.current().nextInt(0,  25 + 1);    //fino a -25 vita
                        Status.setHp(Status.getHp() - Math.abs(randomNum - Status.getDifesa()));

                        possibilitaTrovaOggetto(10);
                    }
                    else if (inputOggettiCurativi(line) == false) {
                        System.out.println("Non puoi fare quello.");
                        wrong = true;
                    }
                }
                break;

            case 9:     //villaggio
                if (line.contains("1")) {               //scelta 1 - niente

                    //niente

                } else if (line.contains("2")) {        //scelta 2 - depreda cibo

                    int randomNum = ThreadLocalRandom.current().nextInt(0,  50 + 1);       //fino a +50 cibo
                    Status.setCibo(Status.getCibo() + randomNum);

                    randomNum = ThreadLocalRandom.current().nextInt(0,  25 + 1);    //fino a -25 vita
                    Status.setHp(Status.getHp() - Math.abs(randomNum - Status.getDifesa()));
                }
                else if (line.contains("3")) {        //scelta 2 - depreda acqua

                    int randomNum = ThreadLocalRandom.current().nextInt(0,  50 + 1);       //fino a +50 acqua
                    Status.setAcqua(Status.getAcqua() + randomNum);

                    randomNum = ThreadLocalRandom.current().nextInt(0,  25 + 1);    //fino a -25 vita
                    Status.setHp(Status.getHp() - Math.abs(randomNum - Status.getDifesa()));
                }
                else if (line.contains("4")) {        //scelta 2 - depreda denaro

                    int randomNum = ThreadLocalRandom.current().nextInt(0,  80 + 1);       //fino a +80 denaro
                    Status.setAcqua(Status.getAcqua() + randomNum);

                    randomNum = ThreadLocalRandom.current().nextInt(0,  30 + 1);    //fino a -30 vita
                    Status.setHp(Status.getHp() - Math.abs(randomNum - Status.getDifesa()));
                }
                else if (line.contains("5")) {        //scelta 2 - cerca mercante

                    Loop.next = 6;

                    int randomNum = ThreadLocalRandom.current().nextInt(0,  25 + 1);    //fino a -25 vita
                    Status.setHp(Status.getHp() - Math.abs(randomNum - Status.getDifesa()));
                }
                else if (line.contains("6")) {        //scelta 2 - armeria

                    possibilitaTrovaOggetto(90);
                    possibilitaTrovaOggetto(5);
                    possibilitaTrovaOggetto(1);

                    int randomNum = ThreadLocalRandom.current().nextInt(0,  60 + 1);    //fino a -60 vita
                    Status.setHp(Status.getHp() - Math.abs(randomNum - Status.getDifesa()));
                }
                else {                                //errore oppure oggetti curativi oppure armi
                    if (inputAttrezzi(line) == true)
                    {
                        int randomNum = ThreadLocalRandom.current().nextInt(0,  60 + 1);    //fino a -60 vita
                        Status.setHp(Status.getHp() - Math.abs(randomNum - Status.getDifesa()));

                        randomNum = ThreadLocalRandom.current().nextInt(0,  40 + 1);       //fino a +40 acqua
                        Status.setAcqua(Status.getAcqua() + randomNum);

                        randomNum = ThreadLocalRandom.current().nextInt(0,  40 + 1);       //fino a +40 cibo
                        Status.setCibo(Status.getCibo() + randomNum);

                        randomNum = ThreadLocalRandom.current().nextInt(0,  50 + 1);       //fino a +50 soldi
                        Status.setSoldi(Status.getSoldi() + randomNum);

                        possibilitaTrovaOggetto(30);
                        possibilitaTrovaOggetto(2);
                    }
                    else if (inputOggettiCurativi(line) == false) {
                        System.out.println("Non puoi fare quello.");
                        wrong = true;
                    }
                }
                break;

            case 10:     //covo di zombie
                if (line.contains("1")) {               //scelta 1 - niente

                    //niente

                } else if (line.contains("2")) {        //scelta 2 - corri e ruba quel che riesci

                    int randomNum = ThreadLocalRandom.current().nextInt(20,  35 + 1);    //fino a -35 vita
                    Status.setHp(Status.getHp() - Math.abs(randomNum - Status.getDifesa()));

                    randomNum = ThreadLocalRandom.current().nextInt(0,  10 + 1);    //fino a +10 cibo
                    Status.setCibo(Status.getCibo() + randomNum);

                    randomNum = ThreadLocalRandom.current().nextInt(0,  10 + 1);    //fino a +10 acqua
                    Status.setCibo(Status.getCibo() + randomNum);

                    randomNum = ThreadLocalRandom.current().nextInt(0,  20 + 1);    //fino a +20 soldi
                    Status.setSoldi(Status.getSoldi() + randomNum);

                    possibilitaTrovaOggetto(10);

                } else if (line.contains("3")) {        //scelta 3 - fai fuori gli zombie

                    int randomNum = ThreadLocalRandom.current().nextInt(40,  90 + 1);    //fino a -90 vita
                    Status.setHp(Status.getHp() - Math.abs(randomNum - Status.getDifesa()));

                    randomNum = ThreadLocalRandom.current().nextInt(30,  60 + 1);    //fino a +60 cibo
                    Status.setCibo(Status.getCibo() + randomNum);

                    randomNum = ThreadLocalRandom.current().nextInt(30,  60 + 1);    //fino a +60 acqua
                    Status.setCibo(Status.getCibo() + randomNum);

                    randomNum = ThreadLocalRandom.current().nextInt(30,  80 + 1);    //fino a +80 soldi
                    Status.setSoldi(Status.getSoldi() + randomNum);

                    possibilitaTrovaOggetto(100);
                    possibilitaTrovaOggetto(50);
                    possibilitaTrovaOggetto(20);

                }
                else {                                //errore oppure oggetti curativi oppure armi
                    if (inputArmi(line) == true)
                    {
                        int randomNum = ThreadLocalRandom.current().nextInt(40,  80 + 1);    //fino a -90 vita
                        Status.setHp(Status.getHp() - Math.abs(randomNum - Status.getDifesa()));

                        randomNum = ThreadLocalRandom.current().nextInt(30,  70 + 1);    //fino a +70 cibo
                        Status.setCibo(Status.getCibo() + randomNum);

                        randomNum = ThreadLocalRandom.current().nextInt(30,  70 + 1);    //fino a +70 acqua
                        Status.setCibo(Status.getCibo() + randomNum);

                        randomNum = ThreadLocalRandom.current().nextInt(30,  100 + 1);    //fino a +100 soldi
                        Status.setSoldi(Status.getSoldi() + randomNum);

                        possibilitaTrovaOggetto(100);
                        possibilitaTrovaOggetto(60);
                        possibilitaTrovaOggetto(30);
                    }
                    else if (inputOggettiCurativi(line) == false) {
                        System.out.println("Non puoi fare quello.");
                        wrong = true;
                    }
                }
                break;
        }

        return wrong;
    }

    /**
     * Metodo che stampa gli oggetti curativi usabili.
     * @return stringa che si concatena alla stringa da stampare.
     */
    private String stampaOggettiCurativiUsabili()
    {
        String str = "";

        if (getStatus().oggettoUsi.get(0) > 0)     //se hai il medikit
        {
            str = str.concat("\nUsabile: medikit [+25 vita, prosegui e ignora]");
        }

        if (getStatus().oggettoUsi.get(8) > 0)     //se hai le erbe curative
        {
            str = str.concat("\nUsabile: erbe curative [+15 vita, prosegui e ignora]");
        }

        if (getStatus().oggettoUsi.get(9) > 0)     //se hai le bende
        {
            str = str.concat("\nUsabile: bende [+40 vita, prosegui e ignora]");
        }

        if (getStatus().oggettoUsi.get(10) > 0)     //se hai il kit di pronto soccorso
        {
            str = str.concat("\nUsabile: kit pronto soccorso [+90 vita, prosegui e ignora]");
        }

        return str;
    }

    /**
     * Metodo che stampa le armi usabili.
     * @return stringa che si concatena alla stringa da stampare.
     */
    private String stampaArmaUsabili()
    {
        String str = "";

        if (getStatus().oggettoUsi.get(1) > 0)     //se hai il coltello
        {
            str = str.concat("\nUsabile: coltello [+10 difesa, attacca]");
        }

        if (getStatus().oggettoUsi.get(5) > 0)     //se hai la pistola
        {
            str = str.concat("\nUsabile: pistola [+20 difesa, attacca]");
        }

        if (getStatus().oggettoUsi.get(6) > 0)     //se hai il fucile da caccia
        {
            str = str.concat("\nUsabile: fucile caccia [+30 difesa, attacca]");
        }

        if (getStatus().oggettoUsi.get(7) > 0)     //se hai il bastone
        {
            str = str.concat("\nUsabile: bastone [+15 difesa, attacca]");
        }

        if (getStatus().oggettoUsi.get(12) > 0)     //se hai la mazza da baseball
        {
            str = str.concat("\nUsabile: mazza baseball [+20 difesa, attacca]");
        }
        if (getStatus().oggettoUsi.get(13) > 0)     //se hai la motosega
        {
            str = str.concat("\nUsabile: motosega [+40 difesa, attacca]");
        }
        if (getStatus().oggettoUsi.get(14) > 0)     //se hai il lanciafiamme
        {
            str = str.concat("\nUsabile: lanciafiamme [+60 difesa, attacca]");
        }

        return str;
    }

    /**
     * Metodo che stampa gli attrezzi usabili.
     * @return stringa che si concatena alla stringa da stampare.
     */
    private String stampaAttrezziUsabili()
    {
        String str = "";

        if (getStatus().oggettoUsi.get(2) > 0)     //se hai la corda
        {
            str = str.concat("\nUsabile: corda [+10 difesa, attacca]");
        }

        if (getStatus().oggettoUsi.get(3) > 0)     //se hai gli attrezzi
        {
            str = str.concat("\nUsabile: attrezzi [+20 difesa, attacca]");
        }

        if (getStatus().oggettoUsi.get(11) > 0)     //se hai il coltellino svizzero
        {
            str = str.concat("\nUsabile: coltellino svizzero [+15 difesa, attacca]");
        }

        if (getStatus().oggettoUsi.get(4) > 0)     //se hai l'ascia
        {
            str = str.concat("\nUsabile: ascia [+20 difesa, attacca]");
        }

        return str;
    }

    /**
     * Metodo che si occupa dell'input degli oggetti curativi usabili.
     * @return valore booleano che indica la validita' dell'input.
     */
    private boolean inputOggettiCurativi(String line)
    {
        boolean risultato = false;      //rimarra' "false" se non viene usato un oggetto curativo

        if ((line.contains("medikit")) && (getStatus().oggettoUsi.get(0) > 0)) {
            getStatus().oggettoUsi.set(0, getStatus().oggettoUsi.get(0) - 1);
            Status.setHp(Status.getHp() + 25);
            risultato = true;
        }

        if ((line.contains("erbe curative")) && (getStatus().oggettoUsi.get(8) > 0)) {
            getStatus().oggettoUsi.set(8, getStatus().oggettoUsi.get(8) - 1);
            Status.setHp(Status.getHp() + 15);
            risultato = true;
        }

        if ((line.contains("bende")) && (getStatus().oggettoUsi.get(9) > 0)) {
            getStatus().oggettoUsi.set(9, getStatus().oggettoUsi.get(9) - 1);
            Status.setHp(Status.getHp() + 40);
            risultato = true;
        }

        if ((line.contains("kit pronto soccorso")) && (getStatus().oggettoUsi.get(10) > 0)) {
            getStatus().oggettoUsi.set(10, getStatus().oggettoUsi.get(10) - 1);
            Status.setHp(Status.getHp() + 90);
            risultato = true;
        }

        return risultato;
    }

    /**
     * Metodo che si occupa dell'input delle armi.
     * @return valore booleano che indica la validita' dell'input.
     */
    private boolean inputArmi(String line)
    {
        boolean risultato = false;      //rimarra' "false" se non viene usato un'arma

        if ((line.contains("coltello")) && (getStatus().oggettoUsi.get(1) > 0)) {
            getStatus().oggettoUsi.set(1, getStatus().oggettoUsi.get(1) - 1);
            Status.setDifesa(10);
            risultato = true;
        }

        if ((line.contains("pistola")) && (getStatus().oggettoUsi.get(5) > 0)) {
            getStatus().oggettoUsi.set(5, getStatus().oggettoUsi.get(5) - 1);
            Status.setDifesa(20);
            risultato = true;
        }

        if ((line.contains("fucile caccia")) && (getStatus().oggettoUsi.get(6) > 0)) {
            getStatus().oggettoUsi.set(6, getStatus().oggettoUsi.get(6) - 1);
            Status.setDifesa(30);
            risultato = true;
        }

        if ((line.contains("bastone")) && (getStatus().oggettoUsi.get(7) > 0)) {
            getStatus().oggettoUsi.set(7, getStatus().oggettoUsi.get(7) - 1);
            Status.setDifesa(15);
            risultato = true;
        }

        if ((line.contains("mazza baseball")) && (getStatus().oggettoUsi.get(12) > 0)) {
            getStatus().oggettoUsi.set(12, getStatus().oggettoUsi.get(12) - 1);
            Status.setDifesa(20);
            risultato = true;
        }

        if ((line.contains("motosega")) && (getStatus().oggettoUsi.get(13) > 0)) {
            getStatus().oggettoUsi.set(13, getStatus().oggettoUsi.get(13) - 1);
            Status.setDifesa(40);
            risultato = true;
        }

        if ((line.contains("lanciafiamme")) && (getStatus().oggettoUsi.get(14) > 0)) {
            getStatus().oggettoUsi.set(14, getStatus().oggettoUsi.get(14) - 1);
            Status.setDifesa(60);
            risultato = true;
        }

        return risultato;
    }

    /**
     * Metodo che si occupa dell'input degli attrezzi usabili.
     * @return valore booleano che indica la validita' dell'input.
     */
    private boolean inputAttrezzi(String line)
    {
        boolean risultato = false;      //rimarra' "false" se non viene usato un'arma

        if ((line.contains("corda")) && (getStatus().oggettoUsi.get(2) > 0)) {
            getStatus().oggettoUsi.set(2, getStatus().oggettoUsi.get(2) - 1);
            Status.setDifesa(20);
            risultato = true;
        }

        if ((line.contains("attrezzi")) && (getStatus().oggettoUsi.get(3) > 0)) {
            getStatus().oggettoUsi.set(3, getStatus().oggettoUsi.get(3) - 1);
            Status.setDifesa(25);
            risultato = true;
        }

        if ((line.contains("coltellino svizzero")) && (getStatus().oggettoUsi.get(11) > 0)) {
            getStatus().oggettoUsi.set(11, getStatus().oggettoUsi.get(11) - 1);
            Status.setDifesa(15);
            risultato = true;
        }

        if ((line.contains("ascia")) && (getStatus().oggettoUsi.get(4) > 0)) {
            getStatus().oggettoUsi.set(4, getStatus().oggettoUsi.get(4) - 1);
            Status.setDifesa(30);
            risultato = true;
        }

        return risultato;
    }

    /**
     * Attraverso la possibilita' ricevuta in input, determina se il giocatore riceve un oggetto oppure no.
     * @param probabilita'
     */
    private void possibilitaTrovaOggetto(int probabilita)       //probabilità su 100
    {
        int randomNum = ThreadLocalRandom.current().nextInt(0,  100 + 1);

        if (randomNum >= probabilita)
        {
            //codice di oggetto trovato
            int indiceOggetto = ThreadLocalRandom.current().nextInt(0,  (getStatus().oggettoNome.size() - 1) + 1);

            String nomeOggetto = getStatus().oggettoNome.get(indiceOggetto);
            getStatus().oggettoUsi.set(indiceOggetto, getStatus().oggettoUsiMax.get(indiceOggetto));

            Loop.oggettiPosseduti += 1;
            System.out.println("TROVATO OGGETTO!!! Trovato: " + nomeOggetto);
        }
    }

}
