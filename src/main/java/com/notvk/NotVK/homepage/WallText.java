package com.notvk.NotVK.homepage;

import jakarta.persistence.*;

@Entity
public class WallText {

    public WallText(Long id, String text, String time) {
        this.id = id;
        this.text = text;
        this.time = time;
    }

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

    @Column(name = "time")
    private String time;
    public WallText() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
