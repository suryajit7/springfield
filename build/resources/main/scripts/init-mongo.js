db.createUser({
    user: 'mongo',
    pwd: 'mongo@123',
    roles: [
        {
            role: 'readWrite',
            db: 'mongoDB',
        },
    ],
});

db = new Mongo().getDB("mongoDB");

db.createCollection('users', { capped: false });
db.createCollection('test', { capped: false });

db.test.insert([
    { "item": 1 },
    { "item": 2 },
    { "item": 3 },
    { "item": 4 },
    { "item": 5 }
]);