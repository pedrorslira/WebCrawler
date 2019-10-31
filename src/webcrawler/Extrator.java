package webcrawler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Queue;
import java.util.Random;

public class Extrator implements IObserver {

    public void saveImgs(Buffer buffer) {

        Queue<Imagem> imagens = buffer.getImagens();
        while (!imagens.isEmpty()) {
            saveImgThread(imagens.poll());
        }
    }

    private void saveImgThread(final Imagem img) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Random rand = new Random();
                    int numeroRandom = rand.nextInt(3000);
                    InputStream in = new URL(img.getUrl()).openStream();
                    Files.copy(in, Paths.get("C:/Users/Pedro/Desktop/teste/Imagem-" + Calendar.getInstance().getTime().getTime() + "-" + numeroRandom + ".jpg"));
                } catch (FileNotFoundException | MalformedURLException ignore) {
                    System.out.println(ignore.toString());
                } catch (IOException e) {
                    System.out.println(e.toString());
                }
            }
        }).start();
    }

    @Override
    public void atualizar(IObservable observable) {
        Buffer buffer = (Buffer) observable;
        saveImgs(buffer);
    }

}
