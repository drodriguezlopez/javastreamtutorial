package es.drodriguezlopez.javastreamtutorial;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class EmployeeCollector implements Collector<Employee, SummarizingEmployee, SummarizingEmployee> {

    @Override
    public Supplier<SummarizingEmployee> supplier() {
        return () -> new SummarizingEmployee();
    }

    @Override
    public BiConsumer<SummarizingEmployee, Employee> accumulator() {
        return (a, b) -> a.accept(b);
    }

    @Override
    public BinaryOperator<SummarizingEmployee> combiner() {
        return (left, right) -> {
            left.combine(right);
            return left;
        };
    }

    @Override
    public Function<SummarizingEmployee, SummarizingEmployee> finisher() {
        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));
    }

    public static Collector<Employee, SummarizingEmployee, SummarizingEmployee> summarizing() {
        return Collector.of(
                () -> new SummarizingEmployee(),
                (a, b) -> a.accept(b),
                (left, right) -> {
                    left.combine(right);
                    return left;
                }
        );
    }
}
