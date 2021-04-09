package frontcontroller;

import io.javalin.Javalin;
import model.Account;

public class FrontController {

    Javalin app;
    Dispatcher dispatcher;

    public FrontController(Javalin app){
        this.app = app;

        dispatcher = new Dispatcher(app);

        app.before("/client/*", context -> {
//            System.out.println("before api endpoint");
            Account user = context.sessionAttribute("currentuser");
            if(user == null) {
                context.redirect("/");
            }
        });
    }
}
