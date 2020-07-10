package com.sgtcadet.keycloakspringbootmicroservice.claims.keycloak;

import com.sgtcadet.keycloakspringbootmicroservice.claims.Claims;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Set;

@Service
public class KeycloakServiceImpl implements KeycloakService{

    @Override
    public Claims getClaims(Principal principal) {
        KeycloakAuthenticationToken keycloakAuthenticationToken = (KeycloakAuthenticationToken) principal;
        KeycloakSecurityContext keycloakSecurityContext;
        Claims claims = newClaimsObject();
        try{
            keycloakSecurityContext = (KeycloakSecurityContext) keycloakAuthenticationToken.getCredentials();
        }catch(NullPointerException e){
            return claims;
        }
        AccessToken token = keycloakSecurityContext.getToken();
        Set<String> roles = keycloakAuthenticationToken.getAccount().getRoles();
        claims.setUserId(token.getId());
//        claims.setUserId(Long.parseLong(token.getId()));
        claims.setUsername(token.getPreferredUsername());
        claims.setSubjectId(token.getSubject());
        claims.setRoles(roles);
        return claims;
    }

    @Autowired
    private ObjectFactory<Claims> claimsObjectFactory;

    protected String getUsername(HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        KeycloakAuthenticationToken keycloakAuthenticationToken = (KeycloakAuthenticationToken) principal;

        KeycloakSecurityContext keycloakSecurityContext = (KeycloakSecurityContext) keycloakAuthenticationToken.getCredentials();

        AccessToken token = keycloakSecurityContext.getToken();


        return token.getPreferredUsername();
    }

    protected String getUserId(HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        KeycloakAuthenticationToken keycloakAuthenticationToken = (KeycloakAuthenticationToken) principal;

        KeycloakSecurityContext keycloakSecurityContext = (KeycloakSecurityContext) keycloakAuthenticationToken.getCredentials();

        AccessToken token = keycloakSecurityContext.getToken();


        return token.getId();
    }

    protected String getUserEmail(HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        KeycloakAuthenticationToken keycloakAuthenticationToken = (KeycloakAuthenticationToken) principal;

        KeycloakSecurityContext keycloakSecurityContext = (KeycloakSecurityContext) keycloakAuthenticationToken.getCredentials();

        AccessToken token = keycloakSecurityContext.getToken();


        return token.getEmail();
    }

    protected boolean hasRole(HttpServletRequest request, String roleName) {

        Principal principal = request.getUserPrincipal();

        KeycloakAuthenticationToken keycloakAuthenticationToken = (KeycloakAuthenticationToken) principal;


        return keycloakAuthenticationToken.getAccount().getRoles().contains(roleName.toLowerCase());

    }

    public Claims newClaimsObject() {
        return claimsObjectFactory.getObject();
    }

}
