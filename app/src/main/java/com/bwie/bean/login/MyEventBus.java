package com.bwie.bean.login;

/**
 * date: 2019/1/3.
 * Created by Administrator
 * function:
 */
public class MyEventBus {
    private String headPic;
    private String nickname;
    private String password;


    public MyEventBus(String headPic, String nickname, String password) {
        this.headPic = headPic;
        this.nickname = nickname;
        this.password = password;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "MyEventBus{" +
                "headPic='" + headPic + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
