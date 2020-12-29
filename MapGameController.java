import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.fxml.FXML;
import javafx.animation.Animation;

public class MapGameController implements Initializable {
    public MapData mapData;
    public MoveChara chara;
    public GridPane mapGrid;
    public ImageView[] mapImageViews;
    public Timeline timer;
    public int secondCount = 0;
    public int minuteCount = 10;

    private int[] Qua = new int[5];

    @FXML
    public Label time;

    @FXML
    public Label FOOD;

    @FXML
    public Label PLAY;

    @FXML
    public Label DOKURINGO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        mapData = new MapData(21, 15);
        chara = new MoveChara(1, 1, mapData);
        mapImageViews = new ImageView[mapData.getHeight() * mapData.getWidth()];
        for (int y = 0; y < mapData.getHeight(); y++) {
            for (int x = 0; x < mapData.getWidth(); x++) {
                setMapImageView(x, y);
            }
        }

        timer = new Timeline(
                /* 1000 milli sec */
                new KeyFrame(Duration.millis(1000), event -> {
                    secondCount--;
                    if (secondCount < 0) {
                        secondCount = 59;
                        minuteCount--;
                    }
                    time.setText(String.format("%02d:%02d", minuteCount, secondCount));

                    if (secondCount == 0 && minuteCount == 0) {
                        try {
                            MapGame.changeSceneByFXML("gameover.fxml");
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }));
        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();

        drawMap(chara, mapData);
        FOOD.setText("0/3"); // ???????????A?C?e??/?S????L????
        PLAY.setText("0/3");// ???????????A?C?e??/?S??????????????
        DOKURINGO.setText("0/3");// ???????????A?C?e??/?S???????????
    }

    // set mapImageViews
    void setMapImageView(int x, int y) {
        int index = y * mapData.getWidth() + x;
        mapImageViews[index] = mapData.getImageView(x, y);
    }

    // Draw the map
    public void drawMap(MoveChara c, MapData m) {
        int cx = c.getPosX();
        int cy = c.getPosY();
        mapGrid.getChildren().clear();
        for (int y = 0; y < mapData.getHeight(); y++) {
            for (int x = 0; x < mapData.getWidth(); x++) {
                int index = y * mapData.getWidth() + x;
                if (x == cx && y == cy) {
                    mapGrid.add(c.getCharaImageView(), x, y);
                } else {
                    mapGrid.add(mapImageViews[index], x, y);
                }
            }
        }
    }

    // Get users key actions
    public void keyAction(KeyEvent event) {
        KeyCode key = event.getCode();
        System.out.println("keycode:" + key);
        if (key == KeyCode.H) {
            leftButtonAction();
        } else if (key == KeyCode.J) {
            downButtonAction();
        } else if (key == KeyCode.K) {
            upButtonAction();
        } else if (key == KeyCode.L) {
            rightButtonAction();
        }

        int data = mapData.getMap(chara.getPosX(), chara.getPosY());
        if (data == MapData.TYPE_FOOD || data == MapData.TYPE_PLAY || data == MapData.TYPE_DOKURINGO) {
            Qua[data]++;
            collection_item(data, Qua[data]);
            mapData.setMap(chara.getPosX(), chara.getPosY(), MapData.TYPE_SPACE);
            mapData.setImageViews();
        }
        drawMap(chara, mapData);

        setMapImageView(chara.getPosX(), chara.getPosY());

        System.out.println(mapData.getMap(chara.getPosX(), chara.getPosY()));
    }

    // Operations for going the cat down
    public void upButtonAction() {
        printAction("UP");
        chara.setCharaDirection(MoveChara.TYPE_UP);
        chara.move(0, -1);
    }

    // Operations for going the cat down
    public void downButtonAction() {
        printAction("DOWN");
        chara.setCharaDirection(MoveChara.TYPE_DOWN);
        chara.move(0, 1);
    }

    // Operations for going the cat right
    public void leftButtonAction() {
        printAction("LEFT");
        chara.setCharaDirection(MoveChara.TYPE_LEFT);
        chara.move(-1, 0);
    }

    // Operations for going the cat right
    public void rightButtonAction() {
        printAction("RIGHT");
        chara.setCharaDirection(MoveChara.TYPE_RIGHT);
        chara.move(1, 0);

    }

    public void func1ButtonAction(ActionEvent event) {
        System.out.println("func1: Nothing to do");
    }

    // Print actions of user inputs
    public void printAction(String actionString) {
        System.out.println("Action: " + actionString);
    }

    private class ImageAnimation extends AnimationTimer {

        private ImageView charaView = null;
        private Image[] charaImages = null;
        private int index = 0;
        private Text time = new Text();

        private long duration = 500 * 1000000L; // 500[ms]
        private long startTime = 0;

        private long count = 0L;
        private long preCount;
        private boolean isPlus = true;

        @Override
        public void handle(long now) {
            if (startTime == 0) {
                startTime = now;
            }
            time.setText(String.valueOf(now));

            preCount = count;
            count = (now - startTime) / duration;
            if (preCount != count) {
                if (isPlus) {
                    index++;
                } else {
                    index--;
                }
                if (index < 0 || 2 < index) {
                    index = 1;
                    isPlus = !isPlus; // true == !false, false == !true
                }
            }
        }
    }

    // ??????????????A?N?V?????@?i?\??j
    public void sleep() {
        try {
            Thread.sleep(10000); // 10?b
        } catch (InterruptedException e) {
        }
    }

    // ?A?C?e??????\??
    public void collection_item(int data, int Que) {

        if (data == MapData.TYPE_PLAY) {
            PLAY.setText(String.format("%d/%d", Que, 3));
            System.out.println(Que);
        } else if (data == MapData.TYPE_FOOD) {
            FOOD.setText(String.format("%d/%d", Que, 3));
            System.out.println(Que);
        } else if (data == MapData.TYPE_DOKURINGO) {
            DOKURINGO.setText(String.format("%d/%d", Que, 1));
            System.out.println(Que);
        }
    }

}
