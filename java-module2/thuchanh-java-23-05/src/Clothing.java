public class Clothing extends Product {
    public Clothing(String name, double basePrice) {
        super(name, basePrice);
    }

    @Override
    public double getShippingFee() {
        return 15000;
    }
}