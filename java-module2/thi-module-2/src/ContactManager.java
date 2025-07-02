import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactManager {
    private List<Contact> contacts;
    private Scanner scanner;
    private static final String CSV_FILE_PATH = "thi-module-2/data/contacts.csv";

    public ContactManager() {
        contacts = new ArrayList<>();
        scanner = new Scanner(System.in);
        createDataFileIfNotExists();
    }

    private void createDataFileIfNotExists() {
        File dataDir = new File("data");
        File csvFile = new File(CSV_FILE_PATH);

        try {
            if (!dataDir.exists()) {
                dataDir.mkdir();
            }
            if (!csvFile.exists()) {
                csvFile.createNewFile();
                try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
                    writer.println("Phone Number,Group,Name,Gender,Address,Date of Birth,Email");
                    writer.println("0123456789,Friends,Nguyen Van A,Male,Ha Noi,1990-01-01,nguyenvana@gmail.com");
                    writer.println("0987654321,Family,Tran Thi B,Female,Ho Chi Minh,1992-05-15,tranthib@gmail.com");
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi tạo file: " + e.getMessage());
        }
    }

    public void showContacts() {
        if (contacts.isEmpty()) {
            System.out.println("Danh bạ trống!");
            return;
        }

        int count = 0;
        for (Contact contact : contacts) {
            System.out.println(contact);
            count++;
            if (count % 5 == 0 && count < contacts.size()) {
                System.out.println("Nhấn Enter để xem tiếp...");
                scanner.nextLine();
            }
        }
    }

    public void addContact() {
        System.out.println("Nhập thông tin liên hệ mới:");

        String phoneNumber;
        do {
            System.out.print("Số điện thoại: ");
            phoneNumber = scanner.nextLine();
            if (!validatePhoneNumber(phoneNumber)) {
                System.out.println("Số điện thoại không hợp lệ! Vui lòng nhập 10 số.");
            }
        } while (!validatePhoneNumber(phoneNumber));

        System.out.print("Nhóm: ");
        String group = scanner.nextLine();

        System.out.print("Họ tên: ");
        String name = scanner.nextLine();

        System.out.print("Giới tính: ");
        String gender = scanner.nextLine();

        System.out.print("Địa chỉ: ");
        String address = scanner.nextLine();

        System.out.print("Ngày sinh: ");
        String dateOfBirth = scanner.nextLine();

        String email;
        do {
            System.out.print("Email: ");
            email = scanner.nextLine();
            if (!validateEmail(email)) {
                System.out.println("Email không hợp lệ!");
            }
        } while (!validateEmail(email));

        Contact contact = new Contact(phoneNumber, group, name, gender, address, dateOfBirth, email);
        contacts.add(contact);
        System.out.println("Thêm liên hệ thành công!");
    }

    public void updateContact() {
        System.out.print("Nhập số điện thoại cần cập nhật: ");
        String phoneNumber = scanner.nextLine();

        Contact contact = findContactByPhone(phoneNumber);
        if (contact == null) {
            System.out.println("Không tìm thấy số điện thoại trong danh bạ!");
            return;
        }

        System.out.println("Nhập thông tin mới (Enter để giữ nguyên):");

        System.out.print("Nhóm (" + contact.getGroup() + "): ");
        String input = scanner.nextLine();
        if (!input.isEmpty()) {
            contact.setGroup(input);
        }

        System.out.print("Họ tên (" + contact.getName() + "): ");
        input = scanner.nextLine();
        if (!input.isEmpty()) {
            contact.setName(input);
        }

        System.out.print("Giới tính (" + contact.getGender() + "): ");
        input = scanner.nextLine();
        if (!input.isEmpty()) {
            contact.setGender(input);
        }

        System.out.print("Địa chỉ (" + contact.getAddress() + "): ");
        input = scanner.nextLine();
        if (!input.isEmpty()) {
            contact.setAddress(input);
        }

        System.out.print("Ngày sinh (" + contact.getDateOfBirth() + "): ");
        input = scanner.nextLine();
        if (!input.isEmpty()) {
            contact.setDateOfBirth(input);
        }

        System.out.print("Email (" + contact.getEmail() + "): ");
        input = scanner.nextLine();
        if (!input.isEmpty()) {
            if (validateEmail(input)) {
                contact.setEmail(input);
            } else {
                System.out.println("Email không hợp lệ, giữ nguyên email cũ!");
            }
        }

        System.out.println("Cập nhật thành công!");
    }

    public void deleteContact() {
        System.out.print("Nhập số điện thoại cần xóa: ");
        String phoneNumber = scanner.nextLine();

        Contact contact = findContactByPhone(phoneNumber);
        if (contact == null) {
            System.out.println("Không tìm thấy số điện thoại trong danh bạ!");
            return;
        }

        System.out.print("Bạn có chắc muốn xóa liên hệ này? (Y/N): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("Y")) {
            contacts.remove(contact);
            System.out.println("Xóa thành công!");
        }
    }

    public void searchContacts() {
        System.out.println("Tìm kiếm theo:");
        System.out.println("1. Số điện thoại");
        System.out.println("2. Họ tên");
        System.out.print("Chọn: ");
        int choice = Integer.parseInt(scanner.nextLine());

        System.out.print("Nhập từ khóa tìm kiếm: ");
        String keyword = scanner.nextLine().toLowerCase();

        boolean found = false;
        for (Contact contact : contacts) {
            if ((choice == 1 && contact.getPhoneNumber().contains(keyword)) ||
                    (choice == 2 && contact.getName().toLowerCase().contains(keyword))) {
                System.out.println(contact);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy kết quả nào!");
        }
    }

    public void readFromFile() {
        System.out.print("Đọc từ file sẽ xóa dữ liệu hiện tại. Tiếp tục? (Y/N): ");
        if (!scanner.nextLine().equalsIgnoreCase("Y")) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            contacts.clear();
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 7) {
                    contacts.add(new Contact(
                            values[0].trim(),
                            values[1].trim(),
                            values[2].trim(),
                            values[3].trim(),
                            values[4].trim(),
                            values[5].trim(),
                            values[6].trim()
                    ));
                }
            }
            System.out.println("Đọc file thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }

    public void writeToFile() {
        System.out.print("Ghi file sẽ xóa dữ liệu cũ trong file. Tiếp tục? (Y/N): ");
        if (!scanner.nextLine().equalsIgnoreCase("Y")) {
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE_PATH))) {
            writer.println("Phone Number,Group,Name,Gender,Address,Date of Birth,Email");

            for (Contact contact : contacts) {
                writer.printf("%s,%s,%s,%s,%s,%s,%s\n",
                        contact.getPhoneNumber(),
                        contact.getGroup(),
                        contact.getName(),
                        contact.getGender(),
                        contact.getAddress(),
                        contact.getDateOfBirth(),
                        contact.getEmail()
                );
            }
            System.out.println("Ghi file thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }

    private Contact findContactByPhone(String phoneNumber) {
        for (Contact contact : contacts) {
            if (contact.getPhoneNumber().equals(phoneNumber)) {
                return contact;
            }
        }
        return null;
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{10}");
    }

    private boolean validateEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}