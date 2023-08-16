package com.notvk.server.dto;

import com.notvk.server.model.WallText;

public class UserDTO {

    public UserDTO(Long id, WallText wallText, String name, String status) {
        this.id = id;
        this.wallText = wallText;
        this.name = name;
        this.status = status;
    }

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WallText getWallText() {
        return wallText;
    }

    public void setWallText(WallText wallText) {
        this.wallText = wallText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private Long id;
    private WallText wallText;


    private String name;


    private String status;
}
