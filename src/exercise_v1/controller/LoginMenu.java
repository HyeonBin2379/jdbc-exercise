package exercise_v1.controller;

import exercise_v1.constant.LoginPage;
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
                System.out.print(LoginPage.LOGIN_MENU_TITLE);
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
        System.out.println(LoginPage.INPUT_ID);
        String userID = input.readLine();
        System.out.println(LoginPage.INPUT_PWD);
        String userPwd = input.readLine();

        User loginUser = dao.login(userID, userPwd);
        WMSMenu wmsMenu = new WMSMenu(loginUser);
        wmsMenu.run();
    }

    public void register() throws IOException {
        LoginPage.print(LoginPage.SIGN_UP);
        System.out.print(LoginPage.INPUT_MEMBERSHIP_TYPE);
        String type = input.readLine();

        boolean ack = false;
        switch (type) {
            case "1":
                User newMember = inputMemberInfo();
                ack = dao.register(newMember);
                break;
            case "2":
                User newManager = inputManagerInfo();
                ack = dao.register(newManager);
                break;
        }

        if (ack) {
            System.out.println("회원가입이 완료되었습니다.");
        } else {
            System.out.println("회원가입에 실패했습니다.");
        }
    }

    public User inputMemberInfo() throws IOException {
        LoginPage.print(LoginPage.MEMBER_REGISTER);

        System.out.println(LoginPage.INPUT_ID);
        String userID = input.readLine();
        System.out.println(LoginPage.INPUT_PWD);
        String userPwd = input.readLine();
        System.out.println(LoginPage.INPUT_COMPANY_NAME);
        String companyName = input.readLine();
        System.out.println(LoginPage.INPUT_PHONE);
        String phone = input.readLine();
        System.out.println(LoginPage.INPUT_EMAIL);
        String email = input.readLine();
        System.out.println(LoginPage.INPUT_COMPANY_CODE);
        String companyCode = input.readLine();
        System.out.println(LoginPage.INPUT_ADDRESS);
        String address = input.readLine();

        User newUser = new User(userID, userPwd, companyName, phone, email, "일반회원");
        newUser.setCompanyCode(companyCode);
        newUser.setAddress(address);
        return newUser;
    }

    public User inputManagerInfo() throws IOException {
        LoginPage.print(LoginPage.MEMBER_REGISTER);
        System.out.println(LoginPage.INPUT_ID);
        String userID = input.readLine();
        System.out.println(LoginPage.INPUT_PWD);
        String userPwd = input.readLine();
        System.out.println(LoginPage.INPUT_NAME);
        String name = input.readLine();
        System.out.println(LoginPage.INPUT_PHONE);
        String phone = input.readLine();
        System.out.println(LoginPage.INPUT_EMAIL);
        String email = input.readLine();
        System.out.println(LoginPage.INPUT_MANAGER_POSITION);
        String position = input.readLine();

        User newUser = new User(userID, userPwd, name, phone, email, position);
        return newUser;
    }

    public void findID() throws IOException {
        LoginPage.print(LoginPage.FIND_ID);
        System.out.println(LoginPage.INPUT_EMAIL);
        String userEmail = input.readLine();

        System.out.println(dao.findID(userEmail));
    }

    public void findPassword() throws IOException {
        LoginPage.print(LoginPage.FIND_PWD);
        System.out.println(LoginPage.INPUT_ID);
        String userID = input.readLine();

        System.out.println(dao.findPassword(userID));
    }

    public void exitLoginMenu() {
        quitLogin = true;
        System.out.println(LoginPage.EXIT_LOGIN_MENU);
    }
}
