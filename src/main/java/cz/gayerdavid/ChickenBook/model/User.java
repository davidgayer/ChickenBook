package cz.gayerdavid.ChickenBook.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "chicken_user")
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Name is required.")
    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Email is required.")
    @Email(message = "Please provide a valid email adress.")
    @NonNull
    @Column(name = "email", nullable = false)
    private String email;

    //TODO: Create Validation controll class for checking user password
    @NotBlank(message = "Password cannot be blank")
    @NonNull
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "city")
    private String city;

    @Column(name = "bio")
    private String bio;

    @Column(name = "friends")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
    name = "user_friends",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private List<User> friends;

    @JsonIgnore
    @Column(name = "posts")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;
    
    @JsonIgnore
    @Column(name = "comments")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private List<Message> receivedMessages;    

    @JsonIgnore
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Message> sendedMessages;  
}
