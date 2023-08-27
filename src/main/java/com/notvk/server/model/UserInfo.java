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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private List<WallText> wallText;

    @Column
    private String username;
    @Column
    private String name;

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", wallText=" + wallText +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Column
    private String password;

    @Column
    private String status;

}
