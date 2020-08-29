package com.company;

import java.awt.*;
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
        while (scannerFile.hasNextLine()) {
            line = scannerFile.nextLine();
            line = line.toLowerCase();
            String[] tmp = line.split(" ");
            product.add(tmp[0]);
            price.add(tmp[1]);
            startingQuantity.add(tmp[2]);
            unit.add(tmp[3]);
        }
    }

    public void toFile() {
        FileWriter fw = null;
        try {
            fw = new FileWriter("Standlap.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        for (int i = 0; i < product.size(); i++) {
            pw.println(product.get(i) + " " + price.get(i) + " " + startingQuantity.get(i) + " " + unit.get(i));
        }
        pw.close();
    }

    public void list() {
        System.out.println("Termék\t\t\t\t\tÁr\t\t\t\t\t\tNyitó készlet\t\t\t\t\tMaradvány");
        System.out.println("-----------------------------------------------------------------------------------------");
        for (int i = 0; i < product.size(); i++) {
            System.out.println(product.get(i) + "\t\t\t\t\t" + price.get(i) + "\t\t\t\t\t\t" + startingQuantity.get(i) + unit.get(i) + "\t\t\t\t\t\t\t" + currentQuantity.get(i) + unit.get(i));
        }
    }

    public void addForStartingProduct() {
        System.out.print("Mihez szeretnél hozzáadni/elvenni? ");
        String line = scanner.nextLine();
        line = line.toLowerCase();
        int index = product.indexOf(line);
        if (index >= 0) {
            System.out.println("A kiválasztott termék a(z) " + product.get(index) + " mennyisége: " + startingQuantity.get(index) + unit.get(index));
            System.out.print("Mennyit szeretnél hozzáadni/elvenni? ");
            double tmp = scanner.nextDouble();
            scanner.nextLine();
            tmp += Double.parseDouble(startingQuantity.get(index));
            startingQuantity.set(index, Double.toString(tmp));
            System.out.println("A(z) " + product.get(index) + " termék új mennyisége: " + startingQuantity.get(index) + unit.get(index));
            toFile();
        } else {
            System.out.println("Nincs ilyen termék!");
        }
    }

    public void addForCurrentProduct(){
        System.out.print("Mihez szeretnél hozzáadni/elvenni? ");
        String line = scanner.nextLine();
        line = line.toLowerCase();
        int index = product.indexOf(line);
        if (index >= 0) {
            System.out.println("A kiválasztott termék a(z) " + product.get(index) + " maradvány mennyisége: " + currentQuantity.get(index) + unit.get(index));
            System.out.print("Mennyit szeretnél hozzáadni/elvenni? ");
            double tmp = scanner.nextDouble();
            scanner.nextLine();
            tmp += currentQuantity.get(index);
            currentQuantity.set(index, tmp);
            System.out.println("A(z) " + product.get(index) + " termék új maradvány mennyisége: " + currentQuantity.get(index) + unit.get(index));
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
        if (!name.isBlank() && !priceTmp.isBlank() && !startingQuantityTmp.isBlank() && !unitTmp.isBlank() && !product.contains(name) && !name.contains(" ")) {
            product.add(name);
            price.add(priceTmp);
            startingQuantity.add(startingQuantityTmp);
            unit.add(unitTmp);
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

    public void setCurrentQuantity(){
        for (int i = 0; i < product.size(); i++)
            currentQuantity.add(0.0);
    }

}

