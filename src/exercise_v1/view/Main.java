package exercise_v1.view;

import exercise_v1.controller.user.LoginMenu;

public class Main {

    public static void main(String[] args) {
        LoginMenu loginMenu = LoginMenu.getInstance();
        loginMenu.loginMenu();
    }
}
