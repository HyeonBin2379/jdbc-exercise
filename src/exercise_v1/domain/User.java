package exercise_v1.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class User {

    private String id;
    private String pwd;
    private String name;
    private String phone;
    private String email;
    private String type;

    @Override
    public boolean equals(Object o) {
        if (o instanceof User that) {
            return this.id.equals(that.id);
        }
        throw new ClassCastException("비교할 수 없는 클래스 타입입니다.");
    }
}
