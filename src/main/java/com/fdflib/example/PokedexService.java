package com.fdflib.example;

import com.fdflib.example.model.Pokemon;
import com.fdflib.example.model.Trainer;
import com.fdflib.example.service.PokemonService;
import com.fdflib.example.service.TrainerService;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.persistence.database.DatabaseUtil;
import com.fdflib.service.FdfServices;
import com.fdflib.util.FdfSettings;
import java.sql.Connection;
import java.sql.DriverManager;

import java.util.ArrayList;
import java.util.List;

public class PokedexService {
    public static void main(String[] args) {
    	
    	
    	Connection c = null;
        try {
           Class.forName("org.postgresql.Driver");
           c = DriverManager
              .getConnection("jdbc:postgresql://enterprise211129.cvtq9j4axrge.us-east-1.rds.amazonaws.com:5432/postgres?currentSchema=\"tanbirA\"",
              "postgres", "postgres");
        } catch (Exception e) {
           e.printStackTrace();
           System.err.println(e.getClass().getName()+": "+e.getMessage());
           System.exit(0);
        }
        System.out.println("Opened database successfully");
     }
    
    
    private static void insertSomeData() throws InterruptedException {
        TrainerService ts = new TrainerService(); // Create an instance of both services
        PokemonService ps = new PokemonService();

        Trainer ash = new Trainer();
        ash.firstName = "Ash";
        ash.lastName = "Ketchum";
        ash.phoneNumber = "212-555-1212";
        ts.saveTrainer(ash);

        Trainer misty = new Trainer();
        misty.firstName = "Misty";
        misty.lastName = "Water";
        misty.phoneNumber = "212-555-1313";
        misty = ts.saveTrainer(misty);

        Trainer brock = new Trainer();
        brock.firstName = "Brock";
        brock.lastName = "Rock";
        brock.phoneNumber = "212-555-1414";
        brock = ts.saveTrainer(brock);


        Pokemon turtwig = new Pokemon();
        turtwig.name = "Turtwig";
        turtwig.color = "Green";
        turtwig.primaryType = "Grass";
        turtwig.secondaryType = "";
        turtwig.height = .4;
        turtwig.weight = 10.2;
        turtwig.catchRate = 11.9;
        turtwig.description = "Tiny Leaf Pokémon";
        turtwig.currentTrainerId = ash.id;
        ps.savePokemon(turtwig);

        Pokemon chimchar = new Pokemon();
        chimchar.name = "Chimchar";
        chimchar.color = "Red";
        chimchar.primaryType = "Fire";
        chimchar.secondaryType = "";
        chimchar.height = .5;
        chimchar.weight = 6.2;
        chimchar.catchRate = 11.9;
        chimchar.description = "Chimp Pokémon";
        chimchar.currentTrainerId = ash.id;
        ps.savePokemon(chimchar);

        Pokemon piplup = new Pokemon();
        piplup.name = "Piplup";
        piplup.color = "Blue";
        piplup.primaryType = "Water";
        piplup.secondaryType = "";
        piplup.height = .4;
        piplup.weight = 5.2;
        piplup.catchRate = 11.9;
        piplup.description = "Penguin Pokémon";
        piplup.currentTrainerId = misty.id;
        ps.savePokemon(piplup);

        Pokemon bulbasaur  = new Pokemon();
        bulbasaur.name = "Bulbasaur";
        bulbasaur.color = "Green";
        bulbasaur.primaryType = "Grass";
        bulbasaur.secondaryType = "Poison";
        bulbasaur.height = .7;
        bulbasaur.weight = 6.9;
        bulbasaur.catchRate = 11.9;
        bulbasaur.description = "Seed Pokémon";
        bulbasaur.currentTrainerId = brock.id;
        ps.savePokemon(bulbasaur);

        Pokemon charmander  = new Pokemon();
        charmander.name = "Charmander";
        charmander.color = "Red";
        charmander.primaryType = "Fire";
        charmander.secondaryType = "";
        charmander.height = .6;
        charmander.weight = 8.5;
        charmander.catchRate = 11.9;
        charmander.description = "Lizard Pokémon";
        charmander.currentTrainerId = ash.id;
        ps.savePokemon(charmander);

        Pokemon squirtle = new Pokemon();
        squirtle.name = "Squirtle";
        squirtle.color = "Blue";
        squirtle.primaryType = "Water";
        squirtle.secondaryType = "";
        squirtle.height = .5;
        squirtle.weight = 9.0;
        squirtle.catchRate = 11.9;
        squirtle.description = "Tiny Turtle Pokémon";
        squirtle.currentTrainerId = misty.id;
        ps.savePokemon(squirtle);


        Thread.sleep(3000);

        turtwig = ps.getPokemonByName("Ash project");

        if(turtwig != null) {
            System.out.println("Car: " + turtwig.primaryType + " Trainer Id (shoud be empty): " + turtwig.currentTrainerId);
        }
        

        turtwig.currentTrainerId = ash.id;
        Thread.sleep(3000);
        ps.savePokemon(turtwig);

        turtwig = ps.getPokemonByName("Brians project");;
        System.out.println("Pokemon: " + turtwig.primaryType + " Driver Id: "
                + turtwig.currentTrainerId + " [assigned!]"); // should have an id now!
    	
//    	// This is the actual method that's kicked off when we run our JAR
//        System.out.println("Hello 4DF World!"); // Display the string.
//
//        // use the  settings within this method to customize the 4DFLib.  Note, everything in this method is optional.
//        setOptionalSettings(); // This sets 4df to connect to the database

        // Create a array that will hold the classes that make up our 4df data model
        List<Class> myModel = new ArrayList<>(); // You must add models to the DB for them to be added to the database.

        // Add our 2 classes
        myModel.add(Trainer.class); // The .class syntax refers to the actual class
        myModel.add(Pokemon.class);

        // call the initialization of library!
        // insert some demo data
        try {
            insertSomeData();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        // do a few queries and output the results

    
    /**
     * Everything set in this method is optional, but useful
     */
//    private static void setOptionalSettings() {
//
//        // get the 4dflib settings singleton (think ConnectionUtil)
//        FdfSettings fdfSettings = FdfSettings.getInstance();
//
//        // set the database type and name and connection information
//        // PostgreSQL settings
//        fdfSettings.PERSISTENCE = DatabaseUtil.DatabaseType.POSTGRES;
//        fdfSettings.DB_PROTOCOL = DatabaseUtil.DatabaseProtocol.JDBC_POSTGRES;
//
//        // postgres default root user
//        // root user settings are only required for initial database creation.  Once the database is created you
//        // should remove this information
//        fdfSettings.DB_ROOT_USER = "postgres";

        // MySQL settings
        //fdfSettings.PERSISTENCE = DatabaseUtil.DatabaseType.MYSQL;
        //fdfSettings.DB_PROTOCOL = DatabaseUtil.DatabaseProtocol.JDBC_MYSQL;

        // MariaDB settings
//        fdfSettings.PERSISTENCE = DatabaseUtil.DatabaseType.MARIADB;
//        fdfSettings.DB_PROTOCOL = DatabaseUtil.DatabaseProtocol.JDBC_MARIADB;

        // MariaDB and MySQL default
        // root user settings are only required for initial database creation.  Once the database is created you
        // should remove this information
//        fdfSettings.DB_ROOT_USER = "root";

//        // root user password
//        fdfSettings.DB_ROOT_PASSWORD = "postgres"; // changed from ""
//
//        // Database encoding
//        fdfSettings.DB_ENCODING = DatabaseUtil.DatabaseEncoding.UTF8;
//
//        // Application Database name
//        fdfSettings.DB_NAME = "tester";
//
//        // Database host
//        fdfSettings.DB_HOST = "localhost";
//
//        // Port is not required for DB defaults can be changed when needed
//         fdfSettings.DB_PORT = 5432;
//
//        // Database user information
//        fdfSettings.DB_USER = "postgres";
//        fdfSettings.DB_PASSWORD = "postgres";





//
//        // set the default system information
//        fdfSettings.DEFAULT_SYSTEM_NAME = "PokeDex API";
//        fdfSettings.DEFAULT_SYSTEM_DESCRIPTION = "Central API service for the PokeDex Application";
//
//        // set the default tenant information
//        fdfSettings.DEFAULT_TENANT_NAME = "PokeDex";
//        fdfSettings.DEFAULT_TENANT_DESRIPTION = "Main system Tenant";
//        fdfSettings.DEFAULT_TENANT_IS_PRIMARY = true;
//        fdfSettings.DEFAULT_TENANT_WEBSITE = "http://www.4dflib.com";
//
//        // local dev, no ssl
//        fdfSettings.USE_SSL = false;
//        
//
//    }




        // lets also find all cars from the year 2001
//        List<Pokemon> pokemonWater = ps.getPokemonByPrimaryType("Water");
//        for(Pokemon pokemon: pokemonWater) {
//            // see if there should be a driver and get the driver if so!
//            if(pokemon.currentTrainerId >= 0) {
//                pokemon.currentTrainer = ts.getTrainerById(pokemon.currentTrainerId);
//            }
//            String trainer = (pokemon.currentTrainer != null) ? pokemon.currentTrainer.firstName : "no driver";
//
//            System.out.println("Pokemon with water type : " + pokemon.name + " which is " + pokemon.height + " " + pokemon.weight + " driven by: "
//                    + trainer);
//        }


//        // Wait a few seconds
//        Thread.sleep(6000);
//
//        // change all 2014 cars status to needing repair
//        for(Pokemon car: cars01) {
//            car.isInNeedOfRepair = true;
//            ps.saveCar(car);
//        }
//
//        // Wait a few seconds
//        Thread.sleep(6000);
//
//        // get one of the cars and change it back
//        List<Pokemon> cars01new = ps.getCarsByYear(2001);
//        cars01new.get(0).isInNeedOfRepair = false;
//        ps.saveCar(cars01new.get(0));
//
//        System.out.println("\n%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

        // re-run the query and output the results again, this time with history so we can see the change!
//        List<FdfEntity<Pokemon>> cars01withHistory = ps.getPokemonByPrimaryTypeWithHistory(2001);
//        for(FdfEntity<Pokemon> carWithHistory: cars01withHistory) {
//            // first output the cars current status
//            System.out.println("2001 cars [after updates]: " + carWithHistory.current.name + " which is a "
//                    + carWithHistory.current.make + " " + carWithHistory.current.model
//                    + " has a [current] repair status: " + carWithHistory.current.isInNeedOfRepair);
//
//            System.out.println("----- History -----");
//            // Now show the historical records for the car
//            for(Pokemon carHistory : carWithHistory.history) {
//                System.out.println("Start time: " + carHistory.arsd + " End time: " + carHistory.ared
//                        + " repair status: " + carHistory.isInNeedOfRepair + " it was driven by userid "
//                        + carHistory.currentDriverId);
//            }
//            System.out.println("___________________");
//
//        }

        // for fun lets try a custom query
//        Pokemon newCharmander = ps.customPokemonQuery("Tanbir's project").current;
//        System.out.println("\n^^^^^^^^^^^^^^^^ Custom Query Results ^^^^^^^^^^^^^^^^^^^^^^");
//        System.out.println("Pokemon weight & type & color: " + newCharmander.weight + " " + newCharmander.primaryType + " " + newCharmander.color);
//        System.out.println("name: " + newCharmander.name + " Desc: " + newCharmander.description);
    }
}