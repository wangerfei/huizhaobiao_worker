package com.huizhaobiao.User.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userPost")
public class UserPost {
    @Id
   private  int id;
   private  String userwork;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserwork() {
        return userwork;
    }

    public void setUserwork(String userwork) {
        this.userwork = userwork;
    }
}
