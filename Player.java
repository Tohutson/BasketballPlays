import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.animation.PathTransition;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Duration;

public class Player {

    private Circle playerCircle;
    private List<PathTransition> paths;
    double offsetX;
    double offsetY;

    public Player(double x, double y, Pane root) {
        paths = new ArrayList<>();
        createDraggablePlayer(x, y, root);
    }

    public void followPath(int n) {
        if (n < paths.size()) {
            paths.get(n).play();
        }
    }

    public double getCenterX() {
        return playerCircle.getCenterX();
    }

    public double getCenterY() {
        return playerCircle.getCenterY();
    }

    public void addPath(Shape path, Duration duration) {
        PathTransition transition = new PathTransition();
        transition.setNode(playerCircle);
        transition.setDuration(duration);
        transition.setPath(path);

        paths.add(transition);
    }

    private void createDraggablePlayer(double x, double y, Pane root){
        playerCircle = new Circle(x, y, 20, Color.RED);
        playerCircle.setStroke(Color.BLACK);
        playerCircle.setStrokeWidth(3);
        playerCircle.setCenterX(x);
        playerCircle.setCenterY(y);

        playerCircle.setOnMousePressed(e -> {
            offsetX = e.getX() - playerCircle.getCenterX();
            offsetY = e.getY() - playerCircle.getCenterY();
            System.out.println(offsetY);
        });

        playerCircle.setOnMouseDragged(e -> {handlePointMouseDragged(e, playerCircle);});

        root.getChildren().add(playerCircle);
    }

    private void handlePointMouseDragged(MouseEvent event, Circle player) {
        Pane parent = (Pane) player.getParent(); // Get the parent pane
        double radius = player.getRadius();
        
        // Get new coordinates
        double newX = event.getX() - offsetX;
        double newY = event.getY() - offsetY;

        // Ensure the circle stays within bounds
        double minX = radius;
        double minY = radius;
        double maxX = parent.getWidth() - radius;
        double maxY = parent.getHeight() - radius;
    
        // Clamp values within boundaries
        newX = Math.max(minX, Math.min(maxX, newX));
        newY = Math.max(minY, Math.min(maxY, newY));
    
        // Update position
        player.setCenterX(newX);
        player.setCenterY(newY);
    }
}
