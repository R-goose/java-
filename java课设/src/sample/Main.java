package sample;

import DrawPane.MyTreeView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.stage.StageStyle;

public class Main extends Application {
    public static Stage main_stage;

    public static BorderPane pane;
    @Override
    public void start(Stage primaryStage) throws Exception{
        // 加载主FXML文件
        Parent root = FXMLLoader.load(getClass().getResource("fxml/sample.fxml"));
        // 创建场景并设置到主舞台
        primaryStage.setTitle("思维导图");
        primaryStage.setScene(new Scene(root, 1260, 840));
        titleBarController titleBarController=new titleBarController();
        titleBarController.setStage(primaryStage);
//        primaryStage.setResizable(false);
        main_stage=primaryStage;
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
