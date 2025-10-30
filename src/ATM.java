public class ATM {
    private int tot_amount=0;
    int thousands=20;
    int hundreads=100;
    int fivehundread=300;

    public int getHundreads() {
        return hundreads;
    }

    public void setHundreads(int hundreads) {
        this.hundreads = hundreads;
    }

    public int getThousands() {
        return thousands;
    }

    public void setThousands(int thousands) {
        this.thousands = thousands;
    }

    public int getFivehundread() {
        return fivehundread;
    }

    public void setFivehundread(int fivehundread) {
        this.fivehundread = fivehundread;
    }

    public ATM()
    {

    }
    public int getTot_amount() {
        return tot_amount;
    }

    public void setTot_amount(int n) {
        this.tot_amount += (100000*n);
        int tem=tot_amount;
        thousands*=n;
        hundreads*=n;
        fivehundread*=n;
        System.out.println("Available Money :"+tot_amount);
        System.out.println("Rs.1000  -> "+thousands);
        System.out.println("Rs.500 -> "+fivehundread);
        System.out.println("Rs.100 -> "+hundreads);

    }

    @Override
    public String toString() {
        return "ATM{" +
                "thousands=" + thousands +
                ", hundreads=" + hundreads +
                ", fivehundread=" + fivehundread +
                '}';
    }

    public void decrease(int n)
    {
        tot_amount-=n;
        System.out.println(tot_amount);
    }
}
