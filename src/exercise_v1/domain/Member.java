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
public class Member extends User {

    private String companyCode;
    private String address;
    private boolean login;
    private Date start_date;
    private Date expired_date;

    @Override
    public String toString() {
        return "Member{" +
                "companyCode='" + companyCode + '\'' +
                ", address='" + address + '\'' +
                ", login=" + login +
                ", start_date=" + start_date +
                ", expired_date=" + expired_date +
                ", id='" + getId() + '\'' +
                ", pwd='" + getPwd() + '\'' +
                ", name='" + getName() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", type='" + getType() + '\'' +
                '}';
    }
}
