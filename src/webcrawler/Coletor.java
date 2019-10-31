package webcrawler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Coletor {

    private BufferedReader arquivo;
    private Buffer buffer;

    public Coletor() throws IOException {
        this.arquivo = new BufferedReader(new FileReader("./src/websites.txt"));
        this.buffer = Buffer.getInstanciaBuffer();
    }

    public void leituraTxt() throws Exception {
        String linha;
        while (((linha = arquivo.readLine()) != null) && !("".equals(linha))) {
            getImgs(linha);
        }
    }

    public void getImgs(final String url) throws Exception {

        new Thread() {
            public void run() {
                String codigoHtml = null;
                String regexUrlImg = "(http)?s?:?(\\/\\/[^\"']*\\.(?:png|jpg|jpeg|gif|png|svg))";
                String urlImg;
                URLConnection connection = null;
                try {
                    connection = new URL(url).openConnection();
                    Scanner scanner = new Scanner(connection.getInputStream());
                    scanner.useDelimiter("\\Z");
                    codigoHtml = scanner.next();
                    scanner.close();
                    Pattern pattern = Pattern.compile(regexUrlImg);
                    Matcher matcher = pattern.matcher(codigoHtml);
                    while (matcher.find()) {
                        if (!matcher.group(0).startsWith("http")) {
                            urlImg = "https:" + matcher.group(0);
                        } else {
                            urlImg = matcher.group(0);
                        }
                        System.out.println(urlImg);
                        buffer.adicionar(new Imagem(urlImg));
                    }
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }.start();
    }
}
