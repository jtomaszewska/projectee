package domain;

import domain.base.DomainException;
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

    public Task(String title, Priority priority,
                String description, TaskType taskType) {
        super();
        this.title = title;
        this.type = taskType;
        this.status = Status.created;
        this.priority = priority;
        this.description = description;
        this.createDate = LocalDateTime.now();
        this.startDate = null;
        this.endDate = null;
    }

    public static List<Task> getTasks(Status status) {
        List<Task> allTasks = getTasks();
        return allTasks.stream().filter(t -> t.getStatus().equals(status)).collect(Collectors.toList());
    }

    public static List<Task> getTasks() {
        return allExtents.get(Task.class).stream().map(obj -> (Task) obj)
                .collect(Collectors.toList());
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
            System.out.println("This task is already connected to other user");
        }
        if (this.getClass().equals(LinksMetadata.SPRINT_TASK.targetObjectClass) &&
                this.getClass().equals(LinksMetadata.SPRINT_TASK.objectClass)) {
            this.addLink(LinksMetadata.SPRINT_TASK.roleName, LinksMetadata.SPRINT_TASK.reverseRoleName, this);
        } else {
            throw new DomainException("Can't link this objects");
        }
    }
}
