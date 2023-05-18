import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Employee implements Serializable {
    private String name;
    private double salary;
    private transient String socialSecurityNumber;

    public Employee(String name, double salary, String socialSecurityNumber) {
        this.name = name;
        this.salary = salary;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Salary: " + salary + ", SSN: " + socialSecurityNumber;
    }
}

public class TestEmployee {
    private static final String FILE_NAME = "emp.dat";

    public static void main(String[] args) {
        Employee employee1 = new Employee("Mark Harris", 54000, "123-45-6789");
        Employee employee2 = new Employee("Linda Miller", 79000, "234667890");
        Employee employee3 = new Employee("Joe Hoffman", 88000, "3612-512-11");

        System.out.println("Original Information: ");
        System.out.println(employee1);
        System.out.println(employee2);
        System.out.println(employee3);


        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        writeToFile(employees);

        List<Employee> employeesFromFile = readFromFile();
        System.out.println("Reading from:" + FILE_NAME);
        for (Employee employee : employeesFromFile) {
            System.out.println(employee.toString());
        }

    }

    private static void writeToFile(List<Employee> employees) {
        try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            objectOut.writeObject(employees);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Employee> readFromFile() {
        List<Employee> employees = new ArrayList<>();

        try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            employees = (List<Employee>) objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return employees;
    }
}
