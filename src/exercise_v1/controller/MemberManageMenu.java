package exercise_v1.controller;

import exercise_v1.constant.LoginPage;
import exercise_v1.constant.UserPage;
import exercise_v1.domain.Member;
import exercise_v1.domain.User;
import exercise_v1.model.MemberDAO;

import java.io.IOException;

public class MemberManageMenu implements UserManageMenu {

    private final MemberDAO dao;

    public MemberManageMenu(Member loginUser) {
        this.dao = new MemberDAO(loginUser);
    }

    public void printMenu() {
        System.out.print(UserPage.MEMBER_MEMBER_MENU_TITLE);
    }

    public void read() throws IOException {
        // 일반회원이면 자신만 조회
        System.out.println(UserPage.CURRENT_USER_SELECT);
        dao.searchUserDetails();
    }

    public void update() throws IOException {
        User newUserInfo = inputNewMember();
        boolean ack = dao.updateUserInfo(newUserInfo);
        System.out.println(ack ? UserPage.MEMBER_UPDATE : UserPage.MEMBER_UPDATE_FAILED);
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

    public boolean delete() {
        try {
            System.out.println(UserPage.USER_DELETE_TITLE);
            String yesOrNo = input.readLine();
            if (!yesOrNo.equalsIgnoreCase("Y")) {
                System.out.println(UserPage.MEMBER_NOT_DELETE);
                return false;
            }

            boolean ack = dao.deleteUserInfo();
            if (ack) {
                System.out.println(UserPage.MEMBER_DELETE);
                return true;
            }
            System.out.println(UserPage.MEMBER_NOT_DELETE);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
