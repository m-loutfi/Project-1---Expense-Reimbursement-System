package frontcontroller;

import controller.AccountController;
import controller.ReimbursementController;
import io.javalin.Javalin;

public class Dispatcher {

    AccountController userAccount;
    ReimbursementController userReimb;


    public Dispatcher(Javalin app) {
        userAccount = new AccountController(app);
        userReimb = new ReimbursementController(app);

        app.post("/login", userAccount::loginAccount);
        app.post("/logout", userAccount::logoutAccount);
        app.post("/addreimbpage", userAccount::newPageRedirect);

        app.get("/getallreimb", userReimb::pullAllReimbursements);
        app.get("/getuserreimb", userReimb::pullUserReimbursements);
        app.post("/newreimb", userReimb::createReimbursement);
        app.get("/updatereimb", userReimb::updateReimbursement);
        app.get("/userfullname", userAccount::userFullName);

    }
}
