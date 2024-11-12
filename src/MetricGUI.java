import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MetricGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Metric Converter");

        Label label = new Label("Welcome to Metric Converter!");
        ComboBox<String> conversionOptions = new ComboBox<>();
        conversionOptions.getItems().addAll(
            "kg to lb",
            "mm to in",
            "km to mile",
            "g to oz",
            "yard to ft"
        );
        conversionOptions.setPromptText("Select Conversion Option");

        TextField quantityField = new TextField();
        quantityField.setPromptText("Enter quantity");

        Button convertButton = new Button("Convert");
        Label resultLabel = new Label();

        convertButton.setOnAction(e -> {
            String selectedOption = conversionOptions.getValue();
            String quantityText = quantityField.getText();

            try {
                double quantity = Double.parseDouble(quantityText);

                String result = convertUnits(selectedOption, quantity);
                resultLabel.setText(result);

            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter a valid number.");
            } catch (NullPointerException ex) {
                resultLabel.setText("Please select a conversion option.");
            }
        });

        VBox layout = new VBox(10, label, conversionOptions, quantityField, convertButton, resultLabel);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String convertUnits(String option, double quantity) {
        switch (option) {
            case "kg to lb":
                return quantity + " kilograms is equal to " + (quantity * 2.205) + " pounds.";
            case "mm to in":
                return quantity + " millimeters is equal to " + (quantity / 25.4) + " inches.";
            case "km to mile":
                return quantity + " kilometers is equal to " + (quantity / 1.609) + " miles.";
            case "g to oz":
                return quantity + " grams is equal to " + (quantity / 28.35) + " ounces.";
            case "yard to ft":
                return quantity + " yards is equal to " + (quantity * 3) + " feet.";
            default:
                return "Invalid option.";
        }
    }
}
