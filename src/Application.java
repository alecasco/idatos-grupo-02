import controllers.AnepController;
import domain.AnepCenter;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        System.out.println("Hola mundo de Integracion de Datos");
        AnepController anepController = new AnepController();
        anepController.LoadCentros();

        // PRUEBA
        List<AnepCenter> centrosEnPocitos = anepController.findCenters("Montevideo", "Montevideo", "Pocitos");
        System.out.println("Centros en Pocitos:");
        for (AnepCenter a: centrosEnPocitos) {
            System.out.println(a.getNombre());
        }
    }
}
