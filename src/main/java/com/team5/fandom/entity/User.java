package com.team5.fandom.entity;

import com.team5.fandom.common.utils.LevelAttributeConverter;
import com.team5.fandom.common.utils.RoleAttributeConverter;
import com.team5.fandom.entity.value.Level;

import com.team5.fandom.entity.value.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Convert(converter = RoleAttributeConverter.class)
    @Column(name = "role", nullable = false)
    private Role role;

    @Convert(converter = LevelAttributeConverter.class)
    @Column(name = "fan_level", nullable = false)
    private Level fanLevel;

    @Column(name = "fan_exp", nullable = false)
    private Integer fanExp;

    @ManyToOne
    @JoinColumn(name = "fandom_id")
    private Fandom fandom;

    private User(String userName, String email, String password, Role role, Level fanLevel, Integer fanExp, Fandom fandom) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.fanLevel = fanLevel;
        this.fanExp = fanExp;
        this.fandom = fandom;
    }

    public User(Integer userId, String username, String email, String password, Role role) {

    }


    //No ID
    public static User of( String userName, String email, String password, Role role, Level fanLevel, Integer fanExp, Fandom fandom) {
        return new User(userName, email, password, role, fanLevel, fanExp, fandom);
    }
    


    
    

    
}


