package exercise_v1.constant;

public enum InputInfo {

    INPUT_ID("아이디를 입력해주세요."),
    INPUT_PWD("비밀번호를 입력해주세요."),
    INPUT_COMPANY_NAME("소속사를 입력해주세요."),
    INPUT_NAME("이름을 입력해주세요."),
    INPUT_PHONE("연락처를 입력해주세요.(입력 형식: 010-XXXX-XXXX)"),
    INPUT_EMAIL("이메일을 입력해주세요."),
    INPUT_COMPANY_CODE("사업자등록번호를 입력해주세요.(형식은 XXX-XX-XXXXX)"),
    INPUT_ADDRESS("주소지를 입력해주세요."),
    INPUT_MANAGER_POSITION("관리자 직급을 입력하세요.(창고관리자, 총관리자 중 택1)");

    private final String msg;

    InputInfo(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
