import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
    

    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

        // Ler a imagem

        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // Criar uma nova imagem com base no tamanho da original com transparencia

        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // Copiar a nova imagem para a imagem maior em memória

        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // Mudar fonte de texto

        Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);

        // Escrever a frase para o Stickers

        graphics.drawString("TOPZERA", 100, novaAltura - 100);

        // Escrever a nova imagem em um arquivo

        ImageIO.write(novaImagem, "png", new File("saida/" + nomeArquivo));

    }

}