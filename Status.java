package com.company;

import java.util.ArrayList;

/**
 * Classe che definisce e gestisce le variabili relative al giocatore. [cibo, acqua, vita, soldi, inventario]
 */

public class Status {

    //statistiche fondamentali

    private int hp = 100;         //salute del giocatore - "hitpoints"
    private int cibo = 100;       //cibo a disposizione del giocatore
    private int acqua = 100;      //acqua a disposizione del giocatore
    private int soldi = 0;      //soldi nel portafoglio del giocatore

    //statistiche potenziabili

    private int maxHp = 100;
    private int consumoCibo = 5;
    private int consumoAcqua = 10;
    private int difesa = 0;         //percentuale di danno filtrato
    private int rischio = 50;       //percentuale di rischio di fallire un attacco/esplorazione pericolosa (puo' essere abbassato dalle armi)

    //oggetti dell'inventario

        //oggetti acquistabili - equipaggiamento di normale fattura, acquistabile con i soldi
        private int medikit = 0;            //oggetto per le cure - cura 40 hp
        private int coltello = 0;           //usabile sia come arma che come oggetto d'esplorazione
        private int corda = 0;              //oggetto d'esplorazione
        private int cacciavite = 0;         //oggetto d'esplorazione
        private int martello = 0;           //oggetto d'esplorazione
        private int sprayPeperoncino = 0;   //oggetto per la fuga
        private int ascia = 0;              //usabile sia come arma che come oggetto d'esplorazione
        private int pistola = 0;            //arma
        private int fucileCaccia = 0;       //arma

        //oggetti costruibili - equipaggiamento debole ma costruibile a costo zero
        private int scudoLegno = 0;         //scudo
        private int zattera = 0;            //oggetto di trasporto
        private int bastoneAffilato = 0;    //arma
        private int mazzaLegno = 0;         //oggetto d'esplorazione
        private int erbeCurative = 0;       //oggetto per le cure - cura 15 hp

        //oggetti trovabili - miglior equipaggiamento del gioco, trovabile solo esplorando zone pericolose
        private int bende = 0;              //oggetto per le cure - cura 70 hp
        private int kitProntoSoccorso = 0;  //oggetto per le cure - cura 110 hp
        private int scudoAntisommossa = 0;  //scudo
        private int coltellinoSvizzero = 0; //oggetto d'esplorazione
        private int mazzaBaseball = 0;      //arma
        private int motosega = 0;           //arma, strumento per tagliare il legno
        private int lanciaFiamme = 0;       //arma
        private int fucilePompa = 0;        //arma
        private int fumogeno = 0;           //oggetto per la fuga
        private int petardo = 0;            //oggetto per la fuga
        private int gommone = 0;            //oggetto di trasporto

    //oggetti dell'inventario - usi massimi

        //oggetti acquistabili - equipaggiamento di normale fattura, acquistabile con i soldi
        private int maxMedikit = 1;            //oggetto per le cure - cura 40 hp
        private int maxColtello = 2;           //usabile sia come arma che come oggetto d'esplorazione
        private int maxCorda = 2;              //oggetto d'esplorazione
        private int maxCacciavite = 2;         //oggetto d'esplorazione
        private int maxMartello = 3;           //oggetto d'esplorazione
        private int maxSprayPeperoncino = 1;   //oggetto per la fuga
        private int maxAscia = 3;              //usabile sia come arma che come oggetto d'esplorazione
        private int maxPistola = 5;            //arma
        private int maxFucileCaccia = 5;       //arma

        //oggetti costruibili - equipaggiamento debole ma costruibile a costo zero
        private int maxScudoLegno = 3;         //scudo
        private int maxZattera = 2;            //oggetto di trasporto
        private int maxBastoneAffilato = 3;    //arma
        private int maxMazzaLegno = 2;         //oggetto d'esplorazione
        private int maxErbeCurative = 1;       //oggetto per le cure - cura 15 hp

