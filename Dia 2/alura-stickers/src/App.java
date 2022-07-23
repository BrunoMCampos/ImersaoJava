import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        
        // Criar uma conexão http com o servidor da API e retornar os dados
        URI uri = new URI("https://alura-filmes.herokuapp.com/conteudos");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();

        String body = client.send(request, BodyHandlers.ofString()).body();

        // Extrair os dados que interessam

        List<Map<String,String>> listaDeFilmes = new JsonParser().parse(body);

        // Transforma os dados em Stickers

        GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();

        for(Map<String, String> filme : listaDeFilmes){
            String title = filme.get("title");
            String urlImage = filme.get("image").substring(0, filme.get("image").length()-32) + ".jpg";
            
            System.out.println(urlImage);

            InputStream inputStream = new URL(urlImage).openStream();
            String nomeArquivo = title + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(title);
            System.out.println();
        }
    }
}
