package com.an.optimize.ab;


import java.util.Map;

import com.an.optimize.db.OptimizeDbModule;


public class AbExperimentManager extends ExperimentManager {


    @Override
    public Experiment getExperiment(String expId) {
        OptimizeDbModule dbModule = OptimizeDbModule.getInstance();
        Map<String, Object> attributesMap = dbModule.getAttributes();

        if (attributesMap != null && attributesMap.containsKey(expId)) {
            Experiment experiment = new AbExperiment();
            experiment.setParams(attributesMap);
            return experiment;
        }

        return null;
    }
}
