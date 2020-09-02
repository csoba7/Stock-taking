package com.company;

import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
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
            scannerFile.nextLine();
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
        if (fw != null) {
            PrintWriter pw = new PrintWriter(fw);
            pw.println("//Termék Ár Nyitókészlet Mértékegység Hűtő");
            for (int i = 0; i < product.size(); i++) {
                pw.println(product.get(i) + " " + price.get(i) + " " + startingQuantity.get(i) + " " + unit.get(i) + " " + fridge.get(i));
            }
            pw.close();
        } else
            System.out.println("Nem sikerült beolvasni a fájlt!");
    }

    private int whichFridge() {
        System.out.print("Melyik hűtő termékei legyen listázva? ");
        int fridgeTmp = scanner.nextInt();
        scanner.nextLine();
        if (!fridge.contains(fridgeTmp) && fridgeTmp != 0) return -1;
        return fridgeTmp;
    }

    public void list() {
        int fridgeTmp = whichFridge();
        if (fridgeTmp != -1) {
            System.out.println("Termék\t\t\t\t\tÁr\t\t\t\t\t\tNyitókészlet\t\t\t\t\tMaradvány\t\t\t\t\t\tFogyás\t\t\t\t\t\t\tEladási érték");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------");
            for (int i = 0; i < product.size(); i++) {
                if (fridgeTmp == fridge.get(i) || fridgeTmp == 0) {
                    if (product.get(i).length() < 4)
                        System.out.print(product.get(i) + "\t\t\t\t\t\t");
                    else if (product.get(i).length() < 8)
                        System.out.print(product.get(i) + "\t\t\t\t\t");
                    else if (product.get(i).length() < 12)
                        System.out.print(product.get(i) + "\t\t\t\t");
                    else
                        System.out.print(product.get(i) + "\t\t\t");
                    if (price.get(i).length() < 4) {
                        System.out.print(price.get(i) + "\t\t\t\t\t\t" + startingQuantity.get(i) + unit.get(i) + "\t\t\t\t\t\t\t" + currentQuantity.get(i) + unit.get(i));
                    } else if (price.get(i).length() < 8) {
                        System.out.print(price.get(i) + "\t\t\t\t\t" + startingQuantity.get(i) + unit.get(i) + "\t\t\t\t\t\t\t" + currentQuantity.get(i) + unit.get(i));
                    } else if (product.get(i).length() < 15) {
                        System.out.print(price.get(i) + "\t\t\t\t" + startingQuantity.get(i) + unit.get(i) + "\t\t\t\t\t\t\t" + currentQuantity.get(i) + unit.get(i));
                    }
                    if (getDifference(i) < 10) {
                        System.out.println("\t\t\t\t\t\t\t" + getDifference(i) + "\t\t\t\t\t\t\t\t" + formatNumber(getDifference(i) * Double.parseDouble(price.get(i))) + "Ft");
                    } else if (getDifference(i) < 100000) {
                        System.out.println("\t\t\t\t\t\t\t" + getDifference(i) + "\t\t\t\t\t\t\t" + formatNumber(getDifference(i) * Double.parseDouble(price.get(i))) + "Ft");
                    } else {
                        System.out.println("\t\t\t\t\t\t\t" + getDifference(i) + "\t\t\t\t\t\t" + formatNumber(getDifference(i) * Double.parseDouble(price.get(i))) + "Ft");
                    }
                }
            }
        } else
            System.out.println("Nincs ilyen hűtő!");
    }

    private double getDifference(int i) {
        return Double.parseDouble(startingQuantity.get(i)) - currentQuantity.get(i);
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
        int tmp;
        switch (fridge) {
            case 1:
                for (int i = 0; i < fridge1.size(); i++)
                    System.out.println(i + 1 + " - " + fridge1.get(i));
                tmp = askWhichProduct();
                if (tmp < 0 || tmp >= fridge1.size()) {
                    return -1;
                }
                return product.indexOf(fridge1.get(tmp));
            case 2:
                for (int i = 0; i < fridge2.size(); i++)
                    System.out.println(i + 1 + " - " + fridge2.get(i));
                tmp = askWhichProduct();
                if (tmp < 0 || tmp >= fridge2.size()) {
                    return -1;
                }
                return product.indexOf(fridge2.get(tmp));
            case 3:
                for (int i = 0; i < fridge3.size(); i++)
                    System.out.println(i + 1 + " - " + fridge3.get(i));
                tmp = askWhichProduct();
                if (tmp < 0 || tmp >= fridge3.size()) {
                    return -1;
                }
                return product.indexOf(fridge3.get(tmp));
            default:
                System.out.println("Nincs ilyen hűtő!");
                return -1;
        }
    }

    public void addForStartingProduct() {
        int fridgeTmp = whichFridge();
        if (fridgeTmp != -1 && fridgeTmp != 0) {
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
        } else
            System.out.println("Nincs ilyen hűtő!");
    }

    public void addForCurrentProduct() {
        int fridgeTmp = whichFridge();
        if (fridgeTmp != -1 && fridgeTmp != 0) {
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
        } else {
            System.out.println("Nincs ilyen hűtő!");
        }
    }


    private String formatNumber(double number) {
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setGroupingSeparator(' ');
        formatter.setDecimalFormatSymbols(symbols);
        return formatter.format(number);
    }

    public void count() {
        double sum = 0;
        for (int i = 0; i < product.size(); i++) {
            sum += (Double.parseDouble(startingQuantity.get(i)) - currentQuantity.get(i)) * Double.parseDouble(price.get(i));
        }
        System.out.println("A standolás végösszege: " + formatNumber(sum) + "Ft");
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
            if (Integer.parseInt(fridgeTmp) == 1) fridge1.add(name);
            else if (Integer.parseInt(fridgeTmp) == 2) fridge2.add(name);
            else if (Integer.parseInt(fridgeTmp) == 3) fridge3.add(name);
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

    public void changeStartingQuantity() {
        System.out.print("Biztosan szeretnéd, hogy új nyitókészlet legyen?[Igen] ");
        if (scanner.nextLine().equals("Igen")) {
            for (int i = 0; i < product.size(); i++) {
                startingQuantity.set(i, Double.toString(currentQuantity.get(i)));
            }
            toFile();
            System.out.println("Új lett a nyitókészlet!");
        } else
            System.out.println("Nem lett importálva az új nyitókészlet!");
    }
}