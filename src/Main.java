//import domain.*;
//
//import java.io.FileOutputStream;
//import java.io.ObjectOutputStream;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class Main {
//
//    public static void main(String[] args) throws Exception {
//
////        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("objects.txt"));
////        ObjectPlus.readExtents(objectInputStream);
////        System.out.println("before: ");
////        ObjectPlus.logExtents();
//
//        String user1Email1 = "azlotopolska@gmail.com";
//        List<String> user1Emails = new ArrayList<>();
//        user1Emails.add(user1Email1);
//        user1Emails.add("azlotopolska2@gmail.com");
//        Employee user1 = new Employee("Anna", "Złotopolska", user1Emails);
//        Task myTask1 = new Task("Task 1", Priority.minor, "Some things to do");
//        myTask1.changeStatus(Status.in_progress);
//        List<Task> tasksInProgress = Task.getTasks(Status.in_progress);
//        for (Task task : tasksInProgress) {
//            System.out.println(task + ": " + Status.in_progress.toString());
//        }
//        System.out.println(myTask1.getTimeSpent());
//        Task task2 = new Task("Task 2", Priority.critical, "Nanana");
//        Task task3 = new Task("Task 3", Priority.major, "Desc 3");
//
//        //zwykła 1..* User-Task
//        user1.assignTask(myTask1);
//        user1.assignTask(task2);
//        task3.assignOwner(user1);
//
//        user1.showLinks(LinksMetadata.USER_TASK.roleName, System.out);
//        task2.showLinks(LinksMetadata.USER_TASK.reverseRoleName, System.out);
//
//        Team team1 = new Team("Team 1");
//        Team team2 = new Team("Team 2");
//        User user2 = new User("Jan", "Studencki", Arrays.asList("jstudencki@js.pl", "jan.studencki@gmail.com"));
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
//
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("objects.txt"));
//        ObjectPlus.writeExtents(objectOutputStream);
//        System.out.println("after: ");
//        ObjectPlus.logExtents();
//
//    }
