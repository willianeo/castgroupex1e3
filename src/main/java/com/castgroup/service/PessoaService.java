package com.castgroup.service;

import java.util.List;

import com.castgroup.model.Pessoa;

public interface PessoaService {

	Pessoa findById(Integer id);
	
	Pessoa findByName(String name);
	
	Pessoa savePessoa(Pessoa pessoa);
	
	Pessoa updatePessoa(Pessoa pessoa);
	
	void deletePessoaById(Integer id);
	
	void deleteAllPessoas();
	
	List<Pessoa> findAllPessoas();
	
	boolean isPessoaExist(Pessoa pessoa);
	
	
}
