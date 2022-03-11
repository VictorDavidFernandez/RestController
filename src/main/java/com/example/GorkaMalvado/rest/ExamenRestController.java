package com.example.GorkaMalvado.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.GorkaMalvado.models.Key;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(value = "/api/")
public class ExamenRestController {

	@Autowired
	private Key key;

	@GetMapping(path = "generateKey")
	public ResponseEntity<Key> create(@RequestBody Key key) {
		this.key = key;
		this.key.setGeneratedKey("Victor");
		return new ResponseEntity<>(this.key, HttpStatus.OK);

	}

	@GetMapping(path = "changeTries")
	public ResponseEntity<Key> changeTries(@RequestParam Integer tries) {
		this.key.setTries(tries);
		return new ResponseEntity<>(this.key, HttpStatus.OK);
	}

	@GetMapping(path = "answerSize/{size}")
	public ResponseEntity<Key> answerSize(@PathVariable("size") int size) {
		this.key.setSize(size);
		String victor = "";
		for (int i = 0; i < size; i++) {
			victor += "victor,";
		}
		victor = victor.substring(0, victor.length() - 1);
		this.key.setGeneratedKey(victor);
		return new ResponseEntity<>(this.key, HttpStatus.OK);
	}
}