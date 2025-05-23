public class PayrollSystem {
    public static void main(String[] args) {
        Employee[] employees = new Employee[5];

        employees[0] = new FullTimeEmployee("Mạnh", 15000000);
        employees[1] = new PartTimeEmployee("Tuấn", 80, 100000);
        employees[2] = new Intern("Hằng", 3000000);
        employees[3] = new FullTimeEmployee("Đức", 16000000);
        employees[4] = new PartTimeEmployee("Huy", 60, 120000);

        double totalSalary = 0.0;

        for (Employee emp : employees) {
            String name = emp.getName();
            String type = emp.getType();
            double salary = emp.calculateSalary();

            System.out.println("Tên NV: " + name);
            System.out.println("Kiểu NV: " + type);
            System.out.println("Lương: " + String.format("%,.0f", salary) + " VNĐ");

            if (emp instanceof Intern) {
                ((Intern) emp).attendTraining();
            }

            if (emp instanceof PartTimeEmployee) {
                System.out.println("Giờ Làm Việc: " + ((PartTimeEmployee) emp).getHoursWorked() + " giờ");
            }

            System.out.println("----------------------------");
            totalSalary += salary;
        }

        System.out.println("Tổng lương phải trả của công ty: " + String.format("%,.0f", totalSalary) + " VNĐ");
    }
}
