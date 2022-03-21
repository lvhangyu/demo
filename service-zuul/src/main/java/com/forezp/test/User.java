package com.forezp.test;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    private Long id;
    private String username;
    private String password;
//    private List<Role> authorities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public void setAuthorities(List<Role> authorities) {
//        this.authorities = authorities;
//    }


}
