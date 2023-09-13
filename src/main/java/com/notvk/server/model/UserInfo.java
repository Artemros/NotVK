package com.notvk.server.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<WallText> wallText;

    @Column(name = "username")
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

    public List<UserInfo> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<UserInfo> friendList) {
        this.friendList = friendList;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "friend_list")
    private List<UserInfo> friendList;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfo userInfo = (UserInfo) o;

        if (!id.equals(userInfo.id)) return false;
        if (!username.equals(userInfo.username)) return false;
        if (!name.equals(userInfo.name)) return false;
        if (!password.equals(userInfo.password)) return false;
        return Objects.equals(status, userInfo.status);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
