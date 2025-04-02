import org.apache.jena.rdf.model.*;
import org.apache.jena.reasoner.*;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ReasonerRDF {

    public static void main(String[] args) {
        try {
            long startTime = System.currentTimeMillis();
            String ontologyFile = "ttl/ontologie.ttl"; 
            String dataFile = "ttl/football.ttl";     

            Model ontologyModel = ModelFactory.createDefaultModel();
            ontologyModel.read(ontologyFile, "TTL");

            Model dataModel = ModelFactory.createDefaultModel();
            dataModel.read(dataFile, "TTL");

            Model combinedModel = ModelFactory.createUnion(ontologyModel, dataModel);

            Reasoner reasoner = ReasonerRegistry.getRDFSReasoner();
            reasoner = reasoner.bindSchema(ontologyModel);

            InfModel infModel = ModelFactory.createInfModel(reasoner, combinedModel);

            StmtIterator iter = infModel.listStatements();
            while (iter.hasNext()) {
                Statement stmt = iter.nextStatement();
                System.out.println(stmt);
            }

            String outputFile = "football_enriched.ttl";
            OutputStream out = new FileOutputStream(outputFile);
            infModel.write(out, "TTL");
            out.close();

            System.out.println("\nModèle enrichi sauvegardé dans : " + outputFile);
            long endTime = System.currentTimeMillis();
            System.out.println("Temps d'exécution : " + (endTime - startTime) + " ms");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}