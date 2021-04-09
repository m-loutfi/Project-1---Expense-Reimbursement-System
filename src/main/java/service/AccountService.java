package service;

import model.Account;

public interface AccountService {

    //Gathers account data and stores it in database
    public boolean createAccount(Account user);

    //Pull account by username
    public Account pullAccountByUsername(String username);
    //Pull all employee accounts
    public String pullAccountName(int user);
    //Pull account type
    public String pullAccountType(Account user);

    //Update specific account info
    public boolean updateAccount(Account user);

    //Remove account completely
    public boolean removeAccount(Account user);

}
