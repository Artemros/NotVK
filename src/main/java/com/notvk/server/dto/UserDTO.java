package com.notvk.server.dto;

import com.notvk.server.model.WallText;

public class UserDTO {

    public UserDTO(Long id, WallText wallText) {
        this.id = id;
        this.wallText = wallText;
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

    private Long id;
    private WallText wallText;
}
