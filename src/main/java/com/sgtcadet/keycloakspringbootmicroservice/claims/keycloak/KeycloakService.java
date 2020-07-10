package com.sgtcadet.keycloakspringbootmicroservice.claims.keycloak;

import com.sgtcadet.keycloakspringbootmicroservice.claims.Claims;

import java.security.Principal;

public interface KeycloakService {
    Claims getClaims(Principal principal);
}
