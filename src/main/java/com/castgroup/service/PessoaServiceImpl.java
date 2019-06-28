package com.castgroup.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.castgroup.model.Pessoa;
import com.castgroup.repository.PessoaRepository;

@Service
public class PessoaServiceImpl implements PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Override
	public Pessoa findById(Integer id) {
		return pessoaRepository.getOne(id);
	}

	public Pessoa findByName(String name) {
		return pessoaRepository.findByName(name);
	}

	@Override
	public Pessoa savePessoa(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	@Override
	public Pessoa updatePessoa(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	@Override
	public void deletePessoaById(Integer id) {
		pessoaRepository.deleteById(id);
	}

	@Override
	public void deleteAllPessoas() {
		pessoaRepository.deleteAll();
	}

	@Override
	public List<Pessoa> findAllPessoas() {
		return pessoaRepository.findAll();
	}

	@Override
	public boolean isPessoaExist(Pessoa pessoa) {
		return findByName(pessoa.getName()) != null;
	}

}
