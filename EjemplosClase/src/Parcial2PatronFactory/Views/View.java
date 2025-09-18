package Parcial2PatronFactory.Views;
import Parcial2PatronFactory.Entity;

public class View<T extends Entity> {
    public void showEntity(T entity) {
        System.out.println(entity.getInfo());
    }
}