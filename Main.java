package com.company;

import java.io.File;
import java.util.*;

/**
 * Metodo Main.
 *
 */

public class Main {

    public static void main(String[] args) {

        //titoli di testa
        stampaMappa();

        MainMenu mm = new MainMenu();   //istanziamento della classe MainMenu;
        Loop loo = new Loop();          //istanziamento della classe Loop;

    }

    /**
     * Metodo che si occupa di gestire il caricamento del logo iniziale.
     */

    private static void stampaMappa()
    {
        String mappa = caricaMappa();
        disegnaMappa(mappa);
    }

    /**
     * Metodo che carica la mappa in una stringa
     * @return utile stringa che contiene tutta la mappa.
     */

    private static String caricaMappa()
    {
        String content = "";

        try {
            Scanner scanner = new Scanner(new File("C:/Users/712800/IdeaProjects/GovernoReal/src/com/company/foo.txt"));
            scanner.useDelimiter("\\Z");
            content = scanner.next();
        }
        catch(java.io.FileNotFoundException e) {

        }

        return content;
    }

    /**
     * Metodo che disegna la mappa.
     */

    private static void disegnaMappa(String mappa)
    {
        for (int count = 0; count < mappa.length(); count++)
        {
            char carattere = mappa.charAt(count);
            System.out.print(mappa.charAt(count));
        }
    }
}