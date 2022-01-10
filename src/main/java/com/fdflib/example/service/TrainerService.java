package com.fdflib.example.service;

import com.fdflib.example.model.Trainer;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;

public class TrainerService extends FdfCommonServices {

    public Trainer saveTrainer(Trainer trainer) {
        if(trainer != null) {
            return this.save(Trainer.class, trainer).current;
        }
        return null;
    }

    public Trainer getTrainerById(long id) {
        return getTrainerWithHistoryById(id).current;

    }

    public FdfEntity<Trainer> getTrainerWithHistoryById(long id) {
        FdfEntity<Trainer> trainer = new FdfEntity<>();

        // get the test
        if(id >= 0) {
            trainer = this.getEntityById(Trainer.class, id);
        }

        return trainer;
    }
}
