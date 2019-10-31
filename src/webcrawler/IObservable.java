package webcrawler;

public interface IObservable {

    void notificar();

    IObserver getObserver();

    void setObserver(IObserver observer);
}
