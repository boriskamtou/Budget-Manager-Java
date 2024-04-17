package budget;

public class PurchaseCategory {
    public PurchaseCategory(String category, double totalPrice) {
        this.category = category;
        this.totalPrice = totalPrice;
    }
    private String category;
    private double totalPrice;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "PurchaseCategory{" +
                "category='" + category + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
