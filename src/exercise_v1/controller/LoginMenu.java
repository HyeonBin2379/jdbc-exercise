package exercise_v1.controller;

import exercise_v1.constant.LoginMessage;
import exercise_v1.domain.Manager;
import exercise_v1.domain.User;
import exercise_v1.model.LoginDAO;
import exercise_v1.config.DBUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class LoginMenu {

    private static class LazyHolder {
        private static final LoginMenu LOGIN_CONTROLLER = new LoginMenu();
    }
    private static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private static boolean quitLogin;
    private static WMSMenu wmsMenu;

    private final LoginDAO loginDAO;
    private User loginUser;

    private LoginMenu() {
        loginDAO = new LoginDAO();
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
                    case "1" -> {
                        login();
                        wmsMenu.run();
                    }
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

        // 해당하는 회원이 존재하면 회원가입유형을 가져온다.(프로시저 사용)
        String sql = "{call login(?, ?, ?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement call = conn.prepareCall(sql)) {
            call.setString(1, userID);
            call.setString(2, userPwd);
            call.registerOutParameter(3, Types.VARCHAR);

            if(!call.execute()) {
                return;
            }

            String userType = call.getString(3);
            System.out.println(userType);
            try (ResultSet rs = call.getResultSet();) {
                if (rs.next()) {
                    if (userType.contains("관리자")) {
                        Manager manager = new Manager();
                        manager.setId(rs.getString(1));
                        manager.setPwd(rs.getString(2));
                        manager.setName(rs.getString(3));
                        manager.setPhone(rs.getString(4));
                        manager.setEmail(rs.getString(5));
                        manager.setLogin(rs.getBoolean(6));
                        manager.setHireDate(rs.getDate(7));
                        manager.setPosition(rs.getString(8));

                        loginUser = manager;
                        wmsMenu = WMSMenu.getInstance(manager);
                    } else if (userType.equals("일반회원")) {

                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loginMember() {

    }

    public void loginManager() {

    }

    public void register() throws IOException {
        LoginMessage.print(LoginMessage.SIGN_UP);
        System.out.print(LoginMessage.INPUT_MEMBERSHIP_TYPE);
        String type = input.readLine();
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
        String email = input.readLine();
    }

    public void findPassword() throws IOException {
        LoginMessage.print(LoginMessage.FIND_PWD);
        System.out.println(LoginMessage.INPUT_ID);
        String userID = input.readLine();
    }

    public void exitLoginMenu() {
        quitLogin = true;
        System.out.println(LoginMessage.EXIT_LOGIN_MENU);
    }
}
