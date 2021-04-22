package es.drodriguezlopez.javastreamtutorial;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StreamTest {

    @Test
    public void testCollectList() {
        Stream<Integer> ns = Stream.of(1, 2, 3, 1, 4, 5);
        List<Integer> nl = ns.collect(Collectors.toList());
        assertNotNull(nl);
        assertEquals(6, nl.size());
    }

    @Test
    public void testCollectAveragingDouble() {
        Stream<Double> ns = Stream.of(1.0, 2.0, 3.0);
        Double average = ns.collect(Collectors.averagingDouble(n -> n));
        assertEquals(2, average);
    }

    @Test
    public void testCollectAveragingDouble2() {
        Stream<String> ns = Stream.of("1", "2", "3");
        Double average = ns.collect(Collectors.averagingDouble(Double::parseDouble));
        assertEquals(2, average);
    }

    @Test
    public void testCollectAveragingInt() {
        Stream<Integer> ns = Stream.of(1, 2, 3);
        Double average = ns.collect(Collectors.averagingInt(n -> n));
        assertEquals(2, average);
    }

    @Test
    public void testCollectCollectingAndThen() {
        Stream<Integer> ns = Stream.of(1, 2, 3);
        List<Integer> ns2 = ns.collect(Collectors.collectingAndThen(Collectors.toList(),
                Collections::unmodifiableList));
        assertNotNull(ns2);
        assertEquals(3, ns2.size());
    }

    @Test
    public void testCollectSummingInt() {
        Stream<Integer> ns = Stream.of(1, 2, 3);
        Integer sum = ns.collect(Collectors.summingInt(n -> n));
        assertEquals(6, sum);
    }

    @Test
    public void testCount() {
        Stream<Integer> ns = Stream.of(1, 2, 3);
        long c = ns.count();
        assertEquals(c, 3);
    }

    @Test
    public void testDistinct() {
        Stream<Integer> ns = Stream.of(1, 2, 3);
        Stream<Integer> d = ns.distinct();
        assertNotNull(d);

        ns = Stream.of(1, 2, 3, 3, 2);
        d = ns.distinct();
        assertNotNull(d);
        assertEquals(3, d.count());
    }

    @Test
    public void testFindAny() {
        Stream<Integer> ns = Stream.of(1, 2, 3);
        Optional<Integer> d = ns.parallel().findAny();
        assertTrue(d.isPresent());
    }

    @Test
    public void testFindFirst() {
        Stream<Integer> ns = Stream.of(1, 2, 3);
        Optional<Integer> d = ns.findFirst();
        assertTrue(d.isPresent());
        assertEquals(1, d.get());
    }

    @Test
    public void testFlatMap() {
        Stream<Integer> ns = Stream.of(1, 2, 3);
        List<Integer> d = ns.flatMap(n -> Stream.of(n + 1)).collect(Collectors.toList());
        assertNotNull(d);
        assertEquals(2, d.get(0));
    }

    @Test
    public void testFlatMapToInt() {
        Stream<Integer> ns = Stream.of(1, 2, 3);
        int d = ns.flatMapToInt(IntStream::of).sum();
        assertEquals(6, d);
    }

    @Test
    public void testForEach() {
        Stream<Integer> ns = Stream.of(1, 2, 3);
        ns.forEach(System.out::println);
    }

    @Test
    public void testMap() {
        Stream<Integer> ns = Stream.of(1, 2, 3);
        List<Integer> d = ns.map(n -> n + 1).collect(Collectors.toList());
        assertNotNull(d);
        assertEquals(2, d.get(0));
    }

}