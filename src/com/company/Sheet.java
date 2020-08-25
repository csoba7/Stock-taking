package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Sheet {
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<String> product = new ArrayList<String>();
    private ArrayList<String> price = new ArrayList<String>();
    private ArrayList<String> quantity = new ArrayList<String>();
    private ArrayList<String> unit = new ArrayList<String>();

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
            quantity.add(tmp[2]);
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
            pw.println(product.get(i) + " " + price.get(i) + " " + quantity.get(i) + " " + unit.get(i));
        }
        pw.close();
    }

    public void list() {
        System.out.println("Termék\t\tÁr\t\tMennyiség");
        System.out.println("-----------------------------");
        for (int i = 0; i < product.size(); i++) {
            System.out.println(product.get(i) + "\t\t" + price.get(i) + "\t\t" + quantity.get(i) + unit.get(i));
        }
    }

    public void addForItem() {
        System.out.print("Mihez szeretnél hozzáadni/elvenni? ");
        String line = scanner.nextLine();
        line = line.toLowerCase();
        int index = product.indexOf(line);
        if (index >= 0) {
            System.out.println("A kiválasztott termék a(z) " + product.get(index) + " mennyisége: " + quantity.get(index) + unit.get(index));
            System.out.print("Mennyit szeretnél hozzáadni/elvenni? ");
            int tmp = scanner.nextInt();
            scanner.nextLine();
            tmp += Integer.parseInt(quantity.get(index));
            quantity.set(index, Integer.toString(tmp));
            System.out.println("A(z) " + product.get(index) + " termék új mennyisége: " + quantity.get(index) + unit.get(index));
            toFile();
        } else {
            System.out.println("Nincs ilyen termék!");
        }
    }

    public void count(){
        System.out.println("Folyamatban");
    }

    public void closeSheet(){
        scanner.close();
    }

}

