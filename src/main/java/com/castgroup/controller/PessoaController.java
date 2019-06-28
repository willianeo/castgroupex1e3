package com.castgroup.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.castgroup.model.Pessoa;
import com.castgroup.service.PessoaServiceImpl;


@RestController
@RequestMapping("/api")
public class PessoaController {

	private static final Logger logger = LoggerFactory.getLogger(PessoaController.class);
	
	@Autowired
	PessoaServiceImpl pessoaService; //pessoaService
	
	@GetMapping("/pessoa")
	public ResponseEntity<List<Pessoa>> listAllPessoas() {
		List<Pessoa> pessoas = this.pessoaService.findAllPessoas();
		if (pessoas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(pessoas, HttpStatus.OK);
		
	}
	
	@GetMapping("/pessoa/{id}")
	public ResponseEntity<Pessoa> getPessoa(@PathVariable(name="id") Integer id) {
		logger.info("Fetching Pessoa with id {}", id);
		Pessoa pessoa = this.pessoaService.findById(id);
		if (pessoa == null) {
			logger.error("Pessoa with id {} not found.", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(pessoa, HttpStatus.OK);
	}
	
	@PostMapping(path="/pessoa", consumes = "application/json")
	public ResponseEntity<Pessoa> saveEmployee(@RequestBody Pessoa pessoa) {
		logger.info("Creatting Pessoa: {}", pessoa);

		try {
			Pessoa npessoa = this.pessoaService.savePessoa(pessoa);
			return new ResponseEntity<>(npessoa, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Unable to create. A Pessoa with name {} alread exist", pessoa.getName());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

    
	@DeleteMapping("/pessoa/{id}")
	public ResponseEntity<Pessoa> deleteById(@PathVariable Integer id) {
		logger.info("Fetching & Deleting Pessoa with id {}", id);
		
		Pessoa pessoa = pessoaService.findById(id);
		if (pessoa == null) {
			logger.error("Unable to delete. Pessoa with id {} not found.", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		pessoaService.deletePessoaById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	@PutMapping("pessoa/{id}")
	public ResponseEntity<Pessoa> updateEmployee(@PathVariable("id") int id, @RequestBody Pessoa pessoa) {
		logger.info("Updating Pessoa with id {}", id);
		
		Pessoa currentPessoa = pessoaService.findById(id);
		
		if (currentPessoa == null) {
			logger.error("Unable to update. User with id {} not found.", id);
			return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		currentPessoa.setCellphone(pessoa.getCellphone());
		currentPessoa.setCity(pessoa.getCity());
		currentPessoa.setName(pessoa.getName());
		currentPessoa.setNeighborhood(pessoa.getNeighborhood());
		currentPessoa.setPhone(pessoa.getPhone());
		currentPessoa.setState(pessoa.getState());
		currentPessoa.setStreet(pessoa.getStreet());
		
		pessoaService.updatePessoa(currentPessoa);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
    @DeleteMapping("/pessoa")
    public ResponseEntity<Pessoa> deleteAllPessoa() {
        logger.info("Deleting All Pessoas");
 
        pessoaService.deleteAllPessoas();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
	
}
