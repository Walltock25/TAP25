package Parcial2PatronBuilder;
public class Main {
    public static void main(String[] args) {
        System.out.println("=== PATRÓN BUILDER - CONSTRUCCIÓN DE CASAS ===\n");

        // 1. Construcción directa con Builder (sin Director)
        System.out.println("1. CONSTRUCCIÓN PERSONALIZADA:");
        Casa casaPersonalizada = new Casa.CasaBuilder(
                "Cimientos de piedra",
                "Paredes de adobe",
                "Techo de paja")
                .conVentanas("Ventanas de madera")
                .conPuertas("Puerta rústica")
                .conJardin()
                .build();

        System.out.println(casaPersonalizada);

        // 2. Usando el Director para construcciones predefinidas
        DirectorCasa director = new DirectorCasa();

        System.out.println("2. CASA BÁSICA (usando Director):");
        Casa casaBasica = director.construirCasaBasica();
        System.out.println(casaBasica);

        System.out.println("3. CASA FAMILIAR (usando Director):");
        Casa casaFamiliar = director.construirCasaFamiliar();
        System.out.println(casaFamiliar);

        System.out.println("4. CASA DE LUJO (usando Director):");
        Casa casaDeLujo = director.construirCasaDeLujo();
        System.out.println(casaDeLujo);

        // 3. Ventaja del patrón: Flexibilidad en la construcción
        System.out.println("5. CONSTRUCCIÓN FLEXIBLE:");
        Casa casaMinimalista = new Casa.CasaBuilder(
                "Cimientos ecológicos",
                "Paredes de bambú",
                "Techo verde")
                .conPiscina() // Solo piscina, sin garaje ni jardín
                .build();

        System.out.println(casaMinimalista);

        System.out.println("¡El patrón Builder permite crear objetos complejos paso a paso!");
        System.out.println("Separa la construcción de la representación del objeto.");
        System.out.println("Permite crear diferentes representaciones del mismo objeto.");
    }
}