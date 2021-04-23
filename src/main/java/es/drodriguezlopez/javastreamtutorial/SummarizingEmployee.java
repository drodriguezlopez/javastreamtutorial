package es.drodriguezlopez.javastreamtutorial;

import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.LongSummaryStatistics;

public class SummarizingEmployee {

    private DoubleSummaryStatistics height = new DoubleSummaryStatistics();
    private IntSummaryStatistics age = new IntSummaryStatistics();
    private LongSummaryStatistics salary = new LongSummaryStatistics();

    public void accept(Employee employee) {
        height.accept(employee.getHeight());
        age.accept(employee.getAge());
        salary.accept(employee.getSalary());
    }

    public void combine(SummarizingEmployee summarizingEmployee) {
        height.combine(summarizingEmployee.getHeight());
        age.combine(summarizingEmployee.getAge());
        salary.combine(summarizingEmployee.getSalary());
    }

    public DoubleSummaryStatistics getHeight() {
        return height;
    }

    public IntSummaryStatistics getAge() {
        return age;
    }

    public LongSummaryStatistics getSalary() {
        return salary;
    }

}
