package com.example.GorkaMalvado.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Message {

	private String message;
	private String reason;
}
