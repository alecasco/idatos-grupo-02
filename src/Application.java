import controllers.AnepController;
import controllers.InfoCasasController;
import controllers.MercadoLibreController;
import controllers.RDFModelController;
import domain.AnepCenter;
import domain.InfocasasProperty;
import domain.MercadoLibreProperty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Application {

    public static void main(String[] args) throws IOException {
        String barrio = "";
        String dormitorios = "";
        String banios = "";
        String nombreBarrio = null;

        AnepController anepController = new AnepController();
        anepController.LoadCentros();

        InfoCasasController infoCasasController = new InfoCasasController();
        infoCasasController.loadInfocasasProperties();

        String idBarrio = null;
        while (!barrio.equals("salir")) {
            System.out.println("Bienvenido, seleccione un barrio:");
            System.out.println("1. Carrasco");
            System.out.println("2. Malvín");
            System.out.println("3. Buceo");
            System.out.println("4. Pocitos");
            System.out.println("5. Palermo");
            System.out.println("6. Cordón");
            System.out.println("7. Centro");
            System.out.println("8. Aguada");
            System.out.println("9. Prado");
            System.out.println("10. Peñarol");
            System.out.println("salir");
            System.out.println();
            System.out.print("Ingrese el numero de barrio: ");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            barrio = reader.readLine();

            if (!barrio.equals("salir")) {
                switch (barrio) {
                    case "1":
                        idBarrio = "TUxVQ0NBUmRhYWU0";
                        nombreBarrio = "Carrasco";
                        break;
                    case "2":
                        idBarrio = "TUxVQ01BTDE0YmY1";
                        nombreBarrio = "Malvin";
                        break;
                    case "3":
                        idBarrio = "TUxVQ0JVQzNlMDdl";
                        nombreBarrio = "Buceo";
                        break;
                    case "4":
                        idBarrio = "TUxVQ1BPQzM5ZGRi";
                        nombreBarrio = "Pocitos";
                        break;
                    case "5":
                        idBarrio = "TUxVQ1BBTDU0NzY";
                        nombreBarrio = "Palermo";
                        break;
                    case "6":
                        idBarrio = "TUxVQ0NPUjZmZjNm";
                        nombreBarrio = "Cordon";
                        break;
                    case "7":
                        idBarrio = "TUxVQ0NFTjVjMTM";
                        nombreBarrio = "Centro";
                        break;
                    case "8":
                        idBarrio = "TUxVQ0FHVWUwMzc3";
                        nombreBarrio = "Aguada";
                        break;
                    case "9":
                        idBarrio = "TUxVQ1BSQTYwOTJl";
                        nombreBarrio = "Prado";
                        break;
                    case "10":
                        idBarrio = "TUxVQ1BF0TUxNDI";
                        nombreBarrio = "Peñarol";
                        break;
                    default:
                        System.out.println("Barrio no soportado");
                        System.out.println();
                }

                if (idBarrio != null) {
                    System.out.println("Barrio seleccionado: "+ nombreBarrio);
                    System.out.println();

                    System.out.print("Ingrese la cantidad de dormitorios: ");
                    dormitorios = reader.readLine();

                    System.out.print("Ingrese la cantidad de baños: ");
                    banios = reader.readLine();

                    MercadoLibreController mercadoLibreController = new MercadoLibreController();
                    List<MercadoLibreProperty> inmueblesMeli = mercadoLibreController.LoadAlojamientos(idBarrio);

                    List<InfocasasProperty> propiedadesInfocasas = infoCasasController.findProperties(nombreBarrio);

                    RDFModelController rdfModelController = new RDFModelController();
                    rdfModelController.LoadinmueblesRDF(inmueblesMeli, propiedadesInfocasas);

                    List<AnepCenter> anepCenters = anepController.findCenters("Montevideo", "Montevideo", nombreBarrio);
                    rdfModelController.LoadANEPRDF(anepCenters);

                    rdfModelController.filtroInmuebles(dormitorios, banios);
                    rdfModelController.filtroAnepPorBarrio(nombreBarrio);

                    System.out.println();
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println();
                }
            }
        }

        System.out.println("Chau chau");
    }
}
