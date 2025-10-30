import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class CustomerHandler {
    static int trams_id=1;
    Scanner sc=new Scanner(System.in);
    Customer customer;
    ATM atm;
    List<Customer> customers=new ArrayList<>();
    HashMap<Integer,Customer> pinmap=new HashMap<>();
    public void addCustomer(Customer customer)
    {
        this.customer=customer;
        customers.add(customer);
        pinmap.put(customer.getAccount_no(),customer);
    }

    public void showCustomers()
    {
        if(customers.size()==0) System.out.println("No Customers found");
        else
        {
            for(Customer customer:customers)
            {
                System.out.println(customer);
            }
        }
    }

    public boolean authenticate(int accNo, int pin) {
        if(!pinmap.containsKey(accNo)) return false;
        int pinnumber=pinmap.get(accNo).pin_number;
        return pinnumber==pin;
    }
    public String getTransaction(String sourse,int to,int from,int amount)
    {
        if(sourse.equals("ATM"))
        {
            return "Debited "+amount+" from "+sourse;
        }
        else if(to!=-1)
        {
            return "Fund Transfer to Acc "+to;
        }
        else {
            return "Fund Transfer from Acc "+from;
        }
    }
    public void showOperations(ATM atm)
    {
        this.atm=atm;
        while(true) {
            System.out.println("1.CheckBalance : ");
            System.out.println("2.Withdraw : ");
            System.out.println("3.Transfer Money : ");
            System.out.println("4.Mini Statement : ");
            int choice=sc.nextInt();
            switch (choice)
            {
                case 1->
                {
                    System.out.println(customer.getBalance_amount());
                }
                case 2->
                {
                    System.out.println("Enter the amount withdraw");
                    int amount=sc.nextInt();
                    if(checkWthdraw(amount))
                    {
                        atm.decrease(amount);
                        customer.balance_amount -= amount;
                        String message=getTransaction("ATM",-1,-1,amount);
                        Transaction transaction=new Transaction(trams_id++,message,"Debit",amount,"ATM");
                        customer.addTransactionList(transaction);
                        }
                    else {
                       if(customer.balance_amount<amount) System.out.println("Not enought money in your account");
                       else System.out.println("Not enough mmoney in the ATM ");
                    }
                }
                case 3->
                {
                    System.out.println("Enter the Receiver Account Number :");
                    int acc_no=sc.nextInt();
                    sc.nextLine();
                    if(pinmap.containsKey(acc_no)) {
                        Customer reciever=pinmap.get(acc_no);
                        System.out.println("Enter the Amount :");
                        int amount = sc.nextInt();
                        sc.nextLine();
                        if(amount<=customer.balance_amount) {
                            String sen = getTransaction("Transfer", reciever.getAccount_no(), -1, amount);
                            int cus_amount=customer.getBalance_amount();
                            System.out.println(cus_amount);
                            cus_amount-=amount;
                            customer.setBalance_amount(cus_amount);
                            Transaction t1 = new Transaction(trams_id++, sen, "Debit", amount, "Fund Transfer");
                            customer.addTransactionList(t1);
                        }
                        else System.out.println("Insufficient Amount");
                    }
                    else System.out.println("Invalid Account Number !");

                }
                case 4->
                {
                    List<Transaction> cur=customer.transactionList;
                    for(Transaction t:cur) System.out.println(t);
                }
            }
        }

    }
    public boolean checkWthdraw(int amount)
    {
        if(amount>10000 && amount<100)
        {
            System.out.println("Amount should be between 100 and 10000");
            return false;
        }
        else if(customer.balance_amount <amount && atm.getTot_amount() <amount)
        {
            if(customer.balance_amount<amount) System.out.println("Not enought money in your account");
            else System.out.println("Not enough money in the ATM ");
        }
        else if(amount%100!=0)
        {
            System.out.println("Amount should be in the multiples of 100");
            return false;
        }
        int thousand=0;
        int fivehund=0;
        int onehund=0;
        if(amount<=5000)
        {
            while(amount!=0)
            {
                if(amount>=1000 && thousand!=1)
                {
                    amount-=1000;
                    thousand++;
                }
                else if(amount<1000 && fivehund!=6)
                {
                    amount-=500;
                    fivehund++;
                }
                else {
                    amount-=100;
                    onehund++;
                }
            }

        }
        else {
            while(amount!=0)
            {
                if(amount>5000 && thousand!=3)
                {
                    amount-=1000;
                    thousand++;
                }
                else if(amount<=5000 && fivehund!=2)
                {
                    amount-=500;
                    fivehund++;
                }
                else {
                    amount-=100;
                    onehund++;
                }
            }

        }
        int thous=atm.getThousands()-thousand;
        int five=atm.getFivehundread()-fivehund;
        int one=atm.getHundreads()-onehund;
        atm.setThousands(thous);
        atm.setFivehundread(five);
        atm.setHundreads(one);
        System.out.println(atm);
        return true;
    }
}
