package ui;

import domain.BacklogTask;
import domain.Project;
import domain.SprintTask;
import domain.Task;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.List;

public class BacklogWindowController {

    Task taskToEdit;
    ObservableList sprintTaskObservableList = FXCollections.observableArrayList();
    ObservableList detailsTaskObservableList = FXCollections.observableArrayList();
    @FXML
    // The reference of inputText will be injected by the FXML loader
    private ListView<SprintTask> sprintTaskListView;
    @FXML
    private ListView<BacklogTask> backlogTaskListView;
    @FXML
    private ListView taskDetailsListView;

    @FXML
    private ImageView rocket;
    @FXML
    private ComboBox userActionsComboBox;


    public BacklogWindowController() {
    }

    @FXML
    private void initialize() {
        System.out.println("initializing backlogTaskListView window");

        //part for init upper bar

        Image img = new Image("file:rocket.png");
        rocket.setImage(img);

        userActionsComboBox.getItems().addAll("Settings", "Log out");
        userActionsComboBox.setValue("User actions");

        Project masProject = Project.getProject("MAS Project");
        List<SprintTask> sprintTasks = masProject.getSprintTasks();
        sprintTaskObservableList.setAll(sprintTasks);
        sprintTaskListView.setItems(sprintTaskObservableList);
        ListProperty<String> listProperty = new SimpleListProperty<>();
        System.out.println("tasks shown");
        taskDetailsListView.setItems(detailsTaskObservableList);

        sprintTaskListView.setCellFactory(lv -> new ListCell<SprintTask>() {
            @Override
            protected void updateItem(SprintTask item, boolean empty) {
                super.updateItem(item, empty);
                setText(item == null ? null : item.getTitle());
            }
        });
        final Task taskInDetailsView = null;
        sprintTaskListView.getSelectionModel().selectedItemProperty().addListener(observable -> {
            if (sprintTaskListView.getSelectionModel().getSelectedItems().size() > 0) {
                taskToEdit = (Task) sprintTaskListView.getSelectionModel().getSelectedItems().get(0);
                showSelectedTask(taskToEdit);
                Main.selectedTask = taskToEdit;
            }
        });

        List<BacklogTask> backlogTasks = masProject.getBacklogTasks();
        backlogTaskListView.getItems().addAll(backlogTasks);

        backlogTaskListView.setCellFactory(lv -> new ListCell<BacklogTask>() {
            @Override
            protected void updateItem(BacklogTask item, boolean empty) {
                super.updateItem(item, empty);
                setText(item == null ? null : item.getTitle());
            }
        });

        backlogTaskListView.getSelectionModel().selectedItemProperty().addListener(observable -> {
            if (backlogTaskListView.getSelectionModel().getSelectedItems().size() > 0) {
                taskToEdit = (Task) backlogTaskListView.getSelectionModel().getSelectedItems().get(0);
                showSelectedTask(taskToEdit);
                Main.selectedTask = taskToEdit;
            }
        });
    }

    public void showSelectedTask(Task chosenTask) {
        detailsTaskObservableList.clear();
        detailsTaskObservableList.add("Task type: " + getDisplayValue(chosenTask.getType()));
        detailsTaskObservableList.add("Title: " + getDisplayValue(chosenTask.getTitle()));
        detailsTaskObservableList.add("Status: " + getDisplayValue(chosenTask.getStatus()));
        detailsTaskObservableList.add("Priority: " + getDisplayValue(chosenTask.getPriority()));
        detailsTaskObservableList.add("Description: " + getDisplayValue(chosenTask.getDescription()));
        detailsTaskObservableList.add("Create date: " + getDisplayValue(chosenTask.getCreateDate()));
        detailsTaskObservableList.add("Start date: " + getDisplayValue(chosenTask.getStartDate()));
        detailsTaskObservableList.add("End date: " + getDisplayValue(chosenTask.getEndDate()));
        detailsTaskObservableList.add("Story points: " + (chosenTask.getStoryPoints() != 0 ? getDisplayValue(chosenTask.getStoryPoints()) : ""));
        detailsTaskObservableList.add("Environment: " + getDisplayValue(chosenTask.getEnvironment()));
    }

    public String getDisplayValue(Object parameter) {
        return parameter != null ? parameter.toString() : " ";
    }

    @FXML
    public void handleEditButtonAction(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("TaskWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 500);

        Main.stage.setTitle("Task Edit");
        Main.stage.setScene(scene);
        Main.stage.show();
        Main.stage.setMaximized(false);
    }
}
