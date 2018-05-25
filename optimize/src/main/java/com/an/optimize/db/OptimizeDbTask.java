package com.an.optimize.db;


public class OptimizeDbTask implements Runnable {

    private String key;
    private Object value;
    public OptimizeDbTask(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public void run() {
        OptimizeDbModule.getInstance().write(key, value);
    }
}
