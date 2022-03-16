package com.example.GorkaMalvado.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class User {
	private String name;
	private String initial;
	private Integer tries;
}
