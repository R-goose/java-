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

public class HidingRight {

    @FXML
    private Button rightShow;

    @FXML
    private Button fillingColor;

    @FXML
    private Button searching;

    @FXML
    private Button deleteNode;

    @FXML
    private Button openOne;

    @FXML
    private Button center_layout;

    @FXML
    private Button viewing;

    @FXML
    private Button left_layout;

    @FXML
    private Button setting;

    @FXML
    private Button saving;

    @FXML
    private Button deriving;

    @FXML
    private Button GetNewOne;

    @FXML
    private Button right_layout;

    @FXML
    private Button borderRadious;

    @FXML
    private Button newNode;

    @FXML
    private Button fontSize;

    @FXML
    private Button fontColor;

    @FXML
    private Button allHide;

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
    void newNode(ActionEvent event) {

    }



    @FXML
    void deleteNode(ActionEvent event) {

    }



    @FXML
    void left_layout(ActionEvent event) {

    }



    @FXML
    void right_layout(ActionEvent event) {

    }



    @FXML
    void center_layout(ActionEvent event) {

    }


    @FXML
    void borderRadious(ActionEvent event) {

    }


    @FXML
    void fillingColor(ActionEvent event) {

    }



    @FXML
    void fontSize(ActionEvent event) {

    }



    @FXML
    void fontColor(ActionEvent event) {

    }



    @FXML
    void allHide(ActionEvent event) {
        allHide.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                URL url = getClass().getResource("fxml/hiding_all.fxml");
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
                URL url = getClass().getResource("fxml/sample.fxml");
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
