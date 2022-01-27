package Prolect.Chat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainChatController implements Initializable {
    @FXML
    public VBox mainChatPanel;

    @FXML
    public TextArea mainChatArea;

    @FXML
    public ListView contactList;

    @FXML
    public TextField inputField;

    @FXML
    public Button btnSend;


    public void ConnectToServer(ActionEvent actionEvent) {
    }

    public void DisconnectFromServer(ActionEvent actionEvent) {
    }

    public void mockAction(ActionEvent actionEvent) {
    }

    public void Exit(ActionEvent actionEvent) {
        System.exit(1);
    }

    public void showHelp(ActionEvent actionEvent) {
    }

    public void showAbout(ActionEvent actionEvent) {
    }

    public void sendMessage(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var contacts = new ArrayList<String>();

    }
}
