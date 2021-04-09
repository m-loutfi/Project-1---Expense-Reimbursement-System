package dao;

import model.Account;
import model.Reimbursement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ReimbursementDAOImplTest {

    ReimbursementDAOImpl reimbDAO = new ReimbursementDAOImpl();

    @BeforeEach
    void setUp() {
        TestConfig.h2init();
    }

    @AfterEach
    void tearDown() {
        TestConfig.h2break();
    }

    @Test
    void insertReimb() {
        assertTrue(reimbDAO.insertReimb(new Reimbursement(100, "food", null, 3, 1, 1,0)));
    }

    @Test
    void pullReimbByID() {
        reimbDAO.insertReimb(new Reimbursement(100, "food", null, 3, 1, 1,0));
        assertEquals(1, reimbDAO.pullReimbByID(1).getReimbID());
    }

    @Test
    void pullAllUserReimb() {
        reimbDAO.insertReimb(new Reimbursement(100, "food", null, 3, 1, 1,0));
        assertEquals(1, reimbDAO.pullAllUserReimb().size());
    }


    @Test
    void pullReimbType() {
        reimbDAO.insertReimb(new Reimbursement(100, "food", null, 3, 1, 1,0));
        assertEquals(1, reimbDAO.pullReimbStatus(3));
    }

    @Test
    void pullReimbStatus() {
        Reimbursement newReimb = new Reimbursement(100, "food", null, 1, 1, 1,0);
        reimbDAO.insertReimb(newReimb);
        assertEquals("Approved",reimbDAO.pullReimbStatus(2));
    }
}