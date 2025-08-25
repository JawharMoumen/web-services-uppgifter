// mongo_test.js
const { MongoClient, ServerApiVersion } = require("mongodb");
require("dotenv").config(); // Läser .env-filen

const uri = process.env.DB_CONNECTION_STRING;
const dbName = process.env.DB_NAME;

if (!uri || !dbName) {
  throw new Error("DB_CONNECTION_STRING och DB_NAME måste finnas i .env");
}

const client = new MongoClient(uri, {
  serverApi: {
    version: ServerApiVersion.v1,
    strict: true,
    deprecationErrors: true,
  },
});

async function testConnection() {
  try {
    await client.connect();
    console.log("Ansluten till MongoDB!");
    const db = client.db(dbName);
    const movies = db.collection("movies");
    const film = await movies.findOne({});
    console.log("Exempel-film:", film);
  } catch (err) {
    console.error("Fel vid anslutning:", err);
  } finally {
    await client.close();
  }
}

testConnection();
