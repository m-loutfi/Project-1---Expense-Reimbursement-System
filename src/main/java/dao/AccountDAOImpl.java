package dao;

import model.Account;

import java.sql.*;

public class AccountDAOImpl implements AccountDAO{

    public static String url = "jdbc:postgresql://dataking.cdghozvheaha.us-east-2.rds.amazonaws.com/Project 1";
    public static String username = "dataking";
    public static String password = "p4ssw0rd";

//
//    public static String url = "jdbc:h2:C:\\Users\\micha\\Desktop\\p1Testing";
//    public static String username = "sa";
//    public static String password = "sa";

    /**
     * Establish connection with database to submit SQL query. SQL keyword "INSERT INTO" used to add a
     * record into the users table.
     * @param user Account object of user within the application. Role ID attribute distinguishes the
     *             type of user account.
     * @return Boolean value confirming record query was executed without any errors.
     */
    @Override
    public boolean insertAccount(Account user) {

        try(Connection conn = DriverManager.getConnection(url, username, password)){

            String sql = "INSERT INTO users(username, user_password, first_name, last_name, user_email, role_id) VALUES (?, ?, ?, ?, ?, ?);";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getUserEmail());
            ps.setInt(6, user.getRoleID());

            return ps.executeUpdate() !=0;

        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Establish connection with database to submit SQL query. Using SQL keyword "select" to return
     * a result set of the desired user using username. Resulting record attributes are passed into an Account object.
     * @param user String value of username of desired account.
     * @return Account object containing user information.
     */
    @Override
    public Account pullAccountByUsername(String user) {
        Account account = null;
        try(Connection conn = DriverManager.getConnection(url, username, password)){

            String sql = "SELECT * FROM users WHERE username = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user);

            ResultSet rs =  ps.executeQuery();

            while(rs.next()){
                account = new Account(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6),rs.getInt(7));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return account;
    }

    /**
     * Establish connection with database to submit SQL query that retrieves all records from users table.
     * @return ArrayList of Account objects that hols
     */
    @Override
    public String pullAccountName(int userID) {
        String userFullName = "";
        try(Connection conn = DriverManager.getConnection(url, username, password)){

            String sql = "SELECT first_name, last_name FROM users WHERE user_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);
            ResultSet rs =  ps.executeQuery();

            while(rs.next()) {
                userFullName = rs.getString(1)+" "+rs.getString(2);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return userFullName;
    }

    /**
     * After establishing connection with the database, an SQL join query is submitted to
     * obtain the desired user's role in the table.
     * @param user_id User account id (int)
     * @return String of the user's role based off of given role ID
     */
    @Override
    public String pullAccountType(int user_id) {
        String userType = null;
        try(Connection conn = DriverManager.getConnection(url, username, password)){

            String sql = "SELECT user_role FROM users u JOIN user_roles ur ON u.role_id = ur.role_id WHERE user_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user_id);

            ResultSet rs =  ps.executeQuery();

            while(rs.next()){
                userType = rs.getString(1);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return userType;
    }

    /**
     * Connect to database via sql query to update user email. Email Strings are NOT NULL and UNIQUE to the user.
     * @param user Username String used to identify current user.
     * @param email User email string chosen to replace the existing string.
     * @return Boolean value that confirms if update query actually applied the changes.
     */
    @Override
    public boolean updateAccount(String user, String email) {
        try(Connection conn = DriverManager.getConnection(url, username, password)){

            String sql = "UPDATE user SET email = ? WHERE username = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, user);

            return ps.executeUpdate() !=0;

        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Establishes connection to database to submit a SQL delete query to remove user record.
     * @param user Username String chosen to identify the account to be deleted.
     * @return Boolean value that confirms the record was actually removed.
     */
    @Override
    public boolean deleteAccount(String user) {
        try(Connection conn = DriverManager.getConnection(url, username, password)){

            String sql = "DELETE FROM users WHERE username = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user);

            return ps.executeUpdate() !=0;

        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
