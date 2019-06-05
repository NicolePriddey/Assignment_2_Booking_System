package gui;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;

public class DatabaseTest {

public static void main(String[] args) {
	MongoClientURI uri = new MongoClientURI(
		    "mongodb+srv://nicole:1@priddey-8mqsj.mongodb.net/test?retryWrites=true&w=majority");
		
		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase database = mongoClient.getDatabase("DaySpa");
		MongoCollection<Document> collection = database.getCollection("Customer");
		System.out.println(database.getName());
		System.out.println(database.listCollections());
		for ( Document c : database.listCollections()) {
			System.out.println(c);
			
		}
		Document myDoc = collection.find(eq("CustomerID", 6001)).first();
		
			System.out.println(myDoc);
		
		
}
	

	
	
	
	
	
	
//	static final String dbUrl = "jdbc:mysql://localhost:3306/SkyDiving";
//	static final String usr = "root";
//	static final String pwd = "";
//
//	private static Connection myCon = null;
//	private static Statement stmt = null;
//	private static ResultSet rs = null;
//	public static String times[]; 
//	
//	public static void main(String[] args) throws SQLException {
//		myCon = DriverManager.getConnection(dbUrl, usr, pwd);
//		System.out.println("DB connection successful...");
//
//		// create a statement
//		stmt = myCon.createStatement();
//
//		// execute the statement
//		String sql = "select * from Session";
//		rs = stmt.executeQuery(sql);
//		
//		while (rs.next()) {
//			System.out.println(rs.getString("date") + ", " + rs.getString("booked"));
//		}
//		
//		sql = "SELECT * FROM session WHERE date = '2019-05-30'";
//		rs = stmt.executeQuery(sql);
//		
//		//String times[]; 
//		System.out.println("Loop: ");
//		int count = 0;
//		while (rs.next()) {
//			
//			times[count] = rs.getString("date") + ", " + rs.getString("booked");
//			count++;
//		}
//		
//		for (int i = 0; i < times.length; i++) {
//			System.out.println(times[i]);
//		}
//		
//		
//		stmt.close();
//		myCon.close();
	}	
