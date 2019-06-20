import domain.*;
import domain.base.ObjectPlus;
import domain.metadata.LinksMetadata;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

//        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("objects.txt"));
//        ObjectPlus.readExtents(objectInputStream);
//        System.out.println("before: ");
//        ObjectPlus.logExtents();

        Project masProject = new Project("MAS Project");
        Project project2 = new Project("p2");
        SprintTask task191 = new SprintTask("Wymagania użytkownika", Priority.critical, TaskType.feature);
        SprintTask task192 = new SprintTask("Diagram przypadków użycia", Priority.critical, TaskType.feature);
        SprintTask task193 = new SprintTask("Diagram klas – analityczny", Priority.major, TaskType.feature);
        SprintTask task194 = new SprintTask("Diagram klas – projektowy", Priority.major, TaskType.feature);
        SprintTask task195 = new SprintTask("Scenariusz przypadku użycia", Priority.major, TaskType.feature);
        SprintTask task196 = new SprintTask("Diagram aktywności", Priority.minor, TaskType.feature);
        SprintTask task197 = new SprintTask("Diagram stanu dla klasy", Priority.minor, TaskType.feature);
        SprintTask task198 = new SprintTask("Diagram interakcji (sekwencji)", Priority.major, TaskType.feature);
        SprintTask task199 = new SprintTask("Projekt GUI", Priority.major, TaskType.feature);

        BacklogTask task421 = new BacklogTask("Klasy i powiązania", Priority.major, TaskType.feature);
        BacklogTask task422 = new BacklogTask("Metody", Priority.major, TaskType.feature);
        BacklogTask task423 = new BacklogTask("Implementacja GUI", Priority.major, TaskType.feature);
        BacklogTask task424 = new BacklogTask("Integracja GUI z domeną", Priority.major, TaskType.feature);
        BacklogTask task425 = new BacklogTask("Ergonomia, użyteczność GUI", Priority.major, TaskType.feature);
        BacklogTask task426 = new BacklogTask("Trwałość danych", Priority.major, TaskType.feature);
        BacklogTask task427 = new BacklogTask("Javadocs", Priority.major, TaskType.feature);

        Sprint sprint1 = masProject.addSprint("Wykonanie analizy", LocalDate.of(2019, 06, 11), 1);
        sprint1.add(task191);
        sprint1.add(task192);
        sprint1.add(task193);
        sprint1.add(task194);
        sprint1.add(task195);
        sprint1.add(task196);
        sprint1.add(task197);
        sprint1.add(task198);
        sprint1.add(task199);

        List<SprintTask> currentSprint = masProject.getSprintTasks();

        sprint1.showLinks(LinksMetadata.SPRINT_TASK.roleName, System.out);

        Sprint backlog = masProject.getBacklog();
        backlog.add(task421);
        backlog.add(task422);
        backlog.add(task423);
        backlog.add(task424);
        backlog.add(task425);
        backlog.add(task426);
        backlog.add(task427);


        Employee emp1 = new Employee("Anna", "Złotopolska", "azlotopolska@gmail.com");
        Employee emp2 = new Employee("Jan", "Kowalski", "jkowalski@gmail.com");
        Employee emp3 = new Employee("Karolina", "Poniatowska", "kponiatowska@gmail.com");
        Employee emp4 = new Employee("Ignacy", "Tuwim", "ituwim@gmail.com");
        Employee emp5 = new Employee("Janina", "Kowalska", "jkowalska@gmail.com");

        Team t1 = new Team("Team 1");
        Team t2 = new Team("Team 2");


        TimePeriod period = new TimePeriod(LocalDate.of(2000, 01, 19), LocalDate.of(2020, 01, 01));
        t1.addMember(emp1, period);
        t1.addMember(emp2, period);
        t2.addMember(emp3, period);
        t2.addMember(emp4, period);
        t1.addMember(emp5, period);
        project2.createdBy(t2);
        masProject.createdBy(t1);
        t1.assignTo(masProject);
        task191.assignOwner(emp1);
        task192.assignOwner(emp2);
        task193.assignOwner(emp1);
        task194.assignOwner(emp2);
        List<Employee> employeesToLink = task194.getAvailableEmployees();

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("objects.txt"));
        ObjectPlus.writeExtents(objectOutputStream);
        System.out.println("after: ");
        ObjectPlus.logExtents();

    }
}
