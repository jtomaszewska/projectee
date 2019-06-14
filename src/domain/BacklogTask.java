package domain;

public class BacklogTask extends Task {
    public BacklogTask(String title, Priority priority, TaskType type) {
        super(title, priority, type);
        this.setStatus(Status.created);
    }

    public void addToSprint(Sprint sprint) {
        new SprintTask(this);
    }

    @Override
    public void changeStatus(Status status) {
        if (status == Status.in_progress) {
            System.out.println("You should add task to sprint to start progress");
        } else if (status == Status.closed || status == Status.in_test || status == Status.resolved) {
            System.out.println("Action is inconsistent with task workflow");
        }
        if (status == Status.archived) {
            this.setStatus(status);
        }
    }


    @Override
    public String toString() {
        return "BacklogTask{" +
                "title='" + super.getTitle() + '\'' +
                ", status=" + super.getStatus() +
                ", priority=" + super.getPriority() +
                ", description='" + super.getDescription() + '\'' +
                ", createDate=" + super.getCreateDate() +
                ", startDate=" + super.getStartDate() +
                ", endDate=" + super.getEndDate() +
                ", type=" + super.getType() +
                ", storyPoints=" + super.getStoryPoints() +
                '}';
    }
}
