package com.an.optimize.ab;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AbExperiment extends Experiment {

    private Map<String,Object> params;

    @Override
    public String getStringValue(String param) {
        String value = (String) params.get(param);
        return value;
    }

    @Override
    public Number getNumberValue(String param) {
        String value = (String) params.get(param);
        if(value!=null) return Double.parseDouble(value);
        else return null;
    }

    @Override
    public Integer getIntegerValue(String param) {
        String value = (String) params.get(param);
        if(value!=null) return Integer.parseInt(value);
        else return null;
    }

    @Override
    public Double getDoubleValue(String param) {
        String value = (String) params.get(param);
        if(value!=null) return Double.parseDouble(value);
        else return null;
    }

    @Override
    public Boolean getBooleanValue(String param) {
        String value = (String) params.get(param);
        if(value!=null) return Boolean.parseBoolean(value);
        else return null;
    }

    @Override
    public List getList(String param) {
        String value = (String) params.get(param);
        if(value!=null) return Arrays.asList(value.split("~"));
        else return null;
    }

    @Override
    public Map getParams() {
        return params;
    }

    @Override
    public void setParams(Map params) {
        this.params = params;
    }

}
