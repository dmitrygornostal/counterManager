package com.example.countersManager;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CountersService {

    private final Map<String, Long> counters = new HashMap<>();

    public void createCounter(String name) {
        if (counters.containsKey(name)) {
            throw new IllegalArgumentException(String.format("The counter with name %s is already exists. It's value is %d.",
                    name, counters.get(name)));
        }
        counters.put(name, 0L);
    }

    public void incrementCounter(String name) {
        checkCounterForExistence(name);
        counters.merge(name, 1L, Long::sum);
    }

    public Long getCounterValue(String name) {
        checkCounterForExistence(name);
        return counters.get(name);
    }

    public void deleteCounter(String name) {
        checkCounterForExistence(name);
        counters.remove(name);
    }

    public Long getCountersValuesSum() {
        checkIfCountersExist();
        return counters.values().stream()
                .reduce(0L, Long::sum);
    }

    public List<String> getCounterNames() {
        checkIfCountersExist();
        return new ArrayList<>(counters.keySet());
    }

    private void checkCounterForExistence(String name) {
        if (!counters.containsKey(name)) {
            throw new IllegalArgumentException(String.format("There is no counter with name %s. Provide valid name.",
                    name));
        }
    }

    private void checkIfCountersExist() {
        if (counters.isEmpty()) {
            throw new IllegalArgumentException("There is no counters exist.");
        }
    }

    public Map<String, Long> getCounters() {
        return counters;
    }
}
