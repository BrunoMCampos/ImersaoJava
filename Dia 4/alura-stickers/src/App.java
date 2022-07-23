import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // Fazer uma conexão http e recuperar os 250 top filmes
        // Extrair só os dados que interessam (Titulo e Imagem)

        //String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        //String json = new ClienteHttp().buscaDados(url);
        //List<Conteudo> listaDeConteudos = new ExtratorDeConteudoDoImdb().extraiConteudo(json);

        //String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD";
        //String json = new ClienteHttp().buscaDados(url);
        //List<Conteudo> listaDeConteudos = new ExtratorDeConteudoDaNasa().extraiConteudo(json);

        String url = "http://localhost:8080/linguagens";
        String json = new ClienteHttp().buscaDados(url);
        List<Conteudo> listaDeConteudos = new ExtratorDeConteudoDoLinguagensApi().extraiConteudo(json);

        // Gerar Figurinhas

        GeradoraDeFigurinhas gerador = new GeradoraDeFigurinhas();

        InputStream input = null;

        for(Conteudo conteudo : listaDeConteudos){
            input = new URL(conteudo.getUrlImagem()).openStream();

            gerador.cria(input, "saida/" + conteudo.getTitulo() + ".png");
            System.out.println("Gerando Sticker de: " + conteudo.getTitulo());
            System.out.println();
        }

    }
}
