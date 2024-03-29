package ui;

import domain.*;
import domain.base.ObjectPlus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TaskWindowController {

    Task task = Main.selectedTask instanceof BacklogTask ? (BacklogTask) Main.selectedTask : (SprintTask) Main.selectedTask;
    ObservableList sprintTaskObservableList = FXCollections.observableArrayList();
    @FXML
    private ImageView rocket;
    @FXML
    private ComboBox userActionsComboBox;

    @FXML
    private ComboBox assignedEmployeeComboBox;

    @FXML
    private TextArea titleTextArea;

    @FXML
    private void initialize() {

        Image img = new Image("file:rocket.png");
        rocket.setImage(img);

        userActionsComboBox.getItems().addAll("Settings", "Log out");
        userActionsComboBox.setValue("User actions");

        Sprint sprint = task.getSprint().get(0);

        Project project = sprint.getProject();

        List<Employee> employeesToLink = task.getAvailableEmployees();

        assignedEmployeeComboBox.getItems().addAll(employeesToLink);
        if (task.hasOwner()) {
            assignedEmployeeComboBox.setValue((Employee) task.getOwner());
        }

        assignedEmployeeComboBox.setCellFactory(lv -> new ListCell<Employee>() {
            @Override
            protected void updateItem(Employee item, boolean empty) {
                super.updateItem(item, empty);
                setText(item == null ? null : showOwner(item));
            }
        });

        titleTextArea.setText(task.getTitle());
    }

    public void handleCancelButtonAction(ActionEvent actionEvent) throws IOException {
        loadBacklogWindow();
    }

    public void handleSaveButtonAction(ActionEvent actionEvent) throws Exception {
        task.assignOwner((Employee) assignedEmployeeComboBox.getSelectionModel().getSelectedItem());
        loadBacklogWindow();
        refreshExtension();
    }

    private void refreshExtension() throws IOException, ClassNotFoundException {
        Path fileToDeletePath = Paths.get("objects.txt");
        Files.delete(fileToDeletePath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("objects.txt"));
        ObjectPlus.writeExtents(objectOutputStream);

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("objects.txt"));
        ObjectPlus.readExtents(objectInputStream);
    }

    private void loadBacklogWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("BacklogWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 500);

        Main.stage.setTitle("Task Edit");
        Main.stage.setScene(scene);
        Main.stage.show();
        Main.stage.setMaximized(false);
    }

    public String showOwner(Employee employee) {
        return employee.getFirstName() + " " + employee.getLastName();
    }


}
