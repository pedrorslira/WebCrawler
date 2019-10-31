
import webcrawler.Buffer;
import webcrawler.Coletor;
import webcrawler.Extrator;

public class WebCrawler {

    public static void main(String[] args) throws Exception {
        Buffer buffer = Buffer.getInstanciaBuffer();
        buffer.setObserver(new Extrator());
        Coletor coletor = new Coletor();
        coletor.leituraTxt();
    }
}
 