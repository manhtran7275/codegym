public interface InstallmentSupport {
    int getInstallmentMonths();
    double getMonthlyInstallment(double finalPrice);
}