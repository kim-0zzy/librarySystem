package com.example.libraryProject.Controller.Manager;

import com.example.libraryProject.Controller.Manager.Form.SignUpManagerForm;
import com.example.libraryProject.DTO.ManagerDTO;
import com.example.libraryProject.DTO.MessageResponseDTO;
import com.example.libraryProject.Entity.Manager;

import com.example.libraryProject.Service.ManagerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
public class APIManagerController {

    private final ManagerService managerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/apiManager/signUp")
    public ResponseEntity<MessageResponseDTO> signUpManager(@RequestBody SignUpManagerForm signUpManagerForm) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        if (managerService.findManagerByName(signUpManagerForm.getManagerName()) != null) {
            return new ResponseEntity<>(new MessageResponseDTO(
                    "Exist Manager Failure", HttpStatus.BAD_REQUEST.value(), signUpManagerForm.getManagerName()),
                    httpHeaders, HttpStatus.BAD_REQUEST);
        }
        Manager manager = new Manager(signUpManagerForm.getManagerName(), signUpManagerForm.getRealName(),
                passwordEncoder.encode(signUpManagerForm.getPassword()));
        managerService.join_Manager(manager);
        ManagerDTO managerDTO = managerService.buildManager(manager);


        return new ResponseEntity<>(new MessageResponseDTO("Register Success", HttpStatus.OK.value(), managerDTO), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<MessageResponseDTO> logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        response.sendRedirect("/");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return new ResponseEntity<>(new MessageResponseDTO("Logout Success", HttpStatus.OK.value(), new redirectURLInfo("/")), httpHeaders, HttpStatus.OK);

    }

    @GetMapping("/apiManager/findOne")
    public ResponseEntity<MessageResponseDTO> findOneManager(@RequestParam String managerName) {

        ManagerDTO managerDTO = managerService.buildManager(managerService.findManagerByName(managerName));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return new ResponseEntity<>(new MessageResponseDTO("find Success", HttpStatus.OK.value(), managerDTO), httpHeaders, HttpStatus.OK);
    }

    static class redirectURLInfo{
        private String redirectURL;
        public redirectURLInfo(String redirectURL) {
            this.redirectURL = redirectURL;
        }
    }
}
