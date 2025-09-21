package exercise_v1.controller;

import exercise_v1.common.LoginMenu;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginController {

    private static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private static boolean quit;

    private static class LazyHolder {
        private static final LoginController LOGIN_CONTROLLER = new LoginController();
    }

    private LoginController() {
    }

    public static LoginController getInstance() {
        return LazyHolder.LOGIN_CONTROLLER;
    }

    public void run() throws IOException {
        while (!quit) {
            System.out.print(LoginMenu.MAIN_MENU_TITLE);
            String menuNum = input.readLine();
            switch (menuNum) {
                case "1" -> login();
                case "2" -> signUp();
                case "3" -> findID();
                case "4" -> findPassword();
                case "5" -> exitWMS();
            }
        }
    }

    public void login() throws IOException {
        LoginMenu.print(LoginMenu.LOGIN);
        System.out.println(LoginMenu.INPUT_ID);
        String userID = input.readLine();
        System.out.println(LoginMenu.INPUT_PWD);
        String userPwd = input.readLine();
    }

    public void signUp() throws IOException {
        LoginMenu.print(LoginMenu.SIGN_UP);
        System.out.print(LoginMenu.INPUT_MEMBERSHIP_TYPE);
        String type = input.readLine();
        switch (type) {
            case "1":
                registerMember();
                break;
            case "2":
                registerManager();
                break;
        }
    }

    public void registerMember() {

    }

    public void registerManager() {

    }

    public void findID() throws IOException {
        LoginMenu.print(LoginMenu.FIND_ID);
        System.out.println(LoginMenu.INPUT_EMAIL);
        String email = input.readLine();
    }

    public void findPassword() throws IOException {
        LoginMenu.print(LoginMenu.FIND_PWD);
        System.out.println(LoginMenu.INPUT_ID);
        String userID = input.readLine();
    }

    public void exitWMS() {
        quit = true;
        System.out.println(LoginMenu.EXIT_WMS);
    }
}
