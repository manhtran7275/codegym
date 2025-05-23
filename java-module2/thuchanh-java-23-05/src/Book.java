public class Book extends Product implements Discountable {
    private double discountPercent;

    public Book(String name, double basePrice, double discountPercent) {
        super(name, basePrice);
        this.discountPercent = discountPercent;
    }

    @Override
    public double getShippingFee() {
        return 0;
    }

    @Override
    public double getDiscountPercent() {
        return discountPercent;
    }

    public double getDiscountedPrice() {
        return getFinalPrice() * (1 - discountPercent / 100);
    }
}