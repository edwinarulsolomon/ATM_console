public class Transaction {
    int trans_id;
    String trans_remark;
    String type;
    int amount;
    int from;
    int to;
    String sourse;
    public Transaction(int trans_id, String trans_remark, String type, int amount,String sourse) {
        this.trans_id = trans_id;
        this.trans_remark = trans_remark;
        this.type = type;
        this.amount = amount;
        this.sourse=sourse;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trans_id=" + trans_id +
                ", trans_remark='" + trans_remark + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                '}';
    }
}
