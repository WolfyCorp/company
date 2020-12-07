package com.company;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Object;
import java.io.File;
import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Classe che si occupa del gioco in sé.
 * <p>-Ciclo di gioco e timer dei giorni; </p>
 * <p>-Possibile status di game over; </p>
 * <p>-Gestione di tutte le classi che costituiscono gli oggetti di gioco. </p>
 */

public class Loop {

    private boolean gameOver = false;
    private boolean firstPlay = true;       //se è la prima iterazione del ciclo di gioco

    private int giorno = 1;
    private int nEventi = 10;       //numero dei possibili tipi di eventi!!

    public static int oggettiPosseduti = 0;

    public static int next = 0;             //se è zero, un evento non ne porta a un altro. modificato dai vari eventi.

    //metodo costruttore
    public Loop()
    {
        //ciclo di eventi
        int i = 0;

        System.out.println("\nGiorno " + giorno);
        System.out.println("Vitalita': " + Status.getHp());
        System.out.println("Cibo: " + Status.getCibo());
        System.out.println("Acqua: " + Status.getAcqua());
        System.out.println("Denaro': " + Status.getSoldi());

        do {
            if (next == 0)
            {
                int randomNum = ThreadLocalRandom.current().nextInt(1, nEventi + 1);
                Evento evento = new Evento(randomNum);
            }
            else {
                Evento evento = new Evento(next);
            }

            //clearConsole();
            Status.setDifesa(0);        //dovrò mettere "setDifesa(Status.getArmor);

            if (Status.getCibo() == 0)
            {
                Status.setHp(Status.getHp() - 10);
            }

            if (Status.getAcqua() == 0)
            {
                Status.setHp(Status.getHp() - 20);
            }

            nextDay();
            i++;

            if (Status.getHp() > 0)
            {
                giorno++;
            }
            else
            {
                gameOver = true;
            }

            firstPlay = false;
        }
        while(!gameOver);

        menuGameOver();     //vai al game over quando il ciclo di gioco è interrotto
    }

    /**
     * Metodo che va avanti di un giorno e consuma cibo e acqua
     */

    private void nextDay()          //di base di giorno in giorno cibo e acqua diminuiscono in base al rispettivo consumo.
    {
        //consumo cibo e acqua
        Status.setCibo(Status.getCibo() - Status.getConsumoCibo());
        Status.setAcqua(Status.getAcqua() - Status.getConsumoAcqua());

        //clamp
        if (Status.getHp() < 0)
            Status.setHp(0);

        //if (Status.getHp() > Status.getMaxHp())
        //    Status.setHp(Status.getMaxHp());

        if (Status.getCibo() < 0)
            Status.setCibo(0);

        if (Status.getAcqua() < 0)
            Status.setAcqua(0);

        if (Status.getSoldi() < 0)
            Status.setSoldi(0);

        //stampa
        System.out.println("Giorno " + giorno);
        System.out.println("Vitalita': " + Status.getHp());
        System.out.println("Cibo: " + Status.getCibo());
        System.out.println("Acqua: " + Status.getAcqua());
        System.out.println("Denaro: " + Status.getSoldi());
    }

    /**
     * Metodo che ripulisce lo schermo.
     */

    private void clearConsole()
    {
        //System.out.flush();
        for(int i = 0; i < 10; i++)
            System.out.println("\n\n\n");
    }

    /**
     * Metodo che gestisce il menù di game over.
     */

    private void menuGameOver()
    {
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("SEI MORTO! GAME OVER!");
        System.out.println("N° giorni sopravvissuti: " + giorno);
        System.out.println("cibo rimanente: " + Status.getCibo());
        System.out.println("acqua rimanente: " + Status.getAcqua());
        System.out.println("soldi rimanenti: " + Status.getSoldi());
        System.out.println("oggetti posseduti in totale: " + oggettiPosseduti);
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

        //distruggi tutti gli oggetti inutili

    }

}
