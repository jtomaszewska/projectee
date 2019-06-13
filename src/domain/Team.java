package domain;

import domain.base.ObjectPlusPlus;

import java.time.LocalDate;

public class Team extends ObjectPlusPlus {

    private String name;

    protected Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected void addMember(Employee employee, TimePeriod timePeriod) {
        new TeamEmployee(this, employee, timePeriod);
    }

    protected void endCooperation(TeamEmployee teamEmployee, LocalDate timeEnd) {
        LocalDate timeStart = teamEmployee.getTimePeriod().getDateStart();
        TimePeriod period = new TimePeriod(timeStart, timeEnd);
        teamEmployee.setTimePeriod(period);
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                '}';
    }
}
