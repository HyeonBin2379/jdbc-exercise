package exercise_v1.controller;

import exercise_v1.constant.LoginPage;
import exercise_v1.constant.UserPage;
import exercise_v1.domain.Manager;
import exercise_v1.domain.Member;
import exercise_v1.domain.User;
import exercise_v1.model.ManagerDAO;
import exercise_v1.model.MemberDAO;
import exercise_v1.model.UserDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserMenu {

    private static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private final UserDAO dao;

    private boolean quitMenu;
    private boolean isUserDeleted;

    public UserMenu(User loginUser) {
        this.quitMenu = false;
        this.isUserDeleted = false;
        if (loginUser instanceof Manager) {
            this.dao = new ManagerDAO((Manager)loginUser);
        } else {
            this.dao = new MemberDAO((Member)loginUser);
        }
    }

    public boolean run() {
        while (!quitMenu) {
            try {
                printMenu();
                String menuNum = input.readLine();
                switch (menuNum) {
                    case "1":
                        select();
                        break;
                    case "2":
                        update();
                        break;
                    case "3":
                        delete();
                        break;
                    case "4":
                        exitMenu();
                        break;
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return isUserDeleted;
    }

    public void printMenu() {
        if (dao instanceof ManagerDAO) {
            System.out.print(UserPage.MANAGER_MEMBER_MENU_TITLE);
        } else {
            System.out.print(UserPage.MEMBER_MEMBER_MENU_TITLE);
        }
    }

    public void select() {
        // 일반회원이면 자신만 조회
        if (dao instanceof MemberDAO) {
            dao.searchUserDetails();
            System.out.println(UserPage.MEMBER_SELECT);
            return;
        }
        selectByManager();
    }

    public void selectByManager() {
        System.out.println(UserPage.MANAGER_SELECT_TITLE);
    }

    public void update() throws IOException {
        // 일반회원이면 자신만 변경
        if (dao instanceof MemberDAO) {
            updateCurrentMember();
            return;
        }
    }

    public void updateCurrentMember() throws IOException {
        User newUserInfo = (dao instanceof MemberDAO) ? inputNewMember() : inputNewManager();
        boolean ack = dao.updateUserInfo(newUserInfo);
        System.out.println(ack ? UserPage.MEMBER_UPDATE : UserPage.MEMBER_UPDATE_FAILED);
    }

    public void updateByManager() {

    }

    private User inputNewMember() throws IOException {
        System.out.println(UserPage.MEMBER_UPDATE_TITLE);
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

        User user = new User();
        user.setPwd(userPwd);
        user.setName(companyName);
        user.setPhone(phone);
        user.setEmail(email);
        user.setCompanyCode(companyCode);
        user.setAddress(address);
        return user;
    }
    public User inputNewManager() {
        return null;
    }

    public void delete() {
        isUserDeleted = true;
        if (dao instanceof MemberDAO) {
            // 일반회원인 경우, 회원탈퇴를 진행한다.
            deleteMember();
        } else {

        }
    }

    private void deleteMember() {
        try {
            System.out.println(UserPage.MEMBER_DELETE_TITLE);
            String yesOrNo = input.readLine();
            if (!yesOrNo.equalsIgnoreCase("Y")) {
                System.out.println(UserPage.MEMBER_NOT_DELETE);
                return;
            }
            boolean ack = dao.deleteUserInfo();
            if (ack) {
                quitMenu = true;
                System.out.println(UserPage.MEMBER_DELETE);
            } else {
                System.out.println(UserPage.MEMBER_NOT_DELETE);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void exitMenu() {
        quitMenu = true;
        System.out.println(UserPage.MEMBER_MENU_PREVIOUS);
    }
}
