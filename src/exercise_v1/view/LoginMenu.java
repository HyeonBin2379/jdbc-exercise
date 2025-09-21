package exercise_v1.view;

public enum LoginMenu {

    START_MENU("""
            
            """);

    private String pages;

    LoginMenu(String pages) {
        this.pages = pages;
    }

    public String getPages() {
        return pages;
    }
}
