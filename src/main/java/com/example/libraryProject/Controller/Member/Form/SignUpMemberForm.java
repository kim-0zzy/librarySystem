package com.example.libraryProject.Controller.Member.Form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpMemberForm {

    @NotBlank(message = "반드시 입력해주십시오.")
    private String username;
    @NotBlank(message = "반드시 입력해주십시오.")
    private String password;
    @NotBlank(message = "반드시 입력해주십시오.")
    private String tel;
    @NotBlank(message = "반드시 입력해주십시오.")
    private String address_LoadName;
    @NotBlank(message = "반드시 입력해주십시오.")
    private String address_Detail;

}
