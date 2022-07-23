package br.com.alura.linguagensapi;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class LinguagemController {

	@Autowired
	private LinguagemRepository repositorio;

	@GetMapping("/linguagem-preferida")
	public String processaLinguagemPreferida() {
		return "oi java";
	}

	@GetMapping("/linguagens")
	public List<LinguagemDto> obterLinguagens() {
		List<Linguagem> linguagens = repositorio.findAll();
		return LinguagemDto.converter(linguagens);
	}
	
	@PostMapping("/linguagens")
	public ResponseEntity<LinguagemDto> cadastrarLinguagem(@RequestBody Linguagem linguagem, UriComponentsBuilder uriBuilder) {
		Linguagem linguagemSalva = repositorio.save(linguagem);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(linguagemSalva.getId()).toUri();
		return ResponseEntity.created(uri).body(new LinguagemDto(linguagemSalva));
	}
	
	@DeleteMapping("/linguagens/{ranking}")
	@Transactional
	public ResponseEntity<LinguagemDto> deletar(@PathVariable int ranking) {
		repositorio.deleteByRanking(ranking);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/linguagens/{ranking}")
	@Transactional
	public ResponseEntity<LinguagemDto> atualizar(@PathVariable int ranking, @RequestBody LinguagemDto dto){
		Linguagem linguagem = dto.atualizar(ranking, repositorio);
		
		return ResponseEntity.ok(new LinguagemDto(linguagem));
	}
	
}
