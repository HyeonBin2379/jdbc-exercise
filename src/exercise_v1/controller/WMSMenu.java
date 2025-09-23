package exercise_v1.controller;

import exercise_v1.domain.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WMSMenu {

    private static class LazyHolder {
        private static final WMSMenu WMS_MENU = new WMSMenu();
    }
    private static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private static boolean quitWMS;

    private static User currentUser;

    private WMSMenu() {
    }

    public static WMSMenu getInstance(User loginUser) {
        currentUser = loginUser;
        return LazyHolder.WMS_MENU;
    }

    public void run() {
        System.out.println(currentUser.getType());
    }

    public void printMenu() {
    }
}
