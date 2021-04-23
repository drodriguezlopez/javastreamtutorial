package es.drodriguezlopez.javastreamtutorial;

public class Employee {
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

    public Double getHeight() {
        return height;
    }

    public Integer getAge() {
        return age;
    }

    public Long getSalary() {
        return salary;
    }

}
