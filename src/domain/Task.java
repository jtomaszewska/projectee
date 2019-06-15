package domain;

import domain.base.DomainException;
import domain.base.ObjectPlus;
import domain.base.ObjectPlusPlus;
import domain.metadata.LinksMetadata;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Task extends ObjectPlusPlus implements Serializable {
    private String title;
    private TaskType type;
    private Status status;
    private Priority priority;
    private String description;
    private LocalDateTime createDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int storyPoints;
    private String environment;

    public Task(String title, Priority priority,
                TaskType taskType) {
        super();
        this.title = title;
        this.type = taskType;
        this.status = Status.created;
        this.priority = priority;
        this.createDate = LocalDateTime.now();
    }

    public static <T extends Task> List<T> getTasks(Status status, Class<T> taskClass) {
        List<T> allTasks = getTasks(taskClass).stream().map(obj -> (T) obj).collect(Collectors.toList());
        return allTasks.stream().filter(t -> t.getStatus().equals(status)).collect(Collectors.toList());
    }

    public static <T extends Task> List<T> getTasks(Class<T> taskClass) {
        List<ObjectPlus> tasks = allExtents.get(taskClass);
        return tasks.stream().map(obj -> (T) obj).collect(Collectors.toList());
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public int getStoryPoints() {
        return storyPoints;
    }

    public void setStoryPoints(int storyPoints) {
        if (type == TaskType.bug) {
            System.out.println("Cant add this parameter for a bug");
            return;
        }
        this.storyPoints = storyPoints;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void assignOwner(Employee employee) throws Exception {
        employee.assignTask(this);
    }

    public void changeStatus(Status status) {
    }

    public void implementedDuring(Sprint sprint) {
        if (!this.objectHasNoLinks(LinksMetadata.TASK_SPRINT.roleName)) {
            System.out.println("This task is already connected to other Sprint");
        }
        if (this.getClass().getSuperclass().equals(LinksMetadata.SPRINT_TASK.targetObjectClass) &&
                sprint.getClass().equals(LinksMetadata.SPRINT_TASK.objectClass)) {
//            this.addPart(LinksMetadata.TASK_SPRINT.roleName, LinksMetadata.TASK_SPRINT.reverseRoleName, sprint);
            sprint.addLink(LinksMetadata.TASK_SPRINT.reverseRoleName, LinksMetadata.TASK_SPRINT.roleName, this);
        } else {
            throw new DomainException("Can't link this objects");
        }
    }
}
