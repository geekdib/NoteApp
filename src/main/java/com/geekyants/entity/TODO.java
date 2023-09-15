package com.geekyants.entity;

import com.geekyants.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "todo")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TODO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	Status status;

	String value;

}