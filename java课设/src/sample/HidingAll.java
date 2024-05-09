package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HidingAll {

    @FXML
    private Button saving;

    @FXML
    private Button rightShow;

    @FXML
    private Button deriving;

    @FXML
    private Button searching;

    @FXML
    private Button GetNewOne;

    @FXML
    private Button openOne;

    @FXML
    private Button leftShow;

    @FXML
    private Button viewing;

    @FXML
    private Button setting;

    @FXML
    private Button miniSizeButton;

    @FXML
    private Button closeButton;

    @FXML
    void GetNewOne(ActionEvent event) {
        GetNewOne.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                URL url = getClass().getResource("fxml/sample.fxml");
                Parent root=null;
                try {
                    root = FXMLLoader.load(url);
                }catch (IOException e) {
                    e.printStackTrace();
                }

                Scene scene = new Scene(root,1260, 840);
                Stage stage = new Stage();
//                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(scene);
                stage.show();
            }
        });
    }



    @FXML
    void openOne(ActionEvent event) {

    }




    @FXML
    void searching(ActionEvent event) {

    }



    @FXML
    void saving(ActionEvent event) {

    }


    @FXML
    void deriving(ActionEvent event) {

    }



    @FXML
    void aba9a9(ActionEvent event) {

    }

    @FXML
    void leftShow(ActionEvent event) {
        leftShow.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                URL url = getClass().getResource("fxml/hiding_right.fxml");
                Parent root=null;
                try {
                    root = FXMLLoader.load(url);
                }catch (IOException e) {
                    e.printStackTrace();
                }

                // 获取当前舞台
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                // 将新的页面加载到当前舞台的场景中
                Scene scene = new Scene(root,1260, 840);
                stage.setScene(scene);
                stage.show();

            }
        });
    }


    @FXML
    void rightShow(ActionEvent event) {
        rightShow.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                URL url = getClass().getResource("fxml/hiding_left.fxml");
                Parent root=null;
                try {
                    root = FXMLLoader.load(url);
                }catch (IOException e) {
                    e.printStackTrace();
                }

                // 获取当前舞台
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                // 将新的页面加载到当前舞台的场景中
                Scene scene = new Scene(root,1260, 840);
                stage.setScene(scene);
                stage.show();

            }
        });
    }
    @FXML
    void miniSizeButton(ActionEvent event) {

    }

    @FXML
    void closeButton(ActionEvent event) {

    }

}
