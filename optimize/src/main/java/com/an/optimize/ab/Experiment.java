package com.an.optimize.ab;

import java.util.List;
import java.util.Map;

public abstract class Experiment {

    public abstract String getStringValue(String param);

    public abstract Number getNumberValue(String param);

    public abstract Double getDoubleValue(String param);

    public abstract Integer getIntegerValue(String param);

    public abstract Boolean getBooleanValue(String param);

    public abstract List getList(String param);

    public abstract Map getParams();

    public abstract void setParams(Map params);

}
