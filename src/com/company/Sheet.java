package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Sheet {
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<String> product = new ArrayList<>();
    private ArrayList<String> price = new ArrayList<>();
    private ArrayList<String> startingQuantity = new ArrayList<>();
    private ArrayList<String> unit = new ArrayList<>();
    private ArrayList<Double> currentQuantity = new ArrayList<>();
    private ArrayList<Integer> fridge = new ArrayList<>();
    private ArrayList<String> fridge1 = new ArrayList<>();
    private ArrayList<String> fridge2 = new ArrayList<>();
    private ArrayList<String> fridge3 = new ArrayList<>();


    public void toArrayList() {
        File file = new File("Standlap.txt");
        Scanner scannerFile = null;

        {
            try {
                scannerFile = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        String line;
        if (scannerFile != null) {
            while (scannerFile.hasNextLine()) {
                line = scannerFile.nextLine();
                line = line.toLowerCase();
                String[] tmp = line.split(" ");
                product.add(tmp[0]);
                price.add(tmp[1]);
                startingQuantity.add(tmp[2]);
                unit.add(tmp[3]);
                fridge.add(Integer.parseInt(tmp[4]));
            }
        }
    }

    public void toFile() {
        FileWriter fw = null;
        try {
            fw = new FileWriter("Standlap.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(fw != null) {
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < product.size(); i++) {
                pw.println(product.get(i) + " " + price.get(i) + " " + startingQuantity.get(i) + " " + unit.get(i) + " " + fridge.get(i));
            }
            pw.close();
        }
        else
            System.out.println("Nem sikerült beolvasni a fájlt!");
    }

    private int whichFridge() {
        System.out.println("Melyik hűtő termékei legyen listázva? ");
        int fridgeTmp = scanner.nextInt();
        scanner.nextLine();
        return fridgeTmp;
    }

    public void list() {
        int fridgeTmp = whichFridge();
        System.out.println("Termék\t\t\t\t\tÁr\t\t\t\t\t\tNyitó készlet\t\t\t\t\tMaradvány");
        System.out.println("-----------------------------------------------------------------------------------------");
        for (int i = 0; i < product.size(); i++) {
            if (fridgeTmp == fridge.get(i) || fridgeTmp == 0)
                System.out.println(product.get(i) + "\t\t\t\t\t" + price.get(i) + "\t\t\t\t\t\t" + startingQuantity.get(i) + unit.get(i) + "\t\t\t\t\t\t\t" + currentQuantity.get(i) + unit.get(i));
        }
    }

    public void productToFridge() {
        for (int i = 0; i < product.size(); i++) {
            switch (fridge.get(i)) {
                case 1:
                    fridge1.add(product.get(i));
                    break;
                case 2:
                    fridge2.add(product.get(i));
                    break;
                case 3:
                    fridge3.add(product.get(i));
                    break;
            }
        }
    }

    private int askWhichProduct() {
        System.out.print("Melyik termék legyen? ");
        int tmp = scanner.nextInt();
        scanner.nextLine();
        return tmp - 1;
    }

    private int indexOfProductFromFridge(int fridge) {
        switch (fridge) {
            case 1:
                for (int i = 0; i < fridge1.size(); i++)
                    System.out.println(i + 1 + " - " + fridge1.get(i));
                return product.indexOf(fridge1.get(askWhichProduct()));
            case 2:
                for (int i = 0; i < fridge2.size(); i++)
                    System.out.println(i + 1 + " - " + fridge2.get(i));
                return product.indexOf(fridge2.get(askWhichProduct()));
            case 3:
                for (int i = 0; i < fridge3.size(); i++)
                    System.out.println(i + 1 + " - " + fridge3.get(i));
                return product.indexOf(fridge3.get(askWhichProduct()));
            default:
                System.out.println("Nincs ilyen hűtő!");
                return -1;
        }
    }

    public void addForStartingProduct() {
        int fridgeTmp = whichFridge();
        int index = indexOfProductFromFridge(fridgeTmp);
        if (index >= 0) {
            System.out.println("A kiválasztott termék a(z) " + product.get(index) + " mennyisége: " + startingQuantity.get(index) + unit.get(index));
            System.out.print("Mennyit szeretnél hozzáadni/elvenni? ");
            double tmp = Double.parseDouble(scanner.nextLine());
            tmp += Double.parseDouble(startingQuantity.get(index));
            startingQuantity.set(index, Double.toString(tmp));
            System.out.println("A(z) " + product.get(index) + " termék új mennyisége: " + startingQuantity.get(index) + unit.get(index));
            toFile();
        } else {
            System.out.println("Nincs ilyen termék!");
        }
    }

    public void addForCurrentProduct() {
        int fridgeTmp = whichFridge();
        int index = indexOfProductFromFridge(fridgeTmp);
        if (index >= 0) {
            System.out.println("A kiválasztott termék a(z) " + product.get(index) + " mennyisége: " + currentQuantity.get(index) + unit.get(index));
            System.out.print("Mennyit szeretnél hozzáadni/elvenni? ");
            double tmp = Double.parseDouble(scanner.nextLine());
            tmp += currentQuantity.get(index);
            currentQuantity.set(index, tmp);
            System.out.println("A(z) " + product.get(index) + " termék új mennyisége: " + currentQuantity.get(index) + unit.get(index));
            toFile();
        } else {
            System.out.println("Nincs ilyen termék!");
        }
    }

    public void count() {
        System.out.println("Folyamatban");
    }

    public void closeSheet() {
        scanner.close();
    }

    public void addProduct() {
        System.out.print("Termék neve:");
        String name = scanner.nextLine();
        name = name.toLowerCase();
        System.out.print("Termék ára: ");
        String priceTmp = scanner.nextLine();
        System.out.print("Termék mennyisége: ");
        String startingQuantityTmp = scanner.nextLine();
        System.out.print("Termék mértékegysége: ");
        String unitTmp = scanner.nextLine();
        System.out.print("Termék hűtője: ");
        String fridgeTmp = scanner.nextLine();
        if (!name.isBlank() && !priceTmp.isBlank() && !startingQuantityTmp.isBlank() && !unitTmp.isBlank() && !fridgeTmp.isBlank() && !product.contains(name) && !name.contains(" ")) {
            product.add(name);
            price.add(priceTmp);
            startingQuantity.add(startingQuantityTmp);
            unit.add(unitTmp);
            fridge.add(Integer.parseInt(fridgeTmp));
            currentQuantity.add(0.0);
            System.out.println(name + " hozzáadva a listához!");
            toFile();
        } else {
            if (name.contains(" "))
                System.out.println("A név nem tartalmazhat szóközt!");
            else
                System.out.println("Hiba!");
        }
    }

    public void removeProduct() {
        System.out.print("Termék neve:");
        String name = scanner.nextLine();
        name = name.toLowerCase();
        if (product.contains(name)) {
            int index = product.indexOf(name);
            product.remove(index);
            price.remove(index);
            startingQuantity.remove(index);
            unit.remove(index);
            currentQuantity.remove(index);
            System.out.println(name + " termék törölve!");
            toFile();
        } else
            System.out.println("Nincs ilyen termék a listában!");
    }

    public void setCurrentQuantity() {
        for (int i = 0; i < product.size(); i++)
            currentQuantity.add(0.0);
    }
}