package com.example.libraryProject.Controller.Member.Form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResponseMemberForm {

    @NotBlank(message = "반드시 입력해주십시오.")
    private String username;
    @NotBlank(message = "반드시 입력해주십시오.")
    private String code;

    public ResponseMemberForm(String username, String code) {
        this.username = username;
        this.code = code;
    }
}
