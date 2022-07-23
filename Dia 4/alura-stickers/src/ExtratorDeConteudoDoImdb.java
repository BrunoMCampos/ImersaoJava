import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDoImdb implements ExtratorDeConteudo{
    public List<Conteudo> extraiConteudo(String json){

        //Extrair o que interessa do Json

        List<Map<String, String>> listaDeAtributos = new JsonParser().parse(json);

        List<Conteudo> conteudos = new ArrayList<>();

        // Popular a lista

        for (Map<String,String> atributo : listaDeAtributos) {
            Conteudo conteudo = new Conteudo(atributo.get("title"), atributo.get("image"));
            conteudos.add(conteudo);
        }

        return conteudos;
    }
}
