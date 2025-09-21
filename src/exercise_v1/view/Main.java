package exercise_v1.view;

import exercise_v1.controller.LoginController;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            LoginController loginController = LoginController.getInstance();
            loginController.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
