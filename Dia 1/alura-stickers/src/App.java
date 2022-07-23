import java.net.URI;
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

        // Exibir e manipular os dados

        for(Map<String, String> filme : listaDeFilmes){
            System.out.println(filme.get("title"));
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imDbRating"));
            System.out.println();
        }
    }
}
