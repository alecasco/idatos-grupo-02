package controllers;

import domain.MercadoLibreProperty;
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
        model = ModelFactory.createDefaultModel();
        String baseURI = "http://example.org/";
        for (int i=0; i<inmueblesMeli.size(); i++) {
            MercadoLibreProperty inmueble = inmueblesMeli.get(i);
            model.createResource(normalizar(baseURI + inmueble.getTitulo()))
                    .addProperty(VCARD.Street, normalizar(baseURI + inmueble.getDireccion()))
                    .addProperty(VCARD.Locality, normalizar(baseURI + inmueble.getBarrio()))
                    // FALTA m2
                    .addProperty(VCARD.TITLE, normalizar(baseURI + inmueble.getTitulo()))
                    .addProperty(RDF.type, normalizar(baseURI + inmueble.getTipo()))
                    // FALTA cantBanios
                    // FALTA cantDormitorios
                    .addProperty(VCARD.Given, // TODO CHEQUEAR CÓMO PONER CONTACTO
                            model.createResource()
                                    .addProperty(VCARD.NAME, normalizar(baseURI + inmueble.getContacto().getNombre()))
                                    .addProperty(VCARD.TEL, normalizar(baseURI + inmueble.getContacto().getTelefono())));
                    // FALTA GARAGES
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
