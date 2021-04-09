package dao;

import model.Account;

public interface AccountDAO {


    //CREATE - Insert account into database (users table)
    boolean insertAccount(Account user);

    //READ - Pulls account from database
    Account pullAccountByUsername(String user);
    //READ - Pulls all accounts from users table
    String pullAccountName(int userID);
    //READ - Pull account type from user roles table
    String pullAccountType(int user_id);

    //UPDATE - Update a specific account
    boolean updateAccount(String user, String email);

    //DELETE - Remove an account record from the table
    boolean deleteAccount(String user);

}
