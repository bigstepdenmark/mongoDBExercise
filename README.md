<h1>MongoDBExercise</h1>
<p><b>Group:</b> Ismail Cam og Mazlum Dogan Sert</p>

<hr>
<h3>For at køre programmet, kør Main class og følg guide</h3>
<p>/mongoDBExercise/src/main/java/Main.java</p>

<p><b>Shell version: <a href="https://github.com/bigstepdenmark/mongoDBExercise/blob/master/files/exerciseShellVersion/exercises.js">Se fil</a></b></p>
<hr>

<h3>How many Twitter users are in our database?</h3>

```java
    public void countUsers()
    {
        int count = setup.collection.distinct( "user", BsonValue.class )
                                    .into( new ArrayList<BsonValue>() ).size();
        System.out.println( "Tweeter users: " + count );
    }
```


<h3>Which Twitter users link the most to other Twitter users? (Provide the top ten.)</h3>

```java
    public void topUsers(int top)
    {
        setup.collection.aggregate( Arrays.asList(
                Aggregates.match( regex( "text", ".*@.*" ) ),
                Aggregates.group( "$user", sum( "tweets", 1 ) ),
                Aggregates.sort( descending( "tweets" ) ),
                Aggregates.limit( top )
        ) ).forEach( printBlock() );
    }
```

<h3>Who is are the most mentioned Twitter users? (Provide the top five.)</h3>

```java
    public void popularUsers()
    {
        // ????
    }
```

<h3>Who are the most active Twitter users (top ten)?</h3>

```java
    public void mostActiveUsers(int top)
    {
        setup.collection.aggregate( Arrays.asList(
                Aggregates.group( "$user", sum( "tweets", 1 ) ),
                Aggregates.sort( descending( "tweets" ) ),
                Aggregates.limit( top )
        ) ).forEach( printBlock() );
    }
```

<h3>Who are the five most grumpy (most negative tweets) and the most happy (most positive tweets)? (Provide five users for each group)</h3>

```java
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
```

```java
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
```
