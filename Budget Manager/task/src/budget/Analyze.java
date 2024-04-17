package budget;

import java.util.*;

// Implement the strategy pattern

class PurchaseComparator implements Comparator<Purchase> {
    @Override
    public int compare(Purchase o1, Purchase o2) {
        return Double.compare(o2.getPurchasePrice(), o1.getPurchasePrice()); // Descending order
    }
}

class PurchaseCategoryComparator implements Comparator<PurchaseCategory> {
    @Override
    public int compare(PurchaseCategory o1, PurchaseCategory o2) {
        return Double.compare(o2.getTotalPrice(), o1.getTotalPrice()); // Descending order
    }
}


public class Analyze {
    private final IAnalyze analyze;

    public Analyze(IAnalyze analyze) {
        this.analyze = analyze;
    }

    public void sort(List<Purchase> purchases) {
        analyze.sort(purchases);
    }
}

interface IAnalyze {
    void sort(List<Purchase> purchases);
}

class SortAllPurchases implements IAnalyze {


    @Override
    public void sort(List<Purchase> purchases) {
        double sum = 0;
        System.out.println();
        purchases.sort(new PurchaseComparator());
        if (!purchases.isEmpty()) {
            System.out.println("All");
            for (Purchase purchase : purchases) {
                System.out.println(purchase.getPurchaseName() + " " + String.format("$%.2f", purchase.getPurchasePrice()));
                sum += purchase.getPurchasePrice();
            }
            System.out.printf("Total sum: $%.2f\n", sum);
        } else {
            System.out.println("The purchase list is empty!");
        }
    }
}

class SortPurchasesByType implements IAnalyze {

    private double totalPrice = 0;

    private String getTotalPriceByCategory(List<Purchase> purchases, String category) {
        double sum = 0.00;

        List<Purchase> listToDisplay = new ArrayList<>();

        for (Purchase purchase : purchases) {
            if (purchase.getPurchaseCategory().equals(category)) {
                listToDisplay.add(purchase);
            }
        }
        if (!listToDisplay.isEmpty()) {
            for (Purchase purchase : listToDisplay) {
                sum += purchase.getPurchasePrice();
            }
            totalPrice += sum;
            return String.format("$%.2f", sum);
        } else {
            return String.format("$%.0f", sum);
        }
    }

    @Override
    public void sort(List<Purchase> purchases) {
        System.out.println();
        List<PurchaseCategory> maps = new ArrayList<>(List.of(
                new PurchaseCategory("Food", Double.parseDouble(getTotalPriceByCategory(purchases, "Food").split("\\$")[1].replace(",", "."))),
                new PurchaseCategory("Entertainment", Double.parseDouble(getTotalPriceByCategory(purchases, "Entertainment").split("\\$")[1].replace(",", "."))),
                new PurchaseCategory("Clothes", Double.parseDouble(getTotalPriceByCategory(purchases, "Clothes").split("\\$")[1].replace(",", "."))),
                new PurchaseCategory("Other", Double.parseDouble(getTotalPriceByCategory(purchases, "Other").split("\\$")[1].replace(",", ".")))
        ));

        maps.sort(new PurchaseCategoryComparator());

        maps.forEach(purchaseCategory -> System.out.println(purchaseCategory.getCategory() + " - " + String.format("$%.2f", purchaseCategory.getTotalPrice())));

        System.out.println("Total sum: " + (totalPrice == 0 ? String.format("$%.0f", totalPrice) : String.format("$%.2f", totalPrice)));
    }
}

class SortCertainPurchases implements IAnalyze {

    // Display purchases by category
    private static void displayPurchasesByCategory(String category, List<Purchase> purchases) {

        double sum = 0;
        List<Purchase> listToDisplay = new ArrayList<>();

        System.out.println();
        System.out.println(category + ":");
        if (!purchases.isEmpty()) {
            for (Purchase purchase : purchases) {
                if (purchase.getPurchaseCategory().equals(category)) {
                    listToDisplay.add(purchase);
                }
            }
        } else {
            System.out.println("The purchase list is empty!");
        }

        listToDisplay.sort(new PurchaseComparator());
        listToDisplay.forEach(purchase -> System.out.println(purchase.getPurchaseName() + " " +  String.format("$%.2f", purchase.getPurchasePrice())));
    }

    @Override
    public void sort(List<Purchase> purchases) {
        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println("Choose the type of purchase");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");

        String userChoice = sc.nextLine();

        switch (userChoice) {
            case "1" -> displayPurchasesByCategory("Food", purchases);
            case "2" -> displayPurchasesByCategory("Clothes", purchases);
            case "3" -> displayPurchasesByCategory("Entertainment", purchases);
            case "4" -> displayPurchasesByCategory("Other", purchases);
        }
    }
}