import java.util.*;

public class CustomerHandler {
    private static int transId = 1;
    private final Scanner sc = new Scanner(System.in);
    private final List<Customer> customers = new ArrayList<>();
    private final Map<Integer, Customer> pinMap = new HashMap<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
        pinMap.put(customer.getAccountNo(), customer);
    }

    public void showCustomers() {
        if (customers.isEmpty())
            System.out.println("No customers found.");
        else
            customers.forEach(System.out::println);
    }

    public Customer authenticate(int accNo, int pin) {
        Customer c = pinMap.get(accNo);
        return (c != null && c.getPinNumber() == pin) ? c : null;
    }

    public void showOperations(Customer customer, ATM atm) {
        while (true) {
            System.out.println("\n1. Check Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer Money");
            System.out.println("4. Mini Statement");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> System.out.println("Your balance: Rs." + customer.getBalanceAmount());
                case 2 -> handleWithdrawal(customer, atm);
                case 3 -> handleTransfer(customer);
                case 4 -> showMiniStatement(customer);
                case 5 -> {
                    System.out.println("Thank you for using our ATM!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void handleWithdrawal(Customer customer, ATM atm) {
        System.out.print("Enter amount to withdraw: ");
        int amount = sc.nextInt();

        if (amount < 100 || amount > 10000 || amount % 100 != 0) {
            System.out.println("Invalid amount! Enter between 100–10000 in multiples of 100.");
            return;
        }
        if (customer.getBalanceAmount() < amount) {
            System.out.println("Insufficient account balance!");
            return;
        }
        if (atm.getTotalAmount() < amount) {
            System.out.println("ATM doesn’t have enough cash!");
            return;
        }

        int original = amount;
        int atmTh = atm.getThousands();
        int atmFh = atm.getFiveHundreds();
        int atmH  = atm.getHundreds();

        int useTh = 0, useFh = 0, useH = 0;

        if (original <= 5000) {
            // Limits: 1×1000, 6×500, 10×100
            useTh = Math.min(1, Math.min(original / 1000, atmTh));
            amount -= useTh * 1000;

            useFh = Math.min(amount / 500, Math.min(6, atmFh));
            amount -= useFh * 500;

            useH = Math.min(amount / 100, Math.min(10, atmH));
            amount -= useH * 100;

            // Try to reduce 500s by replacing them with 100s if possible
            while (useFh > 0) {
                int remainderIfReduced = ( (useFh * 500) + (amount) ); // amount is what remains after current picks
                int neededHundredsToReplace = (remainderIfReduced) / 100;
                if (remainderIfReduced % 100 != 0) break; // can't exactly replace
                // how many hundreds ATM has available beyond already allocated useH
                int availableHundreds = atmH - useH;
                if (neededHundredsToReplace <= availableHundreds && neededHundredsToReplace <= 10 - useH) {
                    // replace all current five-hundreds with hundreds
                    useH += neededHundredsToReplace;
                    amount = 0;
                    useFh = 0;
                    break;
                }
                // try reducing one 500 at a time (replace 1x500 with 5x100)
                if (atmH - useH >= 5 && (10 - useH) >= 5) {
                    useFh -= 1;
                    useH += 5;
                    // amount doesn't change because we're converting 500 -> five 100s
                } else break;
            }
        } else {
            // original > 5000: prefer five-hundreds as much as possible (bounded by ATM)
            // Use up to atmFh of 500s to cover remainder after 1000s optionally or use them first
            // Strategy: try many 500s first, then thousands, then hundreds.
            useFh = Math.min(original / 500, atmFh);
            amount -= useFh * 500;

            // fill with thousands next
            useTh = Math.min(amount / 1000, atmTh);
            amount -= useTh * 1000;

            // remaining hundreds
            useH = Math.min(amount / 100, atmH);
            amount -= useH * 100;

            // If still remainder, try alternative: use fewer 500s to allow thousands usage
            if (amount != 0) {
                // attempt to free some 500s to use thousands (if possible)
                int tempUseFh = useFh;
                int tempAmount = amount;
                while (tempUseFh > 0) {
                    // convert one 500 back to amount, try to use a 1000 instead
                    tempUseFh--;
                    tempAmount += 500;
                    int tryTh = Math.min(tempAmount / 1000, atmTh - useTh);
                    int remAfterTh = tempAmount - tryTh * 1000;
                    int tryH = Math.min(remAfterTh / 100, atmH - useH);
                    if (remAfterTh - tryH * 100 == 0) {
                        // accept this redistribution
                        useFh = tempUseFh;
                        useTh += tryTh;
                        useH += tryH;
                        amount = 0;
                        break;
                    }
                }
            }
        }

        // Final check
        if (amount != 0) {
            System.out.println("ATM cannot dispense the requested denomination with its current note counts.");
            return;
        }

        // Update ATM and customer
        atm.setThousands(atmTh - useTh);
        atm.setFiveHundreds(atmFh - useFh);
        atm.setHundreds(atmH - useH);
        atm.decrease(original);
        customer.setBalanceAmount(customer.getBalanceAmount() - original);

        Transaction t = new Transaction(transId++, "Cash withdrawn", "Debit", original, "ATM");
        customer.addTransaction(t);

        System.out.printf("Withdrawal successful! Dispensed: %d×1000, %d×500, %d×100%n", useTh, useFh, useH);
        atm.showATMBalance();
    }


    private void handleTransfer(Customer sender) {
        System.out.print("Enter receiver account number: ");
        int accNo = sc.nextInt();

        if (!pinMap.containsKey(accNo)) {
            System.out.println("Invalid account number!");
            return;
        }

        Customer receiver = pinMap.get(accNo);
        System.out.print("Enter transfer amount: ");
        int amount = sc.nextInt();

        if (amount <= 0 || amount > sender.getBalanceAmount()) {
            System.out.println("Insufficient funds!");
            return;
        }

        sender.setBalanceAmount(sender.getBalanceAmount() - amount);
        receiver.setBalanceAmount(receiver.getBalanceAmount() + amount);

        Transaction t1 = new Transaction(transId++, "Transfer to " + receiver.getAccountNo(), "Debit", amount, "Fund Transfer");
        Transaction t2 = new Transaction(transId++, "Transfer from " + sender.getAccountNo(), "Credit", amount, "Fund Transfer");

        sender.addTransaction(t1);
        receiver.addTransaction(t2);

        System.out.println("Transfer successful!");
    }

    private void showMiniStatement(Customer customer) {
        List<Transaction> transactions = customer.getTransactions();
        if (transactions.isEmpty())
            System.out.println("No transactions yet.");
        else
            transactions.forEach(System.out::println);
    }
}
