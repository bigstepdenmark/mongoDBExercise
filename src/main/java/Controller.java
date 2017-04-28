import com.mongodb.Block;
import org.bson.BsonValue;
import org.bson.Document;
import com.mongodb.client.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Accumulators.*;
import static com.mongodb.client.model.Filters.regex;
import static com.mongodb.client.model.Sorts.*;

/**
 * Created by ismailcam on 25/04/2017.
 */
public class Controller
{
    private DatabaseSetup setup;

    public Controller()
    {
        setup = new DatabaseSetup();
        setup.setup();
    }

    /**
     * ================ DONE ================
     * How many Twitter users are in our database?
     * Shell version: ./files/exerciseShellVersion/exercises.js
     */
    public void countUsers()
    {
        int count = setup.collection.distinct( "user", BsonValue.class )
                                    .into( new ArrayList<BsonValue>() ).size();
        System.out.println( "Tweeter users: " + count );
    }

    /**
     * ================ DONE ================
     * Which Twitter users link the most to other Twitter users? (Provide the top ten.)
     * Shell version: ./files/exerciseShellVersion/exercises.js
     */
    public void topUsers(int top)
    {
        setup.collection.aggregate( Arrays.asList(
                Aggregates.match( regex( "text", ".*@.*" ) ),
                Aggregates.group( "$user", sum( "tweets", 1 ) ),
                Aggregates.sort( descending( "tweets" ) ),
                Aggregates.limit( top )
        ) ).forEach( printBlock() );
    }

    /**
     * ================ NOT DONE ================
     * Who is are the most mentioned Twitter users? (Provide the top five.)
     * Shell version: ./files/exerciseShellVersion/exercises.js
     */
    public void popularUsers()
    {
        // ????
    }

    /**
     * ================ DONE ================
     * Who are the most active Twitter users (top ten)?
     * Shell version: ./files/exerciseShellVersion/exercises.js
     */
    public void mostActiveUsers(int top)
    {
        setup.collection.aggregate( Arrays.asList(
                Aggregates.group( "$user", sum( "tweets", 1 ) ),
                Aggregates.sort( descending( "tweets" ) ),
                Aggregates.limit( top )
        ) ).forEach( printBlock() );
    }

    /**
     * ================ NOT DONE ================
     * Who are the five most grumpy (most negative tweets) and the most happy (most positive tweets)? (Provide five users for each group)
     * Shell version: ./files/exerciseShellVersion/exercises.js
     */
    public void mostGrumpy(int top)
    {
        setup.collection.aggregate( Arrays.asList(
                Aggregates.match( new Document( "polarity", 0 ) ),
                Aggregates.group( "$user", sum( "tweets", 1 ) ),
                Aggregates.sort( descending( "tweets" ) ),
                Aggregates.limit( top )
        ) ).forEach( printBlock() );
    }

    public void mostHappy(int top)
    {
        setup.collection.aggregate( Arrays.asList(
                Aggregates.match( new Document( "polarity", 4 ) ),
                Aggregates.group( "$user", sum( "tweets", 1 ) ),
                Aggregates.sort( descending( "tweets" ) ),
                Aggregates.limit( top )
        ) ).forEach( printBlock() );
    }

    private Block<Document> printBlock()
    {
        return new Block<Document>()
        {
            @Override
            public void apply(final Document document)
            {
                System.out.println( document.toJson() );
            }
        };
    }
}
