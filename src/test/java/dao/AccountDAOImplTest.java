package dao;

import model.Account;
import model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountDAOImplTest {

    AccountDAOImpl accountDAO = new AccountDAOImpl();

    @BeforeEach
    void setUp() {
        TestConfig.h2init();
    }

    @AfterEach
    void tearDown() {
        TestConfig.h2break();
    }

    @Test
    void insertAccount() {
        Account testUser = new Account(1, "testuser", "test", "user", "test", "testuser@test.com", 2);
        assertTrue(accountDAO.insertAccount(testUser));
    }

    @Test
    void pullAccountByUsername() {
        Account testUser = new Account(1, "testuser", "test", "user", "test", "testuser@test.com", 2);
        accountDAO.insertAccount(testUser);
        assertEquals(4, accountDAO.pullAccountByUsername(testUser.getUsername()).getPassword().length());
    }

    @Test
    void pullAccountName() {
        Account testUser = new Account(1, "testuser", "test", "user", "test", "testuser@test.com", 2);
        accountDAO.insertAccount(testUser);
        assertEquals(9, accountDAO.pullAccountName(testUser.getUserID()).length());
    }

    @Test
    void pullAccountType() {
        Account testUser = new Account(1, "testuser", "test", "user", "test", "testuser@test.com", 1);
        accountDAO.insertAccount(testUser);
        assertEquals("Employee", accountDAO.pullAccountType(testUser.getUserID()));
    }
}