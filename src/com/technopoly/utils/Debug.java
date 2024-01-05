package com.technopoly.utils;

import java.util.ArrayList;

public class Debug {

	static ArrayList<LogListener> logListeners = new ArrayList<LogListener>();

	public static void AddListener(LogListener l) {
		if (!logListeners.contains(l)) {
			logListeners.add(l);
		}
	}

	public static void RemoveListener(LogListener l) {
		if (logListeners.contains(l)) {
			logListeners.remove(l);
		}
	}

	public static void Log(String msg) {
		for (LogListener l : logListeners) {
			if (l != null) {
				l.onLog(msg);
			} else {
				LogError("Null Log Listener");
			}
		}
	}

	public static void LogError(String msg) {
		Log("[ERROR] " + msg);
	}

}
