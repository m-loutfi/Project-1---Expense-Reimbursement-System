package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConfig {

    public static String url = "jdbc:h2:C:\\Users\\micha\\Desktop\\p1Testing";
    public static String username = "sa";
    public static String password = "sa";

    public static void h2init(){

        try(Connection conn = DriverManager.getConnection(url, username, password)){

            String sql = "CREATE TABLE user_roles(\n" +
                    "\trole_ID SERIAL,\n" +
                    "\tuser_role varchar(50),\n" +
                    "\tPRIMARY KEY (role_ID)\n" +
                    ");\n" +
                    "\n" +
                    "----Users Table\n" +
                    "CREATE TABLE Users(\n" +
                    "\tuser_ID SERIAL,\n" +
                    "\tusername varchar(50) NOT NULL UNIQUE,\n" +
                    "\tuser_password varchar(50)NOT NULL,\n" +
                    "\tfirst_name varchar(100),\n" +
                    "\tlast_name varchar(100),\n" +
                    "\tuser_email varchar(200)NOT NULL UNIQUE,\n" +
                    "\trole_ID int,\n" +
                    "\tPRIMARY KEY (user_ID),\n" +
                    "\tFOREIGN KEY (role_ID) REFERENCES user_roles(role_ID)\n" +
                    ");\n" +
                    "\n" +
                    "\n" +
                    "----Reimbursement Status Table----\n" +
                    "CREATE TABLE reimb_status (\n" +
                    "\tstatus_ID serial,\n" +
                    "\treimb_status varchar(20),\n" +
                    "\tPRIMARY KEY (status_ID)\n" +
                    ");\n" +
                    "\n" +
                    "\n" +
                    "---Reimbursement Type Table----\n" +
                    "CREATE TABLE reimb_type (\n" +
                    "\ttype_ID Serial,\n" +
                    "\treimb_type varchar(20),\n" +
                    "\tPRIMARY KEY (type_id)\n" +
                    ");\n" +
                    "\n" +
                    "\n" +
                    "--Reimbursement Table\n" +
                    "CREATE TABLE Reimbursement (\n" +
                    "\treimb_ID Serial,\n" +
                    "\tamount real,\n" +
                    "\ttime_submitted timestamp,\n" +
                    "\ttime_resolved timestamp,\n" +
                    "\tdescription varchar(300),\n" +
                    "\treceipt bytea,\n" +
                    "\tauthor int,\n" +
                    "\tresolver int,\n" +
                    "\tstatus_id int,\n" +
                    "\ttype_id int,\n" +
                    "\tPRIMARY KEY (reimb_id),\n" +
                    "\tFOREIGN KEY (author) REFERENCES users(user_id),\n" +
                    "\tFOREIGN KEY (resolver) REFERENCES users(user_id),\n" +
                    "\tFOREIGN KEY (status_id) REFERENCES reimb_status(status_id),\n" +
                    "\tFOREIGN KEY (type_id) REFERENCES reimb_type(type_id)\n" +
                    ");\n" +
                    "\n" +
                    "--ALTER TABLE reimbursement ALTER COLUMN time_submitted \n" +
                    "\n" +
                    "----TABLE SELECTS----\n" +
                    "SELECT * FROM reimb_status;\n" +
                    "SELECT * FROM reimb_type;\n" +
                    "SELECT * FROM reimbursement;\n" +
                    "\n" +
                    "SELECT * FROM users;\n" +
                    "SELECT * FROM user_roles;\n" +
                    "--\n" +
                    "--DROP TABLE reimbursement;\n" +
                    "----CRUD Statements----\n" +
                    "\n" +
                    "--INSERT\n" +
                    "INSERT INTO reimb_status(reimb_status) VALUES ('Pending');\n" +
                    "INSERT INTO reimb_status(reimb_status) VALUES ('Approved');\n" +
                    "INSERT INTO reimb_status(reimb_status) VALUES ('Denied');\n" +
                    "\n" +
                    "INSERT INTO reimb_type (reimb_type) VALUES\t('Lodging');\n" +
                    "INSERT INTO reimb_type (reimb_type) VALUES\t('Travel');\n" +
                    "INSERT INTO reimb_type (reimb_type) VALUES\t('Food');\n" +
                    "INSERT INTO reimb_type (reimb_type) VALUES\t('Other');\n" +
                    "\n" +
                    "INSERT INTO\tuser_roles(user_role) VALUES ('Employee');\n" +
                    "INSERT INTO\tuser_roles(user_role) VALUES ('Finance Manager');\n" +
                    "INSERT INTO users(username, user_password, first_name, last_name, user_email, role_id) \n" +
                    "VALUES ('John', 'Cena', 'John', 'Cena', 'attitudeadjustment@wwe.com', '1');";

            Statement statement = conn.createStatement();
            statement.execute(sql);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void h2break() {
        try(Connection conn = DriverManager.getConnection(url, username, password)){

            String sql = "DROP TABLE reimbursement;\n" +
                    "DROP TABLE users;\n" +
                    "DROP TABLE reimb_type;\n" +
                    "DROP TABLE reimb_status;\n" +
                    "DROP TABLE user_roles;";

            Statement statement = conn.createStatement();
            statement.execute(sql);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
