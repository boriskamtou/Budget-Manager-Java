package budget;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static double balance = 0.00;
    static List<Purchase> purchases = new ArrayList<>();

    static List<String> purchasesCategories = List.of("Food", "Clothes", "Entertainment", "Other", "Back");

   static DecimalFormat dec = new DecimalFormat("#.00");
    // Call this function when user enter (1)
    private static void addIncome() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter income:");
        if (scanner.hasNextDouble() || scanner.hasNextInt()) {
            String income = scanner.nextLine();
            balance = balance + Double.parseDouble(income);
            System.out.println("Income was added!");
        }
    }

    // Call this function when user enter (4)
    private static void showBalance() {
        System.out.println();
        if (balance < 0) {
            System.out.printf("Balance: $%.2f\n", 0.00);
        } else {
            System.out.printf("Balance: $%.2f\n", balance);
        }
    }

    // Call this function when user enter (2)
    private static void addPurchase() {
        try {
            while (true) {
                Scanner scanner = new Scanner(System.in);
                System.out.println();
                System.out.println("Choose the type of purchase");
                for (int i = 0; i < purchasesCategories.size(); i++) {
                    System.out.printf("%d) %s%n", i + 1, purchasesCategories.get(i));
                }
                String purchaseCategory = scanner.nextLine();
                switch (purchaseCategory) {
                    case "1" -> purchaseCategory = "Food";
                    case "2" -> purchaseCategory = "Clothes";
                    case "3" -> purchaseCategory = "Entertainment";
                    case "4" -> purchaseCategory = "Other";
                    case "5" -> {
                        return;
                    }
                }

                System.out.println();

                System.out.println("Enter purchase name:");
                String purchaseName = scanner.nextLine();

                System.out.println("Enter its price:");
                String purchasePrice = scanner.nextLine();

                // update income
                balance = balance - Double.parseDouble(purchasePrice);

                Purchase purchase = new Purchase(purchaseName, purchaseCategory, Double.parseDouble(purchasePrice));
                purchases.add(purchase);

                System.out.println("Purchase was added!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Display purchases by category
    private static void displayPurchasesByCategory(String category) {
        System.out.println();

        double sum = 0;

        List<Purchase> listToDisplay = new ArrayList<>();

        if (!category.equals("All")) {
            for (Purchase purchase : purchases) {
                if (purchase.getPurchaseCategory().equals(category)) {
                    listToDisplay.add(purchase);
                }
            }
        } else {
            listToDisplay.addAll(purchases);
        }

        System.out.println(category + ":");
        if (!listToDisplay.isEmpty()) {
            for (Purchase purchase : listToDisplay) {
                System.out.println(purchase.getPurchaseName() + " " + String.format("$%.2f", purchase.getPurchasePrice()));
                sum += purchase.getPurchasePrice();
            }
            System.out.printf("Total sum: $%.2f\n", sum);
        } else {
            System.out.println("The purchase list is empty!");
        }
    }

    // Display purchases type
    private static void displayPurchaseType() {
        System.out.println();
        System.out.println("Choose the type of purchases");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
        System.out.println("5) All");
        System.out.println("6) Back");
    }

    // Call this function when user enter (3)
    private static void showPurchaseList() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayPurchaseType();
            String category = scanner.nextLine();
            switch (category) {
                case "1" -> displayPurchasesByCategory("Food");
                case "2" -> displayPurchasesByCategory("Clothes");
                case "3" -> displayPurchasesByCategory("Entertainment");
                case "4" -> displayPurchasesByCategory("Other");
                case "5" -> displayPurchasesByCategory("All");
                case "6" -> {
                    return;
                }
            }
        }

    }


    // Display menu actions
    private static void displayAction() {
        System.out.println();
        System.out.println("Choose your action:");
        System.out.println("1) Add income");
        System.out.println("2) Add purchase");
        System.out.println("3) Show list of purchases");
        System.out.println("4) Balance");
        System.out.println("5) Save");
        System.out.println("6) Load");
        System.out.println("0) Exit");
    }


    // This function is used to save purchases into
    // purchases.txt file - (Write into a file)
    private static void savePurchases() {
        System.out.println();

        File savePurchasesFile = new File("purchases.txt");
        // Save purchases
        try (PrintWriter printWriter = new PrintWriter(savePurchasesFile)) {
            printWriter.printf("Balance=%.2f\n", balance);
            for (Purchase purchase : purchases) {
                printWriter.println(purchase.getPurchaseName() + "," + purchase.getPurchaseCategory() + "," + purchase.getPurchasePrice());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Purchases were saved!");
    }


    // Read data into purchases file and add them into purchases list
    private static void loadPurchases() {
        System.out.println();

        File savePurchasesFile = new File("purchases.txt");

        try (Scanner scanner = new Scanner(savePurchasesFile)) {
            String balanceLine = scanner.nextLine();
            if (balanceLine.contains("=")) {
                String balanceInString = balanceLine.split("=")[1];
                balance = balanceInString.contains(",") ? Double.parseDouble(balanceInString.replace(",", ".")) : Double.parseDouble(balanceLine.split("=")[1]);
            }
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] p = line.trim().split(",");
                Purchase purchase = new Purchase(p[0], p[1], Double.parseDouble(p[2]));
                purchases.add(purchase);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Purchases were loaded!");
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userChoice;

        while (true) {
            displayAction();
            userChoice = scanner.nextLine();
            switch (userChoice) {
                case "1" -> addIncome();
                case "2" -> addPurchase();
                case "3" -> showPurchaseList();
                case "4" -> showBalance();
                case "5" -> savePurchases();
                case "6" -> loadPurchases();
                case "0" -> {
                    System.out.println();
                    System.out.println("Bye!");
                    scanner.close();
                    return;
                }
            }
        }
    }
}
