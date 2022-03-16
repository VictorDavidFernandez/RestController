package com.example.GorkaMalvado.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.GorkaMalvado.models.Answer;
import com.example.GorkaMalvado.models.Key;
import com.example.GorkaMalvado.models.Message;
import com.example.GorkaMalvado.models.User;

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

	@GetMapping(path = "checkAnswer")
	public ResponseEntity<Message> checkAnswer(@RequestBody Answer answer) {
		String[] arrayKey = this.key.getGeneratedKey().split(",");
		boolean checkAnswer = Arrays.equals(arrayKey, answer.getAnswer());
		if (arrayKey.length != answer.getAnswer().length)
			return new ResponseEntity<>(
					Message.builder().message("answer size invalid").reason("expected max size: 5").build(),
					HttpStatus.NOT_ACCEPTABLE);
		else if (checkAnswer)
			return new ResponseEntity<>(Message.builder().message("answer correct").build(), HttpStatus.OK);
		else
			return new ResponseEntity<>(Message.builder().message("answer incorrect").build(), HttpStatus.BAD_REQUEST);
	}

	@PostMapping(path = "checkTries")
	public ResponseEntity<Key> checkTries() {

		return new ResponseEntity<>(this.key, HttpStatus.CREATED);
	}

	@PostMapping(path = "saveRecordScore")
	public ResponseEntity<User> saveRecordScore(String name, String initial) {
		System.out.println("NOMBRE" + name + " " + "INITIAL " + initial);
		return new ResponseEntity<>(User.builder().name(name).initial(initial).build(), HttpStatus.CREATED);
	}

	@GetMapping(path = "listScoreboard")
	public ResponseEntity<List<User>> scoreBoard(@RequestParam Integer maxSize) {
		List<User> users = new ArrayList<>();
		for (int i = 0; i < maxSize; i++) {
			users.add(User.builder().name("Victor").initial("V").tries(4).build());
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
}