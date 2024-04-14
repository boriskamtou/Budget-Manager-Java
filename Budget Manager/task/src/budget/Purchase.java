package budget;

public class Purchase {

    public Purchase(String purchaseName, String purchaseCategory, double purchasePrice) {
        this.purchaseName = purchaseName;
        this.purchaseCategory = purchaseCategory;
        this.purchasePrice = purchasePrice;
    }

    private String purchaseName;
    private String purchaseCategory;
    private double purchasePrice;

    @Override
    public String toString() {
        return "Purchase{" +
                "purchaseName='" + purchaseName + '\'' +
                ", purchaseCategory='" + purchaseCategory + '\'' +
                ", purchasePrice='" + purchasePrice + '\'' +
                '}';
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getPurchaseName() {
        return purchaseName;
    }

    public void setPurchaseName(String purchaseName) {
        this.purchaseName = purchaseName;
    }

    public String getPurchaseCategory() {
        return purchaseCategory;
    }

    public void setPurchaseCategory(String purchaseCategory) {
        this.purchaseCategory = purchaseCategory;
    }

}
