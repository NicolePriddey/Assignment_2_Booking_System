package bookingSys;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class Connect {	
	public static void main(String[] args) {
		System.out.println("Hello world");
		
		try {
			MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://nicole:M@ngoDb1@priddey-8mqsj.mongodb.net/test?retryWrites=true"));
			DB database = mongoClient.getDB("sample_mflix");
			DBCollection collection = database.getCollection("movies");
			DBObject query = new BasicDBObject("plot", "Three men hammer on an anvil and pass a bottle of beer around.");
			DBCursor cursor = collection.find(query);
			DBObject movie = cursor.one();
			System.out.println((String)cursor.one().get("title"));	
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

