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
    private TextArea gradeDisplayArea;
    private List<Student> students;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        students = new ArrayList<>();

        firstNameField = new TextField();
        lastNameField = new TextField();
        courseField = new TextField();
        gradeComboBox = new ComboBox<>();
        gradeComboBox.getItems().addAll("A", "B", "C", "D", "F");
        gradeComboBox.setValue("A");


        Button saveButton = new Button("Save");
        Button clearButton = new Button("Clear");
        Button viewGradesButton = new Button("View Grades");

        gradeDisplayArea = new TextArea();
        gradeDisplayArea.setEditable(false);
        gradeDisplayArea.setPrefHeight(200);

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
        VBox layout = new VBox(15, formGrid, buttonBox, gradeDisplayArea);
        layout.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(layout, 450, 400);
        primaryStage.setTitle("Grade Book App");
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
        students.add(student);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            if (new File(FILE_NAME).length() == 0) {
                bufferedWriter.write("firstName,lastName,course,grade\n");
            }
            bufferedWriter.write(student.getFirstName() + "," + student.getLastName() + "," + student.getCourse() + "," + student.getGrade() + "\n");
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
        gradeDisplayArea.clear();
        for (Student student : students) {
            gradeDisplayArea.appendText(student.toString() + "\n");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("firstName")) {
                    gradeDisplayArea.appendText(line + "\n");
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
