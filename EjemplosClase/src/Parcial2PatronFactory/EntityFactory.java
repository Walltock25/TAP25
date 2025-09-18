package Parcial2PatronFactory;

public class EntityFactory<T extends Entity> {
    private final Class<T> type;

    public EntityFactory(Class<T> type) {
        this.type = type;
    }

    public T create(Object... args) {
        try {
            for (var ctor : type.getConstructors()) {
                if (ctor.getParameterCount() == args.length) {
                    return type.cast(ctor.newInstance(args));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("No se pudo crear la entidad: " + e.getMessage());
        }
        return null;
    }
}