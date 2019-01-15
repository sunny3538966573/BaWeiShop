package com.bwie.bean.login;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * date: 2018/12/28.
 * Created by Administrator
 * function:
 */
@Entity
public class Login {
        /**
         * headPic : http://172.17.8.100/images/small/head_pic/2018-12-20/20181220194510.jpg
         * nickName : 35_N3B12
         * phone : 17701283117
         * sessionId : 1546066355444107
         * sex : 1
         * userId : 107
         */
        private String headPic;
        private String nickName;
        private String phone;
        private String sessionId;
        private int sex;
        private int statues;


    public int getStatues() {
        return statues;
    }

    public void setStatues(int statues) {
        this.statues = statues;
    }

    @Id
        private long userId;
        @Generated(hash = 1411600939)
        public Login(String headPic, String nickName, String phone, String sessionId, int sex,
                int statues, long userId) {
            this.headPic = headPic;
            this.nickName = nickName;
            this.phone = phone;
            this.sessionId = sessionId;
            this.sex = sex;
            this.statues = statues;
            this.userId = userId;
        }

        @Generated(hash = 1827378950)
        public Login() {
        }
        public String getHeadPic() {
            return this.headPic;
        }
        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }
        public String getNickName() {
            return this.nickName;
        }
        public void setNickName(String nickName) {
            this.nickName = nickName;
        }
        public String getPhone() {
            return this.phone;
        }
        public void setPhone(String phone) {
            this.phone = phone;
        }
        public String getSessionId() {
            return this.sessionId;
        }
        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }
        public int getSex() {
            return this.sex;
        }
        public void setSex(int sex) {
            this.sex = sex;
        }
        public long getUserId() {
            return this.userId;
        }
        public void setUserId(int userId) {
            this.userId = userId;
        }
        public void setUserId(long userId) {
            this.userId = userId;
        }

}
