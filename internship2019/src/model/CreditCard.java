package model;

public class CreditCard {
    private String type;
    private float fee;
    private int withdrawlimit;
    private String exprdate;
    private int amount;

    public CreditCard(String type, float fee, int withdrawlimit, String exprdate, int amount) {
        this.type = type;
        this.fee = fee;
        this.withdrawlimit = withdrawlimit;
        this.exprdate = exprdate;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public int getWithdrawlimit() {
        return withdrawlimit;
    }

    public void setWithdrawlimit(int withdrawlimit) {
        this.withdrawlimit = withdrawlimit;
    }

    public String getExprdate() {
        return exprdate;
    }

    public void setExprdate(String exprdate) {
        this.exprdate = exprdate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "model.CreditCard{" +
                "type='" + type + '\'' +
                ", fee=" + fee +"%"+
                ", withdrawlimit=" + withdrawlimit +
                ", exprdate='" + exprdate + '\'' +
                ", amount=" + amount +
                '}';
    }
}
