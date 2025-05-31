import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class ViduthalairajuGradeBookApp extends Application {

    private static final String FILE_NAME = "grades.csv";
    private TextField firstNameField, lastNameField, courseField;
    private ComboBox<String> gradeComboBox;
    private TableView<Student> gradeTable;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        firstNameField = new TextField();
        lastNameField = new TextField();
        courseField = new TextField();
        gradeComboBox = new ComboBox<>();
        gradeComboBox.getItems().addAll("A", "B", "C", "D", "F");
        gradeComboBox.setValue("A");


        Button saveButton = new Button("Save");
        Button clearButton = new Button("Clear");
        Button viewGradesButton = new Button("View Grades");

        saveButton.setOnAction(e -> saveGrade());
        clearButton.setOnAction(e -> clearForm());
        viewGradesButton.setOnAction(e -> viewGrades());

        Label firstNameLabel = new Label("First Name:");
        Label lastNameLabel = new Label("Last Name:");
        Label courseLabel = new Label("Course:");
        Label gradeLabel = new Label("Grade:");

        GridPane formGrid = new GridPane();
        formGrid.setVgap(10);
        formGrid.setHgap(10);
        formGrid.add(firstNameLabel, 0, 0);
        formGrid.add(firstNameField, 1, 0);
        formGrid.add(lastNameLabel, 0, 1);
        formGrid.add(lastNameField, 1, 1);
        formGrid.add(courseLabel, 0, 2);
        formGrid.add(courseField, 1, 2);
        formGrid.add(gradeLabel, 0, 3);
        formGrid.add(gradeComboBox, 1, 3);

        HBox buttonBox = new HBox(10, saveButton, clearButton, viewGradesButton);

        // TableView setup
        gradeTable = new TableView<>();
        gradeTable.setPrefHeight(200);

        TableColumn<Student, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFirstName()));

        TableColumn<Student, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getLastName()));

        TableColumn<Student, String> courseCol = new TableColumn<>("Course");
        courseCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCourse()));

        TableColumn<Student, String> gradeCol = new TableColumn<>("Grade");
        gradeCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getGrade()));

        gradeTable.getColumns().addAll(firstNameCol, lastNameCol, courseCol, gradeCol);

        VBox layout = new VBox(15, formGrid, buttonBox, gradeTable);
        layout.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(layout, 600, 450);
        primaryStage.setTitle("Prakash Viduthalairaju Grade Book App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void saveGrade() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String course = courseField.getText();
        String grade = gradeComboBox.getValue();

        if (firstName.isEmpty() || lastName.isEmpty() || course.isEmpty() || grade == null) {
            showAlert("Error", "All fields must be filled in!");
            return;
        }

        Student student = new Student(firstName, lastName, course, grade);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            File file = new File(FILE_NAME);
            if (file.length() == 0) {
                bufferedWriter.write("firstName,lastName,course,grade\n");
            }
            bufferedWriter.write(String.join(",", firstName, lastName, course, grade) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        clearForm();
    }

    private void clearForm() {
        firstNameField.clear();
        lastNameField.clear();
        courseField.clear();
        gradeComboBox.setValue("A");
    }

    private void viewGrades() {
        gradeTable.getItems().clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    Student student = new Student(parts[0], parts[1], parts[2], parts[3]);
                    gradeTable.getItems().add(student);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
