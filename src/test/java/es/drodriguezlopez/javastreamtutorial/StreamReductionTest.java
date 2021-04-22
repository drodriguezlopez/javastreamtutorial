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
        Stream<Employee> ns = createEmployees();
        Double average = ns.collect(Collectors.averagingDouble(Employee::getHeight));
        assertEquals(1.65, average);
    }

    /**
     * Collectors.averagingDouble retorna la media aritmetica
     * de los elementos del stream del tipo integer
     */
    @Test
    public void testCollectAveragingInt() {
        Stream<Employee> ns = createEmployees();
        Double average = ns.collect(Collectors.averagingInt(Employee::getAge));
        assertEquals(32.5, average);
    }

    /**
     * Collectors.averagingDouble retorna la media aritmetica
     * de los elementos del stream del tipo Long
     */
    @Test
    public void testCollectAveragingLong() {
        Stream<Employee> ns = createEmployees();
        Double average = ns.collect(Collectors.averagingLong(Employee::getSalary));
        assertEquals(1750, average);
    }

    /**
     * Collectors.summarizingDouble calcula la media,
     * el número de elementos, el minimo, el maximo y la suma
     * de los elementos del stream del tipo Double
     */
    @Test
    public void testCollectSummarizingDouble() {
        Stream<Employee> ns = createEmployees();
        DoubleSummaryStatistics sum = ns.collect(Collectors.summarizingDouble(Employee::getHeight));
        assertEquals(1.65, sum.getAverage());
        assertEquals(4, sum.getCount());
        assertEquals(1.8, sum.getMax());
        assertEquals(1.5, sum.getMin());
        assertEquals(6.6, sum.getSum());
    }

    /**
     * Collectors.summarizingDouble calcula la media,
     * el número de elementos, el minimo, el maximo y la suma
     * de los elementos del stream del tipo Integer
     */
    @Test
    public void testCollectSummarizingInt() {
        Stream<Employee> ns = createEmployees();
        IntSummaryStatistics sum = ns.collect(Collectors.summarizingInt(Employee::getAge));
        assertEquals(32.5, sum.getAverage());
        assertEquals(4, sum.getCount());
        assertEquals(40, sum.getMax());
        assertEquals(20, sum.getMin());
        assertEquals(130, sum.getSum());
    }

    /**
     * Collectors.summarizingDouble calcula la media,
     * el número de elementos, el minimo, el maximo y la suma
     * de los elementos del stream del tipo Long
     */
    @Test
    public void testCollectSummarizingLong() {
        Stream<Employee> ns = createEmployees();
        LongSummaryStatistics sum = ns.collect(Collectors.summarizingLong(Employee::getSalary));
        assertEquals(1750, sum.getAverage());
        assertEquals(4, sum.getCount());
        assertEquals(2500, sum.getMax());
        assertEquals(1000, sum.getMin());
        assertEquals(7000, sum.getSum());
    }

    /**
     * Collectors.summingDouble retorna la suma
     * de los elementos del stream del tipo Double
     */
    @Test
    public void testCollectSummingDouble() {
        Stream<Employee> ns = createEmployees();
        Double sum = ns.collect(Collectors.summingDouble(Employee::getHeight));
        assertEquals(6.6, sum);
    }

    /**
     * Collectors.summingInt retorna la suma
     * de los elementos del stream del tipo Integer
     */
    @Test
    public void testCollectSummingInt() {
        Stream<Employee> ns = createEmployees();
        Integer sum = ns.collect(Collectors.summingInt(Employee::getAge));
        assertEquals(130, sum);
    }

    /**
     * Collectors.summingLong retorna la suma
     * de los elementos del stream del tipo Long
     */
    @Test
    public void testCollectSummingLong() {
        Stream<Employee> ns = createEmployees();
        Long sum = ns.collect(Collectors.summingLong(Employee::getSalary));
        assertEquals(7000, sum);
    }

    private Stream<Employee> createEmployees() {
        return Stream.of(new Employee("David", 1.5, 20, 1000L),
                new Employee("Juan", 1.6, 30, 1500L),
                new Employee("Luis", 1.7, 40, 2000L),
                new Employee("Luis", 1.8, 40, 2500L));
    }

    private class Employee {
        private String name;
        private Double height;
        private Integer age;
        private Long salary;

        public Employee(String name, Double height, Integer age, Long salary) {
            this.name = name;
            this.height = height;
            this.age = age;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getHeight() {
            return height;
        }

        public void setHeight(Double height) {
            this.height = height;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Long getSalary() {
            return salary;
        }

        public void setSalary(Long salary) {
            this.salary = salary;
        }
    }
}
