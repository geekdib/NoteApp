package com.dib.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "user")
@ToString
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private int active;

    
	@ManyToMany(fetch = FetchType.EAGER,
	        cascade = {
	                CascadeType.ALL
	                
	            })
    @JoinTable(name="note_user", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="note_id"))
    private List<Note> noteList;

}