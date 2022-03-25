package com.dib.entity;

import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "note")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	String title;

	@ManyToMany(fetch = FetchType.EAGER,
	        cascade = {
	                CascadeType.ALL
	                
	            })
	@JoinTable(name = "todo_note", joinColumns = @JoinColumn(name = "note_id"), inverseJoinColumns = @JoinColumn(name = "todo_id"))
	List<TODO> description;

}