package domain;

import domain.base.DomainException;
import domain.metadata.LinksMetadata;

import java.time.LocalDate;
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

    public <T extends Task> void assignTask(T task) {
        if (!task.objectHasNoLinks(LinksMetadata.TASK_EMPLOYEE.roleName)) {
            System.out.println("This task was already connected to other user");
        }
        if (task.getClass().getSuperclass().equals(LinksMetadata.EMPLOYEE_TASK.targetObjectClass) &&
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

    public <T extends Task> List<T> showTasks(Class<T> taskClass) {
        //dodać filtrowanie zadań dla teamu do którego należy employee wywyołujący metodę
        return Task.getTasks(taskClass);
    }

    //metody dla poszczególnych employeesów

    /**
     * Method available for Analytics. Creates new BacklogTask.
     * User should choose type, title
     * default status - created
     * default priority - major
     * default createDate - now
     * storyPoints - visible only for improvement and feature
     *
     * @return
     */
    public Task createTask() {
        //TO_DO
        return null;
    }

    /**
     * Method available for Tester. Creates new SprintTask.
     * User should choose title, environment, description(optional)
     * type - bug
     * default status - created
     * default priority - major
     * default createDate - now
     * storyPoints -  not visible
     *
     * @return
     */
    public Task reportBug() {
        //TO_DO
        return null;
    }

    /**
     * Available for testers
     * changes task status from done to in_test and assign current Employee to task
     *
     * @param task
     */
    public void startTesting(Task task) {
        //TO_DO
    }

    /**
     * Changes Status on closed only for SprintTask witch are in_test
     *
     * @param task
     */
    public void close(Task task) {
        //TO_DO
    }

    /**
     * Available only for Leaders
     *
     * @return
     */
    public Sprint createSprint(String name, LocalDate startDate) {
        return Sprint.registerSprint(name, startDate, Sprint.getDefaultDurationWeeks());
    }

    /**
     * Only for Programmers and SprintTask
     * changes status from to_do on in_progress and assign current user to task
     */
    public void startProgress(Task task) {
        //TO_DO
    }

    /**
     * from in_progress to done,
     * available only for programmer who is owner of task
     */
    public void endWork() {

    }
}
