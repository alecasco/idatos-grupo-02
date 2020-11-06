import controllers.AnepController;
import domain.AnepCenter;
import controllers.MercadoLibreController;

import java.io.IOException;
import java.util.List;

public class Application {

    public static void main(String[] args) throws IOException {
        System.out.println("Hola mundo de Integracion de Datos");
        AnepController anepController = new AnepController();
        anepController.LoadCentros();

        // PRUEBA
        List<AnepCenter> centrosEnPocitos = anepController.findCenters("Montevideo", "Montevideo", "Pocitos");
        System.out.println("Centros en Pocitos:");
        for (AnepCenter a: centrosEnPocitos) {
            System.out.println(a.getNombre());
        }

        MercadoLibreController mercadoLibreController = new MercadoLibreController();
        mercadoLibreController.LoadAlojamientos();
    }
}
