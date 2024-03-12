package com.jpa.springboot_jpa.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;

@Entity(name = "t_admin")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic
    @Column(name = "username", nullable = false, length = 1024)
    private String username;
    @Basic
    @Column(name = "password", nullable = false, length = 1024)
    private String password;
    @Basic
    @Column(name = "token", nullable = false, length = 1024)
    private String token;

}
