package exercise_v1.constant;

public enum Membership {

    MEMBER_MEMBER_INFO_TITLE("""
            ---------------------<< 일반회원 회원관리 >>--------------------
            1.회원 조회 | 2.회원 수정 | 3.회원 탈퇴 | 4.뒤로가기
            ---------------------------------------------------------
            메뉴를 선택해주세요.
            """),
    MANAGER_MEMBER_INFO_TITLE("""
            ---------------------<< 관리자 회원관리 >>--------------------
            1.회원 조회 | 2.회원 수정 | 3.회원 삭제 | 4.뒤로가기
            ---------------------------------------------------------
            메뉴를 선택해주세요.
            """);

    private final String page;

    Membership(String page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return page;
    }
}
