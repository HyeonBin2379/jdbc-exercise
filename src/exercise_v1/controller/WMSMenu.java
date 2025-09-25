package exercise_v1.controller;

import exercise_v1.constant.WMSPage;
import exercise_v1.domain.Manager;
import exercise_v1.domain.Member;
import exercise_v1.domain.User;
import exercise_v1.model.LoginDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WMSMenu {

    private static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    private final User currentLoginUser;
    private boolean quitWMS;

    private UserManageMenu userManageMenu;

    public WMSMenu(User loginUser) {
        this.currentLoginUser = loginUser;
    }

    public void run() {
        quitWMS = false;
        while (!quitWMS) {
            try {
                if (currentLoginUser instanceof Member member) {
                    memberMenuList(member);
                } else if (currentLoginUser instanceof Manager manager) {
                    managerMenuList(manager);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void memberMenuList(Member member) throws IOException {
        System.out.print(WMSPage.MEMBER_MENU_TITLE);
        String menuNum = input.readLine();
        switch (menuNum) {
            case "1":   // 회원관리
                userManagement(member);
                break;
            case "2":   // 고객센터
                break;
            case "3":   // 재고관리
                break;
            case "4":   // 입고
                break;
            case "5":   // 출고
                break;
            case "6":   // 로그아웃
                logout(member.getId());
                break;
        }
    }

    public void managerMenuList(Manager manager) throws IOException {
        // 관리자 전용 기능이 존재하여 memberMenu(), managerMenu()를 구분
        System.out.print(WMSPage.MANAGER_MENU_TITLE);
        String menuNum = input.readLine();
        switch (menuNum) {
            case "1":   // 회원관리
                userManagement(manager);
                break;
            case "2":   // 고객센터
                break;
            case "3":   // 창고관리
                break;
            case "4":   // 재고관리
                break;
            case "5":   // 입고
                break;
            case "6":   // 출고
                break;
            case "7":   // 로그아웃
                logout(manager.getId());
                break;
        }
    }

    public void userManagement(User user) {
        // 회원 탈퇴 시 자동 종료
        if (user instanceof Manager manager) {
            userManageMenu = new ManagerManageMenu(manager);
            quitWMS = userManageMenu.run();
        } else if (user instanceof Member member) {
            userManageMenu = new MemberManageMenu(member);
            quitWMS = userManageMenu.run();
        }
    }

    public void logout(String userID) {
        LoginDAO.logout(userID);
        quitWMS = true;
    }
}
