import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ViduthalairajuFutureValueApp extends Application {

    
    private TextField txtMonthlyPayment = new TextField();
    private TextField txtInterestRate = new TextField();
    private TextArea txtResult = new TextArea();
    private Label lblMonthlyPayment = new Label("Monthly Payment");
    private Label lblInterestRate = new Label("Interest Rate");
    private Label lblInterestRateFormat = new Label("Enter 11.1% as 11.1");
    private Label lblYears = new Label("Years");
    private ComboBox<Integer> cmbYears = new ComboBox<>();
    private Button btnCalculate = new Button("Calculate");
    private Button btnClear = new Button("Clear");

    @Override
    public void start(Stage primaryStage) {

        // Set the title of the primary stage
        primaryStage.setTitle("Viduthalairaju Future Value App");

        // Create a GridPane layout
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

		double fieldWidth = 400.0; // Set to the desired width
        txtMonthlyPayment.setPrefWidth(fieldWidth);
        txtInterestRate.setPrefWidth(fieldWidth);
        cmbYears.setPrefWidth(fieldWidth);
        // Set the ComboBox for years (example values from 1 to 10)
        for (int i = 1; i <= 10; i++) {
            cmbYears.getItems().add(i);
        }

        // Set the lblInterestRateFormat label to red and align to the right
        lblInterestRateFormat.setTextFill(javafx.scene.paint.Color.RED);
        GridPane.setHalignment(lblInterestRateFormat, HPos.RIGHT);

        // Add controls to the GridPane
        pane.add(lblMonthlyPayment, 0, 0);
        pane.add(txtMonthlyPayment, 1, 0);
        pane.add(lblInterestRate, 0, 1);
        pane.add(txtInterestRate, 1, 1);
        pane.add(lblInterestRateFormat, 1, 2);
        pane.add(lblYears, 0, 3);
        pane.add(cmbYears, 1, 3);
        pane.add(txtResult, 1, 5);

        // Create an HBox for the buttons (Calculate and Clear)
        HBox actionBtnContainer = new HBox();
        actionBtnContainer.setPadding(new Insets(15, 0, 15, 30));
        actionBtnContainer.setSpacing(10);
        actionBtnContainer.getChildren().addAll(btnClear, btnCalculate);

        // Add the HBox to the GridPane
        pane.add(actionBtnContainer, 1, 4);

        // Set up the Calculate button action
        btnCalculate.setOnAction(e -> {
            // Future Value calculation logic goes here (just an example)
            try {
                // Validate if fields are empty or combo box selection is not made
                if (txtMonthlyPayment.getText().isEmpty() || txtInterestRate.getText().isEmpty() || cmbYears.getValue() == null) {
                    txtResult.setText("Please enter all values.");
                    return;
                }

                // Parse the values from the text fields and combo box
                double payment = Double.parseDouble(txtMonthlyPayment.getText());
                double interestRate = Double.parseDouble(txtInterestRate.getText()) / 100;
                int years = cmbYears.getValue();

                // Simple future value formula: FV = P * ((1 + r)^t)
                double futureValue = payment * Math.pow(1 + interestRate, years);
                txtResult.setText(String.format("Future Value: %.2f", futureValue));
            } catch (NumberFormatException ex) {
                txtResult.setText("Invalid input. Please enter valid numbers.");
            }
        });

        // Set up the Clear button action
        btnClear.setOnAction(e -> {
            txtMonthlyPayment.clear();
            txtInterestRate.clear();
            cmbYears.setValue(null);
            txtResult.clear();
        });

        // Create the scene and set it on the primary stage
        Scene scene = new Scene(pane, 400, 350);
        primaryStage.setScene(scene);

        // Show the primary stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
