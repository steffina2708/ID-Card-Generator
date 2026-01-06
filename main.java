package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class IDCardGenerator extends Application {

    private ImageView photoView;
    private VBox textInfoBox;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ID Card Generator");

        // Input fields
        TextField nameField = new TextField();
        nameField.setPromptText("Enter Name");

        TextField roleField = new TextField();
        roleField.setPromptText("Enter Role");

        TextField idNumberField = new TextField();
        idNumberField.setPromptText("Enter ID Number");

        TextField departmentField = new TextField();
        departmentField.setPromptText("Enter Department");

        // Image selection button and display
        Button uploadPhotoButton = new Button("Upload Photo");
        photoView = new ImageView();
        photoView.setFitWidth(100);
        photoView.setFitHeight(100);
        photoView.setStyle("-fx-border-color: black; -fx-border-width: 1;");

        uploadPhotoButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Photo");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
            );
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                Image image = new Image(file.toURI().toString());
                photoView.setImage(image);
            }
        });

        // Button to generate ID
        Button generateButton = new Button("Generate ID Card");

        // College name label
        Label collegeNameLabel = new Label("JSRM University of Technology");
        collegeNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        collegeNameLabel.setTextFill(Color.BLACK);
        collegeNameLabel.setAlignment(Pos.CENTER);

        // Text information area for ID card
        textInfoBox = new VBox(5);
        textInfoBox.setPadding(new Insets(10));
        textInfoBox.setAlignment(Pos.TOP_LEFT);

        // Display area for ID Card with college name, photo, and details
        VBox idCardContent = new VBox(10, collegeNameLabel, new HBox(10, photoView, textInfoBox));
        idCardContent.setAlignment(Pos.CENTER);
        idCardContent.setPadding(new Insets(10));
        idCardContent.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-insets: 3; " +
                               "-fx-border-width: 3 3 3 3, 6 6 6 6; -fx-border-style: solid inside, solid outside;");
        idCardContent.setMinSize(300, 180);

        // Generate button action
        generateButton.setOnAction(e -> {
            String name = nameField.getText();
            String role = roleField.getText();
            String idNumber = idNumberField.getText();
            String department = departmentField.getText();

            // Update textInfoBox with ID card details using larger, bold font
            textInfoBox.getChildren().clear();
            textInfoBox.getChildren().addAll(
                createBoldLabel("Name: " + name),
                createBoldLabel("Role: " + role),
                createBoldLabel("ID Number: " + idNumber),
                createBoldLabel("Department: " + department)
            );
        });

        // Layout for input fields, photo button, and generate button
        VBox inputLayout = new VBox(10, nameField, roleField, idNumberField, departmentField, uploadPhotoButton, generateButton);
        inputLayout.setPadding(new Insets(10));
        inputLayout.setAlignment(Pos.CENTER);

        // Main layout
        VBox mainLayout = new VBox(20, inputLayout, idCardContent);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));

        // Scene setup
        Scene scene = new Scene(mainLayout, 400, 550);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Helper method to create a bold label with larger font size
    private Label createBoldLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        return label;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