        //oggetti trovabili - miglior equipaggiamento del gioco, trovabile solo esplorando zone pericolose
        private int maxBende = 1;              //oggetto per le cure - cura 70 hp
        private int maxKitProntoSoccorso = 1;  //oggetto per le cure - cura 110 hp
        private int maxScudoAntisommossa = 5;  //scudo
        private int maxColtellinoSvizzero = 3; //oggetto d'esplorazione
        private int maxMazzaBaseball = 4;      //arma
        private int maxMotosega = 3;           //arma, strumento per tagliare il legno
        private int maxLanciaFiamme = 2;       //arma
        private int maxFucilePompa = 5;        //arma
        private int maxFumogeno = 1;           //oggetto per la fuga
        private int maxPetardo = 1;            //oggetto per la fuga
        private int maxGommone = 5;            //oggetto di trasporto

    public ArrayList<String> oggettoNome = new ArrayList<String>();
    public ArrayList<Integer> oggettoUsi = new ArrayList<Integer>();
    public ArrayList<Integer> oggettoUsiMax = new ArrayList<Integer>();

    private static Status status;
    private Status() //Privato perchè la classe non deve essere istanziata manualmente.
    {
        //oggetti dell'inventario (trovabili o comprabili durante il gioco)

        //0
        oggettoNome.add("medikit");
        oggettoUsi.add(0);
        oggettoUsiMax.add(1);

        //1
        oggettoNome.add("coltello");
        oggettoUsi.add(0);
        oggettoUsiMax.add(2);

        //2
        oggettoNome.add("corda");
        oggettoUsi.add(0);
        oggettoUsiMax.add(2);

        //3
        oggettoNome.add("attrezzi");
        oggettoUsi.add(0);
        oggettoUsiMax.add(2);

        //4
        oggettoNome.add("ascia");
        oggettoUsi.add(0);
        oggettoUsiMax.add(3);

        //5
        oggettoNome.add("pistola");
        oggettoUsi.add(0);
        oggettoUsiMax.add(5);

        //6
        oggettoNome.add("fucile caccia");
        oggettoUsi.add(0);
        oggettoUsiMax.add(5);

        //7
        oggettoNome.add("bastone");
        oggettoUsi.add(0);
        oggettoUsiMax.add(3);

        //8
        oggettoNome.add("erbe curative");
        oggettoUsi.add(0);
        oggettoUsiMax.add(1);

        //9
        oggettoNome.add("bende");
        oggettoUsi.add(0);
        oggettoUsiMax.add(1);

        //10
        oggettoNome.add("kit pronto soccorso");
        oggettoUsi.add(0);
        oggettoUsiMax.add(1);

        //11
        oggettoNome.add("coltellino svizzero");
        oggettoUsi.add(0);
        oggettoUsiMax.add(3);

        //12
        oggettoNome.add("mazza baseball");
        oggettoUsi.add(0);
        oggettoUsiMax.add(4);

        //13
        oggettoNome.add("motosega");
        oggettoUsi.add(0);
        oggettoUsiMax.add(3);

        //14
        oggettoNome.add("lanciafiamme");
        oggettoUsi.add(0);
        oggettoUsiMax.add(2);
    }

    /**
     * Metodo usato per istanziare una sola volta Status.
     * @return istanza di status
     */

    //ottieni istanza
    public static Status getStatus()
    {
        if (status == null)
            status = new Status();

        return status;
    }

    //Metodi per ottenere le variabili
    public static int getHp() { return getStatus().hp; }

    public static int getCibo()
    {
        return getStatus().cibo;
    }

    public static int getAcqua()
    {
        return getStatus().acqua;
    }

    public static int getSoldi()
    {
        return getStatus().soldi;
    }

    public static int getMaxHp() { return getStatus().maxHp; }

    public static int getConsumoCibo() { return getStatus().consumoCibo; }

    public static int getConsumoAcqua() { return getStatus().consumoAcqua; }

    public static int getDifesa() { return getStatus().difesa; }

    //
    //Metodi per settare le variabili
    public static void setHp(int n) { getStatus().hp = n; }

    public static void setCibo(int n)
    {
        getStatus().cibo = n;
    }

    public static void setAcqua(int n)
    {
        getStatus().acqua = n;
    }

    public static void setSoldi(int n)
    {
        getStatus().soldi = n;
    }

    public static void setMaxHp(int n)
    {
        getStatus().maxHp = n;
    }

    public static void setConsumoCibo(int n)
    {
        getStatus().consumoCibo = n;
    }

    public static void setConsumoAcqua(int n)
    {
        getStatus().consumoAcqua = n;
    }

    public static void setDifesa(int n)
    {
        getStatus().difesa = n;
    }
}
