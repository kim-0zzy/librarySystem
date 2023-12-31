package com.example.libraryProject.DTO;

import com.example.libraryProject.Entity.Address;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {
    private String username;
    private String tel;
    private String code;
    private Address address;
}
