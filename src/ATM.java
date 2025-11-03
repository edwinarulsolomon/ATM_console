public class ATM {
    private int totalAmount = 0;
    private int thousands = 20;
    private int hundreds = 100;
    private int fiveHundreds = 300;

    public int getHundreds() {
        return hundreds;
    }

    public void setHundreds(int hundreds) {
        this.hundreds = hundreds;
    }

    public int getThousands() {
        return thousands;
    }

    public void setThousands(int thousands) {
        this.thousands = thousands;
    }

    public int getFiveHundreds() {
        return fiveHundreds;
    }

    public void setFiveHundreds(int fiveHundreds) {
        this.fiveHundreds = fiveHundreds;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void loadCash(int n) {
        this.totalAmount += (100000 * n);
        thousands *= n;
        hundreds *= n;
        fiveHundreds *= n;

        System.out.println("\nCash Loaded Successfully!");
        showATMBalance();
    }

    public void showATMBalance() {
        System.out.println("Available Money : " + totalAmount);
        System.out.println("Rs.1000 -> " + thousands);
        System.out.println("Rs.500  -> " + fiveHundreds);
        System.out.println("Rs.100  -> " + hundreds);
    }

    public void decrease(int amount) {
        totalAmount -= amount;
    }

    @Override
    public String toString() {
        return "ATM{" +
                "thousands=" + thousands +
                ", fiveHundreds=" + fiveHundreds +
                ", hundreds=" + hundreds +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
