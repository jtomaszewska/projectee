package domain;

import domain.base.ObjectPlusPlus;
import domain.metadata.LinksMetadata;

public class TeamEmployee extends ObjectPlusPlus {

    private TimePeriod timePeriod;

    public TeamEmployee(Team team, User user, TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
        if (LinksMetadata.TEAM_EMPLOYEE.objectClass.equals(team.getClass()) &&
                LinksMetadata.TEAM_EMPLOYEE.targetObjectClass.equals(user.getClass())) {
            team.addLink(LinksMetadata.TEAM_EMPLOYEE.roleName, LinksMetadata.TEAM_EMPLOYEE.reverseRoleName, this);
            user.addLink(LinksMetadata.TEAM_EMPLOYEE.reverseRoleName, LinksMetadata.TEAM_EMPLOYEE.roleName, this);
        } else {
            throw new RuntimeException("Can't link this objects");
        }
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        StringBuilder objectsToReturn = new StringBuilder();

        for (ObjectPlusPlus link :
                this.getLinks(LinksMetadata.TEAM_EMPLOYEE.roleName)) {
            objectsToReturn.append(link.toString());
        }
        for (ObjectPlusPlus link :
                this.getLinks(LinksMetadata.EMPLOYEE_TEAM.roleName)) {
            objectsToReturn.append(link.toString());
        }
        return objectsToReturn.toString();
    }

}
