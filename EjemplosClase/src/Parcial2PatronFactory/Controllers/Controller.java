package Parcial2PatronFactory.Controllers;

import Parcial2PatronFactory.Entity;
import Parcial2PatronFactory.EntityFactory;
import Parcial2PatronFactory.Views.View;

public class Controller<T extends Entity> {
    private final EntityFactory<T> factory;
    private final View<T> view;

    public Controller(EntityFactory<T> factory, View<T> view) {
        this.factory = factory;
        this.view = view;
    }

    public void createAndShow(Object... args) {
        T entity = factory.create(args);
        view.showEntity(entity);
    }
}