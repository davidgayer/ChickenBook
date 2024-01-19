package cz.gayerdavid.ChickenBook.model;

import jakarta.persistence.Entity;

@Entity
public class User {

    private Long id;

    private String name;

    private String email;

    private String password;

    private String city;

    private String bio;

    private Byte[] profilePicture;

    
}
