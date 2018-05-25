package com.an.optimize;

import android.content.Context;

import com.an.optimize.ab.Experiment;
import com.an.optimize.ab.ExperimentManager;
import com.an.optimize.db.DbExecutorService;
import com.an.optimize.db.OptimizeDbTask;
import com.an.optimize.rest.RestModule;

import java.util.List;
import java.util.Map;

public class OptimizeModule extends RestModule {

    private ExperimentManager manager;

    protected OptimizeModule() {
        manager = ExperimentManager.getManager();
    }

    protected void initialize(Context context, String url) {
        DbExecutorService.submit(new OptimizeDbTask("packageName", context.getPackageName()));
        DbExecutorService.submit(new OptimizeDbTask("url", url));
        initialize(url);
    }


    protected String getAbStringValue(String param, String defaultValue) {
        try {
            Experiment experiment = manager.getExperiment(param);
            if (experiment != null) {
                String value = experiment.getStringValue(param);
                return null == value ? defaultValue : value;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }


    protected Number getAbNumberValue(String param, Number defaultValue) {
        try {
            Experiment experiment = manager.getExperiment(param);
            if (experiment != null) {
                Number value = experiment.getNumberValue(param);
                return null == value ? defaultValue : value;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }


    protected Integer getAbIntegerValue(String param, Integer defaultValue) {
        try {
            Experiment experiment = manager.getExperiment(param);
            if (experiment != null) {
                Integer value = experiment.getIntegerValue(param);
                return null == value ? defaultValue : value;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }


    protected Double getAbDoubleValue(String param, Double defaultValue) {
        try {
            Experiment experiment = manager.getExperiment(param);
            if (experiment != null) {
                Double value = experiment.getDoubleValue(param);
                return null == value ? defaultValue : value;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }


    protected Boolean getAbBooleanValue(String param, Boolean defaultValue) {
        try {
            Experiment experiment = manager.getExperiment(param);
            if (experiment != null) {
                Boolean value = experiment.getBooleanValue(param);
                return null == value ? defaultValue : value;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }


    protected Map getAbParams(String param, Map defaultValues) {
        try {
            Experiment experiment = manager.getExperiment(param);
            if (experiment != null) {
                Map value = experiment.getParams();
                return null == value ? defaultValues : value;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValues;
    }


    protected List getAbList(String param, List defaultValues) {
        try {
            Experiment experiment = manager.getExperiment(param);
            if (experiment != null) {
                List value = experiment.getList(param);
                return null == value ? defaultValues : value;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValues;
    }
}
