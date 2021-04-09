package service;

import dao.AccountDAO;
import dao.AccountDAOImpl;
import dao.ReimbursementDAO;
import dao.ReimbursementDAOImpl;
import model.Account;
import model.Reimbursement;

import java.util.List;

public interface ReimbursementService {

    AccountDAO accountDAO = new AccountDAOImpl();
    ReimbursementDAO reimbDAO = new ReimbursementDAOImpl();

    public boolean createReimb(Reimbursement reimb);

    //Pull reimbursement info by US
    public Reimbursement pullReimb(int reimbID);
    //Pulls a group of Reimbursements of an account
    public List<Reimbursement> pullAllReimbursements(Account user);
    //Pulls all reimbursement of a specific user
    public List<Reimbursement> pullUserReimbursements(Account user);

    //Updates reimbursement status
    public boolean updateReimb(Reimbursement reimb, int newStatus, Account user);

    //Remove Reimbursement
    public boolean removeReimb(Reimbursement reimb);



}
