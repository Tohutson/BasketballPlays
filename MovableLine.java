import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class MovableLine {

    Line line;
    Circle startPoint, endPoint;

    public MovableLine(Pane root) {
        createDraggableLine(root);
    }

    private void createDraggableLine(Pane root){
        // Create the line
        line = new Line(50, 50, 100, 100);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(2);

        // Create the start and end points
        startPoint = createDraggablePoint(line.getStartX(), line.getStartY());
        endPoint = createDraggablePoint(line.getEndX(), line.getEndY());

        // Add mouse event handlers for dragging
        startPoint.setOnMouseDragged(e -> handlePointMouseDragged(e, line, true));
        endPoint.setOnMouseDragged(e -> handlePointMouseDragged(e, line, false));

        root.getChildren().addAll(line, startPoint, endPoint);
    }

    private void handlePointMouseDragged(MouseEvent event, Line line, Boolean startPoint) {
        Circle point = (Circle) event.getSource();
        double offsetX = event.getX();
        double offsetY = event.getY();
        point.setCenterX(offsetX);
        point.setCenterY(offsetY);
        point.setLayoutX(event.getSceneX() - offsetX);
        point.setLayoutY(event.getSceneY() - offsetY);

        if (startPoint) {
            line.setStartX(offsetX);
            line.setStartY(offsetY);
        } else {
            line.setEndX(offsetX);
            line.setEndY(offsetY);
        }
    }

    private Circle createDraggablePoint(double x, double y) {
        Circle point = new Circle(x, y, 5, Color.RED);
        point.setStroke(Color.BLACK);
        point.setStrokeWidth(1);
        point.setCenterX(x);
        point.setCenterY(y);
        return point;
    }


    public void removeLineFrom(Pane root){
        root.getChildren().remove(line);
        root.getChildren().remove(startPoint);
        root.getChildren().remove(endPoint);
    }
}