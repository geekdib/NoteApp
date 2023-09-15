package com.geekyants.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NoteDTO {

	private String title;

	private List<TodoDTO> description;

}