package com.company;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static boolean run = true;
    private static Scanner scanner = new Scanner(System.in);
    private static Sheet sheet = new Sheet();

    private static void addSheet() {
        System.out.println();
        sheet.addForItem();
        System.out.println();
    }

    private static void listSheet() {
        System.out.println();
        sheet.list();
        System.out.println();
    }

    private static void count() {
        System.out.println();
        sheet.count();
        System.out.println();
    }

    private static void exit() {
        sheet.closeSheet();
        run = false;
    }

    public static void main(String[] args) throws FileNotFoundException {
        sheet.toArrayList();
        while (run) {
            System.out.println("1 - Listázás\n" +
                    "2 - Termékhez adás/elvétel\n" +
                    "3 - Kiszámolás\n" +
                    "Kilépés - Kilépés");
            System.out.print("Opció választása: ");
            switch (scanner.nextLine().toLowerCase()) {
                case "1":
                    listSheet();
                    break;
                case "2":
                    addSheet();
                    break;
                case "3":
                    count();
                    break;
                case "kilépés":
                case "kilepes":
                case "kilépes":
                case "kilepés":
                case "kilép":
                    exit();
                    break;
                default:
                    System.out.println();
                    System.out.println("Érvénytelen opció!\nPróbáld újra!");
                    System.out.println();
                    break;
            }
        }
        scanner.close();
    }
}
