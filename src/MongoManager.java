

import java.util.ArrayList;
import java.util.Iterator;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoManager {

	static MongoClientURI uri;
	static MongoClient client;
	static MongoDatabase db;
	static MongoCollection<Document> mongoCollection;

	public static boolean insertDocuments(String uriString, String tableName, ArrayList<Document> documents) {
		// Realizamos la conexión a la base de datos.
		connect(uriString);
		// Insertamos los documentos en la tabla.
		return create(tableName, documents);
	}
	
	private static boolean create(String tableName, ArrayList<Document> documents) {
		// Extraemos la base de datos.
		String aux = "Salida";
		db = client.getDatabase(aux);
		// Extraemos la colección. Si no existe, la creamos.
		mongoCollection = db.getCollection(tableName);
		
		Iterator<Document> iterator = documents.iterator();
		// Para cada uno de los documentos a insertar...
		while(iterator.hasNext()) {
			// Seleccionamos el documento.
			Document document = iterator.next();
			// Realizamos la inserción.
			mongoCollection.insertOne(document);
		}
		// Mostramos el contenido de la colección al usuario.
		Iterator iteratoraux = mongoCollection.find().iterator();
		while(iteratoraux.hasNext()) {
			System.out.println(iteratoraux.next());
		}
		return true;
	}
	
	private static void connect(String uriString) {
		uri  = new MongoClientURI(uriString); 
		client = new MongoClient(uri);
	}
}