package domain;

import domain.base.ObjectPlusPlus;
import domain.metadata.LinksMetadata;

public class Report extends ObjectPlusPlus {
    private String name;
    private String urlToLocation;

    public Report(String name, String urlToLocation) {
        this.name = name;
        this.urlToLocation = urlToLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlToLocation() {
        return urlToLocation;
    }

    public void setUrlToLocation(String urlToLocation) {
        this.urlToLocation = urlToLocation;
    }

    public void connectToProject(Project project, String qualifier) {
        if (!this.objectHasNoLinks(LinksMetadata.PROJECT_REPORT.roleName)) {
            System.out.println("This report is already connected to other project");
        }
        if (project.getClass().equals(LinksMetadata.REPORT_PROJECT.targetObjectClass) &&
                this.getClass().equals(LinksMetadata.REPORT_PROJECT.objectClass)) {
            this.addLink(LinksMetadata.REPORT_PROJECT.roleName, LinksMetadata.REPORT_PROJECT.reverseRoleName, project, qualifier);
        } else {
            throw new RuntimeException(String.format("Can't link objects from class %s and %s.",
                    project.getClass().getName(), this.getClass().getName()));
        }
    }

    @Override
    public String toString() {
        return "Report{" +
                "name='" + name + '\'' +
                ", urlToLocation='" + urlToLocation + '\'' +
                '}';
    }
}
