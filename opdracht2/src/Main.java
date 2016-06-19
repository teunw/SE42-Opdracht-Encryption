import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    public static void main(String[] args) {
//        System.out.println("Hello World!");
        launch(args);
    }

    TextField messageField = new TextField();
    PasswordField passwordField = new PasswordField();
    Button encryptBtn = new Button();
    Button decryptBtn = new Button();
    File chosenFile = null;

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        root.getChildren().add(vbox);
        vbox.getChildren().add(new Label("Encrypt or decrypt your text"));
        messageField.setPromptText("text to encrypt or decrypt");
        vbox.getChildren().add(messageField);
        passwordField.setPromptText("password");
        vbox.getChildren().add(passwordField);
        HBox hbox = new HBox();

        encryptBtn.setText("Encrypt");
        encryptBtn.setOnAction(e -> encryptButtonClicked());
        decryptBtn.setText("Decrypt");
        decryptBtn.setOnAction(e -> decryptButtonClicked());
        Button b = new Button("Choose file");
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser f = new FileChooser();
                chosenFile = f.showOpenDialog(primaryStage);
            }
        });
        vbox.getChildren().add(b);
        hbox.getChildren().add(encryptBtn);
        hbox.getChildren().add(decryptBtn);


        vbox.getChildren().add(hbox);


        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    Utils utils = new Utils();

    private void encryptButtonClicked() {
        if (chosenFile != null) {
            char[] input = passwordField.getText().toCharArray();
            String message = messageField.getText();
            passwordField.setText("");
            try {
                utils.encrypt(chosenFile, message, input);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No file chosen");
            alert.setHeaderText("Please select a file first");

            alert.showAndWait();
        }
    }

    private void decryptButtonClicked() {
        if (chosenFile != null) {

            char[] input = passwordField.getText().toCharArray();
            passwordField.setText("");
            try {
                messageField.setText(utils.decrypt(chosenFile, input));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No file chosen");
            alert.setHeaderText("Please select a file first");

            alert.showAndWait();
        }
    }
}
