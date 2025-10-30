import java.util.ArrayList;
import java.util.List;

public class Customer {
    int customer_id;
    int account_no;
    String account_holder;
    int pin_number;
    int balance_amount;
    List<Transaction> transactionList;
    public Customer(int customer_id, int account_no, String account_holder, int pin_number, int balance_amount) {
        this.customer_id = customer_id;
        this.account_no = account_no;
        this.account_holder = account_holder;
        this.pin_number = pin_number;
        this.balance_amount = balance_amount;
        transactionList=new ArrayList<>();
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getAccount_no() {
        return account_no;
    }

    public void setAccount_no(int account_no) {
        this.account_no = account_no;
    }

    public String getAccount_holder() {
        return account_holder;
    }

    public void setAccount_holder(String account_holder) {
        this.account_holder = account_holder;
    }

    public int getPin_number() {
        return pin_number;
    }

    public void setPin_number(int pin_number) {
        this.pin_number = pin_number;
    }

    public int getBalance_amount() {
        return balance_amount;
    }
    public void setBalance_amount(int balance_amount) {
        this.balance_amount = balance_amount;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customer_id +
                ", account_no=" + account_no +
                ", account_holder='" + account_holder + '\'' +
                ", pin_number=" + pin_number +
                ", balance_amount=" + balance_amount +
                '}';
    }
    public void addTransactionList(Transaction transaction)
    {
        transactionList.add(transaction);
    }
}
