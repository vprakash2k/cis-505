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

public class ViduthalairajuEnhancedFutureValueApp extends Application {

    private TextField txtMonthlyPayment = new TextField();
    private TextField txtInterestRate = new TextField();
    private ComboBox<Integer> cmbYears = new ComboBox<>();
    private TextArea txtResult = new TextArea();
    private Label lblMonthlyPayment = new Label("Monthly Payment:");
    private Label lblInterestRate = new Label("Interest Rate:");
    private Label lblInterestRateFormat = new Label("Enter 11.1% as 11.1");
    private Label lblYears = new Label("Years:");
    private Label lblFutureValueDate = new Label();
    private Button btnCalculate = new Button("Calculate");
    private Button btnClear = new Button("Clear");

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Viduthalairaju Future Value App");

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        HBox actionBtnContainer = new HBox();
        actionBtnContainer.setPadding(new Insets(15, 0, 15, 0));
        actionBtnContainer.setSpacing(10);
        actionBtnContainer.setAlignment(Pos.CENTER_RIGHT);
        actionBtnContainer.getChildren().addAll(btnClear, btnCalculate);

		double fieldWidth = 300.0;
		double textAreaWidth = 300.0;
		double textAreaHeight = 3.0;
        txtMonthlyPayment.setPrefWidth(fieldWidth);
        txtInterestRate.setPrefWidth(fieldWidth);
        cmbYears.setPrefWidth(fieldWidth);

        for (int i = 1; i <= 10; i++) {
            cmbYears.getItems().add(i);
        }

        txtResult.setPrefWidth(textAreaWidth);
        txtResult.setPrefRowCount((int) textAreaHeight);

        lblInterestRateFormat.setTextFill(javafx.scene.paint.Color.RED);
        GridPane.setHalignment(lblInterestRateFormat, HPos.RIGHT);

        pane.add(lblMonthlyPayment, 0, 0);
        pane.add(txtMonthlyPayment, 1, 0);
        pane.add(lblInterestRate, 0, 1);
        pane.add(txtInterestRate, 1, 1);
        pane.add(lblInterestRateFormat, 1, 2);
        pane.add(lblYears, 0, 3);
        pane.add(cmbYears, 1, 3);
        pane.add(actionBtnContainer, 1, 4);
        pane.add(lblFutureValueDate, 0, 5, 2, 1);
        //pane.add(txtResult, 1, 6);
        pane.add(txtResult, 0, 6, 2, 1);

        btnCalculate.setOnAction(e -> calculateResults());

        btnClear.setOnAction(e -> clearFormFields());

        Scene scene = new Scene(pane, 350, 350);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private String getTodaysDate() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM/dd/yyyy");
        return sdf.format(new java.util.Date());
    }

    private void clearFormFields() {
        txtMonthlyPayment.clear();
        txtInterestRate.clear();
        txtResult.clear();
        cmbYears.setValue(null);
        lblFutureValueDate.setText("");
    }

    private void calculateResults() {
        try {
            if (txtMonthlyPayment.getText().isEmpty() ||
                    txtInterestRate.getText().isEmpty() ||
                    cmbYears.getValue() == null) {
                txtResult.setText("Please enter all values.");
                return;
            }

            double monthlyPayment = Double.parseDouble(txtMonthlyPayment.getText());
            double interestRate = Double.parseDouble(txtInterestRate.getText());
            int years = cmbYears.getValue();

            double futureValue = FinanceCalculator.calculateFutureValue(monthlyPayment, interestRate, years);
            String today = getTodaysDate();

            lblFutureValueDate.setText("Calculation as of " + today);
            txtResult.setText("The future value is $" + String.format("%,.2f", futureValue));

        } catch (NumberFormatException e) {
            txtResult.setText("Invalid input. Please enter numeric values.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
