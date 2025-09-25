package exercise_v1.constant;

import exercise_v1.domain.Manager;
import exercise_v1.domain.Member;

public enum UserPage {

    // 일반회원, 관리자 공통 기능
    MEMBER_MEMBER_MENU_TITLE("""
            ------------------<< 일반회원 회원관리 >>------------------
            1.내정보조회 | 2.내정보수정 | 3.탈퇴 | 4.뒤로가기
            ---------------------------------------------------------
            메뉴를 선택해주세요.
            """),
    CURRENT_USER_SELECT("현재 회원정보를 조회합니다."),

    MEMBER_UPDATE_TITLE("--------------------<< 일반회원 회원정보 수정 >>--------------------------"),
    MEMBER_UPDATE("현재 회원정보의 변경이 완료되었습니다."),
    MEMBER_UPDATE_FAILED("현재 회원정보의 변경에 실패했습니다."),

    MEMBER_DELETE_TITLE("""
            --------------------<< 일반회원 회원탈퇴 >>--------------------------
            회원탈퇴를 진행하시겠습니까?(Y 입력 시 진행)
            """),
    MEMBER_DELETE("회원 탈퇴가 완료되었습니다. 로그인 메뉴로 되돌아갑니다."),
    MEMBER_NOT_DELETE("회원탈퇴를 진행하지 않습니다. 회원관리 메뉴로 되돌아갑니다."),
    MEMBER_DELETE_FAILED("회원탈퇴를 진행할 수 없습니다. 작업을 중단합니다."),

    // 관리자 전용 회원관리 기능
    MANAGER_MEMBER_MENU_TITLE("""
            -------------------<< 관리자 회원관리 >>-------------------
            1.회원 조회 | 2.회원 수정 | 3.회원 삭제 | 4.뒤로가기
            ---------------------------------------------------------
            메뉴를 선택해주세요.
            """),
    MANAGER_SELECT_TITLE("""
            -------------------<< 관리자 전용 조회 >>------------------
            1.회원상세보기 | 2.전체회원조회 | 3.권한별 회원조회 | 4.뒤로가기
            ---------------------------------------------------------
            메뉴를 선택해주세요.
            """),
    MANAGER_DETAIL_INFO("""
            -------------------<< 회원상세보기 >>----------------------
            조회할 회원의 아이디를 입력하세요.
            """),
    MANAGER_DETAIL("""
            --------------------<< %1$s 회원정보 조회 >>--------------------------
            아이디: %1$s
            이름: %2$s
            연락처: %3$s
            이메일: %4$s
            입사일: %5$s
            직급: %6$s
            ----------------------------------------------------------
            """),
    MEMBER_DETAIL("""
            --------------------<< %1$s 회원정보 조회 >>--------------------------
            아이디: %1$s
            소속사: %2$s
            연락처: %3$s
            이메일: %4$s
            사업자번호: %5$s
            주소: %6$s
            계약시작일: %7$s
            계약종료일: %8$s
            ----------------------------------------------------------
            """),

    MANAGER_SEARCH_ALL("""
            -------------------<< 전체회원조회 >>-----------------------
            """),
    MANAGER_SEARCH_BY_MEMBER_TYPE("""
            -------------------<< 권한별 회원조회 >>--------------------
            1.일반회원     2.관리자
            ----------------------------------------------------------
            어떤 권한을 보유한 회원을 조회할 것인지 선택해주세요.
            """),

    MANAGER_UPDATE_TITLE("""
            -------------------<< 관리자 전용 수정 >>------------------
            1.회원정보수정 | 2.회원권한수정 | 3.뒤로가기
            ---------------------------------------------------------
            메뉴를 선택해주세요.
            """),
    MANAGER_UPDATE_SUBTITLE("--------------------<< 관리자 회원정보 수정 >>--------------------------"),
    INPUT_ID_FOR_UPDATE_INFO("정보를 수정할 회원의 아이디를 입력해주세요."),
    INPUT_ID_FOR_UPDATE_ROLE("권한을 변경할 회원의 아이디를 입력해주세요."),

    MANAGER_DELETE_TITLE("""
            -------------------<< 관리자 전용 삭제 >>------------------
            1.회원정보삭제 | 2.회원권한삭제 | 3.뒤로가기
            -----------------------------------------------------------
            메뉴를 선택해주세요.
            """),
    INPUT_ID_FOR_DELETE_INFO("탈퇴시킬 회원의 아이디를 입력해주세요."),
    INPUT_ID_FOR_DELETE_ROLE("권한을 삭제할 회원의 아이디를 입력해주세요."),

    NOT_HAVE_PERMISSION("해당 작업을 수행할 권한이 없습니다."),
    USER_MENU_PREVIOUS("이전 메뉴로 돌아갑니다.");

    private final String page;

    UserPage(String page) {
        this.page = page;
    }

    public static void memberDetails(Member member) {
        System.out.printf(MEMBER_DETAIL.toString(),
                member.getId(), member.getName(),
                member.getPhone(), member.getEmail(), member.getCompanyCode(),
                member.getAddress(), member.getStart_date(), member.getExpired_date());
    }

    public static void managerDetails(Manager manager) {
        System.out.printf(MANAGER_DETAIL.toString(),
                manager.getId(), manager.getPwd(), manager.getName(),
                manager.getPhone(), manager.getEmail(), manager.getHireDate(), manager.getType());
    }

    @Override
    public String toString() {
        return page;
    }
}
