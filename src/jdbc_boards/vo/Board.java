package jdbc_boards.vo;

import lombok.*;

import java.util.Date;

@ToString
public class Board {

    private int bno;
    private String bTitle;
    private String bContent;
    private String bWriter;
    private Date bDate;

    public Board() {}

    public Board(int bno, String bTitle, String bContent, String bWriter, Date bDate) {
        this.bno = bno;
        this.bTitle = bTitle;
        this.bContent = bContent;
        this.bWriter = bWriter;
        this.bDate = bDate;
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getbTitle() {
        return bTitle;
    }

    public void setbTitle(String bTitle) {
        this.bTitle = bTitle;
    }

    public String getbContent() {
        return bContent;
    }

    public void setbContent(String bContent) {
        this.bContent = bContent;
    }

    public String getbWriter() {
        return bWriter;
    }

    public void setbWriter(String bWriter) {
        this.bWriter = bWriter;
    }

    public Date getbDate() {
        return bDate;
    }

    public void setbDate(Date bDate) {
        this.bDate = bDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Board that){
            return bno == that.bno;
        }
        throw new ClassCastException("비교할 대상의 타입이 올바르지 않습니다.");
    }
}
