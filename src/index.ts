import express from "express";
import type { Request, Response } from "express"; // Type-only import
import dotenv from "dotenv";
import { connectDB } from "./database.js"; // OBS nodenext kräver .js

dotenv.config();

const app = express();
const port = process.env.PORT || 3000;

// Middleware för JSON
app.use(express.json());

// Root route
app.get("/", (req: Request, res: Response) => {
  res.send("Servern fungerar! Besök /movies för data.");
});

// Hämta första 5 filmer
app.get("/movies", async (req: Request, res: Response) => {
  try {
    const db = await connectDB();
    const moviesCollection = db.collection("movies");
    const movies = await moviesCollection.find({}).limit(5).toArray();
    res.json(movies);
  } catch (err) {
    res.status(500).json({ error: "Kunde inte hämta data från databasen" });
  }
});

// Dynamisk route /movies/:id
app.get("/movies/:id", async (req: Request, res: Response) => {
  const id = Number(req.params.id);

  if (isNaN(id)) {
    return res.status(400).send("Not a number"); // return stoppar exekvering
  }

  try {
    const db = await connectDB();
    const moviesCollection = db.collection("movies");
    const movie = await moviesCollection.find().skip(id - 1).limit(1).toArray();

    if (!movie || movie.length === 0) {
      return res.status(404).send("Movie not found");
    }

    res.json(movie[0]);
  } catch (err) {
    res.status(500).json({ error: "Kunde inte hämta data från databasen" });
  }
});

// Starta servern EFTER databasen är ansluten
async function startServer() {
  try {
    await connectDB();
    app.listen(port, () => {
      console.log(`Servern kör på http://localhost:${port}`);
    });
  } catch (err) {
    console.error("Kunde inte ansluta till databasen:", err);
    process.exit(1); // Stoppa appen om databasen inte kan starta
  }
}

startServer();
