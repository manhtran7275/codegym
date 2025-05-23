import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Book("Java Basics Course", 100000, 10));
        products.add(new Electronic("Smartphone", 5000000, 12));
        products.add(new Clothing("T-Shirt", 200000));

        for (Product p : products) {
            System.out.println("Tên sản phẩm: " + p.getName());
            System.out.println("Giá gốc: " + p.getBasePrice());
            System.out.println("Phí vận chuyển: " + p.getShippingFee());
            System.out.println("Giá sau vận chuyển: " + p.getFinalPrice());

            if (p instanceof Discountable) {
                Discountable d = (Discountable) p;
                double discountedPrice = ((Book) p).getDiscountedPrice();
                System.out.println("Chiết khấu: " + d.getDiscountPercent() + "%");
                System.out.println("Giá sau chiết khấu: " + discountedPrice);
            }

            if (p instanceof InstallmentSupport) {
                InstallmentSupport i = (InstallmentSupport) p;
                double monthly = i.getMonthlyInstallment(p.getFinalPrice());
                System.out.println("Trả góp: " + i.getInstallmentMonths() + " tháng");
                System.out.println("Mỗi tháng trả: " + monthly);
            }

            System.out.println("--------------------------");
        }
    }
}
