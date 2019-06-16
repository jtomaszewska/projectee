package domain;

import domain.base.DomainException;
import domain.base.ObjectPlusPlus;
import domain.metadata.LinksMetadata;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Team extends ObjectPlusPlus {

    private String name;

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addMember(Employee employee, TimePeriod timePeriod) {
        new TeamEmployee(this, employee, timePeriod);
    }

    protected void endCooperation(TeamEmployee teamEmployee, LocalDate timeEnd) {
        LocalDate timeStart = teamEmployee.getTimePeriod().getDateStart();
        TimePeriod period = new TimePeriod(timeStart, timeEnd);
        teamEmployee.setTimePeriod(period);
    }

    public void assignTo(Project project) {
        if (!this.objectHasNoLinks(LinksMetadata.TEAM_PROJECT.roleName)) {
            System.out.println("This team is already connected to other project");
        }
        if (this.getClass().equals(LinksMetadata.PROJECT_TEAM.targetObjectClass) &&
                project.getClass().equals(LinksMetadata.PROJECT_TEAM.objectClass)) {
            project.addLink(LinksMetadata.PROJECT_TEAM.roleName, LinksMetadata.PROJECT_TEAM.reverseRoleName, this);
        } else {
            throw new DomainException("Can't link this objects");
        }
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                '}';
    }

    public List<Employee> members() {
        List<TeamEmployee> linked = getLinkedObjects(LinksMetadata.TEAM_EMPLOYEE.roleName)
                .stream().map(t -> (TeamEmployee) t).collect(Collectors.toList());
        List<Employee> employees = new ArrayList<>();
        for (TeamEmployee emp :
                linked) {
            employees.addAll(emp.getAssignedUsers());
        }
        return employees;
    }

}
