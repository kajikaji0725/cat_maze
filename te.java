import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * Test4 ƒV[ƒ“‘JˆÚ
 */
public class te extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    Scene sc1, sc2;

    static Stage currentStage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        currentStage = primaryStage;

        Button b = new Button("ƒV[ƒ“‘JˆÚ");
        b.setOnMouseClicked(e -> {
            System.out.println("clicked");
            ChangeScene();
        });

        BorderPane bp = new BorderPane();
        bp.setCenter(b);
        sc1 = new Scene(bp);

        Label lb = new Label("hello");
        BorderPane bp2 = new BorderPane();
        bp2.setCenter(lb);
        sc2 = new Scene(bp2);

        primaryStage.setScene(sc1);
        currentStage.setHeight(400);
        currentStage.setWidth(400);
        currentStage.show();

    }

    public void ChangeScene() {
        if (currentStage.getScene() == sc1) {
            currentStage.setScene(sc2);
        } else {
            currentStage.setScene(sc1);
        }
    }
}