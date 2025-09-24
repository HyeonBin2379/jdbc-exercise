package exercise_v1.controller;

import exercise_v1.constant.MemberManagement;
import exercise_v1.domain.Member;
import exercise_v1.domain.User;
import exercise_v1.model.MemberDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MemberMenu {

    private static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    private final MemberDAO dao;
    private final User user;

    private boolean quitMenu;

    public MemberMenu(User loginUser) {
        this.dao = new MemberDAO();
        this.user = loginUser;
        this.quitMenu = false;
    }

    public void run() {
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
                throw new RuntimeException(e);
            }

        }
    }

    public void printMenu() {
        if (user instanceof Member) {
            System.out.println(MemberManagement.MEMBER_MEMBER_MENU_TITLE);
        } else {
            System.out.println(MemberManagement.MANAGER_MEMBER_MENU_SEARCH);
        }
    }

    public void select() {

    }

    public void update() {

    }

    public void delete() {

    }

    public void exitMenu() {
        quitMenu = true;
        System.out.println(MemberManagement.MEMBER_MENU_PREVIOUS);
    }
}
