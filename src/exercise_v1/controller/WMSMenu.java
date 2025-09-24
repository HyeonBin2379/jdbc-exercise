package exercise_v1.controller;

import exercise_v1.constant.WMSMessage;
import exercise_v1.domain.Manager;
import exercise_v1.domain.Member;
import exercise_v1.domain.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WMSMenu {

    private static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private static boolean quitWMS;

    private final User currentLoginUser;

    public WMSMenu(User loginUser) {
        this.currentLoginUser = loginUser;
    }

    public void run() {
        quitWMS = false;
        if (currentLoginUser instanceof Member member) {
            memberMenu(member);
        } else if (currentLoginUser instanceof Manager manager) {
            managerMenu(manager);
        }
    }

    public void memberMenu(Member member) {
        while (!quitWMS) {
            try {
                System.out.println(WMSMessage.MEMBER_MENU_TITLE);
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
                    case "5":
                        break;
                    case "6":
                        break;
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void managerMenu(Manager manager) {
        // 관리자 전용 기능이 존재하여 memberMenu(), managerMenu()를 구분
        while (!quitWMS) {
            try {
                System.out.println(WMSMessage.MEMBER_MENU_TITLE);
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
                    case "5":
                        break;
                    case "6":
                        break;
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
