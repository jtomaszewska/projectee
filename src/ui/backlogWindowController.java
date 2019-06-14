package ui;

import domain.Project;
import domain.SprintTask;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.List;

public class backlogWindowController {

    @FXML
    // The reference of inputText will be injected by the FXML loader
    private TableView sprint;

    public backlogWindowController() {
    }

    @FXML
    private void initialize() {
        System.out.println("initializing backlog window");

        Project masProject = Project.getProject("MAS Project");
        List<SprintTask> tasks = masProject.getSprintTasks();
        sprint.getItems().addAll(tasks);
        System.out.println("tasks shown");
    }
}
