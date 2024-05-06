package DrawPane;

import Deque.NodeList;
import Deque.NodePos;
import Tree.TreeNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.Controller;

// 显示带有文本框的窗口，编辑节点相关内容，重新绘制相关视图
public class MyTextArea {

    // 显示文本输入框窗口方法
    public static void showTextArea(TreeNode node) {
        // 创建一个AnchorPane作为窗口的根节点
        AnchorPane an = new AnchorPane();
        // 创建一个新的舞台
        Stage stage = new Stage();

        // 创建文本输入框，并设置其初始文本内容、字体大小和大小
        TextArea ta = new TextArea();
        ta.appendText(node.getText());
        ta.setFont(Font.font(16));
        ta.setPrefWidth(250);
        ta.setPrefHeight(150);
        ta.setWrapText(true);

        // 创建确定按钮，并设置其位置和大小
        Button btn = new Button("确定");
        btn.setLayoutX(0);
        btn.setLayoutY(150);
        btn.setPrefWidth(250);
        btn.setPrefHeight(35);

        // 将文本输入框和按钮添加到AnchorPane中
        an.getChildren().addAll(ta, btn);

        // 按钮点击事件处理
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // 获取文本输入框中的内容
                String str = ta.getText();
                // 判断内容是否发生改变
                if (!str.equals(node.getText())) {
                    // 更新节点的文本内容和文本属性
                    node.setText(str);
                    node.setTxt(str);

                    // 重新应用CSS样式、布局和绘制相关视图
                    Controller.g.applyCss();
                    Controller.g.layout();
                    CheckPane.controlPane();
                    NodePos.posX(NodeList.getRoot());
                    NodePos.posY(NodeList.getRoot());
                    Controller.draw();

                    // 更新TreeView
                    try {
                        MyTreeView.setTreeView();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // 关闭窗口
                stage.close();
            }
        });

        // 创建场景，并将AnchorPane设置为根节点
        Scene scene = new Scene(an);
        // 将场景设置到舞台上
        stage.setScene(scene);
        // 设置舞台标题和大小
        stage.setTitle("文本输入框");
        stage.setWidth(265);
        stage.setHeight(225);
        // 显示舞台
        stage.show();
    }
}
