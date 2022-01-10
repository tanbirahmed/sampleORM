package com.fdflib.example.service;

import com.fdflib.example.model.Pokemon;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.model.util.WhereClause;
import com.fdflib.persistence.FdfPersistence;
import com.fdflib.service.impl.FdfCommonServices;
import com.fdflib.model.util.SqlStatement;

import java.util.ArrayList;
import java.util.List;

public class PokemonService extends FdfCommonServices {

    public Pokemon savePokemon(Pokemon pokemon) {
        if(pokemon != null) {
            // name is unique for car so we are going to check for existing one first
            Pokemon existingPokemon = getPokemonByName(pokemon.name);
            if(existingPokemon != null) {
                // if a match is found, just set the id to match the existing one and it will automatically update
                // instead of insert.
                pokemon.id = existingPokemon.id;
            }

            if (pokemon != null) {
                return this.save(Pokemon.class, pokemon).current;
            }
        }
        return null;
    }

    /*
        Library function available for any object that extends CommonState
     */
    public Pokemon getPokemonById(long id) {
        return getPokemonWithHistoryById(id).current;
    }

    /*
        Another library function available for any object extending CommonState this one returns an FdfEntity which
        includes current FdfEntity.currrent (returns Car) and historical data FdfEntity.history (returns List<Car>)
     */
    public FdfEntity<Pokemon> getPokemonWithHistoryById(long id) {
        FdfEntity<Pokemon> pokemon = new FdfEntity<>();

        // get the test
        if(id >= 0) {
            pokemon = this.getEntityById(Pokemon.class, id);

            // get the drivers
            pokemon.current.currentTrainer = new TrainerService().getTrainerById(pokemon.current.currentTrainerId);
            for(Pokemon pokemonHistory: pokemon.history) {
                pokemonHistory.currentTrainer = new TrainerService().getTrainerById(pokemonHistory.currentTrainerId);
            }
        }

        return pokemon;
    }


    /*
        Queries for all cars with the name passed
        Returns each with history as a FdfEntity<Car>
     */
    public FdfEntity<Pokemon> getPokemonByNameWithHistory(String name) {
        List<FdfEntity<Pokemon>> pokemonWithHistory = getEntitiesByValueForPassedField(Pokemon.class, "name", name);
        if(pokemonWithHistory.size() > 0) {
            return pokemonWithHistory.get(0);
        }
        return null;
    }

    /*
        Returns the same as the last method, but removes the historical records
        Returns simple Car
     */
    public Pokemon getPokemonByName(String name) {
        FdfEntity<Pokemon> pokemonWithHistory = getPokemonByNameWithHistory(name);
        if(pokemonWithHistory != null && pokemonWithHistory.current != null) {
            return pokemonWithHistory.current;
        }
        return null;
    }

   
//    public List<FdfEntity<Pokemon>> getPokemonByYearWithHistory(int year) {
//
//        List<FdfEntity<Pokemon>> carsByYear
//                = getEntitiesByValueForPassedField(Pokemon.class, "year", Integer.toString(year));
//
//        return carsByYear;
//    }
//
//    public List<Pokemon> getPokemonByPrimaryType(String primaryType) {
//
//        List<FdfEntity<Pokemon>> pokemonByPrimaryTypeWithHistory = getPokemonByPrimaryTypeWithHistory(primaryType);
//        List<Pokemon> pokemon = new ArrayList<>();
//        for(FdfEntity<Pokemon> pokemonWithHistory: pokemonByPrimaryTypeWithHistory) {
//            if(pokemonWithHistory.current != null) {
//                pokemon.add(pokemonWithHistory.current);
//            }
//        }
//        return pokemon;
//    }

    /**
     * "Control Syntax" Want more control over the query? your are in luck! see com.fdflib.model.util.SqlStatement
     * https://github.com/briangormanly/4dflib/blob/master/src/main/java/com/fdflib/model/util/SqlStatement.java
     * contribution credit: Corley Herman
     *
     * @param name
     * @return FdfEntity<Car>
     */
    public FdfEntity<Pokemon> customPokemonQuery(String name) {
        FdfEntity<Pokemon> pokemon = new FdfEntity<>();

        if(pokemon != null) {

            // do the selection
            WhereClause whereName = new WhereClause();
            whereName.name = "name";
            whereName.operator = WhereClause.Operators.EQUAL;
            whereName.value = name;
            whereName.valueDataType = String.class;

            List<Pokemon> returnedStates = SqlStatement.build().where(whereName).run(Pokemon.class);

            // create a List of entities
            return manageReturnedEntity(returnedStates);
        }

        return pokemon;
    }
}
