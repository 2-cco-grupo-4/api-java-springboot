package com.example.picmejava.instagram.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AccessToken {

    private String access_token;
    private String user_id;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
