package controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import model.Account;
import model.Employee;
import model.FinanceManager;
import service.AccountServiceImpl;

public class AccountController {

    public static AccountServiceImpl accountService = new AccountServiceImpl();

    Javalin app;

    public AccountController(Javalin app) {
        this.app = app;
    }


    public void logoutAccount(Context ctx){
        ctx.sessionAttribute("currentuser", null);
        ctx.result("You are logged out!");
        ctx.redirect("/");
    }

    public void loginAccount(Context ctx){
        checkUsername(ctx);
    }

    private static void checkUsername(Context ctx){

        String inputUsername = ctx.formParam("username");
        Account currentUser = accountService.pullAccountByUsername(inputUsername);

        if(currentUser == null){
            ctx.result("Username does not exist! Please Try Again...").status(404);
        }else{
            checkPassword(ctx);
        }
    }

    private static void checkPassword(Context ctx){
        String inputUsername = ctx.formParam("username");
        String inputPassword = ctx.formParam("password");
        Account currentUser = accountService.pullAccountByUsername(inputUsername);

        if (inputPassword == null){
            ctx.result("Please enter a password").status(401);
        }else if(!inputPassword.equals(currentUser.getPassword())){
            ctx.result("Credentials are wrong...Try again...").status(401);
        }else{
            ctx.sessionAttribute("currentuser", currentUser);
            accountType(ctx);
        }
    }

    private static void accountType(Context ctx){
        Account currentUser = ctx.sessionAttribute("currentuser");
        String userType = accountService.pullAccountType(currentUser);

        if(userType.equals(Employee.getRole())){
            ctx.redirect("/client/emp/emp-dash.html");
        }else if(userType.equals(FinanceManager.getRole())){
            ctx.redirect("/client/fm/fm-dash.html");
        }else{
            ctx.result("User account type not found...").status(404);
            ctx.redirect("/");
        }
    }

    public void newPageRedirect(Context ctx){
        ctx.redirect("/client/emp/newreimb/newReimb-form.html");
    }

    public void userFullName(Context ctx){
//        int accountID = Integer.parseInt(ctx.queryParam("account_id"));
        Account user = ctx.sessionAttribute("currentuser");
        String fullName = accountService.pullAccountName(user.getUserID());
        ctx.result(fullName);
    }

}
