package com.example.web.entity;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "DBUSER")
@NamedQueries({@NamedQuery(name = DbUser.NAMED_QUERY_GET_ALL, query = DbUser.NAMED_QUERY_GET_ALL_QUERY)})
public class DbUser {
    public static final String NAMED_QUERY_GET_ALL = "getAll";
    public static final String NAMED_QUERY_GET_ALL_QUERY = "SELECT d FROM DbUser d";
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
