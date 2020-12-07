package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Classe che si occupa di processare gli input e le richieste necessarie per far funzionare il menù principale.
 * <p>La classe include la gestione di: Schermata del titolo e menù principale.</p>
 */

public class MainMenu {

    public MainMenu()
    {
        schermataTitolo();
        menuPrincipale();
    }

    /**
     *  Metodo che gestisce la schermata del Titolo: stampe e input.
     */

    private void schermataTitolo()
    {
        System.out.println("\n");

        //INPUT---------------------------------------------------*

        //costruzione oggetto per l'input
        BufferedReader input = new BufferedReader(
                new InputStreamReader( System.in )
        );
        try
        {
            //acquisisce input di "scelta"
            System.out.print("--> Sai come si gioca?: (s/n)" + " ");
            String line = "a";

            while(!(line.equals("s")) && !(line.equals("n")))
            {
                line = input.readLine();
                if (line.equals("s"))
                {

                }
                else if (line.equals("n"))
                {
                    displayIstruzioni();
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Errore in input");
        }

        //FINE INPUT----------------------------------------------*
    }

    /**
     *  Metodo che stampa le istruzioni del gioco.
     */

    private void displayIstruzioni()
    {
        System.out.println("*/*/*/*/*/*/*/*/*/* Istruzioni di gioco */*/*/*/*/*/*/*/*/*\n");
        System.out.println("L'obiettivo del gioco e' sopravvivere piu' giorni possibili.\nDovrai mantenere la tua vitalita' sopra zero. Inoltre,\ndovrai ottenere cibo e acqua per non morire di fame.\nIl gioco presenta varie scelte in diversi scenari che definiscono\nla perdita o l'aumento delle tue risorse.\n\nPer scegliere un'opzione, scrivi il numero dell'opzione oppure il nome dell'oggetto che vuoi usare.\nPotrai ottenere oggetti facendo certe scelte.\n");
        System.out.println("\n*/*/*/*/*/*/*/*/*/* Fine Istruzioni di gioco */*/*/*/*/*/*/*/*/*");
    }

    /**
     *  Metodo che gestisce il menù principale: stampe e input.
     */

    private void menuPrincipale()
    {
        System.out.println("1. Gioca");
        System.out.println("2. Esci");

        //INPUT---------------------------------------------------*

        //costruzione oggetto per l'input
        BufferedReader input = new BufferedReader(
                new InputStreamReader( System.in )
        );
        try
        {
            //acquisisce input di "scelta"
            String line = "a";

            while(!(line.equals("1")) && !(line.equals("2")))
            {
                line = input.readLine();

                if (line.equals("2"))
                {
                    System.exit(0);     //esce dal gioco.
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Errore in input");
        }

        //FINE INPUT----------------------------------------------*
    }

}
