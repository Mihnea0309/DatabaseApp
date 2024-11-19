package com.BD.Service_Auto.auth;

import com.BD.Service_Auto.model.Angajati;
import com.BD.Service_Auto.service.AngajatiService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AngajatiService angajatiService;


    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(HttpSession session, @RequestBody Angajati angajati) {
        boolean angajatPrimit = angajatiService.authenticate(angajati.getEmail(), angajati.getParolaSite());

        if (angajatPrimit) {
            session.setAttribute("employeeId", angajati.getId());
            return ResponseEntity.ok(Map.of("message", "Login successful"));
        } else {
            log.info("Parola primita este " + angajati.getParolaSite() + " angajatuPrimit este " + angajatPrimit);
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid credentials"));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Delogare reușită!");
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody Angajati angajati) {
        String success = angajatiService.register(angajati.getEmail(), angajati.getParolaSite());
        if (success.equals("ok")) {
            return ResponseEntity.ok(Map.of("message", "Account created successfully"));
        } else if (success.equals("The password didnt match one of the conditions")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message","Account creation failed. The password didnt match one of the conditions"));
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message","Account creation failed. The email was not found"));
        }
    }
}
