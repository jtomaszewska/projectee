package ui;

import domain.BacklogTask;
import domain.Project;
import domain.SprintTask;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class backlogWindowController {

    @FXML
    // The reference of inputText will be injected by the FXML loader
    private TableView sprint;

    @FXML
    private TableView backlog;

    @FXML
    private ImageView rocket;

    @FXML
    private ComboBox userActions;

    public backlogWindowController() {
    }

    @FXML
    private void initialize() {
        System.out.println("initializing backlog window");

        //part for init upper bar

        Image img = new Image("file:rocket.png");
        rocket.setImage(img);

        userActions.getItems().addAll("Settings", "Log out");
        userActions.setValue("User actions");

        Project masProject = Project.getProject("MAS Project");
        List<SprintTask> sprintTasks = masProject.getSprintTasks();
        sprint.getItems().addAll(sprintTasks);
        System.out.println("tasks shown");

        List<BacklogTask> backlogTasks = masProject.getBacklogTasks();
        backlog.getItems().addAll(backlogTasks);
    }

}
