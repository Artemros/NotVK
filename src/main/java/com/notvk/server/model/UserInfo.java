package com.notvk.server.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "USERS_INFO")
public class UserInfo {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<WallText> getWallText() {
        return wallText;
    }

    public void setWallText(List<WallText> wallText) {
        this.wallText = wallText;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private List<WallText> wallText;

    @Column
    private String name;

    @Column
    private String status;
    public UserInfo() {
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
}
