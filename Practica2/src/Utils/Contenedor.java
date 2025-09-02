package Utils;
public class Contenedor<T> {
    private T valor;
    public Contenedor(T valor) {this.valor = valor;}
    public T getValor() {return valor;}
    public void setValor(T valor) {this.valor = valor;}
}