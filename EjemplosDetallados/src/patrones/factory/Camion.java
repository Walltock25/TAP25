package patrones.factory;

class Camion implements Vehiculo {
    @Override
    public void conducir() {
        System.out.println("🚚 Conduciendo camión de carga a 90 km/h");
    }

    @Override
    public void detener() {
        System.out.println("🛑 Camión detenido con sistema de frenos neumático");
    }

    @Override
    public String obtenerTipo() {
        return "Camión";
    }
}