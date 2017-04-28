// How many Twitter users are in our database?
db.getCollection('tweets').distinct('user');

// Which Twitter users link the most to other Twitter users? (Provide the top ten.)
db.getCollection('tweets').aggregate([
    {
        $match: {text: /@/}
    },

    {
        $group: {
            _id: "$user",
            tweets: {$sum: 1}
        }
    },
    {
        $sort: {tweets: -1}
    },
    {
        $limit: 10
    }
]);

// Who is are the most mentioned Twitter users? (Provide the top five.)
// ikke færdig


// Who are the most active Twitter users (top ten)?
db.getCollection('tweets').aggregate([
    {
        $group: {
            _id: "$user",
            tweets: {$sum: 1}
        }
    },
    {
        $sort: {tweets: -1}
    },
    {
        $limit: 10
    }
]);

// Who are the five most grumpy (most negative tweets) and the most happy (most positive tweets)? (Provide five users for each group)
db.getCollection('tweets').aggregate([
    {
        $match: {polarity: 0}
    },

    {
        $group: {
            _id: "$user",
            tweets: {$sum: 1}
        }
    },
    {
        $sort: {tweets: -1}
    },
    {
        $limit: 10
    }
]);

// og

db.getCollection('tweets').aggregate([
    {
        $match: {polarity: 4}
    },

    {
        $group: {
            _id: "$user",
            tweets: {$sum: 1}
        }
    },
    {
        $sort: {tweets: -1}
    },
    {
        $limit: 10
    }
]);