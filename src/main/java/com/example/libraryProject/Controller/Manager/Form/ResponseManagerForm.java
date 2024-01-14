package com.example.libraryProject.Controller.Manager.Form;

import lombok.Data;

@Data
public class ResponseManagerForm {

    private String realName;
    private String role;

    public ResponseManagerForm(String realName, String role) {
        this.realName = realName;
        this.role = role;
    }
}
