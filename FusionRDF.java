
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

import java.io.FileOutputStream;
import java.io.IOException;

public class FusionRDF {
    public static void main(String[] args) {
        Model mergedModel = ModelFactory.createDefaultModel();

        String[] files = {
            "ttl/players.ttl",
            "ttl/clubs.ttl",
            "ttl/games.ttl",
            "ttl/transfers.ttl",
            "ttl/competitions.ttl",
            "ttl/country.ttl"
        };

        for (String file : files) {
            mergedModel.read(file, "TURTLE");
        }

        mergedModel.setNsPrefix("", "http://example.org/football#");
        mergedModel.setNsPrefix("xsd", "http://www.w3.org/2001/XMLSchema#");
        mergedModel.setNsPrefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");

        try (FileOutputStream out = new FileOutputStream("ttl/football.ttl")) {
            mergedModel.write(out, "TURTLE");
            System.out.println("Fichier fusionné créé : football.ttl");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}