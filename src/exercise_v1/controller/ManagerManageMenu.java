package exercise_v1.controller;

import exercise_v1.constant.LoginPage;
import exercise_v1.constant.UserPage;
import exercise_v1.domain.Manager;
import exercise_v1.domain.User;
import exercise_v1.model.ManagerDAO;

import java.io.IOException;

public class ManagerManageMenu implements UserManageMenu {

    private final ManagerDAO managerDAO;

    public ManagerManageMenu(Manager manager) {
        this.managerDAO = new ManagerDAO(manager);
    }

    @Override
    public void printMenu() {
        System.out.print(UserPage.MANAGER_MEMBER_MENU_TITLE);
    }

    @Override
    public void select() throws IOException {
        System.out.println(UserPage.MANAGER_SELECT_TITLE);
        String menuNum = input.readLine();
        switch (menuNum) {
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
        }
    }

    public void selectCurrentUser() {
        System.out.println(UserPage.CURRENT_USER_SELECT);
    }

    @Override
    public void update() throws IOException {
        System.out.println(UserPage.MANAGER_UPDATE_TITLE);
        String menuNum = input.readLine();
        switch (menuNum) {
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
        }
    }

    public void updateCurrentMember() throws IOException {

    }

    public void updateByManager() {
        System.out.println(UserPage.MANAGER_UPDATE_TITLE);

    }

    public User inputNewManager() throws IOException {
        System.out.println(UserPage.MANAGER_UPDATE_SUBTITLE);
        System.out.println(LoginPage.INPUT_PWD);
        String userPwd = input.readLine();
        System.out.println(LoginPage.INPUT_COMPANY_NAME);
        String companyName = input.readLine();
        System.out.println(LoginPage.INPUT_PHONE);
        String phone = input.readLine();
        System.out.println(LoginPage.INPUT_EMAIL);
        String email = input.readLine();

        return null;
    }

    @Override
    public boolean delete() throws IOException {
        System.out.println(UserPage.MANAGER_DELETE_TITLE);
        String menuNum = input.readLine();
        switch (menuNum) {
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
        }
        return false;
    }

    private void deleteByManager() {

    }

    @Override
    public boolean exitMenu() {
        System.out.println();
        return true;
    }
}
