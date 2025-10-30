import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ATM atm=new ATM();
        CustomerHandler customerHandler=new CustomerHandler();
        Customer c1=new Customer(1,101,"Edwin",2020,10000);
        Customer c2=new Customer(2,102,"Ajay",2021,10000);
        customerHandler.addCustomer(c1);
        customerHandler.addCustomer(c2);
        while(true)
        {
            System.out.println("1.Load Cash to ATM :");
            System.out.println("2.Show Customer Details :");
            System.out.println("3.Show ATM Operations :");
            int choice=sc.nextInt();
            sc.nextLine();
            switch (choice)
            {
                case 1->
                {
                    System.out.println("Enter the n Multiples of Rs 1,00,000 :");
                    int n=sc.nextInt();
                    atm.setTot_amount(n);
                }
                case 2->
                {
                    customerHandler.showCustomers();
                }
                case 3->
                {
                    System.out.println("Enter your Account Number");
                    int acc_no=sc.nextInt();
                    System.out.println("Enter your Pin Number");
                    int pin=sc.nextInt();
                    if(customerHandler.authenticate(acc_no,pin))
                    {
                        customerHandler.showOperations(atm);
                    }
                    else {
                        System.out.println("Wrong Credentials !!!");
                    }
                }
                case 4->
                {
                    break;
                }
            }
        }
    }
}