package ru.example.rest.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    private Long id;

    private String firstname;

    private String lastname;
    private String data;
    private int party;

    public User(String firstname, String lastname, String data, int party) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.data = data;
        this.party = party;
    }
}
