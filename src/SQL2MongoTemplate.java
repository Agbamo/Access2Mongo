

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bson.Document;

public class SQL2MongoTemplate {

	public static ArrayList<Document> convert(ArrayList<String> columnNames, ResultSet resultSet) throws SQLException {
		
		ArrayList<Document> documents = new ArrayList<Document>();
		// Mientra queden filas por procesar...
		while(resultSet.next()) {
			// Creamos un nuevo documento. 
			// Recordemos que un documento equivale a una línea y una colección a una tabla.
			Document currentDocument = new Document();
			for (int currentColumn = 1; currentColumn<=columnNames.size(); currentColumn++) {
				// Extraemos el siguiente valor y lo añadimos al documento
				// (si el valor es null, añadimos "null")
				currentDocument.append(columnNames.get(currentColumn-1), String.valueOf(resultSet.getObject(currentColumn)));
			}
			// Añadimos el documento formado a la lista.
			documents.add(currentDocument);
		}
		return documents;
	}
}
