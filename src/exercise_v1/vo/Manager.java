package exercise_v1.vo;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Manager extends User {

    private boolean login;
    private Date hireDate;
    private String position;
    private String superId;

    @Override
    public String toString() {
        return "Manager{" +
                "id='" + id + '\'' +
                ", isApproved=" + isApproved +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", type='" + type + '\'' +
                ", login=" + login +
                ", hireDate=" + hireDate +
                ", position='" + position + '\'' +
                ", superId='" + superId + '\'' +
                '}';
    }
}
