package br.com.alura.linguagensapi;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LinguagemRepository extends MongoRepository<Linguagem, String>	{

	void deleteByRanking(int ranking);
	
	Linguagem findByRanking(int ranking);
}
