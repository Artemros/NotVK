package com.notvk.server.dto;

public class WallTextDTO {
    public WallTextDTO(Long id, String text, String time) {
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


    private Long id;

    private String text;


    private String time;
    public WallTextDTO() {
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
