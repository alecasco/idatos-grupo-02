package controllers;

import domain.AnepCenter;
import domain.DireccionInfoCasas;
import domain.InfocasasProperty;
import domain.MercadoLibreProperty;
import dto.VCARDCustomized;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFTest;
import org.apache.jena.vocabulary.VCARD;

import java.util.List;

public class RDFModelController {

    Model inmueblesModel;
    Model anepModel;

    public void LoadinmueblesRDF(List<MercadoLibreProperty> inmueblesMeli, List<InfocasasProperty> inmueblesInfocasas) {
        String exampleURI = "http://example.org/";
        String dbPediaURI ="http://dbpedia.org/page/";

        inmueblesModel = ModelFactory.createDefaultModel();

        for (MercadoLibreProperty inmueble : inmueblesMeli) {
            inmueblesModel.createResource(inmueble.getLink())
                    .addProperty(VCARDCustomized.PRICE, exampleURI + inmueble.getPrecio())
                    .addProperty(VCARD.Street, exampleURI + normalizar(inmueble.getDireccion()))
                    .addProperty(VCARDCustomized.NEIGHBORHOOD, dbPediaURI + normalizar(inmueble.getBarrio()))
                    .addProperty(VCARDCustomized.M2, exampleURI + normalizar(inmueble.getM2()))
                    .addProperty(VCARD.TITLE, exampleURI + normalizar(inmueble.getTitulo()))
                    .addProperty(RDF.type, dbPediaURI + normalizar(inmueble.getTipo()))
                    .addProperty(VCARDCustomized.BATHROOMS, exampleURI + normalizar(inmueble.getCantBanios()))
                    .addProperty(VCARDCustomized.ROOMS, exampleURI + normalizar(inmueble.getCantDormitorios()))
                    .addProperty(VCARDCustomized.CONTACT,
                            inmueblesModel.createResource()
                                    .addProperty(VCARD.NAME, exampleURI + normalizar(inmueble.getContacto().getNombre()))
                                    .addProperty(VCARD.TEL, exampleURI + normalizar(inmueble.getContacto().getTelefono())));
        }

        for (InfocasasProperty inmueble : inmueblesInfocasas) {
            inmueblesModel.createResource(inmueble.getLink())
                    .addProperty(VCARDCustomized.PRICE, exampleURI + inmueble.getPrecio())
                    .addProperty(VCARD.Street, exampleURI + normalizarDireccionInfocasas(inmueble.getDireccion()))
                    .addProperty(VCARDCustomized.NEIGHBORHOOD, dbPediaURI + normalizar(inmueble.getBarrio()))
                    .addProperty(VCARDCustomized.M2, exampleURI + normalizar(String.valueOf(inmueble.getM2())))
                    .addProperty(VCARD.TITLE, exampleURI + normalizar(inmueble.getTitulo()))
                    .addProperty(RDF.type, dbPediaURI + normalizar(inmueble.getTipo()))
                    .addProperty(RDFTest.description, exampleURI + normalizar(inmueble.getDescripcion()))
                    .addProperty(VCARDCustomized.BATHROOMS, exampleURI + normalizar(inmueble.getCantBanios()))
                    .addProperty(VCARDCustomized.ROOMS, exampleURI + normalizar(inmueble.getCantDormitorios()))
                    .addProperty(VCARDCustomized.CONTACT,
                            inmueblesModel.createResource()
                                    .addProperty(VCARD.NAME, exampleURI + normalizar(inmueble.getContacto().getNombre()))
                                    .addProperty(VCARD.TEL, exampleURI + normalizar(inmueble.getContacto().getTelefono())));
        }

        //inmueblesModel.write(System.out);
    }

    public void LoadANEPRDF(List<AnepCenter> anepCenters) {
        String exampleURI = "http://example.org/";
        String dbPediaURI ="http://dbpedia.org/page/";

        anepModel = ModelFactory.createDefaultModel();

        for (AnepCenter centroANEP : anepCenters) {
            anepModel.createResource(exampleURI + normalizar(centroANEP.getId()))
                    .addProperty(VCARD.NAME, exampleURI + normalizar(centroANEP.getNombre()))
                    .addProperty(VCARDCustomized.STATE_NUMBER, exampleURI + normalizar(centroANEP.getDeptoNumero()))
                    .addProperty(VCARDCustomized.STATE_NAME, exampleURI + normalizar(centroANEP.getDeptoNombre()))
                    .addProperty(VCARD.Locality, exampleURI + normalizar(centroANEP.getLocalidad()))
                    .addProperty(VCARDCustomized.NEIGHBORHOOD, dbPediaURI + normalizar(centroANEP.getBarrio()))
                    .addProperty(VCARD.Street, exampleURI + normalizar(centroANEP.getCalle()))
                    .addProperty(VCARDCustomized.STREET_NUMBER, exampleURI + normalizar(centroANEP.getNumeroPuerta()))
                    .addProperty(VCARD.TEL, exampleURI + normalizar(centroANEP.getTelefono()));
        }

        //anepModel.write(System.out);
    }

    private String normalizar(String sentencia) {
        return sentencia.replace(' ', '_')
                .replace(' ', '_')
                .replace('á','a')
                .replace('é','e')
                .replace('í','i')
                .replace('ó','o')
                .replace('ú','u')
                .replace('ñ','n')
                .replace('Ñ','n')
                .replace('-','_')
                .replace('°','o')
                .replace('º','o')
        ;
    }

    private String normalizarDireccionInfocasas(DireccionInfoCasas direccionInfoCasas) {
        String direccion = normalizar(direccionInfoCasas.getCalle()) + "_" + direccionInfoCasas.getNumeroPuerta();
        if (!direccionInfoCasas.getApartamento().equals("0")) {
            direccion += "_apto_" + direccionInfoCasas.getApartamento();
        }
        return direccion;
    }

    public void filtroInmuebles(String dormitorios, String banios) {
        System.out.println();
        System.out.println("RDF QUERY");
        String queryString = "SELECT ?x WHERE { ?x  <http://dbpedia.org/page/Bathroom>  \"http://example.org/"+banios+"\" ." +
                " ?x  <http://dbpedia.org/page/Room>  \"http://example.org/"+dormitorios+"\"}";
        Query query = QueryFactory.create(queryString) ;
        try (QueryExecution qexec = QueryExecutionFactory.create(query, inmueblesModel)) {
            ResultSet results = qexec.execSelect() ;
            for ( ; results.hasNext() ; )
            {
                QuerySolution soln = results.nextSolution() ;
                System.out.println(soln);
            }
        }
    }

    public void filtroAnepPorBarrio(String barrio) {
        System.out.println();
        System.out.println("RDF QUERY ANEP");
        String queryString = "SELECT ?y ?z ?w WHERE { ?x  <http://dbpedia.org/page/Neighbourhood>  \"http://dbpedia.org/page/"+barrio.toUpperCase()+"\" ; <http://www.w3.org/2001/vcard-rdf/3.0#NAME> ?y ; <http://www.w3.org/2001/vcard-rdf/3.0#Street> ?z ; <http://example.org/StreetNumber> ?w}";
        Query query = QueryFactory.create(queryString) ;
        try (QueryExecution qexec = QueryExecutionFactory.create(query, anepModel)) {
            ResultSet results = qexec.execSelect() ;
            for ( ; results.hasNext() ; )
            {
                QuerySolution soln = results.nextSolution() ;
                System.out.println(soln);
            }
        }
    }
}
