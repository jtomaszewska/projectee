package domain;

import domain.base.ObjectPlusPlus;
import domain.metadata.LinksMetadata;

import java.time.LocalDate;

public class Project extends ObjectPlusPlus {

    private String name;

    public Project(String name) {
        this.name = name;
    }

    protected void addReport(Report report, String qualifier) {
        report.connectToProject(this, qualifier);
    }

    protected Report getReport(String name) throws Exception {
        return (Report) this.getLinkedObject(LinksMetadata.PROJECT_REPORT.roleName, name);
    }

    public void addSprint(String name, LocalDate start, int durationWeeks) {
        Sprint sprint = Sprint.registerSprint(name, start, durationWeeks);
        this.addPart(LinksMetadata.PROJECT_SPRINT.roleName, LinksMetadata.PROJECT_SPRINT.reverseRoleName, sprint);
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                '}';
    }
}
