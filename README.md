# KGReasoning

## Description

KGReasoning is a project focused on building and enriching knowledge graphs through data integration and RDFS reasoning. The objective is to integrate several open datasets that share common concepts—such as geographic information on countries and cities from sources like DBpedia, Wikidata, and GeoNames—into a unified RDF dataset. RDFS-based reasoning is then applied to enrich the data, and SPARQL queries are used to extract combined insights from the integrated dataset.

## Project Overview

1. **Related Datasets:**  
   - Various open datasets (in CSV, JSON, RDF dumps, etc.) are collected that share common resources.  
   - Examples include datasets on countries and cities from DBpedia, Wikidata, GeoNames, Kaggle, Datahub, and Google Dataset Search.

2. **Data Extraction:**  
   - Data is extracted using different methods such as downloading CSV files, RDF dumps, querying SPARQL endpoints, or scraping data using available libraries.
   - The datasets are chosen to ensure common concepts (e.g., the city of Paris represented in multiple sources).

3. **Data Integration:**  
   - All datasets are converted to RDF format using custom scripts or available tools.
   - The converted data is merged into a single integrated RDF dataset by adding relationships (such as `owl:sameAs`) or additional triples to establish links between the datasets.
   - Tools like Apache Jena are used to read, manipulate, and write RDF data.

4. **SPARQL Queries:**  
   - A set of SPARQL queries is developed to interrogate the integrated RDF dataset.
   - These queries combine information from various datasets. For instance, one query might extract both the geographic coordinates of Paris from GeoNames and its historical details from DBpedia.
   - The queries include:
     - Federated queries that access external data sources.
     - Queries using OPTIONAL.
     - Queries on named graphs.
     - Aggregation queries.
     - Queries with property path expressions.
     - Queries using MINUS or FILTER NOT EXISTS.

5. **RDFS Reasoning:**  
   - RDFS reasoning is implemented to structure the RDF data by defining a clear and organized schema.
   - **Ontology Construction:**  
     - If the datasets already have an existing schema, it is reused; otherwise, the main entities are identified and organized into classes (e.g., `:City`, `:Country`) and properties (e.g., `:capitalOf`, `:surface`).
     - An RDFS ontology is defined using `rdfs:subClassOf` and `rdfs:subPropertyOf` to create hierarchies, with domains and ranges (`rdfs:domain` and `rdfs:range`) set for each property.
   - **RDFS Reasoning Process:**  
     - The RDF data is imported into a model using tools such as Jena.
     - An RDFS reasoning model is applied to automatically infer additional information.
     - SPARQL queries are executed on the enriched model to compare the raw data with the inferred data, demonstrating how entities are reclassified or how relations are extended.

## Steps to Follow

1. **Download the Data:**  
   Download the dataset from [this Kaggle link](https://www.kaggle.com/datasets/davidcariboo/player-scores/data).

2. **Extract the Files:**  
   Extract the ZIP file (containing CSV files) into the project's `data` directory.

3. **Convert CSV to RDF:**  
   Run the Python script `exectarql.py` to convert the CSV data into RDF format.

4. **Merge RDF Data:**  
   Execute the Java program `FusionRDF.java` to merge the RDF data into a single file called `football.ttl`, which will be stored in the `ttl` folder.

5. **Apply Reasoning:**  
   Run the Java program `ReasonerRDF.java` to apply RDFS reasoning on the `football.ttl` file. The resulting enriched file will be named `football_enriched.ttl`.

6. **SPARQL Queries:**  
   SPARQL queries are stored in the `queries` directory:
   - `queries1.sparql` contains queries for the initial data in `football.ttl`.
   - `queries2.sparql` contains queries for the enriched data in `football_enriched.ttl`.

**Note:** The ontology is located in the `ttl` folder under the name `ontologie.ttl`.
