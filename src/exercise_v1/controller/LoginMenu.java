package exercise_v1.controller;

import exercise_v1.constant.LoginMessage;
import exercise_v1.domain.User;
import exercise_v1.model.LoginDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginMenu {

    private static class LazyHolder {
        private static final LoginMenu LOGIN_CONTROLLER = new LoginMenu();
    }
    private static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private static boolean quitLogin;

    private final LoginDAO dao;

    private LoginMenu() {
        dao = new LoginDAO();
    }

    // 컨트롤러에 싱글톤 패턴 적용
    public static LoginMenu getInstance() {
        return LazyHolder.LOGIN_CONTROLLER;
    }

    public void loginMenu() {
        while (!quitLogin) {
            try {
                System.out.print(LoginMessage.LOGIN_MENU_TITLE);
                String menuNum = input.readLine();
                switch (menuNum) {
                    case "1" -> login();
                    case "2" -> register();
                    case "3" -> findID();
                    case "4" -> findPassword();
                    case "5" -> exitLoginMenu();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void login() throws IOException {
        System.out.println(LoginMessage.INPUT_ID);
        String userID = input.readLine();
        System.out.println(LoginMessage.INPUT_PWD);
        String userPwd = input.readLine();

        User loginUser = dao.login(userID, userPwd);
        WMSMenu wmsMenu = new WMSMenu(loginUser);
        wmsMenu.run();
    }

    public void register() throws IOException {
        LoginMessage.print(LoginMessage.SIGN_UP);
        System.out.print(LoginMessage.INPUT_MEMBERSHIP_TYPE);
        String type = input.readLine();

        User user = null;
        boolean ack = false;
        switch (type) {
            case "1":
                break;
            case "2":
                break;
        }
    }

    public void findID() throws IOException {
        LoginMessage.print(LoginMessage.FIND_ID);
        System.out.println(LoginMessage.INPUT_EMAIL);
        String userEmail = input.readLine();

        System.out.println(dao.findID(userEmail));
    }

    public void findPassword() throws IOException {
        LoginMessage.print(LoginMessage.FIND_PWD);
        System.out.println(LoginMessage.INPUT_ID);
        String userID = input.readLine();

        System.out.println(dao.findPassword(userID));
    }

    public void exitLoginMenu() {
        quitLogin = true;
        System.out.println(LoginMessage.EXIT_LOGIN_MENU);
    }
}
