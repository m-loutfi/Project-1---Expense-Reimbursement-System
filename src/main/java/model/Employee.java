package model;

public class Employee extends Account{

    private static final String role = "Employee";

    public Employee() {
    }

    public static String getRole() {
        return role;
    }

}
