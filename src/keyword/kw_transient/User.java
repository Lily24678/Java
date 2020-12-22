package keyword.kw_transient;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private transient String passwd;
    public static int age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
