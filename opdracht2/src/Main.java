import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args) {
//        System.out.println("Hello World!");
        launch(args);
    }
    TextField messageField = new TextField();
    PasswordField passwordField = new PasswordField();
    Button encryptBtn = new Button();
    Button decryptBtn = new Button();

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        root.getChildren().add(vbox);
        vbox.getChildren().add(new Label("Encrypt or decrypt your text"));
        messageField.setPromptText("text to encrypt or decrypt");
        vbox.getChildren().add(messageField);
        passwordField.setPromptText("password");
        vbox.getChildren().add(passwordField);
        HBox hbox = new HBox();

        encryptBtn.setText("Encrypt");
        encryptBtn.setOnAction(e->encryptButtonClicked());
        decryptBtn.setText("Decrypt");
        decryptBtn.setOnAction(e->decryptButtonClicked());


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
        char[] input = passwordField.getText().toCharArray();
        String message = messageField.getText();
        passwordField.setText("");
        try {
            utils.encrypt(message,input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void decryptButtonClicked() {
        char[] input = passwordField.getText().toCharArray();
        passwordField.setText("");
        try {
            messageField.setText(utils.decrypt(input));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
