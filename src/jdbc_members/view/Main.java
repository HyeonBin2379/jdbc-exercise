package jdbc_members.view;

import jdbc_members.controller.MemberMenu;

import java.io.IOException;

public class Main {

    // jdbc_boards 패키지처럼 controller, model, util, view, vo 패키지 분리하고 코드 작성하기
    public static void main(String[] args) {
        try {
            MemberMenu memberMenu = new MemberMenu();  // 강한 결합
            memberMenu.run();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
