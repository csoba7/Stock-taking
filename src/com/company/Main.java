package com.company;

import java.util.Scanner;

public class Main {
    private static boolean run = true;
    private static Scanner scanner = new Scanner(System.in);
    private static Sheet sheet = new Sheet();

    private static void addCurrentQuantity() {
        System.out.println();
        sheet.addForCurrentProduct();
        System.out.println();
    }

    private static void addSheet() {
        System.out.println();
        sheet.addForStartingProduct();
        System.out.println();
    }

    private static void listSheet() {
        System.out.println();
        sheet.list();
        System.out.println();
    }

    private static void addProduct() {
        System.out.println();
        sheet.addProduct();
        System.out.println();
    }

    private static void removeProduct() {
        System.out.println();
        sheet.removeProduct();
        System.out.println();
    }

    private static void count() {
        System.out.println();
        sheet.count();
        System.out.println();
    }

    private static void newStartingQuantity(){
        System.out.println();
        sheet.changeStartingQuantity();
        System.out.println();
    }

    private static void exit() {
        sheet.closeSheet();
        run = false;
    }

    public static void main(String[] args) {
        sheet.toArrayList();
        sheet.setCurrentQuantity();
        sheet.productToFridge();
        while (run) {
            System.out.println(
                    "1 - Listázás\n" +
                    "2 - Maradványhoz adás/vétel\n" +
                    "3 - Nyitókészlethez adás/elvétel\n" +
                    "4 - Termék hozzáadása\n" +
                    "5 - Termék törlése\n" +
                    "6 - Kiszámolás\n" +
                    "7 - Új nyitókészlet\n" +
                    "Kilépés - Kilépés");
            System.out.print("Opció választása: ");
            switch (scanner.nextLine().toLowerCase()) {
                case "1":
                    listSheet();
                    break;
                case "2":
                    addCurrentQuantity();
                    break;
                case "3":
                    addSheet();
                    break;
                case "4":
                    addProduct();
                    break;
                case "5":
                    removeProduct();
                    break;
                case "6":
                    count();
                    break;
                case "7":
                    newStartingQuantity();
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