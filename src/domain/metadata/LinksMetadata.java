package domain.metadata;

import domain.*;

public enum LinksMetadata {

    TASK_EMPLOYEE(Task.class, Employee.class, "owned by", "responsible for"),
    EMPLOYEE_TASK(Employee.class, Task.class, "responsible for", "owned by"),
    EMPLOYEE_TEAM(Employee.class, Team.class, "member of", "consist of"),
    TEAM_EMPLOYEE(Team.class, Employee.class, "consist of", "member of"),
    PROJECT_REPORT(Project.class, Report.class, "implies", "created for"),
    REPORT_PROJECT(Report.class, Project.class, "created for", "implies"),
    PROJECT_SPRINT(Project.class, Sprint.class, "composed of", "belongs to"),
    SPRINT_PROJECT(Sprint.class, Project.class, "belongs to", "composed of"),
    TASK_SPRINT(Task.class, Sprint.class, "implemented during", "contains"),
    SPRINT_TASK(Sprint.class, Task.class, "contains", "implemented during");

    public Class objectClass;
    public Class targetObjectClass;
    public String roleName;
    public String reverseRoleName;

    LinksMetadata(Class objectClass, Class targetObjectClass, String roleName, String reverseRoleName) {
        this.objectClass = objectClass;
        this.targetObjectClass = targetObjectClass;
        this.roleName = roleName;
        this.reverseRoleName = reverseRoleName;
    }
}
