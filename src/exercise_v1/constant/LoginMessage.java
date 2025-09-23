package exercise_v1.constant;

public enum LoginMessage {

    LOGIN_MENU_TITLE("""
            =========================================================
            \t\t\t로그인 화면
            =========================================================
            1.로그인 | 2.회원가입 | 3.아이디찾기 | 4.비밀번호찾기 | 5.종료
            =========================================================
            메뉴를 선택해주세요.
            """),
    SUB_MENU_TITLE("""
            =========================================================
            %s
            =========================================================
            """),
    LOGIN("로그인"),
    SIGN_UP("회원가입"),
    FIND_ID("아이디 찾기"),
    FIND_PWD("비밀번호 찾기"),
    EXIT_LOGIN_MENU("로그인 메뉴를 종료합니다."),

    INPUT_ID("아이디를 입력해주세요."),
    INPUT_PWD("비밀번호를 입력해주세요."),
    INPUT_EMAIL("이메일을 입력해주세요."),

    INPUT_MEMBERSHIP_TYPE("""
            회원가입유형을 선택해주세요.
            1.일반회원     2.관리자
            """);

    private final String page;

    LoginMessage(String page) {
        this.page = page;
    }

    public static void print(LoginMessage loginMenu) {
        System.out.printf(SUB_MENU_TITLE.toString(), loginMenu.page);
    }

    @Override
    public String toString() {
        return page;
    }
}
