package patrones.factory;

// 4. Clase principal para demostrar el uso del patrón
public class FactoryPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== DEMOSTRACIÓN DEL PATRÓN FACTORY ===\n");

        // El cliente solo necesita llamar a la Factory
        // No necesita conocer las clases concretas (Auto, Moto, Camion)

        // Crear diferentes vehículos usando la Factory
        Vehiculo auto = VehiculoFactory.crearVehiculo("auto");
        Vehiculo moto = VehiculoFactory.crearVehiculo("moto");
        Vehiculo camion = VehiculoFactory.crearVehiculo("camion");

        // Usar los vehículos de manera polimórfica
        System.out.println("--- Probando Auto ---");
        probarVehiculo(auto);

        System.out.println("\n--- Probando Motocicleta ---");
        probarVehiculo(moto);

        System.out.println("\n--- Probando Camión ---");
        probarVehiculo(camion);

        // Demostrar manejo de errores
        System.out.println("\n--- Intentando crear vehículo inválido ---");
        try {
            Vehiculo invalido = VehiculoFactory.crearVehiculo("avion");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    /**
     * Método auxiliar que demuestra el polimorfismo
     * Recibe cualquier tipo de Vehiculo y lo usa sin conocer su tipo específico
     */
    private static void probarVehiculo(Vehiculo vehiculo) {
        System.out.println("Tipo: " + vehiculo.obtenerTipo());
        vehiculo.conducir();
        vehiculo.detener();
    }
}