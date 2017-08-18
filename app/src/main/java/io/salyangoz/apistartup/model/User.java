package io.salyangoz.apistartup.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by erkndeveci on 17/04/2017.
 */

public class User implements Serializable{

    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public User(String name, String email, String password) {

        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    @Override
    public String toString() {

        return name.toString();
    }
}
