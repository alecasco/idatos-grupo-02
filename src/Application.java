import controllers.AnepController;
import controllers.InfoCasasController;
import controllers.MercadoLibreController;
import controllers.RDFModelController;
import domain.InfocasasProperty;
import domain.MercadoLibreProperty;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Application {

    public static void main(String[] args) throws IOException {
        String barrio = null;
        String nombreBarrio = null;
        Console console = System.console();

        AnepController anepController = new AnepController();
        anepController.LoadCentros();

        InfoCasasController infoCasasController = new InfoCasasController();
        infoCasasController.loadInfocasasProperties();


        while (barrio != "salir") {
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
            System.out.println();
            System.out.println("Ingrese el numero de barrio: ");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            barrio = reader.readLine();

            String idBarrio = null;
            switch (barrio) {
                case "1":
                    idBarrio = "TUxVQ0NBUmRhYWU0";
                    nombreBarrio = "Carrasco";
                    break;
                case "2":
                    idBarrio = "TUxVQ1BVTjYxODI";
                    nombreBarrio = "Punta Gorda";
                    break;
                case "3":
                    idBarrio = "TUxVQ01BTDE0YmY1";
                    nombreBarrio = "Malvín";
                    break;
                case "4":
                    idBarrio = "TUxVQ0JVQzNlMDdl";
                    nombreBarrio = "Buceo";
                    break;
                case "5":
                    idBarrio = "TUxVQ1BPQzM5ZGRi";
                    nombreBarrio = "Pocitos";
                    break;
                case "6":
                    idBarrio = "TUxVQ1BVTjJmMjkx";
                    nombreBarrio = "Punta Carretas";
                    break;
                case "7":
                    idBarrio = "TUxVQ1BBUmU3Y2Nj";
                    nombreBarrio = "Parque Rodó";
                    break;
                case "8":
                    idBarrio = "TUxVQ1BBTDU0NzY";
                    nombreBarrio = "Palermo";
                    break;
                case "9":
                    idBarrio = "TUxVQ1BBUjVkNGE4";
                    nombreBarrio = "Parque Batlle";
                    break;
                case "10":
                    idBarrio = "TUxVQ0NPUjZmZjNm";
                    nombreBarrio = "Cordón";
                    break;
                case "11":
                    idBarrio = "TUxVQ0NFTjVjMTM";
                    nombreBarrio = "Centro";
                    break;
                case "12":
                    idBarrio = "TUxVQ0NJVTk5MTU";
                    nombreBarrio = "Ciudad Vieja";
                    break;
                case "13":
                    idBarrio = "TUxVQ0FHVWUwMzc3";
                    nombreBarrio = "Aguada";
                    break;
                case "14":
                    idBarrio = "TUxVQ1BSQTYwOTJl";
                    nombreBarrio = "Prado";
                    break;
                case "15":
                    idBarrio = "TUxVQ1BF0TUxNDI";
                    nombreBarrio = "Peñarol";
                    break;
                case "16":
                    idBarrio = "TUxVQ0xBWjk5YTE5";
                    nombreBarrio = "La Blanqueada";
                    break;
                default:
                    System.out.println("Barrio no soportado");
            }

            System.out.println("Barrio seleccionado: "+ nombreBarrio);

            if (idBarrio != null) {
                MercadoLibreController mercadoLibreController = new MercadoLibreController();
                List<MercadoLibreProperty> inmueblesMeli = mercadoLibreController.LoadAlojamientos(idBarrio);

                List<InfocasasProperty> propiedadesInfocasas = infoCasasController.findProperties(nombreBarrio);
                System.out.println("Propiedades Infocasas en "+nombreBarrio+": ");
                for (InfocasasProperty a: propiedadesInfocasas) {
                    System.out.println(a.getPrecio());
                }
                System.out.println();
                System.out.println();

                RDFModelController rdfModelController = new RDFModelController();
                rdfModelController.LoadRDF(inmueblesMeli, propiedadesInfocasas);
            } else {
                barrio = reader.readLine();
            }
        }

        System.out.println("Chau chau");
    }
}
