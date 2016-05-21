package shapio.largo.model;

/**
 * Created by TheOSka on 21/5/2016.
 */
public class Mail {
    Integer profileImage;
    String userName;
    String date;
    String mailID;

    public void setDate(String date) {
        this.date = date;
    }

    public void setMailID(String mailID) {
        this.mailID = mailID;
    }

    public void setProfileImage(Integer profileImage) {
        this.profileImage = profileImage;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getProfileImage() {
        return profileImage;
    }

    public String getDate() {
        return date;
    }

    public String getMailID() {
        return mailID;
    }

    public String getUserName() {
        return userName;
    }
}
