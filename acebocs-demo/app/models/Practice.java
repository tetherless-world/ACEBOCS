package models;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.vocabulary.RDFS;

import utils.ACEBOCSModel;

public class Practice extends models.Resource {
    public static List<Practice> getAll() {
        System.out.println("Retrieving all practices...");
        List<Practice> practices = new ArrayList<Practice>();
        Model model = ACEBOCSModel.getModel();
        ResIterator iter = model.listSubjectsWithProperty(RDFS.subClassOf, ResourceFactory.createResource("http://purl.org/twc/acebocs/Practice"));
        while (iter.hasNext()) {
            Resource r = iter.nextResource();
            Practice practice = new Practice();
            practice.setUri(r.getURI());
            model.listObjectsOfProperty(r, RDFS.label).forEachRemaining(label -> {
                practice.setLabel(label.asLiteral().getString());
            });
            practices.add(practice);
        }
        return practices;
    }
}
