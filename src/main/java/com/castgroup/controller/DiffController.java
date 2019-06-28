package com.castgroup.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.DatatypeConverter;
import org.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class DiffController {
	private Map<Integer, String> map;
	
	public DiffController() {
		if (this.map == null) {
			this.map = new HashMap<Integer, String>();
		}
	}

	@PostMapping(path = "/diff/{id}/left", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> saveLeftData(@RequestBody String base64Json, @PathVariable("id") Integer id) {

		byte[] decodeBytes = DatatypeConverter.parseBase64Binary(base64Json);
		String decodeString = new String(decodeBytes);
		this.map.put(id, decodeString);
		return new ResponseEntity<>("Dados recebidos com sucesso!", HttpStatus.OK);
	}
	
	@PostMapping(path = "/diff/{id}/right", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> saveRightData(@RequestBody String base64Json, @PathVariable("id") Integer id) {
		
		byte[] decodeBytes = DatatypeConverter.parseBase64Binary(base64Json);
		String decodeString = new String(decodeBytes);
		this.map.put(id, decodeString);
		return new ResponseEntity<>("Dados recebidos com sucesso!", HttpStatus.OK);
	}
	
	@GetMapping("/diff/{leftId}/{rightId}")
	public <T extends Serializable> ResponseEntity<?> diffData(@PathVariable("leftId")Integer leftId
			, @PathVariable("rightId") Integer rightId) {
		try {
			JSONArray leftJsonArr = new JSONArray(this.map.get(leftId));
			JSONArray rightJsonArr = new JSONArray(this.map.get(rightId));
			
			if (leftJsonArr.equals(rightJsonArr)) { // retorna true
				return new ResponseEntity<>(true, HttpStatus.OK);
			} else if (leftJsonArr.length() != rightJsonArr.length()) { // retorna o tamanho
				Map<String, Integer> map = new HashMap<>();
				map.put("leftJson", leftJsonArr.length());
				map.put("rightJson", rightJsonArr.length());
				return new ResponseEntity<>(map, HttpStatus.OK);
			} else { // retorna o dado e o tamanho
				Map<String, String> map = new HashMap<>();
				map.put("leftJson", leftJsonArr.toString());
				map.put("leftJsonLength", Integer.toString(leftJsonArr.length()));
				map.put("rightJson", rightJsonArr.toString());
				map.put("rightJsonLength", Integer.toString(rightJsonArr.length()));
				return new ResponseEntity<>(map, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Você passou um Id incorreto", HttpStatus.NOT_FOUND);
		}
	}
	
}
