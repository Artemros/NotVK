package com.notvk.server.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class WallText {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "text")
    private String text;
    @Basic
    @Column(name = "time")
    private java.sql.Timestamp time;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long user;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserInfo author;


    public WallText() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public UserInfo getAuthor() {
        return author;
    }

    public void setAuthor(UserInfo author) {
        this.author = author;
    }
}
