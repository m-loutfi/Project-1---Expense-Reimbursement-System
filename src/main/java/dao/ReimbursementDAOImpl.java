package dao;

import model.Account;
import model.Reimbursement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAOImpl implements ReimbursementDAO {

    public static String url = "jdbc:postgresql://"+System.getenv("TRAINING_DB_ENDPOINT")+"/"+System.getenv("TRAINING_DB_NAME");
    public static String username = System.getenv("TRAINING_DB_USERNAME");
    public static String password = System.getenv("TRAINING_DB_PASSWORD");


//    public static String url = "jdbc:h2:C:\\Users\\micha\\Desktop\\p1Testing";
//    public static String username = "sa";
//    public static String password = "sa";

    /**
     * Establishes a connection with database to execute SQL delete query to insert new
     * reimbursement record.
     * @param reimb Reimbursement object containing all attributes related to user's reimbursement.
     * @return Boolean value that confirms if reimbursement record was inserted into database.
     */
    @Override
    public boolean insertReimb(Reimbursement reimb) {

        try(Connection conn = DriverManager.getConnection(url, username, password)){

            String sql = "INSERT INTO reimbursement(amount, description, author, receipt, status_id, type_id, time_submitted) VALUES (?, ?, ?, ?, ?, ?, current_timestamp);";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setFloat(1, reimb.getAmount());
            ps.setString(2, reimb.getDescription());
            ps.setInt(3, reimb.getAccountID());
            ps.setBytes(4, reimb.getReceipt());
            ps.setInt(5, reimb.getStatus());
            ps.setInt(6, reimb.getType());

            return ps.executeUpdate() !=0;

        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Establishes connection with the database to submit SQL select query. Retreives user
     * reimbursement based on the reimbursement ID.
     * @param reimbID Reimbursement ID (int) NOT NULL & UNIQUE
     * @return Reimbursement object with all attributes related.
     */
    @Override
    public Reimbursement pullReimbByID(int reimbID) {
        Reimbursement reimb = null;
        try(Connection conn = DriverManager.getConnection(url, username, password)){

            String sql = "SELECT * FROM reimbursement WHERE reimb_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, reimbID);

            ResultSet rs =  ps.executeQuery();

            while(rs.next()){
                reimb = new Reimbursement(rs.getInt(1), rs.getFloat(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getBytes(6),
                        rs.getInt(7), rs.getInt(8),
                        rs.getInt(9), rs.getInt(10));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return reimb;
    }

    /**
     * Establishes a connection with database to submimt SQL select query on reimbursement table.
     * @return List of reimbursement objects specific to the passed user object.
     */
    @Override
    public List<Reimbursement> pullAllUserReimb() {
        List<Reimbursement> reimbList = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, username, password)){

            String sql = "SELECT * FROM reimbursement ORDER BY reimb_id ASC;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs =  ps.executeQuery();

            while(rs.next()){
                reimbList.add(new Reimbursement(rs.getInt(1), rs.getFloat(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getBytes(6),
                        rs.getInt(9), rs.getInt(10),
                        rs.getInt(7), rs.getInt(8)));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return reimbList;
    }

    /**
     * Pulls list of Reimbursement for a specific user
     * @param user User account object
     * @return Array List of Reimbursement objects
     */
    @Override
    public List<Reimbursement> pullUserReimb(Account user) {
        List<Reimbursement> reimbList = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, username, password)){

            String sql = "SELECT * FROM reimbursement WHERE author = ? ORDER BY reimb_id ASC;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getUserID());

            ResultSet rs =  ps.executeQuery();

            while(rs.next()){
                reimbList.add(new Reimbursement(rs.getInt(1), rs.getFloat(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getBytes(6),
                        rs.getInt(9), rs.getInt(10),
                        rs.getInt(7), rs.getInt(8)));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return reimbList;
    }

    /**
     * Connection to database to execute SQL select query on reimb-type table.
     * @param type_id Reimbursement type ID (int) from reibursement object.
     * @return String associated with the type-id of that reimbursement.
     */
    @Override
    public String pullReimbType(int type_id) {
        String reimbType = null;
        try(Connection conn = DriverManager.getConnection(url, username, password)){

            String sql = "SELECT * FROM reimb_status WHERE status_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, type_id);

            ResultSet rs =  ps.executeQuery();

            while(rs.next()){
                reimbType = rs.getString(2);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return reimbType;
    }

    /**
     * Establishes connection with database to submit SQl select query
     * on reimb-status table.
     * @param status_id Reimbursement status ID (int).
     * @return String associated with given status ID.
     */
    @Override
    public String pullReimbStatus(int status_id) {
        String reimbStatus = null;
        try(Connection conn = DriverManager.getConnection(url, username, password)){

            String sql = "SELECT * FROM reimb_status WHERE status_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, status_id);

            ResultSet rs =  ps.executeQuery();

            while(rs.next()){
                reimbStatus = rs.getString(2);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return reimbStatus;
    }

    /**
     * Establishes a connection with the database to submit SQL update query on reimbursement table
     * @param reimb Reimbursement object. Reimbursement ID attribute (int) to be used.
     * @param newStatus Status ID (int) desired to be changed in reimbursement record.
     * @return Boolean value confirming record was updated inside table.
     */
    @Override
    public boolean updateReimbStatus(Reimbursement reimb, int newStatus) {
        try(Connection conn = DriverManager.getConnection(url, username, password)){

            String sql = "UPDATE reimbursement SET status_id = ? WHERE reimb_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, newStatus);
            ps.setInt(2, reimb.getReimbID());

            return ps.executeUpdate() !=0;

        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Establishes connection with database to submit SQL update query for when a
     * reimbursement is resolved by financial manager.
     * @param reimbID Reimbursement object ID (int)
     * @param userID User Account ID (int) that resolved the Reimbursement
     * @return Boolean value that confirms the resolver was updated in the reimbursement record
     */
    @Override
    public boolean updateReslover(int reimbID, int userID) {
        try(Connection conn = DriverManager.getConnection(url, username, password)){

            String sql = "UPDATE reimbursement SET resolver = ?, time_resolved = current_timestamp WHERE reimb_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, userID);
            ps.setInt(2, reimbID);

            return ps.executeUpdate() !=0;

        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }


    /**
     * Establish connection with database to submit SQL delete query on reimbursement table.
     * @param reimb Reimbursement object. Reimb-ID (int) to be used as query condition
     * @return Boolean value that confirms if reimbursement record has been deleted.
     */
    @Override
    public boolean deleteReimb(Reimbursement reimb) {
        try(Connection conn = DriverManager.getConnection(url, username, password)){

            String sql = "DELETE FROM reimbursement WHERE reimb_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, reimb.getReimbID());

            return ps.executeUpdate() !=0;

        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
