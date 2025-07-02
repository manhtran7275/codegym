import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ContactManager manager = new ContactManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ ===");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Tìm kiếm");
            System.out.println("6. Đọc từ file");
            System.out.println("7. Ghi vào file");
            System.out.println("8. Thoát");

            System.out.print("Chọn chức năng: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số!");
                continue;
            }

            switch (choice) {
                case 1:
                    manager.showContacts();
                    break;
                case 2:
                    manager.addContact();
                    break;
                case 3:
                    manager.updateContact();
                    break;
                case 4:
                    manager.deleteContact();
                    break;
                case 5:
                    manager.searchContacts();
                    break;
                case 6:
                    manager.readFromFile();
                    break;
                case 7:
                    manager.writeToFile();
                    break;
                case 8:
                    System.out.println("Tạm biệt!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}