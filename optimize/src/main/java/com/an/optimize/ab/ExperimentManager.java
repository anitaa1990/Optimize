package com.an.optimize.ab;

public abstract class ExperimentManager {

    public abstract Experiment getExperiment(String expId);
    public static ExperimentManager getManager(){
        return new AbExperimentManager();
    }
}