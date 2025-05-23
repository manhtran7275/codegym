public class Electronic extends Product implements InstallmentSupport {
    private int months;

    public Electronic(String name, double basePrice, int months) {
        super(name, basePrice);
        this.months = months;
    }

    @Override
    public double getShippingFee() {
        return basePrice * 0.05;
    }

    @Override
    public int getInstallmentMonths() {
        return months;
    }

    @Override
    public double getMonthlyInstallment(double finalPrice) {
        return finalPrice / months;
    }
}