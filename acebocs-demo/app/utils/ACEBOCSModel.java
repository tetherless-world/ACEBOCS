package utils;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.RiotNotFoundException;
import org.apache.jena.sparql.exec.RowSet.Exception;

public class ACEBOCSModel {
    public static Model getModel() {
        Model model = ModelFactory.createDefaultModel();
        try {
            model.read("./data/acebocs.ttl");
        } catch (RiotNotFoundException e) {
            try {
                model.read("./dist/data/acebocs.ttl");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }
}
