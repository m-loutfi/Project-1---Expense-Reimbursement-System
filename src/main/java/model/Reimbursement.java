package model;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Reimbursement {

    private int reimbID;
    private float amount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private String submitTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private String resolvedTime;
    private String description;
    private byte[] receipt;  //<-----what type should a receipt image take within java?
    private int status;
    private int type;
    private int accountID;
    private int resolverID;

    public Reimbursement() {
    }

    public Reimbursement(float amount, String description, byte[] receipt, int status,
                         int type, int accountID, int resolverID) {
        this.amount = amount;
        this.description = description;
        this.receipt = receipt;
        this.status = status;
        this.type = type;
        this.accountID = accountID;
        this.resolverID = resolverID;
    }

    public Reimbursement(int reimbID, float amount, String submitTime, String resolvedTime,
                         String description, byte[] receipt, int status, int type,
                         int accountID, int resolverID) {
        this.reimbID = reimbID;
        this.amount = amount;
        this.submitTime = submitTime;
        this.resolvedTime = resolvedTime;
        this.description = description;
        this.receipt = receipt;
        this.status = status;
        this.type = type;
        this.accountID = accountID;
        this.resolverID = resolverID;
    }

    public int getReimbID() {
        return reimbID;
    }

    public void setReimbID(int reimbID) {
        this.reimbID = reimbID;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getresolvedTime() {
        return resolvedTime;
    }

    public void setresolvedTime(String resolvedTime) {
        this.resolvedTime = resolvedTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getReceipt() {
        return receipt;
    }

    public void setReceipt(byte[] receipt) {
        this.receipt = receipt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getResolverID() {
        return resolverID;
    }

    public void setResolverID(int resolverID) {
        this.resolverID = resolverID;
    }






    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbID=" + reimbID +
                ", amount=" + amount +
                ", submitTime='" + submitTime + '\'' +
                ", resolvedTime='" + resolvedTime + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
