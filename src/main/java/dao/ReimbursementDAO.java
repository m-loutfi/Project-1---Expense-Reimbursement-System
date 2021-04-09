package dao;

import model.Account;
import model.Reimbursement;

import java.util.List;

public interface ReimbursementDAO {


    //CREATE - Insert Reimbursement into database (reimb table)
    public boolean insertReimb(Reimbursement reimb);


    //READ - Pull Reimbursement from database table
    public Reimbursement pullReimbByID(int reimbID);
    //READ - Pull group of Reimbursements from database
    public List<Reimbursement> pullAllUserReimb();
    //Read - pull all reimbursments for a specific user
    public List<Reimbursement> pullUserReimb(Account user);
    //READ - Pull the type of Reimbursement from database
    public String pullReimbType(int typeID);
    //READ - Pull Reimbursement Status from database
    public String pullReimbStatus(int statusID);

    //UPDATE - Change or update reimb record in the database
    public boolean updateReimbStatus(Reimbursement reimb, int newStatus);
    //UPDATE - change reslover of reimb record in the database
    public boolean updateReslover(int reimbID, int userID);

    //DELETE - Remove reimb record from database
    public boolean deleteReimb(Reimbursement reimb);



}
