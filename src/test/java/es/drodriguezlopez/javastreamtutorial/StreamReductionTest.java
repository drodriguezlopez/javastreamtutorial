package es.drodriguezlopez.javastreamtutorial;

import org.junit.jupiter.api.Test;

import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.LongSummaryStatistics;
import java.util.Map;
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
        Stream<Employee> employees = createEmployees();
        Double average = employees.collect(Collectors.averagingDouble(Employee::getHeight));
        assertEquals(1.65, average);

        employees = createEmployees();
        average = employees.mapToDouble(Employee::getHeight).average().getAsDouble();
        assertEquals(1.65, average);
    }

    /**
     * Collectors.averagingDouble retorna la media aritmetica
     * de los elementos del stream del tipo integer
     */
    @Test
    public void testCollectAveragingInt() {
        Stream<Employee> employees = createEmployees();
        Double average = employees.collect(Collectors.averagingInt(Employee::getAge));
        assertEquals(32.5, average);

        employees = createEmployees();
        average = employees.mapToInt(Employee::getAge).average().getAsDouble();
        assertEquals(32.5, average);
    }

    /**
     * Collectors.averagingDouble retorna la media aritmetica
     * de los elementos del stream del tipo Long
     */
    @Test
    public void testCollectAveragingLong() {
        Stream<Employee> employees = createEmployees();
        Double average = employees.collect(Collectors.averagingLong(Employee::getSalary));
        assertEquals(1750, average);

        employees = createEmployees();
        average = employees.mapToLong(Employee::getSalary).average().getAsDouble();
        assertEquals(1750, average);
    }

    /**
     * Collectors.summarizingDouble calcula la media,
     * el número de elementos, el minimo, el maximo y la suma
     * de los elementos del stream del tipo Double
     */
    @Test
    public void testCollectSummarizingDouble() {
        Stream<Employee> employees = createEmployees();
        DoubleSummaryStatistics statistics = employees.collect(Collectors.summarizingDouble(Employee::getHeight));
        assertEquals(1.65, statistics.getAverage());
        assertEquals(4, statistics.getCount());
        assertEquals(1.8, statistics.getMax());
        assertEquals(1.5, statistics.getMin());
        assertEquals(6.6, statistics.getSum());

        employees = createEmployees();
        statistics = employees.mapToDouble(Employee::getHeight).summaryStatistics();
        assertEquals(1.65, statistics.getAverage());
        assertEquals(4, statistics.getCount());
        assertEquals(1.8, statistics.getMax());
        assertEquals(1.5, statistics.getMin());
        assertEquals(6.6, statistics.getSum());
    }

    /**
     * Collectors.summarizingDouble calcula la media,
     * el número de elementos, el minimo, el maximo y la suma
     * de los elementos del stream del tipo Integer
     */
    @Test
    public void testCollectSummarizingInt() {
        Stream<Employee> employees = createEmployees();
        IntSummaryStatistics statistics = employees.collect(Collectors.summarizingInt(Employee::getAge));
        assertEquals(32.5, statistics.getAverage());
        assertEquals(4, statistics.getCount());
        assertEquals(40, statistics.getMax());
        assertEquals(20, statistics.getMin());
        assertEquals(130, statistics.getSum());

        employees = createEmployees();
        statistics = employees.mapToInt(Employee::getAge).summaryStatistics();
        assertEquals(32.5, statistics.getAverage());
        assertEquals(4, statistics.getCount());
        assertEquals(40, statistics.getMax());
        assertEquals(20, statistics.getMin());
        assertEquals(130, statistics.getSum());
    }

    /**
     * Collectors.summarizingDouble calcula la media,
     * el número de elementos, el minimo, el maximo y la suma
     * de los elementos del stream del tipo Long
     */
    @Test
    public void testCollectSummarizingLong() {
        Stream<Employee> employees = createEmployees();
        LongSummaryStatistics statistics = employees.collect(Collectors.summarizingLong(Employee::getSalary));
        assertEquals(1750, statistics.getAverage());
        assertEquals(4, statistics.getCount());
        assertEquals(2500, statistics.getMax());
        assertEquals(1000, statistics.getMin());
        assertEquals(7000, statistics.getSum());

        employees = createEmployees();
        statistics = employees.mapToLong(Employee::getSalary).summaryStatistics();
        assertEquals(1750, statistics.getAverage());
        assertEquals(4, statistics.getCount());
        assertEquals(2500, statistics.getMax());
        assertEquals(1000, statistics.getMin());
        assertEquals(7000, statistics.getSum());
    }

    /**
     * Collectors.summingDouble retorna la suma
     * de los elementos del stream del tipo Double
     */
    @Test
    public void testCollectSummingDouble() {
        Stream<Employee> employees = createEmployees();
        Double sum = employees.collect(Collectors.summingDouble(Employee::getHeight));
        assertEquals(6.6, sum);

        employees = createEmployees();
        sum = employees.mapToDouble(Employee::getHeight).sum();
        assertEquals(6.6, sum);

        employees = createEmployees();
        sum = employees.map(Employee::getHeight).reduce(0D, (a, b) -> a + b);
        assertEquals(6.6, sum);
    }

    /**
     * Collectors.summingInt retorna la suma
     * de los elementos del stream del tipo Integer
     */
    @Test
    public void testCollectSummingInt() {
        Stream<Employee> employees = createEmployees();
        Integer sum = employees.collect(Collectors.summingInt(Employee::getAge));
        assertEquals(130, sum);

        employees = createEmployees();
        sum = employees.mapToInt(Employee::getAge).sum();
        assertEquals(130, sum);

        employees = createEmployees();
        sum = employees.map(Employee::getAge).reduce(0, (a, b) -> a + b);
        assertEquals(130, sum);
    }

    /**
     * Collectors.summingLong retorna la suma
     * de los elementos del stream del tipo Long
     */
    @Test
    public void testCollectSummingLong() {
        Stream<Employee> employees = createEmployees();
        Long sum = employees.collect(Collectors.summingLong(Employee::getSalary));
        assertEquals(7000, sum);

        employees = createEmployees();
        sum = employees.mapToLong(Employee::getSalary).sum();
        assertEquals(7000, sum);

        employees = createEmployees();
        sum = employees.map(Employee::getSalary).reduce(0L, (a, b) -> a + b);
        assertEquals(7000, sum);
    }

    /**
     * Agrupamos el stream por el campo nombre y contamos el resultado
     */
    @Test
    public void testCollectGroupingByAndCount() {
        Stream<Employee> employees = createEmployees();
        Map<String, Long> groupByEmployee = employees.
                collect(Collectors.groupingBy(Employee::getName, Collectors.counting()));
        assertEquals(2, groupByEmployee.get("Luis"));
    }

    /**
     * Agrupamos el stream por el campo nombre y sumamos el salario
     */
    @Test
    public void testCollectGroupingByAndSumming() {
        Stream<Employee> employees = createEmployees();
        Map<String, Long> groupByEmployee = employees.
                collect(Collectors.groupingBy(Employee::getName, Collectors.summingLong(Employee::getSalary)));
        assertEquals(4500, groupByEmployee.get("Luis"));
    }


    @Test
    public void testCollectGroupingByAndSumming2() {
        Stream<Employee> employees = createEmployees();
        SummarizingEmployee o = employees.parallel().collect(new EmployeeCollector());
        System.out.println(o);
    }

    @Test
    public void testCollectGroupingByAndSumming32() {
        Stream<Employee> employees = createEmployees();
        SummarizingEmployee o = employees.parallel().collect(EmployeeCollector.summarizing());
        System.out.println(o);
    }

    private Stream<Employee> createEmployees() {
        return Stream.of(new Employee("David", 1.5, 20, 1000L),
                new Employee("Juan", 1.6, 30, 1500L),
                new Employee("Luis", 1.7, 40, 2000L),
                new Employee("Luis", 1.8, 40, 2500L));
    }

}
