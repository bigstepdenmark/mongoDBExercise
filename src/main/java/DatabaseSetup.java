/**
 * Created by ismailcam on 25/04/2017.
 */

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DatabaseSetup
{
    public MongoClient client;
    public MongoDatabase database;
    public MongoCollection<Document> collection;

    public void setup()
    {
        client = new MongoClient( "localhost", 27017 );
        database = client.getDatabase( "database_ex" );
        collection = database.getCollection( "tweets" );
    }
}
