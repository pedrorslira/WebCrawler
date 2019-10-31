package webcrawler;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer implements IObservable {

    private Queue<Imagem> imagens;
    private IObserver observer;

    private static Buffer instanciaBuffer = null;

    private Buffer() {
        imagens = new LinkedList<>();
    }

    public static Buffer getInstanciaBuffer() {
        if (instanciaBuffer == null) {
            instanciaBuffer = new Buffer();
        }
        return instanciaBuffer;
    }

    public void adicionar(Imagem imagem) {
        this.imagens.add(imagem);
        this.notificar();
    }

    public Queue<Imagem> getImagens() {
        return imagens;
    }

    @Override
    public void notificar() {
        observer.atualizar(this);
    }

    @Override
    public IObserver getObserver() {
        return observer;
    }

    @Override
    public void setObserver(IObserver observer) {
        this.observer = observer;
    }

}
