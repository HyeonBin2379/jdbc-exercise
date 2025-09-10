package jdbc_boards.vo;

import lombok.*;

import java.util.Date;

@ToString
public class Board {

    private int bno;
    private String btitle;
    private String bcontent;
    private String bwriter;
    private Date bdate;

    public Board() {}

    public Board(int bno, String bTitle, String bContent, String bWriter, Date bDate) {
        this.bno = bno;
        this.btitle = bTitle;
        this.bcontent = bContent;
        this.bwriter = bWriter;
        this.bdate = bDate;
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getBtitle() {
        return btitle;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle;
    }

    public String getBcontent() {
        return bcontent;
    }

    public void setBcontent(String bcontent) {
        this.bcontent = bcontent;
    }

    public String getBwriter() {
        return bwriter;
    }

    public void setBwriter(String bwriter) {
        this.bwriter = bwriter;
    }

    public Date getBdate() {
        return bdate;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Board that){
            return bno == that.bno;
        }
        throw new ClassCastException("비교할 대상의 타입이 올바르지 않습니다.");
    }
}
