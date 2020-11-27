package dto;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;

public class VCARDCustomized {
    /**
     * The namespace of the vocabulary as a string
     */
    public static final String dbPediaURI ="http://dbpedia.org/page/";
    public static final String schemaURI ="https://schema.org/";
    public static final String exampleURI = "http://example.org/";

    private static final Model m = ModelFactory.createDefaultModel();

    public static final Property PRICE = m.createProperty(dbPediaURI + "Price");
    public static final Property NEIGHBORHOOD = m.createProperty(dbPediaURI + "Neighbourhood");
    public static final Property M2 = m.createProperty(dbPediaURI + "Area");
    public static final Property BATHROOMS = m.createProperty(dbPediaURI + "Bathroom");
    public static final Property ROOMS = m.createProperty(dbPediaURI + "Room");
    public static final Property CONTACT = m.createProperty(schemaURI + "ContactPoint");

    public static final Property STATE_NUMBER = m.createProperty(dbPediaURI + "StateNumber");
    public static final Property STATE_NAME = m.createProperty(schemaURI + "StateName");
    public static final Property STREET_NUMBER = m.createProperty(exampleURI + "StreetNumber");
}
