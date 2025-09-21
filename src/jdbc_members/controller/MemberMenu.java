package jdbc_members.controller;

import jdbc_members.model.MemberDAO;
import jdbc_members.vo.Member;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MemberMenu {

    private static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private static boolean quit;

    private final MemberDAO dao;

    public MemberMenu(){
        dao = new MemberDAO();
    }

    public void run() throws IOException {
        while(!quit) {
            System.out.println("메인 메뉴: 1.Create | 2.Search | 3.Update | 4.Clear | 5.Exit");
            System.out.println("메뉴 선택:");
            int choice = 0;

            try {
                choice = Integer.parseInt(input.readLine());
            } catch (IOException e) {
                System.out.println("입력도중 에러 발생");
            } catch (NumberFormatException e1) {
                System.out.println("숫자만 입력하라니까");
            } catch (Exception e2) {
                System.out.println("꿰엑 에라 모르겠다.");
            }

            switch (choice) {
                case 1 -> createBoard();
                case 2 -> searchMember();
                case 3 -> updateMember();
                case 4 -> clearMember();
                case 5 -> exitMenu();
            }
        }
    }

    public Member memberDataInput() throws IOException {
        Member member = new Member();
        System.out.println("새로운 회원 등록");
        System.out.println("회원아이디 입력");
        String userID = input.readLine();
        member.setmUserID(userID);

        System.out.println("비밀번호 입력");
        String pwd = input.readLine();
        member.setmPwd(pwd);

        System.out.println("이메일 입력");
        String email = input.readLine();
        member.setmEmail(email);

        System.out.println("핸드폰번호 입력");
        String hp = input.readLine();
        member.setmHp(hp);

        return member;
    }

    public String userIDInput() throws IOException {
        System.out.println("회원아이디 입력");
        String userID = input.readLine();
        return userID;
    }

    public void createBoard() throws IOException {
        Member row = memberDataInput();
        boolean createACK = dao.createMember(row);

        // 비즈니스 로직 처리 결과로 반환된 ack를 컨트롤러가 수집
        // 컨트롤러는 반환받은 ack값을 바탕으로 피드백
        if(createACK) {
            System.out.println("회원 가입이 완료되었습니다.");
        } else {
            System.out.println("회원 가입 실패, 다시 시도 부탁드립니다. ");
            //원하는 위치로 이동
        }
    }

    public void searchMember() throws IOException {
        System.out.println("회원 조회 방식을 선택해주세요.");
        System.out.println("보조 메뉴: 1.Search One | 2.Search All");
        int menu = Integer.parseInt(input.readLine());

        switch (menu) {
            case 1 -> searchOne();
            case 2 -> searchAll();
        }
    }

    public void searchOne() throws IOException {
        String userID = userIDInput();
        Member searchedOne = dao.searchOne(userID);
        if (searchedOne != null) {
            System.out.println("검색 결과:");
            System.out.println(searchedOne);
        } else {
            System.out.println("아이디에 해당하는 회원이 없습니다.");
        }
    }

    public void searchAll() {
        System.out.println("전체 회원을 조회합니다.");
        List<Member> boardList = dao.selectAll();
        boardList.forEach(System.out::println);
        System.out.println("전체 회원 조회를 종료합니다.");
    }

    public void clearMember() throws IOException {
        String userID = userIDInput();
        boolean deleteACK = dao.deleteMember(userID);
        if (deleteACK) {
            System.out.println("회원 탈퇴가 완료되었습니다.");
        } else {
            System.out.println("회원 탈퇴를 완료하지 못했습니다.");
        }
    }

    public void exitMenu() {
        quit = true;
        System.out.println("회원 메뉴를 종료합니다.");
    }

    public void updateMember() throws IOException {
        System.out.println("회원 정보 수정");
        String userID = userIDInput();
        Member toUpdate = dao.searchOne(userID);

        System.out.println("변경할 정보를 선택하세요: 1.비밀번호 | 2.이메일 | 3.핸드폰번호");
        int menuOption = Integer.parseInt(input.readLine());

        switch (menuOption) {
            case 1:
                System.out.println("비밀번호를 입력하세요.");
                String pwd = input.readLine();
                toUpdate.setmPwd(pwd);
                break;
            case 2:
                System.out.println("이메일을 입력하세요.");
                String email = input.readLine();
                toUpdate.setmEmail(email);
                break;
            case 3:
                System.out.println("핸드폰번호를 입력하세요");
                String hp = input.readLine();
                toUpdate.setmHp(hp);
                break;
        }

        boolean updateACK = dao.updateMember(menuOption, toUpdate);
        if (updateACK) {
            System.out.println("회원정보가 성공적으로 수정되었습니다.");
        } else {
            System.out.println("회원정보를 수정하지 못했습니다.");
        }
    }
}
