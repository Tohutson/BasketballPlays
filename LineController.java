import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

public class LineController {

    List<MovableLine> lines = new ArrayList<>();

    @FXML
    private AnchorPane anchorPane;

    @FXML
    void addLine(ActionEvent event) {
        MovableLine newLine = new MovableLine(anchorPane);
        lines.add(newLine);
    }

    @FXML
    void removeLine(ActionEvent event) {
        MovableLine currentLine = lines.get(0);
        lines.remove(0);
        currentLine.removeLineFrom(anchorPane);
    }
}