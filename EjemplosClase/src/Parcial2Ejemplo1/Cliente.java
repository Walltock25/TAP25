package Parcial2Ejemplo1;

public class Cliente {
    public static void main(String[] args) {
        VehiculoFactory factory = new VehiculoFactory();

        Vehiculo coche = factory.crearVehiculo("coche");
        coche.conducir();

        Vehiculo moto = factory.crearVehiculo("moto");
        moto.conducir();

        Vehiculo camion = factory.crearVehiculo("camion");
        camion.conducir();
    }
}
