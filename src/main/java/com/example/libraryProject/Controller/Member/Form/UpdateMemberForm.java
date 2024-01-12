package com.example.libraryProject.Controller.Member.Form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateMemberForm {

    private String tel;
    private String address_LoadName;
    private String address_Detail;
}
