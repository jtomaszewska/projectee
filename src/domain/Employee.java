package domain;

import domain.base.DomainException;
import domain.metadata.LinksMetadata;

import java.util.List;
import java.util.stream.Collectors;

public class Employee extends User {

    List<PositionType> position;

    public Employee(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    public void addToTeam(Team team, TimePeriod timePeriod) throws Exception {
        new TeamEmployee(team, this, timePeriod);
    }

    public void assignTask(Task task) {
        if (!task.objectHasNoLinks(LinksMetadata.TASK_EMPLOYEE.roleName)) {
            System.out.println("This task is already connected to other user");
        }
        if (task.getClass().equals(LinksMetadata.EMPLOYEE_TASK.targetObjectClass) &&
                this.getClass().equals(LinksMetadata.EMPLOYEE_TASK.objectClass)) {
            this.addLink(LinksMetadata.EMPLOYEE_TASK.roleName, LinksMetadata.EMPLOYEE_TASK.reverseRoleName, task);
        } else {
            throw new DomainException("Can't link this objects");
        }
    }

    public Employee searchForEmployee(String email) {
        List<Employee> employees = allExtents.get(Employee.class).stream().map(obj -> (Employee) obj)
                .collect(Collectors.toList());
        List<Employee> employee = employees.stream().filter(e -> e.getEmail().equals(email)).collect(Collectors.toList());
        if (employee.size() == 0) {
            throw new RuntimeException("There is no employee with given email");
        }
        return employee.get(0);
    }

    public void editTask(Task task) {
    }

    public List<Task> showTasks() {
        return Task.getTasks();
    }

    //metody dla poszczególnych employeesów
}
