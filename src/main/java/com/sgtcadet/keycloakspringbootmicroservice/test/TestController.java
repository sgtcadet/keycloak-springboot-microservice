package com.sgtcadet.keycloakspringbootmicroservice.test;

import com.sgtcadet.keycloakspringbootmicroservice.claims.Claims;
import com.sgtcadet.keycloakspringbootmicroservice.claims.keycloak.KeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired KeycloakService keycloakService;

    @GetMapping(value = "/anonymous")
    public ResponseEntity<String> getAnonymous(){
        return ResponseEntity.ok("Hello Anonymouse");
    }

    @RolesAllowed("user")
    @GetMapping(value = "/user")
    public ResponseEntity<String> getUser() {
        return ResponseEntity.ok("Hello User");
    }

    @RolesAllowed("admin")
    @GetMapping(value = "/admin")
    public ResponseEntity<String> getAdmin() {
        return ResponseEntity.ok("Hello Admin");
    }

    @RolesAllowed({"admin","user"})
    @GetMapping(value = "/all-user")
    public ResponseEntity<String> getAllUser(Principal principal) {
        Claims claims = keycloakService.getClaims(principal);
        int i = 0;
        i +=1;

        return ResponseEntity.ok("Hello All User");
    }

}
