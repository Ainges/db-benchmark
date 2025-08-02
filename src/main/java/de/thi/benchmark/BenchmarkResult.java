package de.thi.benchmark;

import java.util.LinkedHashMap;
import java.util.Map;

public class BenchmarkResult {
    private Map<String, Long> results = new LinkedHashMap<>();

    public void add(String name, Long duration) {
        results.put(name, duration);
    }

    public Map<String, Long> getResults() {
        return results;
    }
}

