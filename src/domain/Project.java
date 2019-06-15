package domain;

import domain.base.ObjectPlusPlus;
import domain.metadata.LinksMetadata;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Project extends ObjectPlusPlus {

    private String name;
    private Sprint backlog;

    public Project(String name) {
        this.name = name;
        this.backlog = this.addSprint("Backlog", LocalDate.of(2000, 01, 01), 1);
    }

    public static Project getProject(String name) {
        return allExtents.get(Project.class)
                .stream().map(obj -> (Project) obj)
                .filter(project -> project.getName().equals(name))
                .collect(Collectors.toList()).get(0);
    }

    public Sprint getBacklog() {
        return backlog;
    }

    public void addReport(Report report, String qualifier) {
        report.connectToProject(this, qualifier);
    }

    public Report getReport(String name) throws Exception {
        return (Report) this.getLinkedObject(LinksMetadata.PROJECT_REPORT.roleName, name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sprint addSprint(String name, LocalDate start, int durationWeeks) {
        Sprint sprint = Sprint.registerSprint(name, start, durationWeeks);
        this.addPart(LinksMetadata.PROJECT_SPRINT.roleName, LinksMetadata.PROJECT_SPRINT.reverseRoleName, sprint);
        return sprint;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                '}';
    }

    public List<SprintTask> getSprintTasks() {
        Sprint activeSprint = getActiveSprint();
        return activeSprint.getSprintTasks();
    }

    public List<BacklogTask> getBacklogTasks() {
        Sprint backlog = this.getBacklog();
        return backlog.getBacklogTasks();
    }

    private Sprint getActiveSprint() {
        List<Sprint> sprints = getLinkedObjects(LinksMetadata.PROJECT_SPRINT.roleName).stream().map(t -> (Sprint) t).collect(Collectors.toList());
        Sprint activeSprint = null;
        for (Sprint sprint : sprints) {
            if (sprint.getStart().isBefore(LocalDate.now()) &&
                    sprint.getStart().plusWeeks(sprint.getDurationWeeks()).isAfter(LocalDate.now())) {
                activeSprint = sprint;
            }
        }
        return activeSprint;
    }
}
