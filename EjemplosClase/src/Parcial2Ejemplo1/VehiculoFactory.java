package Parcial2Ejemplo1;

public class VehiculoFactory {
    public Vehiculo crearVehiculo(String tipo) {
        if (tipo.equalsIgnoreCase("coche")) {
            return new Coche();
        } else if (tipo.equalsIgnoreCase("moto")) {
            return new Moto();
        } else if (tipo.equalsIgnoreCase("camion")) {
            return new Camion();
        }
        return null;
    }
}
