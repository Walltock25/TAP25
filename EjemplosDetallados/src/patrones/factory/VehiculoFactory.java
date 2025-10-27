package patrones.factory;

// 3. Clase Factory - La fábrica que crea los objetos
class VehiculoFactory {

    /**
     * Método Factory: Crea y retorna el tipo de vehículo solicitado
     *
     * @param tipo El tipo de vehículo a crear ("auto", "moto", "camion")
     * @return Una instancia del vehículo solicitado
     * @throws IllegalArgumentException si el tipo no es válido
     */
    public static Vehiculo crearVehiculo(String tipo) {
        // Validación del parámetro
        if (tipo == null || tipo.isEmpty()) {
            throw new IllegalArgumentException("El tipo de vehículo no puede ser nulo o vacío");
        }

        // Lógica de creación basada en el tipo
        // El cliente no necesita saber cómo se construyen estos objetos
        switch (tipo.toLowerCase()) {
            case "auto":
            case "automovil":
            case "carro":
                return new Auto();

            case "moto":
            case "motocicleta":
                return new Motocicleta();

            case "camion":
            case "truck":
                return new Camion();

            default:
                throw new IllegalArgumentException(
                        "Tipo de vehículo no reconocido: " + tipo
                );
        }
    }
}