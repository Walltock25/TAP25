package patrones.factory;

/**
 * PATRÓN FACTORY (Fábrica)
 *
 * Propósito: Proporciona una interfaz para crear objetos en una superclase,
 * pero permite a las subclases decidir qué tipo de objeto crear.
 *
 * Ventajas:
 * - Desacopla el código cliente de las clases concretas
 * - Facilita agregar nuevos tipos sin modificar código existente
 * - Centraliza la lógica de creación de objetos
 */

// 1. Interfaz común para todos los productos
interface Vehiculo {
    void conducir();
    void detener();
    String obtenerTipo();
}

// 2. Clases concretas que implementan la interfaz
class Auto implements Vehiculo {
    @Override
    public void conducir() {
        System.out.println("🚗 Conduciendo un auto a 120 km/h");
    }

    @Override
    public void detener() {
        System.out.println("🛑 Auto detenido con frenos ABS");
    }

    @Override
    public String obtenerTipo() {
        return "Automóvil";
    }
}
