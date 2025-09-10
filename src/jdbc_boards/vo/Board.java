package jdbc_boards.vo;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board {

    private int bno;
    private String bTitle;
    private String bContent;
    private String bWriter;
    private Date bDate;

    @Override
    public boolean equals(Object o) {
        if (o instanceof Board that){
            return bno == that.bno;
        }
        throw new IllegalArgumentException("비교할 대상이 올바르지 않습니다.");
    }
}
