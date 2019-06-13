package domain;

public class ArchiveTask extends Task {

    public ArchiveTask(Task task) {
        super(task.getTitle(), task.getPriority(), task.getDescription(), task.getType());
        if (task.getStatus() != Status.closed) {
            System.out.println("Task should be closed before archive");
        }
        this.setStatus(Status.archived);
    }

    @Override
    public void changeStatus(Status status) {
        if (status == Status.closed || status == Status.in_test || status == Status.resolved || status == Status.created) {
            System.out.println("Action is inconsistent with task workflow");
        } else if (status == Status.to_do) {
            this.setStatus(status);
            System.out.println("Task reopened");
        }
    }

    @Override
    public String toString() {
        return "ArchiveTask{" +
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
