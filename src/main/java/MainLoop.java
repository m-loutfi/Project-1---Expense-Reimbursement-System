import frontcontroller.FrontController;
import io.javalin.Javalin;

public class MainLoop {


    public static void main(String[] args) {
        System.out.println("hello");

        Javalin app = Javalin.create(javalinConfig ->
                javalinConfig.addStaticFiles("/website")).start(1108);

        FrontController fc = new FrontController(app);
    }
}
