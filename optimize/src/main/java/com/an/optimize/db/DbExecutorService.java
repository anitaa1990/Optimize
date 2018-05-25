package com.an.optimize.db;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DbExecutorService {

    private static final ExecutorService _appDbThreadPool = Executors.newFixedThreadPool(5);

    public static void submit(Runnable runnable) {
        getAppDbThreadPool().submit(runnable);
    }

    private static ExecutorService getAppDbThreadPool() {
        return _appDbThreadPool;
    }
}
