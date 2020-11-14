import controllers.AnepController;
import controllers.MercadoLibreController;
import controllers.RDFModelController;
import domain.MercadoLibreProperty;

import java.io.Console;
import java.io.IOException;
import java.util.List;

public class Application {

    public static void main(String[] args) throws IOException {
        String barrio = null;
        Console console = System.console();

        AnepController anepController = new AnepController();
        anepController.LoadCentros();

        //while (barrio != "salir") {
            System.out.println("Bienvenido, seleccione un barrio:");
            System.out.println("1. Carrasco");
            System.out.println("2. Punta Gorda");
            System.out.println("3. Malvín");
            System.out.println("4. Buceo");
            System.out.println("5. Pocitos");
            System.out.println("6. Punta Carretas");
            System.out.println("7. Parque Rodó");
            System.out.println("8. Palermo");
            System.out.println("9. Parque Batlle");
            System.out.println("10. Cordón");
            System.out.println("11. Centro");
            System.out.println("12. Ciudad Vieja");
            System.out.println("13. Aguada");
            System.out.println("14. Prado");
            System.out.println("15. Peñarol");
            System.out.println("16. La blanqueada");
            System.out.println("salir");

            barrio = "1";// console.readLine();

        /* PRUEBA
        List<AnepCenter> centrosEnPocitos = anepController.findCenters("Montevideo", "Montevideo", "Pocitos");
        System.out.println("Centros en Pocitos:");
        for (AnepCenter a: centrosEnPocitos) {
            System.out.println(a.getNombre());
        }*/

            String idBarrio = null;
            switch (barrio) {
                case "1":
                    idBarrio = "TUxVQ0NBUmRhYWU0";
                    break;
                case "2":
                    idBarrio = "TUxVQ1BVTjYxODI";
                    break;
                case "3":
                    idBarrio = "TUxVQ01BTDE0YmY1";
                    break;
                case "4":
                    idBarrio = "TUxVQ0JVQzNlMDdl";
                    break;
                case "5":
                    idBarrio = "TUxVQ1BPQzM5ZGRi";
                    break;
                case "6":
                    idBarrio = "TUxVQ1BVTjJmMjkx";
                    break;
                case "7":
                    idBarrio = "TUxVQ1BBUmU3Y2Nj";
                    break;
                case "8":
                    idBarrio = "TUxVQ1BBTDU0NzY";
                    break;
                case "9":
                    idBarrio = "TUxVQ1BBUjVkNGE4";
                    break;
                case "10":
                    idBarrio = "TUxVQ0NPUjZmZjNm";
                    break;
                case "11":
                    idBarrio = "TUxVQ0NFTjVjMTM";
                    break;
                case "12":
                    idBarrio = "TUxVQ0NJVTk5MTU";
                    break;
                case "13":
                    idBarrio = "TUxVQ0FHVWUwMzc3";
                    break;
                case "14":
                    idBarrio = "TUxVQ1BSQTYwOTJl";
                    break;
                case "15":
                    idBarrio = "TUxVQ1BF0TUxNDI";
                    break;
                case "16":
                    idBarrio = "TUxVQ0xBWjk5YTE5";
                    break;
                default:
                    System.out.println("Barrio no soportado");
            }

            if (idBarrio != null) {
                MercadoLibreController mercadoLibreController = new MercadoLibreController();
                List<MercadoLibreProperty> inmueblesMeli = mercadoLibreController.LoadAlojamientos(idBarrio);

                RDFModelController rdfModelController = new RDFModelController();
                rdfModelController.LoadRDF(inmueblesMeli);
            } else {
                barrio = console.readLine();
            }
        //}

        System.out.println("Chau chau");
    }
}
