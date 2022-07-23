package br.com.alura.linguagensapi;

import java.util.List;

public class LinguagemDto {

	private String nome;
	private String url;
	private int rank;

	public LinguagemDto() {
	}

	public LinguagemDto(Linguagem linguagem) {
		this.nome = linguagem.getTitle();
		this.url = linguagem.getImage();
		this.rank = linguagem.getRanking();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public static List<LinguagemDto> converter(List<Linguagem> linguagens) {
		List<LinguagemDto> listaDeLinguagens = linguagens.stream().map(LinguagemDto::new).toList();
		return listaDeLinguagens;
	}

	public Linguagem atualizar(int ranking, LinguagemRepository repositorio) {
		Linguagem linguagem = repositorio.findByRanking(ranking);

		linguagem.setTitle(this.nome);
		linguagem.setRanking(this.rank);
		linguagem.setImage(this.url);
		
		repositorio.save(linguagem);

		return linguagem;
	}

}
