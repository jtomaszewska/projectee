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

        System.out.println("LOL");
//        masProject.getBacklogTasks();

//        String user1Email1 = "azlotopolska@gmail.com";
//        Employee user1 = new Employee("Anna", "Złotopolska", user1Email1);
//        BacklogTask myTask1 = new BacklogTask("Task 1", Priority.minor, "Some things to do", TaskType.improvement);
//        SprintTask sprintTask = new SprintTask(myTask1);
//        sprintTask.changeStatus(Status.in_progress);
//        List<SprintTask> sprintTasks = Task.getTasks(SprintTask.class);
//        for (SprintTask task : sprintTasks) {
//            System.out.println(task + ": " + Status.in_progress.toString());
//        }
////        System.out.println(myTask1.getTimeSpent());
//        BacklogTask task2 = new BacklogTask("Task 2", Priority.critical, "Nanana", TaskType.bug);
//        BacklogTask task3 = new BacklogTask("Task 3", Priority.major, "Desc 3", TaskType.feature);
//
//        //zwykła 1..* User-Task
//        user1.assignTask(myTask1);
//        user1.assignTask(task2);
//        task3.assignOwner(user1);
//
//        user1.showLinks(LinksMetadata.EMPLOYEE_TASK.roleName, System.out);
//        task2.showLinks(LinksMetadata.EMPLOYEE_TASK.reverseRoleName, System.out);
//
//        Team team1 = new Team("Team 1");
//        Team team2 = new Team("Team 2");
//        Employee user2 = new Employee("Jan", "Studencki", "jstudencki@js.pl");
//
//        // Z atrybutem *..* User-Team
//        TimePeriod timePeriod = new TimePeriod(LocalDate.now(), null);
//        team1.addMember(user1, timePeriod);
//        team1.addMember(user2, timePeriod);
//
//        user1.addToTeam(team2, timePeriod);
//
//        user1.showLinks(LinksMetadata.EMPLOYEE_TEAM.roleName, System.out);
//        user2.showLinks(LinksMetadata.EMPLOYEE_TEAM.roleName, System.out);
//        team1.showLinks(LinksMetadata.TEAM_EMPLOYEE.roleName, System.out);
//        team2.showLinks(LinksMetadata.TEAM_EMPLOYEE.roleName, System.out);
//
//        // kwalifikowana 1..* Project-Report qualifier:name
//        Project project1 = new Project("Project 1");
//        Report reportFin = new Report("Financial1", "\\\\repots.abc.pl\\project1\\fin1");
//        Report reportTech = new Report("Technical1", "\\\\repots.abc.pl\\project1\\tech1");
//
//        project1.addReport(reportFin, reportFin.getName());
//        reportTech.connectToProject(project1, reportTech.getName());
//        Project project2 = new Project("Project 2");
//        project1.showLinks(LinksMetadata.PROJECT_REPORT.roleName, System.out);
//        reportFin.showLinks(LinksMetadata.REPORT_PROJECT.roleName, System.out);
//        reportTech.showLinks(LinksMetadata.REPORT_PROJECT.roleName, System.out);
//        System.out.println(project1.getReport("Financial1").toString());
//        System.out.println(project1.getLinkedObject(LinksMetadata.PROJECT_REPORT.roleName, "Financial1"));
//
//        //kompozycja Project - Sprint
//        project1.addSprint("S1", LocalDate.now(), 1);
//        project1.addSprint("S2", LocalDate.now().plusWeeks(1), 2);
//        project1.showLinks(LinksMetadata.PROJECT_SPRINT.roleName, System.out);
//
//        //sprawdzam czy moge powiązać część z dwoma calościami
//        Sprint s3 = Sprint.registerSprint("S3", LocalDate.now(), 1);
//        project2.addPart(LinksMetadata.PROJECT_SPRINT.roleName, LinksMetadata.PROJECT_SPRINT.reverseRoleName, s3);
//        //project1.addPart(LinksMetadata.PROJECT_SPRINT.roleName, LinksMetadata.PROJECT_SPRINT.reverseRoleName, s3);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("objects.txt"));
        ObjectPlus.writeExtents(objectOutputStream);
        System.out.println("after: ");
        ObjectPlus.logExtents();

    }
}
