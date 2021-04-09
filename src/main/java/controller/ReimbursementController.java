package controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import model.Account;
import model.Reimbursement;
import service.AccountServiceImpl;
import service.ReimbursementServiceImpl;

public class ReimbursementController {

    public static ReimbursementServiceImpl reimbService = new ReimbursementServiceImpl();
    public static AccountServiceImpl accountService = new AccountServiceImpl();

    Javalin app;

    public ReimbursementController(Javalin app) {
        this.app = app;
    }

    public void pullAllReimbursements(Context ctx){
        Account currentUser = ctx.sessionAttribute("currentuser");
        ctx.json(reimbService.pullAllReimbursements(currentUser)).status(200);
    }

    public void pullUserReimbursements(Context ctx){
        Account currentUser = ctx.sessionAttribute("currentuser");
        ctx.json(reimbService.pullUserReimbursements(currentUser));
    }

    public void createReimbursement(Context ctx){
        Reimbursement newUserReimb = new Reimbursement();
        newUserReimb.setAmount(Float.parseFloat(ctx.formParam("amount")));
        newUserReimb.setDescription(ctx.formParam("Description"));
        String reimbType = ctx.formParam("type");

        switch (reimbType) {
            case "Lodging":
                newUserReimb.setType(1);
                break;
            case "Travel":
                newUserReimb.setType(2);
                break;
            case "Food":
                newUserReimb.setType(3);
                break;
            case "Other":
                newUserReimb.setType(4);
                break;
        }
        Account user = ctx.sessionAttribute("currentuser");
        newUserReimb.setAccountID(user.getUserID());
        newUserReimb.setStatus(1);
        reimbService.createReimb(newUserReimb);

        ctx.redirect("/client/emp/emp-dash.html");
    }

    public void updateReimbursement(Context ctx){

        int reimbStatus = Integer.parseInt(ctx.queryParam("status"));
        int reimbID = Integer.parseInt(ctx.queryParam("reimb_id"));

        Reimbursement selectedReimb = reimbService.pullReimb(reimbID);
        Account currentUser = ctx.sessionAttribute("currentuser");
        if(currentUser.getRoleID() == 2){
            if(reimbService.updateReimb(selectedReimb, reimbStatus, currentUser)){
                ctx.json(reimbService.pullAllReimbursements(currentUser)).status(200);
            }else{
                ctx.result("Oops...something went wrong...").status(500);
            }
        }else{
            ctx.result("You are not a finance manager!").status(403);
        }
    }
    public static void pullReimbursement(Context ctx){
    }

}
