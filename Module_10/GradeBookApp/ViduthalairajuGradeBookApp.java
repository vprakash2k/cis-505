

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViduthalairajuGradeBookApp extends Application {

    private static final String FILE_NAME = "grades.csv";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Grade Book App");

        // Labels
        Label firstNameLabel = new Label("First Name:");
        Label lastNameLabel = new Label("Last Name:");
        Label courseLabel = new Label("Course:");
        Label gradeLabel = new Label("Grade:");

        // Input Fields
        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();
        TextField courseField = new TextField();

        // Grade ComboBox
        ComboBox<String> gradeComboBox = new ComboBox<>();
        gradeComboBox.getItems().addAll("A", "B", "C", "D", "E", "F");

        // Buttons
        Button saveButton = new Button("Save");
        Button clearButton = new Button("Clear");
        Button viewButton = new Button("View Grades");

        // Result Area
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setPrefHeight(200);

        // Layout
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

        HBox buttonBox = new HBox(10, saveButton, clearButton, viewButton);
        VBox layout = new VBox(15, formGrid, buttonBox, resultArea);
        layout.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(layout, 450, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Event Handling: Save Button
        saveButton.setOnAction(e -> saveGrade(firstNameField, lastNameField, courseField, gradeComboBox, resultArea));

        // Event Handling: Clear Button
        clearButton.setOnAction(e -> clearForm(firstNameField, lastNameField, courseField, gradeComboBox));

        // Event Handling: View Grades Button
        viewButton.setOnAction(e -> viewGrades(resultArea));
    }

    // Method to Save Grade
    private void saveGrade(TextField firstNameField, TextField lastNameField, TextField courseField, ComboBox<String> gradeComboBox, TextArea resultArea) {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String course = courseField.getText();
        String grade = gradeComboBox.getValue();

        if (firstName.isEmpty() || lastName.isEmpty() || course.isEmpty() || grade == null) {
            resultArea.setText("All fields must be filled in.");
            return;
        }

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.append(firstName).append(",")
                    .append(lastName).append(",")
                    .append(course).append(",")
                    .append(grade).append("\n");
            resultArea.setText("Grade saved for " + firstName + " " + lastName);
            clearForm(firstNameField, lastNameField, courseField, gradeComboBox);
        } catch (IOException e) {
            resultArea.setText("Error saving grade.");
        }
    }

    // Method to Clear the Form
    private void clearForm(TextField firstNameField, TextField lastNameField, TextField courseField, ComboBox<String> gradeComboBox) {
        firstNameField.clear();
        lastNameField.clear();
        courseField.clear();
        gradeComboBox.setValue(null);
    }

    // Method to View Saved Grades
    private void viewGrades(TextArea resultArea) {
        List<String> grades = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                grades.add(line);
            }
        } catch (IOException e) {
            resultArea.setText("Error reading grades.");
            return;
        }

        if (grades.isEmpty()) {
            resultArea.setText("No grades saved.");
        } else {
            resultArea.setText(String.join("\n", grades));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
