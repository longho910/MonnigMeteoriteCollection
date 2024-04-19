package edu.tcu.cs.monnigmeteoritecollection.curator;

import java.io.Serializable;

import jakarta.persistence.Id;

public class Curator implements Serializable{
    
    @Id
    private long id;

    private String username;
    private String password;    // unsure about this field - will expand security and logins
    
}
