package com.nvoip.market.domain;

import com.nvoip.market.domain.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String numbersip;

    private String password;

    private Status status;

    public User(String name, String numbersip, String password) {
        this.name = name;
        this.numbersip = numbersip;
        this.password = password;
        this.status = Status.ACTIVE;
    }
    
}
