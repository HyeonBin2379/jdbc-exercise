package exercise_v1.domain;

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

    @Override
    public String toString() {
        return "Manager{" +
                "id='" + getId() + '\'' +
                ", pwd='" + getPwd() + '\'' +
                ", name='" + getName() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", type='" + getType() + '\'' +
                ", login=" + login +
                ", hireDate=" + hireDate +
                ", position='" + position + '\'' +
                '}';
    }
}
