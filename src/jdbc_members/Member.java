package jdbc_members;

import java.util.Date;

public class Member {

    private int mSeq;
    private String mUserID;
    private String mPwd;
    private String mEmail;
    private String mHp;
    private Date mRegistDate;
    private int mPoint;

    public Member() {
    }

    public Member(int mSeq, String mUserID, String mPwd, String mEmail, String mHp, Date mRegistDate, int mPoint) {
        this.mSeq = mSeq;
        this.mUserID = mUserID;
        this.mPwd = mPwd;
        this.mEmail = mEmail;
        this.mHp = mHp;
        this.mRegistDate = mRegistDate;
        this.mPoint = mPoint;
    }

    public int getmSeq() {
        return mSeq;
    }

    public void setmSeq(int mSeq) {
        this.mSeq = mSeq;
    }

    public String getmUserID() {
        return mUserID;
    }

    public void setmUserID(String mUserID) {
        this.mUserID = mUserID;
    }

    public String getmPwd() {
        return mPwd;
    }

    public void setmPwd(String mPwd) {
        this.mPwd = mPwd;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmHp() {
        return mHp;
    }

    public void setmHp(String mHp) {
        this.mHp = mHp;
    }

    public Date getmRegistDate() {
        return mRegistDate;
    }

    public void setmRegistDate(Date mRegistDate) {
        this.mRegistDate = mRegistDate;
    }

    public int getmPoint() {
        return mPoint;
    }

    public void setmPoint(int mPoint) {
        this.mPoint = mPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Member that){
            return mSeq == that.mSeq;
        }
        throw new ClassCastException("비교할 대상의 타입이 올바르지 않습니다.");
    }

    @Override
    public String toString() {
        return "Member{" +
                "mSeq=" + mSeq +
                ", mUser='" + mUserID + '\'' +
                ", mEmail='" + mEmail + '\'' +
                ", mHp='" + mHp + '\'' +
                ", mRegistDate=" + mRegistDate +
                ", mPoint=" + mPoint +
                '}';
    }
}
