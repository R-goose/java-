package sample;

import Deque.NodeList;
import DrawPane.DrawPane;
import DrawPane.*;
import Tree.TreeUtil;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    public static Stage primarystage;
    public static BorderPane pane;

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
    private Button rightHide;

    @FXML
    private Button viewing;

    @FXML
    private Button left_layout;

    @FXML
    private Button leftHide;

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
    void setting(ActionEvent event) {
        System.out.println(666);
    }


    @FXML
    void GetNewOne(ActionEvent event) {

    }


    @FXML
    void openOne(ActionEvent event) {

    }



    @FXML
    void viewing(ActionEvent event) {

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
    void newNode(ActionEvent event) {
        if(NodeList.list.isEmpty()){
            TreeUtil.creatRoot();
            DrawPane.draw();
            try{
                MyTreeView.setTreeView();
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
    }



    @FXML
    void deleteNode(ActionEvent event) {
        System.out.println("ok");
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
    void leftHide(ActionEvent event) {

    }


    @FXML
    void rightHide(ActionEvent event) {

    }

}
