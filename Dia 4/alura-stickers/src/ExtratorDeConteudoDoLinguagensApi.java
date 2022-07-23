import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDoLinguagensApi implements ExtratorDeConteudo{

    @Override
    public List<Conteudo> extraiConteudo(String json) {
         //Extrair o que interessa do Json

         List<Map<String, String>> listaDeAtributos = new JsonParser().parse(json);

         List<Conteudo> conteudos = new ArrayList<>();
 
         // Popular a lista
 
         for (Map<String,String> atributo : listaDeAtributos) {
             Conteudo conteudo = new Conteudo(atributo.get("nome"), atributo.get("url"));
             conteudos.add(conteudo);
         }
 
         return conteudos;
    }
    
}
