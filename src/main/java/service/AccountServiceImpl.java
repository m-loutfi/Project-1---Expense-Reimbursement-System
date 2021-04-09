package service;

import dao.AccountDAO;
import dao.AccountDAOImpl;
import model.Account;

public class AccountServiceImpl implements AccountService{

    AccountDAO accountDAO = new AccountDAOImpl();
    //ReimbursementDAO reimbDAO = new ReimbursementDAOImpl();


    /**
     * Create user by passing account object from controller layer.
     * @param user User account obj.
     * @return Boolean value confirming if account was stored.
     */
    @Override
    public boolean createAccount(Account user) {
        return accountDAO.insertAccount(user);
    }

    /**
     * Pull account object from DAO layer and passes it to the controller layer.
     * @param username Account username (String)
     * @return User account obj.
     */
    @Override
    public Account pullAccountByUsername(String username) {
        return accountDAO.pullAccountByUsername(username);
    }

    /**
     * Pulls List of all users from the database and removes any user with
     * the role of Finance Manager (role ID = 2)
     * @return Array list of employee accounts only.
     */
    @Override
    public String pullAccountName(int userID) {
        return accountDAO.pullAccountName(userID);
    }

    /**
     * Passes the desired user object to pull the account's role from the database.
     * @param user User account object
     * @return String from database of user's role
     */
    @Override
    public String pullAccountType(Account user) {
        return accountDAO.pullAccountType(user.getUserID());
    }


    ///////////////// MIN VIABLE PRODUCT DOES NOT INCLUDE THESE METHODS
    /////////////////////Could be added later......
    @Override
    public boolean updateAccount(Account user) {
        return false;
    }

    @Override
    public boolean removeAccount(Account user) {
        return false;
    }
}
