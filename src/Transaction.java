public class Transaction {
    private int transId;
    private String remark;
    private String type;
    private int amount;
    private String source;

    public Transaction(int transId, String remark, String type, int amount, String source) {
        this.transId = transId;
        this.remark = remark;
        this.type = type;
        this.amount = amount;
        this.source = source;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transId=" + transId +
                ", remark='" + remark + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", source='" + source + '\'' +
                '}';
    }
}
