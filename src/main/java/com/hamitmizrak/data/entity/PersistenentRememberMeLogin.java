package com.hamitmizrak.data.entity;

import javax.persistence.*;
//Remember me ! database persist

//Entity
@Entity
@Table(name = "persistent_logins")
public class PersistenentRememberMeLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64)
    private String username;

    @Column(length = 64)
    private String series;

    @Column(length = 64)
    private String token;

    @Column(length = 64)
    private String last_used;
}
