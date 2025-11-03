import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int customerId;
    private int accountNo;
    private String accountHolder;
    private int pinNumber;
    private int balanceAmount;
    private List<Transaction> transactionList;

    public Customer(int customerId, int accountNo, String accountHolder, int pinNumber, int balanceAmount) {
        this.customerId = customerId;
        this.accountNo = accountNo;
        this.accountHolder = accountHolder;
        this.pinNumber = pinNumber;
        this.balanceAmount = balanceAmount;
        transactionList = new ArrayList<>();
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public int getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(int balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public void addTransaction(Transaction transaction) {
        transactionList.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactionList;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", accountNo=" + accountNo +
                ", accountHolder='" + accountHolder + '\'' +
                ", balanceAmount=" + balanceAmount +
                '}';
    }
}
