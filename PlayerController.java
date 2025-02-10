import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.net.URL;

public class PlayerController implements Initializable{

    List<MovableLine> lines = new ArrayList<>();
    List<Player> players = new ArrayList<>(); 

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Create and add objects at the start
        addPlayer(300, 100);
        addPlayer(200, 300);
        addPlayer(400, 300);
        addPlayer(500, 200);
        addPlayer(100, 200);

        Circle circle = new Circle(100);
        circle.setCenterX(players.get(0).getCenterX());
        circle.setCenterY(players.get(0).getCenterY());
        players.get(0).addPath(circle, Duration.seconds(10));
        players.get(0).followPath(0);
    }

    void addPlayer(double x, double y) {
        Player newPlayer = new Player(x, y, anchorPane);
        players.add(newPlayer);
    }

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