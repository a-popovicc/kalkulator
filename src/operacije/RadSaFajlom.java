package operacije;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RadSaFajlom {

    private static final String FOLDER =
            System.getProperty("user.home") + "/.javaCalculator/";

    private static final String FILE_PATH =
            FOLDER + "Istorija.txt";


    // kreiranje foldera + fajla ako ne postoje
    private static void ensureFileExists() {
        try {
            Path folderPath = Paths.get(FOLDER);
            Path filePath = Paths.get(FILE_PATH);

            // napravi folder ako ne postoji
            if (!Files.exists(folderPath)) {
                Files.createDirectories(folderPath);
            }

            // ako fajl ne postoji → napravi prazan
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void upisiUFajl(String pomocni, String trenutni) {
        ensureFileExists();
        try (BufferedWriter b = new BufferedWriter(new FileWriter(FILE_PATH, true))) {

            b.write(pomocni + trenutni);
            b.newLine();
            b.newLine(); // prazna linija zbog preglednosti

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String ucitajIzFajla() {
        ensureFileExists();
        StringBuilder sadrzaj = new StringBuilder();

        try (BufferedReader in = new BufferedReader(new FileReader(FILE_PATH))) {

            String linija;
            while ((linija = in.readLine()) != null) {
                sadrzaj.append(linija).append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sadrzaj.toString();
    }


    public static void obrisiSveIzFajla() {
        ensureFileExists();
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            pw.print(""); // obriši sadržaj

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

// *******************************************************************************************
//   ⚠️ DONJI KOD JE SAMO REFERENCA — OVO JE PUTANJA KA FAJLU UNUTAR PROJEKTA (SRC/RESURSI)
//   OVAJ DEO RADI U ECLIPSE-U, ALI NEĆE RADITI U JAR-U JER RESOURCE NIJE PRAVI FAJL.
// *******************************************************************************************

/*
// Primer ako želiš da čitaš fajl iz projekta:
// src/resursi/datoteke/Istorija.txt

private static final String INTERNAL_PATH = "src/resursi/datoteke/Istorija.txt";

public static void upisiUFajl_InterneResursi(String pomocni, String trenutni) {
    try (FileWriter f = new FileWriter(INTERNAL_PATH, true);
         BufferedWriter b = new BufferedWriter(f);
         PrintWriter pw = new PrintWriter(b)) {

        pw.println(pomocni + trenutni);
        pw.println();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

public static String ucitajIzFajla_InterneResursi() {
    StringBuilder sadrzaj = new StringBuilder();

    try (BufferedReader br = new BufferedReader(new FileReader(INTERNAL_PATH))) {
        String line;
        while ((line = br.readLine()) != null) {
            sadrzaj.append(line).append("\n");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return sadrzaj.toString();
}
*/


