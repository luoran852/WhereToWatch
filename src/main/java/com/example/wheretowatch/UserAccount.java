package com.example.wheretowatch;

/**
 * 사용자 계정 정보 모델 클래스
 */

public class UserAccount {

    private String userName;
    private String idToken;     // Firebase Uid (고유 토큰정보)
    private String emailId;     // 이메일 아이디
    private String password;    // 비밀번호

    // 추가적으로 닉네임, 프로필 이미지 url, 앱 토큰 등 저장 가능

    /**
     * firebase realtime database를 쓸 때 모델클래스를 이용해서 갖고와야할 때 빈 생성자를 만들어주어야 함
     * -> 안 그러면 database 조회시 오류
     */
    public UserAccount(String userName, String email, String password) {
        this.userName = userName;
        this.emailId = email;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
