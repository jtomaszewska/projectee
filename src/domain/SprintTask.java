package domain;

public class SprintTask extends Task {

    public SprintTask(Task task) {
        super(task.getTitle(), task.getPriority(), task.getType());
    }

    public SprintTask(String title, Priority priority, TaskType type) {
        super(title, priority, type);
    }

    @Override
    public void changeStatus(Status status) {
        if (status == Status.archived) {
            System.out.println("Cant archive task in sprint");
        } else {
            this.setStatus(status);
        }
    }

    @Override
    public String toString() {
        return "SprintTask{" +
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
