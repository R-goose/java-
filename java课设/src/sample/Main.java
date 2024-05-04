package sample;

import DrawPane.DrawPane;
import DrawPane.MyTreeView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static BorderPane pane;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //将树形图添加到root节点中

//        //获取BorderPane实例
//        pane=(BorderPane) root;
//
//        //创建DrawPane示例
//        DrawPane drawPane=new DrawPane();
//
//        //将drawPane添加到中心位置
//        pane.setCenter(drawPane);

        primaryStage.setTitle("思维导图");
        primaryStage.setScene(new Scene(root, 1260, 840));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
