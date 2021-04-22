package es.drodriguezlopez.javastreamtutorial;

import org.junit.jupiter.api.Test;

import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.LongSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Clase con tests del uso de los operadores de reduccion en stream
 */
public class StreamReductionTest {

    /**
     * Collectors.averagingDouble retorna la media aritmetica
     * de los elementos del stream del tipo double
     */
    @Test
    public void testCollectAveragingDouble() {
        Stream<Double> ns = Stream.of(1.0, 2.0, 3.0);
        Double average = ns.collect(Collectors.averagingDouble(n -> n));
        assertEquals(2, average);
    }

    /**
     * Collectors.averagingDouble retorna la media aritmetica
     * de los elementos del streamn convirtiendolos del tipo string
     * al tipo double
     */
    @Test
    public void testCollectAveragingDouble2() {
        Stream<String> ns = Stream.of("1", "2", "3");
        Double average = ns.collect(Collectors.averagingDouble(Double::parseDouble));
        assertEquals(2, average);
    }

    /**
     * Collectors.averagingDouble retorna la media aritmetica
     * de los elementos del stream del tipo integer
     */
    @Test
    public void testCollectAveragingInt() {
        Stream<Integer> ns = Stream.of(1, 2, 3);
        Double average = ns.collect(Collectors.averagingInt(n -> n));
        assertEquals(2, average);
    }

    /**
     * Collectors.averagingDouble retorna la media aritmetica
     * de los elementos del stream del tipo Long
     */
    @Test
    public void testCollectAveragingLong() {
        Stream<Long> ns = Stream.of(1L, 2L, 3L);
        Double average = ns.collect(Collectors.averagingLong(n -> n));
        assertEquals(2, average);
    }

    /**
     * Collectors.summarizingDouble calcula la media,
     * el número de elementos, el minimo, el maximo y la suma
     * de los elementos del stream del tipo Double
     */
    @Test
    public void testCollectSummarizingDouble() {
        Stream<Double> ns = Stream.of(1.0, 2.0, 3.0);
        DoubleSummaryStatistics sum = ns.collect(Collectors.summarizingDouble(n -> n));
        assertEquals(2, sum.getAverage());
        assertEquals(3, sum.getCount());
        assertEquals(3, sum.getMax());
        assertEquals(1, sum.getMin());
        assertEquals(6, sum.getSum());
    }

    /**
     * Collectors.summarizingDouble calcula la media,
     * el número de elementos, el minimo, el maximo y la suma
     * de los elementos del stream del tipo Integer
     */
    @Test
    public void testCollectSummarizingInt() {
        Stream<Integer> ns = Stream.of(1, 2, 3);
        IntSummaryStatistics sum = ns.collect(Collectors.summarizingInt(n -> n));
        assertEquals(2, sum.getAverage());
        assertEquals(3, sum.getCount());
        assertEquals(3, sum.getMax());
        assertEquals(1, sum.getMin());
        assertEquals(6, sum.getSum());
    }

    /**
     * Collectors.summarizingDouble calcula la media,
     * el número de elementos, el minimo, el maximo y la suma
     * de los elementos del stream del tipo Long
     */
    @Test
    public void testCollectSummarizingLong() {
        Stream<Long> ns = Stream.of(1L, 2L, 3L);
        LongSummaryStatistics sum = ns.collect(Collectors.summarizingLong(n -> n));
        assertEquals(2, sum.getAverage());
        assertEquals(3, sum.getCount());
        assertEquals(3, sum.getMax());
        assertEquals(1, sum.getMin());
        assertEquals(6, sum.getSum());
    }

    /**
     * Collectors.summingDouble retorna la suma
     * de los elementos del stream del tipo Double
     */
    @Test
    public void testCollectSummingDouble() {
        Stream<Double> ns = Stream.of(1.0, 2.0, 3.0);
        Double sum = ns.collect(Collectors.summingDouble(n -> n));
        assertEquals(6, sum);
    }

    /**
     * Collectors.summingInt retorna la suma
     * de los elementos del stream del tipo Integer
     */
    @Test
    public void testCollectSummingInt() {
        Stream<Integer> ns = Stream.of(1, 2, 3);
        Integer sum = ns.collect(Collectors.summingInt(n -> n));
        assertEquals(6, sum);
    }

    /**
     * Collectors.summingLong retorna la suma
     * de los elementos del stream del tipo Long
     */
    @Test
    public void testCollectSummingLong() {
        Stream<Long> ns = Stream.of(1L, 2L, 3L);
        Long sum = ns.collect(Collectors.summingLong(n -> n));
        assertEquals(6, sum);
    }
}
