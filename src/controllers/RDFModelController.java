package controllers;

import domain.MercadoLibreProperty;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.vocabulary.VCARD;

import java.util.List;

public class RDFModelController {

    public void LoadRDF(List<MercadoLibreProperty> inmueblesMeli) {
        String personURI = "http://somewhere/JohnSmith";
        String givenName = "John";
        String familyName = "Smith";
        String fullName = givenName + " " + familyName;

        Model model = ModelFactory.createDefaultModel();
        for (int i=0; i<inmueblesMeli.size(); i++) {
            MercadoLibreProperty inmueble = inmueblesMeli.get(i);
            model.createResource(inmueble.getTitulo())
                    .addProperty(VCARD.Street, inmueble.getDireccion());
                    /*.addProperty(VCARD.N,
                            model.createResource()
                                    .addProperty(VCARD.Given, givenName)
                                    .addProperty(VCARD.Family, familyName));*/
        }

        StmtIterator iter = model.listStatements();

        // print out the predicate, subject and object of each statement
        while (iter.hasNext()) {
            Statement stmt = iter.nextStatement();  // get next statement
            Resource subject = stmt.getSubject();     // get the subject
            Property predicate = stmt.getPredicate();   // get the predicate
            RDFNode object = stmt.getObject();      // get the object

            System.out.print(subject.toString());
            System.out.print(" " + predicate.toString() + " ");
            if (object instanceof Resource) {
                System.out.print(object.toString());
            } else {
                // object is a literal
                System.out.print(" \"" + object.toString() + "\"");
            }

            System.out.println(" .");
        }
    }
}
