package com.richfit.onlinescore.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.os.Looper;

public class ThreadPoolManager {
	private ExecutorService service;

	static {
		Looper.prepare();
	}
	private ThreadPoolManager() {
		int num = Runtime.getRuntime().availableProcessors();
		service = Executors.newFixedThreadPool(num * 2);
	}

	private static final ThreadPoolManager manager = new ThreadPoolManager();

	public static ThreadPoolManager getInstance() {
		return manager;
	}

	public void addTask(Runnable runnable) {
		service.execute(runnable);
	}
}
