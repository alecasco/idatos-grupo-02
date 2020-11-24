package controllers;

import domain.MercadoLibreProperty;
import dto.VCARDCustomized;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.vocabulary.DC_10;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.VCARD;

import java.util.List;

public class RDFModelController {

    Model model;

    public void LoadRDF(List<MercadoLibreProperty> inmueblesMeli) {
        String exampleURI = "http://example.org/";
        String dbPediaURI ="http://dbpedia.org/page/";

        model = ModelFactory.createDefaultModel();

        for (int i=0; i<inmueblesMeli.size(); i++) {
            MercadoLibreProperty inmueble = inmueblesMeli.get(i);
            model.createResource(exampleURI + normalizar(inmueble.getTitulo()))
                    .addProperty(VCARDCustomized.PRICE, exampleURI + inmueble.getPrecio())
                    .addProperty(VCARD.Street, exampleURI + normalizar(inmueble.getDireccion()))
                    .addProperty(VCARDCustomized.NEIGHBORHOOD, dbPediaURI + normalizar(inmueble.getBarrio()))
                    .addProperty(VCARDCustomized.M2, exampleURI + normalizar(inmueble.getM2()))
                    .addProperty(VCARD.TITLE, exampleURI + normalizar(inmueble.getTitulo()))
                    .addProperty(RDF.type, dbPediaURI + normalizar(inmueble.getTipo()))
                    .addProperty(VCARDCustomized.BATHROOMS, exampleURI + normalizar(inmueble.getCantBanios()))
                    .addProperty(VCARDCustomized.ROOMS, exampleURI + normalizar(inmueble.getCantDormitorios()))
                    .addProperty(VCARDCustomized.CONTACT,
                            model.createResource()
                                    .addProperty(VCARD.NAME, exampleURI + normalizar(inmueble.getContacto().getNombre()))
                                    .addProperty(VCARD.TEL, exampleURI + normalizar(inmueble.getContacto().getTelefono())));
        }

        model.write(System.out);
    }

    private String normalizar(String sentencia) {
        return sentencia.replace(' ', '_')
                .replace(' ', '_')
                .replace('á','a')
                .replace('é','e')
                .replace('í','i')
                .replace('ó','o')
                .replace('ú','u');
    }

    public void filtroPorBarrio(String barrio) {
        String queryString = barrio;
        Query query = QueryFactory.create(queryString) ;
        try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
            ResultSet results = qexec.execSelect() ;
            for ( ; results.hasNext() ; )
            {
                QuerySolution soln = results.nextSolution() ;
                RDFNode x = soln.get("varName") ;       // Get a result variable by name.
                Resource r = soln.getResource("VarR") ; // Get a result variable - must be a resource
                Literal l = soln.getLiteral("VarL") ;   // Get a result variable - must be a literal
            }
        }
    }
}
