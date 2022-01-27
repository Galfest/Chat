import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ExampleController {
    @FXML
    public Button btnClick;

    @FXML
    public Label labelText;

    public void click(ActionEvent actionEvent) {
        System.out.println("Click!!!");
        labelText.setText("JAVAFX Hello!");
        btnClick.setText("PRESSED");
        btnClick.setScaleX(5.0);
        btnClick.setScaleY(5.0);
    }
}