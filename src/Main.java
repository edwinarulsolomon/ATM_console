import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ATM atm = new ATM();
        CustomerHandler handler = new CustomerHandler();

        Customer c1 = new Customer(1, 101, "Edwin", 2020, 10000);
        Customer c2 = new Customer(2, 102, "Ajay", 2021, 10000);

        handler.addCustomer(c1);
        handler.addCustomer(c2);

        while (true) {
            System.out.println("\n=== ATM System ===");
            System.out.println("1. Load Cash to ATM");
            System.out.println("2. Show Customer Details");
            System.out.println("3. ATM Operations");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter number of Rs.1,00,000 loads: ");
                    int n = sc.nextInt();
                    atm.loadCash(n);
                }
                case 2 -> handler.showCustomers();

                case 3 -> {
                    System.out.print("Enter Account Number: ");
                    int accNo = sc.nextInt();
                    System.out.print("Enter PIN: ");
                    int pin = sc.nextInt();
                    Customer customer=handler.authenticate(accNo, pin);
                    if (customer != null) {
                        handler.showOperations(customer,atm);
                    } else {
                        System.out.println("Invalid credentials!");
                    }
                }
                case 4 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }
}
