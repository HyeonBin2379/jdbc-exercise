package jdbc_boards.Controller;

import jdbc_boards.model.BoardDAO;
import jdbc_boards.vo.Board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BoardMenu {

    private static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    private BoardDAO dao;

    public BoardMenu(){
        dao = new BoardDAO();
    }

    public void boardMenu() throws IOException {
        System.out.println("메인 메뉴: 1.Create | 2.Read | 3.Clear | 4.Exit");
        System.out.println("메뉴 선택:");
        int choice = 0;
        try{
            choice = Integer.parseInt(input.readLine());
        } catch (IOException e){
            System.out.println("입력도중 에러 발생");
        } catch (NumberFormatException e1){
            System.out.println("숫자만 입력하라니까");
        } catch (Exception e2){
            System.out.println("꿰엑 에라 모르겠다.");
        }
        switch (choice){
            case 1:
                //사용자에게 title,content를 입력받아서 Board 구성하여 createBoard()넘겨주자
                Board row = boardDataInput();
                boolean ack = dao.createBoard(row);

                // 비즈니스 로직 처리 결과로 반환된 ack를 컨트롤러가 수집
                // 컨트롤러는 반환받은 ack값을 바탕으로 피드백
                if(ack) {
                    System.out.println("글이 성공적으로 입력되었습니다.");
                } else {
                    System.out.println("입력 실패, 다시 시도 부탁드립니다. ");
                    //원하는 위치로 이동
                }
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }

    }

    public Board boardDataInput() throws IOException {
        Board board = new Board();

        System.out.println("새로운 글 입력");
        System.out.println("제목 입력");
        String title = input.readLine();
        board.setBtitle(title);

        System.out.println("내용 입력");
        String content = input.readLine();
        board.setBcontent(content);

        System.out.println("글쓴이 입력");
        String writer = input.readLine();
        board.setBwriter(writer);
        return board;
    }
}
