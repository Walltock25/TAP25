package Parcial2PatronBuilder;

// Director opcional - define recetas de construcción
public class DirectorCasa {
    public Casa construirCasaBasica() {
        return new Casa.CasaBuilder("Cimientos de concreto", "Paredes de ladrillo", "Techo de teja")
                .build();
    }

    public Casa construirCasaFamiliar() {
        return new Casa.CasaBuilder("Cimientos reforzados", "Paredes de ladrillo doble", "Techo de teja premium")
                .conVentanas("Ventanas de doble vidrio")
                .conPuertas("Puertas de madera")
                .conGaraje()
                .conJardin()
                .build();
    }

    public Casa construirCasaDeLujo() {
        return new Casa.CasaBuilder("Cimientos de acero y concreto", "Paredes de mármol", "Techo de cristal templado")
                .conVentanas("Ventanas panorámicas")
                .conPuertas("Puertas automáticas")
                .conGaraje()
                .conPiscina()
                .conJardin()
                .build();
    }
}