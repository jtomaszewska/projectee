package domain;

import domain.base.DomainException;
import domain.base.ObjectPlusPlus;
import domain.metadata.LinksMetadata;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Sprint extends ObjectPlusPlus {

    private static int defaultDurationWeeks = 2;
    private String name;
    private LocalDate start;
    private int durationWeeks;

    private Sprint(String name, LocalDate start) {
        this(name, start, defaultDurationWeeks);
    }

    private Sprint(String name, LocalDate start, int durationWeeks) {
        super();
        this.name = name;
        this.start = start;
        this.durationWeeks = durationWeeks;
    }

    public static Sprint registerSprint(String name, LocalDate start, int durationWeeks) {
        return new Sprint(name, start, durationWeeks);
    }

    public static int getDefaultDurationWeeks() {
        return defaultDurationWeeks;
    }

    public static void setDefaultDurationWeeks(int defaultDurationWeeks) {
        Sprint.defaultDurationWeeks = defaultDurationWeeks;
    }

    public void addToProject(Project project, String name, LocalDate start, int durationWeeks) {
        if (project == null) {
            throw new DomainException("Project does not exists");
        }
        project.addSprint(name, start, durationWeeks);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public int getDurationWeeks() {
        return durationWeeks;
    }

    public void setDurationWeeks(int durationWeeks) {
        this.durationWeeks = durationWeeks;
    }

    public void add(Task task) {
        task.implementedDuring(this);
    }

    public List<SprintTask> getSprintTasks() {
        return getLinkedObjects(LinksMetadata.SPRINT_TASK.roleName)
                .stream().map(t -> (SprintTask) t).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Sprint{" +
                "name='" + name + '\'' +
                ", start=" + start +
                ", durationWeeks=" + durationWeeks +
                '}';
    }
}
