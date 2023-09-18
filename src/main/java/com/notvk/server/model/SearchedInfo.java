package com.notvk.server.model;

import jakarta.persistence.*;

@Entity
public class SearchedInfo {

    public SearchedInfo() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getSearched() {
        return searched;
    }

    public void setSearched(String searched) {
        this.searched = searched;
    }

    @Column(name = "searched")
    private String searched;
}
