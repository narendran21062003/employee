package com.example.Employeedetails.security;

// Run this once to generate a valid secret
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Base64;

public class GenerateSecret {
    public static void main(String[] args) {
        String secret = Base64.getEncoder().encodeToString(
                Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded()
        );
        System.out.println("Generated secret: " + secret);
    }
}
