package exercise_v1.controller;

import exercise_v1.constant.LoginPage;
import exercise_v1.constant.UserPage;
import exercise_v1.domain.Manager;
import exercise_v1.domain.User;
import exercise_v1.model.ManagerDAO;

import java.io.IOException;

public class ManagerManageMenu implements UserManageMenu {

    private final ManagerDAO dao;
    private final Manager currentManager;

    public ManagerManageMenu(Manager manager) {
        this.currentManager = manager;
        this.dao = new ManagerDAO(manager);
    }

    @Override
    public void printMenu() {
        System.out.print(UserPage.MANAGER_MEMBER_MENU_TITLE);
    }

    @Override
    public void read() throws IOException {
        boolean quitRead = false;
        while (!quitRead) {
            System.out.println(UserPage.MANAGER_SELECT_TITLE);
            String menuNum = input.readLine();
            switch (menuNum) {
                case "1":
                    readOneUserDetail();
                    break;
                case "2":
                    readAllUser();
                    break;
                case "3":
                    readUserByRole();
                    break;
                case "4":
                    quitRead = quit();
                    break;
            }
        }
    }

    public void readOneUserDetail() throws IOException {
        boolean quitRead = false;
        while (!quitRead) {
            System.out.println(UserPage.MANAGER_DETAIL_INFO_TITLE);
            String menuNum = input.readLine();
            switch (menuNum) {
                case "1":
                    readCurrentUser();
                    break;
                case "2":
                    readOtherUser();
                    break;
                case "3":
                    quitRead = quit();
                    break;
            }
        }
    }
    private void readCurrentUser() {
        System.out.println(UserPage.CURRENT_USER_SELECT);
        dao.searchUserDetails();
    }

    private void readOtherUser() throws IOException {
        System.out.println(UserPage.INPUT_ID_FOR_SEARCH);
        String targetID = input.readLine();
    }

    // 권한에 관계없이 전체 회원 조회(승인된 회원의 공통 정보만 조회)
    public void readAllUser() {
        System.out.println(UserPage.MANAGER_SEARCH_ALL);
    }

    //
    public void readUserByRole() {
        System.out.println(UserPage.MANAGER_SEARCH_BY_ROLE);
    }

    @Override
    public void update() throws IOException {
        boolean quitUpdate = false;
        while (!quitUpdate) {
            System.out.println(UserPage.MANAGER_UPDATE_TITLE);
            String menuNum = input.readLine();
            switch (menuNum) {
                case "1":
                    updateCurrentMember();
                    break;
                case "2":
                    updateManagerRole();
                    break;
                case "3":
                    quitUpdate = quit();
                    break;
            }
        }
    }

    public void updateCurrentMember() throws IOException {
        User newUserInfo = inputNewManager();
        boolean ack = dao.updateUserInfo(newUserInfo);
        System.out.println(ack ? UserPage.MEMBER_UPDATE : UserPage.MEMBER_UPDATE_FAILED);
    }

    public void updateManagerRole() {
        String position = currentManager.getPosition();
        if (!position.equals("총관리자")) {
            System.out.println(UserPage.NOT_HAVE_PERMISSION);
            return;
        }

    }

    public User inputNewManager() throws IOException {
        System.out.println(UserPage.MANAGER_UPDATE_SUBTITLE);
        System.out.println(LoginPage.INPUT_PWD);
        String userPwd = input.readLine();
        System.out.println(LoginPage.INPUT_NAME);
        String name = input.readLine();
        System.out.println(LoginPage.INPUT_PHONE);
        String phone = input.readLine();
        System.out.println(LoginPage.INPUT_EMAIL);
        String email = input.readLine();

        User newInfo = new User();
        newInfo.setPwd(userPwd);
        newInfo.setName(name);
        newInfo.setPhone(phone);
        newInfo.setEmail(email);
        return newInfo;
    }

    @Override
    public boolean delete() throws IOException {
        boolean quitDelete = false;
        boolean isDeleted = false;
        while (!quitDelete && !isDeleted) {
            System.out.println(UserPage.MANAGER_DELETE_TITLE);
            String menuNum = input.readLine();
            switch (menuNum) {
                case "1":
                    isDeleted = deleteCurrentUser();
                    break;
                case "2":
                    deleteManagerRole();
                    break;
                case "3":
                    quitDelete = quit();
                    break;
            }
        }
        return isDeleted;
    }

    public boolean deleteCurrentUser() {
        try {
            System.out.print(UserPage.USER_DELETE_TITLE);
            String yesOrNo = input.readLine();
            if (!yesOrNo.equalsIgnoreCase("Y")) {
                System.out.println(UserPage.USER_NOT_DELETE);
                return false;
            }

            boolean ack = dao.deleteUserInfo();
            if (ack) {
                System.out.println(UserPage.USER_DELETE);
                return true;
            }
            System.out.println(UserPage.USER_DELETE_FAILED);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void deleteManagerRole() {
        String position = currentManager.getPosition();
        if (!position.equals("총관리자")) {
            System.out.println(UserPage.NOT_HAVE_PERMISSION);
            return;
        }
    }

    private boolean quit() {
        System.out.println(UserPage.USER_MENU_PREVIOUS);
        return true;
    }
}
