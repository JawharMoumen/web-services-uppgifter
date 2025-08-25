import { MongoClient, ServerApiVersion } from "mongodb";
import dotenv from "dotenv";

dotenv.config(); // Läser .env-filen

const uri: string | undefined = process.env.DB_CONNECTION_STRING;
const dbName: string | undefined = process.env.DB_NAME;

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

let isConnected = false;

export async function connectDB() {
  if (!isConnected) {
    await client.connect();
    isConnected = true;
    console.log("Ansluten till MongoDB!");
  }
  return client.db(dbName);
}
