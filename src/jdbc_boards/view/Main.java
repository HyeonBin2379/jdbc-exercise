package jdbc_boards.view;

import jdbc_boards.Controller.BoardMenu;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            BoardMenu boardMenu = new BoardMenu();  // 강한 결합
            boardMenu.boardMenu();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
