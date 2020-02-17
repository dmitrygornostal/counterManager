package com.example.countersManager;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class CounterControllerTest {

    private static final String TEST_COUNTER_NAME = "TestCounter";
    private static final String ANOTHER_COUNTER_NAME = "TestCounter2";
    private static final String THIRD_COUNTER_NAME = "TestCounter3";

    private CountersService counterController = new CountersService();


    @Test
    void createCounterTest_success() {
        counterController.createCounter(TEST_COUNTER_NAME);
        Map<String, Long> counters = counterController.getCounters();
        assertTrue(counters.containsKey(TEST_COUNTER_NAME));
        assertEquals(0L, counters.get(TEST_COUNTER_NAME));
    }

    @Test
    void createCounterTest_fail() {
        counterController.createCounter(TEST_COUNTER_NAME);
        assertThrows(IllegalArgumentException.class,
                () -> counterController.createCounter(TEST_COUNTER_NAME));
    }

    @Test
    void incrementCounterTest() {
        counterController.getCounters().put(TEST_COUNTER_NAME, 3L);
        counterController.incrementCounter(TEST_COUNTER_NAME);
        counterController.incrementCounter(TEST_COUNTER_NAME);
        assertEquals(5L, counterController.getCounters().get(TEST_COUNTER_NAME));
    }

    @Test
    void incrementCounterTest_fail() {
        counterController.getCounters().clear();
        assertThrows(IllegalArgumentException.class,
                () -> counterController.incrementCounter(TEST_COUNTER_NAME));
    }

    @Test
    void getCounterValueTest_success() {
        counterController.createCounter(TEST_COUNTER_NAME);
        assertEquals(0L, counterController.getCounterValue(TEST_COUNTER_NAME));
    }

    @Test
    void deleteCounter() {
        counterController.createCounter(TEST_COUNTER_NAME);
        assertTrue(counterController.getCounters().containsKey(TEST_COUNTER_NAME));
        counterController.deleteCounter(TEST_COUNTER_NAME);
        assertFalse(counterController.getCounters().containsKey(TEST_COUNTER_NAME));
    }

    @Test
    void getCountersValuesSumTest() {
        counterController.getCounters().put(TEST_COUNTER_NAME, 3L);
        counterController.getCounters().put(ANOTHER_COUNTER_NAME, 17L);
        assertEquals(20L, counterController.getCountersValuesSum());
    }

    @Test
    void getCounterNamesTest() {
        counterController.getCounters().put(TEST_COUNTER_NAME, 0L);
        counterController.getCounters().put(ANOTHER_COUNTER_NAME, 0L);
        counterController.getCounters().put(THIRD_COUNTER_NAME, 0L);
        List<String> names = Arrays.asList(TEST_COUNTER_NAME, ANOTHER_COUNTER_NAME, THIRD_COUNTER_NAME);
        assertEquals(names, counterController.getCounterNames());
    }
}