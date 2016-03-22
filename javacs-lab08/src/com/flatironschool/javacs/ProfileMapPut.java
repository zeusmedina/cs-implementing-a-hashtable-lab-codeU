package com.flatironschool.javacs;

import java.util.HashMap;
import java.util.Map;

import org.jfree.data.xy.XYSeries;

import com.flatironschool.javacs.Profiler.Timeable;

public class ProfileMapPut {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//profileHashMapPut();
		profileMyHashMapPut();
	}

	/**
	 * Characterize the run time of adding to the end of an ArrayList
	 */
	public static void profileHashMapPut() {
		Timeable timeable = new Timeable() {
			Map<String, Integer> map;

			public void setup(int n) {
				map = new HashMap<String, Integer>();
			}

			public void timeMe(int n) {
				for (int i=0; i<n; i++) {
					map.put(new Integer(i).toString(), i);
				}
			}
		};
		int startN = 8000;
		int endMillis = 1000;
		runProfiler("HashMap put", timeable, startN, endMillis);
	}
	
	/**
	 * Characterize the run time of adding to the end of an ArrayList
	 */
	public static void profileMyHashMapPut() {
		Timeable timeable = new Timeable() {
			Map<String, Integer> map;

			public void setup(int n) {
				map = new MyHashMap<String, Integer>();
			}

			public void timeMe(int n) {
				for (int i=0; i<n; i++) {
					map.put(new Integer(i).toString(), i);
				}
			}
		};
		int startN = 1000;
		int endMillis = 5000;
		runProfiler("MyHashMap put", timeable, startN, endMillis);
	}
	

	/**
	 * Runs the profiles and displays results.
	 * 
	 * @param timeable
	 * @param startN
	 * @param endMillis
	 */
	private static void runProfiler(String title, Timeable timeable, int startN, int endMillis) {
		Profiler profiler = new Profiler(title, timeable);
		XYSeries series = profiler.timingLoop(startN, endMillis);
		profiler.plotResults(series);
	}
}