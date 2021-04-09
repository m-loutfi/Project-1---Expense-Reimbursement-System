package service;

import dao.AccountDAO;
import dao.AccountDAOImpl;
import dao.ReimbursementDAO;
import dao.ReimbursementDAOImpl;
import model.Account;
import model.Reimbursement;

import java.util.List;

public class ReimbursementServiceImpl implements ReimbursementService{

    ReimbursementDAO reimbDAO = new ReimbursementDAOImpl();
    AccountDAO accountDAO = new AccountDAOImpl();

    /**
     * Create and insert new Reimbursement into database.
     * @param reimb Reimbursement obj
     * @return Boolean value confirming reimbursement data was stored
     */
    @Override
    public boolean createReimb(Reimbursement reimb) {
        return reimbDAO.insertReimb(reimb);
    }

    /**
     * Passes reimbursement ID to obtain Reimbursement obj for user
     * @param reimbID Reimbursement ID (int)
     * @return Reibursement object base on given ID
     */
    @Override
    public Reimbursement pullReimb(int reimbID) {
        return reimbDAO.pullReimbByID(reimbID);
    }

    /**
     * Depending on if the user is an Employee or a Finance Manager the method returns
     * a list of reimbursements for that employee or ALL reimbursement that exist in
     * the database
     * @param user User account object trying to access list of reimbursements
     * @return List of reimbursement objects
     */
    @Override
    public List<Reimbursement> pullAllReimbursements(Account user) {
        return reimbDAO.pullAllUserReimb();
    }

    /**
     * Pulls list of reimbursments of a specific user from DAO layer and returns it to controller
     * layer.
     * @param user User account object
     * @return ArrayList of Reimbursement objects
     */
    @Override
    public List<Reimbursement> pullUserReimbursements(Account user) {
        return reimbDAO.pullUserReimb(user);
    }

    /**
     * Updates database reimbursement record when a user's reimbursement has been resolved
     * by a finance manager.
     * @param reimb Reimbursement object that needs to be updated
     * @param newStatus updated Status ID (int) of the reimbursement
     * @param user Account object associated with resolver of reimbursement
     * @return Boolean value confirming reimbursement was updated
     */
    @Override
    public boolean updateReimb(Reimbursement reimb, int newStatus, Account user) {
        return reimbDAO.updateReslover(reimb.getReimbID(), user.getUserID())
                & reimbDAO.updateReimbStatus(reimb, newStatus);
    }


    /////MVP DOES NOT REQUIRE THIS ATM
    @Override
    public boolean removeReimb(Reimbursement reimb) {
        return false;
    }
}
