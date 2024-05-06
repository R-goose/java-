package sample;

import Deque.Deque;
import Deque.NodeList;
//import DrawPane.DrawPane;
import DrawPane.*;
import Tree.TreeNode;
//import Tree.TreeUtil;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Controller {

    private static int id=1;

    public static int fontsize = 20;
    public static Group g = new Group();
    public static ScrollPane sp = new ScrollPane();
    public static TreeNode mouseNode = new TreeNode();
    public static int item = 1;


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
    private Button son;

    @FXML
    private Button brother;

    @FXML
    public  Pane drawPane=new Pane();

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
        if (NodeList.list.isEmpty()) {
            creatRoot();
            draw();
            try {
                MyTreeView.setTreeView();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            newNode.setDisable(true);
            son.setDisable(false);
            brother.setDisable(false);
        }
    }


    @FXML
    void deleteNode(ActionEvent event) {
        son.setDisable(true);
        brother.setDisable(true);
        deleteNode.setDisable(true);
        TreeNode node=mouseNode;
        deleteNode(node);
        if(NodeList.list.isEmpty()){
            newNode.setDisable(false);
            son.setDisable(true);
            brother.setDisable(true);
            deleteNode.setDisable(true);
        }
        SelectItem();
        posX(NodeList.getRoot());
        posY(NodeList.getRoot());
        draw();
    }


    @FXML
    void left_layout(ActionEvent event) {
        item = 2;
        SelectItem();
        posX(NodeList.getRoot());
        posY(NodeList.getRoot());
//        CheckPane.controlPane();
        draw();
    }


    @FXML
    void right_layout(ActionEvent event) {
        item = 3;
        SelectItem();
        posX(NodeList.getRoot());
        posY(NodeList.getRoot());
//        CheckPane.controlPane();
        draw();
    }


    @FXML
    void center_layout(ActionEvent event) {
        item = 1;
        SelectItem();
//        CheckPane.controlPane();
        posX(NodeList.getRoot());
        posY(NodeList.getRoot());
        draw();
    }


    @FXML
    void borderRadious(ActionEvent event) {

    }


    @FXML
    void fillingColor(ActionEvent event) {

    }


    //增加子节点
    @FXML
    void son(ActionEvent event) {
        System.out.println("增加子节点");
        TreeNode node=mouseNode;
        System.out.println(node.getNid());
        System.out.println(node.getPid());
        addNode(node);
        SelectItem();
        posX(NodeList.getRoot());
        posY((NodeList.getRoot()));
        draw();
    }


    //增加同级节点
    @FXML
    void brother(ActionEvent event) {
        System.out.println("增加同级节点");
        TreeNode node=mouseNode;
        TreeNode p=NodeList.getParent(node);
        addNode(p);
        SelectItem();
        posX(NodeList.getRoot());
        posY(NodeList.getRoot());
        draw();
    }


    @FXML
    void leftHide(ActionEvent event) {

    }


    @FXML
    void rightHide(ActionEvent event) {

    }


    /**
     * 初始化成员变量
     */

    public void initialize() {
//        draw();
        drawPane.getChildren().add(g);
        Click();
        doubleClick();
        if(NodeList.list.isEmpty()){
            son.setDisable(true);
            brother.setDisable(true);
            deleteNode.setDisable(true);
        }
    }


    /**
     * 绘制节点的方法
     */
    //绘图方法
    //在绘制面板上绘制结点和连线，处理结点的样式和布局
    public static void draw() {
        //清空群组中的所有子节点
        g.getChildren().clear();
        //将节点列表中的节点插入双端队列中
        Deque.insert(NodeList.list);

        for (int i = 0; i < NodeList.list.size(); i++) {
            TreeNode node = NodeList.list.get(i);   //获取节点
            node.setTxt(node.getTxt());           //设置节点文本

            //设置节点样式
            node.setStyle(
                    "-fx-background-color:#d6ecf0;" +
                            "-fx-background-radius:10;" +
                            "-fx-padding:10;"
            );
            node.setMaxSize(200, 80);
            node.setWrapText(true);
            node.setTextAlignment(TextAlignment.CENTER);
            node.setTextOverrun(OverrunStyle.ELLIPSIS);
            node.setTextFill(Color.BLACK);
            node.setFont(new Font("Arial", 20));


            if (node.isClick()) {
                node.setStyle(
                        "-fx-background-color:#faff72;" +
                                "-fx-background-radius:10;" +
                                "-fx-padding:10;"
                );
            }

            g.getChildren().add(node);   //将节点添加到Group中
            g.applyCss();                //确保更新样式
            g.layout();                  //确保更新节点的位置和大小

            node.setLayoutX(node.getLeft());     //设置节点的X坐标
            node.setLayoutY(node.getTop());      //设置节点的Y坐标

            //绘制节点之间的连线
            if (node != NodeList.getRoot()) {
                line(node);
            }

            //如果节点有图片路径，则添加图片到节点
            if (node.getImagPath() != null) {
                ImageView imageView = new ImageView(node.getImagPath());   //创建ImageView对象
                Label label = node;                                        //将节点转换为Label对象
                imageView.setFitWidth(100);  // 设置图片宽度
                imageView.setFitHeight(100);  // 设置图片高度
                label.setGraphic(imageView);  // 将图片添加到节点的图形属性中
                label.setContentDisplay(ContentDisplay.BOTTOM);  // 设置图片在节点下方显示
                label.setTextAlignment(TextAlignment.CENTER);  // 设置文本居中对齐
            }
        }
    }

    public static void line(TreeNode node) {
        //获取当前节点的父节点
        TreeNode p = NodeList.getParent(node);
        //创建三条连线对象
        Line line1 = new Line();
        Line line2 = new Line();
        Line line3 = new Line();
        //根据当前节点在父节点中的位置关系确认连线的起始点和终点
        if (node.getPos() == 1) {   //如果当前节点在父节点的左侧
            //第一条连线：从父节点右侧中间开始到当前节点左侧中间结束
            line1.startXProperty().bind(p.layoutXProperty().add(p.widthProperty()));
            line1.startYProperty().bind(p.layoutYProperty().add(p.heightProperty().divide(2)));
            line1.endXProperty().bind((p.layoutXProperty().add(p.widthProperty().add(node.layoutXProperty()))).divide(2));
            line1.endYProperty().bind(p.layoutYProperty().add(p.heightProperty().divide(2)));

            //第二条连线：从当前节点左侧中间开始到当前节点右侧中间结束
            line2.startXProperty().bind((p.layoutXProperty().add(p.widthProperty().add(node.layoutXProperty()))).divide(2));
            line2.startYProperty().bind(p.layoutYProperty().add(p.heightProperty().divide(2)));
            line2.endXProperty().bind((p.layoutXProperty().add(p.widthProperty().add(node.layoutXProperty()))).divide(2));
            line2.endYProperty().bind(node.layoutYProperty().add(node.heightProperty().divide(2)));

            //第三条连线：从当前节点右侧中间开始到当前节点右侧底部结束
            line3.startXProperty().bind((p.layoutXProperty().add(p.widthProperty().add(node.layoutXProperty()))).divide(2));
            line3.startYProperty().bind(node.layoutYProperty().add(node.heightProperty().divide(2)));
            line3.endXProperty().bind(node.layoutXProperty());
            line3.endYProperty().bind(node.layoutYProperty().add(node.heightProperty().divide(2)));
        } else { //如果当前节点在父节点的右侧
            //第一条连线：从当前节点右侧中间开始到当前节点左侧中间结束
            line1.startXProperty().bind(node.layoutXProperty().add(node.widthProperty()));
            line1.startYProperty().bind(node.layoutYProperty().add(node.heightProperty().divide(2)));
            line1.endXProperty().bind((node.layoutXProperty().add(node.widthProperty().add(p.layoutXProperty()))).divide(2));
            line1.endYProperty().bind(node.layoutYProperty().add(node.heightProperty().divide(2)));

            //第二条连线：从当前节点右侧中间开始到当前接待你左侧中间结束
            line2.startXProperty().bind((node.layoutXProperty().add(node.widthProperty().add(p.layoutXProperty()))).divide(2));
            line2.startYProperty().bind(node.layoutYProperty().add(node.heightProperty().divide(2)));
            line2.endXProperty().bind((node.layoutXProperty().add(node.widthProperty().add(p.layoutXProperty()))).divide(2));
            line2.endYProperty().bind(p.layoutYProperty().add(p.heightProperty().divide(2)));

            //第三条连线：从当前接待你左侧中间开始到父节点左侧底部结束
            line3.startXProperty().bind((node.layoutXProperty().add(node.widthProperty().add(p.layoutXProperty()))).divide(2));
            line3.startYProperty().bind(p.layoutYProperty().add(p.heightProperty().divide(2)));
            line3.endXProperty().bind(p.layoutXProperty());
            line3.endYProperty().bind(p.layoutYProperty().add(p.heightProperty().divide(2)));
        }

        //设置连线的颜色和宽度
        line1.setStroke(Color.web("#d6ecf0"));
        line2.setStroke(Color.web("#d6ecf0"));
        line3.setStroke(Color.web("#d6ecf0"));
        line1.setStrokeWidth(4);
        line2.setStrokeWidth(4);
        line3.setStrokeWidth(4);

        //将连线添加到绘图面板中
        g.getChildren().addAll(line1, line2, line3);
    }

    //重新绘制整个图形界面
    public static void redraw() {
        //清空绘图面板中的所有节点和连线
        g.getChildren().clear();

        //遍历节点列表
        for (int i = 0; i < NodeList.list.size(); i++) {
            //获取当前节点
            TreeNode node = NodeList.list.get(i);

            //设置节点大小、换行、文本对齐方式等属性
            node.setText(node.getTxt());
            node.setStyle(
                    "-fx-background-color:#d6ecf0;" +
                            "-fx-background-radius:10;" +
                            "-fx-padding:10;"
            );

            //设置节点大小、换行、文本对齐方式等属性
            node.setMaxSize(200, 80);
            node.setWrapText(true);
            node.setTextAlignment(TextAlignment.CENTER);
            node.setTextOverrun(OverrunStyle.ELLIPSIS);
            node.setTextFill(Color.BLACK);
            node.setFont(new Font("Arial", 20));

            //将节点添加到绘图面板中
            g.getChildren().add(node);
            g.applyCss();
            g.layout();

            //设置节点的布局位置为预先计算好的位置
            node.setLayoutX(node.getLeft());
            node.setLayoutY(node.getTop());

            //如果当前节点不是根节点，则会之与父节点的连线
            if (node != NodeList.getRoot()) {
                line(node);
            }

            //如果当前节点有图片路径，则添加图片到节点中
            if (node.getImagPath() != null) {
                ImageView imageView = new ImageView(node.getImagPath());
                Label label = node;
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);
                label.setGraphic(imageView);
                label.setContentDisplay(ContentDisplay.BOTTOM);
                label.setTextAlignment(TextAlignment.CENTER);
            }

        }
    }

    /**
     * 用于计算节点所在的位置
     */

    public static int marginX = 50;   //表示节点在X轴上的边距
    public static int marginY = 20;   //表示节点在Y轴上的边距

    //用于计算节点在X轴上的位置
    public void posX(TreeNode node) {
        //判断是否为根节点，如果不是根节点
        //则获取其父节点并根据节点在父节点中的位置确认当前节点在X轴上的位置
        if (node.getPid() != 0) {
            TreeNode p = NodeList.getParent(node);
            if (node.getPos() == 1) {
                node.setLeft(p.getLeft() + p.getWidth() + marginX);
            } else {
                node.setLeft(p.getLeft() - node.getWidth() - marginX);
            }
        } else {
            //如果是根节点，
            //则将当前节点的左边界位置设置为画板宽度的一半减去当前节点宽度的一半，即居中显示
            node.setLeft(drawPane.getWidth() / 2 - node.getWidth() / 2);
        }
        //对当前节点的子节点进行递归调用，计算他们在X轴上的位置
        for (int i = 0; i < node.getNodeChildren().size(); i++) {
            posX(node.getNodeChildren().get(i));
        }
    }

    //计算节点在Y轴上的位置
    public void posY(TreeNode node) {
        if (node.getPid() != 0) { //判断是否为根节点
            TreeNode p = NodeList.getParent(node); //获取父节点
            //第一个孩子
            if (NodeList.isFirstChild(node)) { //判断是否为父节点的第一个孩子
                double pchildHeight = NodeList.getChildHeight(p, node.getPos());    //获取父节点和当前父节点中的高度
                double childHeight = NodeList.getChildHeight(node, node.getPos()); //获取当前节点在父节点中的高度
                System.out.println("p1+" + pchildHeight);
                System.out.println("c2+" + childHeight);
                //如果当前节点是父节点的最后一个孩子，则将其上边界设置为父节点中向上偏移当前节点高度一半
                if (node.getNid() == p.getNodeChildren().get(p.getNodeChildren().size() - 1).getNid()) {
                    node.setTop(p.getTop() + p.getHeight() / 2 - node.getHeight() / 2);
                } else { //否则，计算当前节点上边界位置，时期处于父节点和前一个孩子节点之间
                    node.setTop(p.getTop() + p.getHeight() / 2 - (pchildHeight - childHeight + node.getHeight()) / 2);
                }
            } else {//剩下的孩子
                //获取前一个孩子节点
                TreeNode preChild = NodeList.getPreChild(node);
                //计算前一个孩子节点和当前节点在父节点中的高度
                double preChildHeight = NodeList.getChildHeight(preChild, node.getPos());
                double childHeight = NodeList.getChildHeight(node, node.getPos());
                //计算当前节点上边界位置，使其处于前一个孩子节点下方
                node.setTop(preChild.getTop() + preChild.getHeight() / 2 + (preChildHeight + childHeight - node.getHeight() / 2 + marginY));
            }
        } else { //设置根节点的位置
            //将根节点的上边界位置设置为花瓣高度的一般减去根节点高度的一般，即垂直居中显示
            node.setTop(drawPane.getHeight() / 2 - node.getHeight() / 2);
        }
        //对当前节点的子节点进行递归调用，计算他们在Y轴上的位置
        for (int i = 0; i < node.getNodeChildren().size(); i++) {
            posY(node.getNodeChildren().get(i));
        }
    }

//    class MouseClick {
//        public static TreeNode node = new TreeNode();
//        //鼠标双击事件：监听节点，双击出现文本输入框，编辑节点文字内容

    /**
     * 鼠标双击出现文本框
     * 并进行相应的编辑
     */
    public void doubleClick() {
        drawPane.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    double x = event.getSceneX();
                    double y = event.getSceneY();
//				     System.out.println(x+","+y);
                    for (int i = 0; i < NodeList.list.size(); i++) {
                        TreeNode node = new TreeNode();
                        node = NodeList.list.get(i);
                        Bounds b = node.getLayoutBounds();
                        Bounds bd = node.localToScene(b);
                        //	node.setClick(false);
//							System.out.println(node.getLeft()+","+node.getWidth());
//							System.out.println(node.getTop()+","+node.getHeight());
                        if (bd.getMinX() <= x && bd.getMaxX() >= x && bd.getMinY() <= y && bd.getMaxY() >= y
//									node.getLeft()<= x&& node.getLeft()+node.getWidth()>=x
//							     &&node.getTop()<=y && node.getTop()+node.getHeight()>=y
                        ) {
                            //	node.setClick(true);
                            showTextArea(node);
                        }
                        //	MyDrawPane.draw(); 另起方法画框
                    }
                }
            }
        });
    }



        //单击事件：监控节点和按钮，进行相应操作
        public  void Click() {
            drawPane.setOnMouseClicked(event -> {
                if(!NodeList.list.isEmpty()) {
                    newNode.setDisable(true);
                    son.setDisable(true);
                    brother.setDisable(true);
                    deleteNode.setDisable(true);
//                    MyButtonBar.b4.setDisable(true);
//                    MyButtonBar.b6.setDisable(true);
//                    MyButtonBar.b7.setDisable(true);
                }

//			System.out.println("isclick");
//			 double x1 = event.getX()
//		     double y1 = event.getY();
                double x = event.getSceneX();
                double y = event.getSceneY();

//		    System.out.printf("%f,%f:\n",x1,y1);
                for (int i = 0; i < NodeList.list.size(); i++) {
                    TreeNode n = new TreeNode();
                    n = NodeList.list.get(i);
                    n.setClick(false);
                    Bounds b = n.getLayoutBounds();
                    Bounds bd = n.localToScene(b);
                    n.setStyle(
                            "-fx-background-color:#d6ecf0;" +
                                    "-fx-background-radius:10;" +
                                    "-fx-padding:10;"
                    );
                    //System.out.printf("%f,%f:\n",bd.getWidth(),bd.getHeight());
//					System.out.printf("%f,%f:\n",bd.getMinX(),bd.getMaxX());
                    if (
                            bd.getMinX() <= x && bd.getMaxX() >= x && bd.getMinY() <= y && bd.getMaxY() >= y
//							n.getLeft()<= x&& n.getLeft()+n.getWidth()>=x
//					     &&n.getTop()<=y && n.getTop()+n.getHeight()>=y
                    ) {
                        n.setClick(true);
                        mouseNode= n;
                        n.setStyle(
                                "-fx-background-color:#faff72;" +
                                        "-fx-background-radius:10;" +
                                        "-fx-padding:10;"
                        );
//                        if(node.getImgPath()!=null) {
//                            MyButtonBar.b7.setDisable(false);
//                            MyButtonBar.b6.setDisable(true);
//                        }
                        newNode.setDisable(true);
                        son.setDisable(false);
                        deleteNode.setDisable(false);
//                        MyButtonBar.b5.setDisable(false);
//                        MyButtonBar.b6.setDisable(false);
                       if(mouseNode != NodeList.getRoot()) {
                           brother.setDisable(false);
                       }else
                       {
                           brother.setDisable(true);
                       }
                       if(!NodeList.list.isEmpty()){
                           newNode.setDisable(true);
                       }else{
                           newNode.setDisable(false);
                       }
//                        else MyButtonBar.b3.setDisable(true);
                    }
                }
                // MyDrawPane.draw();  //另起方法画框
            });

    }
        //鼠标双击事件：监听节点，双击出现文本输入框，编辑节点文字内容


// 显示带有文本框的窗口，编辑节点相关内容，重新绘制相关视图

        // 显示文本输入框窗口方法

        /**
         * 显示文本框输入口的输入方法
         *
         * @param node
         */
        public void showTextArea(TreeNode node) {
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
                        posX(NodeList.getRoot());
                        posY(NodeList.getRoot());
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


        /**
         * 管理节点的工具类
         */

        public void creatRoot() {
            //创建根节点对象，并设置唯一标识和标题
            TreeNode node = new TreeNode(0, "双击输入");
            //设置节点的唯一标识id并自增
            node.setNid(id++);
            System.out.println("createNode");
            System.out.println(id);
            //将根节点添加到绘图面板中，以便显示在页面上
            Controller.g.getChildren().add(node);
            //应用CSS样式到该对象及其子节点
            Controller.g.applyCss();
            //重新计算和布局对象及其子节点的位置和大小
            Controller.g.layout();

            //将根节点添加到节点列表中，永固管理所有节点
            NodeList.list.add(node);
            //控制面板更新，以便在界面上反映节点的变化
            //CheckPane.controlPane();
            node.setLeft(drawPane.getWidth() / 2 - node.getWidth() / 2);
            node.setTop(drawPane.getHeight() / 2 - node.getHeight() / 2);
        }

    public static void addNode(TreeNode p) {
        TreeNode node = new TreeNode(p.getNid(),"分支主题");
        node.setNid(id++);
        NodeList.list.add(node);
        p.getNodeChildren().add(node);
        g.getChildren().add(node);
        g.applyCss();
        g.layout();
        if(p.getPos()==1) {
            node.setLeft(p.getLeft() + p.getWidth() + marginX);
        }else{
            node.setLeft(p.getLeft() - node.getWidth() - marginX);
        }
    }

    public static void deleteNode(TreeNode node) {
        //删除node父亲孩子list中的的结点
        //System.out.println(888);
        TreeNode p = NodeList.getParent(node);
        System.out.println(p.getNodeChildren().size());
        for(int i = 0;i < p.getNodeChildren().size();i++) {
            if(node.getNid() == p.getNodeChildren().get(i).getNid()) {
                p.getNodeChildren().remove(i);
                break;
            }
        }
        System.out.println(p.getNodeChildren().size());
        deleteChildren(node);

    }

    private static void deleteChildren(TreeNode node) {
        for(int i = 0;i < NodeList.list.size();i++) {
            if(NodeList.list.get(i).getNid() == node.getNid()) {
                NodeList.list.remove(i);
            }
        }
        //删除list中node所有的子结点
        if(!node.getNodeChildren().isEmpty()) {
            for(int i = 0;i < node.getNodeChildren().size(); i++) {
                deleteChildren(node.getNodeChildren().get(i));
            }
            //System.out.println("孩子不空");
        }
    }


/**
 * 关于布局
 * 居中，左，右布局
 */
public static void SelectItem() {
    if(item==1) {
        for(int i = 0; i<NodeList.list.size();i++) {
            TreeNode node = new TreeNode();
            node = NodeList.list.get(i);
            //System.out.println("ChildHeight:"+NodeList.getChildHeight(node, node.getPos()));
            if(NodeList.getParent(node)==NodeList.getRoot()) {
                Balance();
            }
            else {
                node.setPos(NodeList.getParent(node).getPos());
            }
        }
    }
    if(item==2) {
        for(int i = 0; i<NodeList.list.size();i++) {
            TreeNode node = new TreeNode();
            node = NodeList.list.get(i);
            node.setPos(0);
        }
    }
    if(item==3) {
        for(int i = 0; i<NodeList.list.size();i++) {
            TreeNode node = new TreeNode();
            node = NodeList.list.get(i);
            node.setPos(1);
        }
    }
}

    //节点平衡逻辑
    public static void Balance() {
        List<TreeNode> node = NodeList.getRoot().getNodeChildren();
        int length = node.size();
        double sum = 0;
        int max = 0;
        double[] height = new double[length];
//		 int[] left = new int[length/2+1];
        for(int i = 0;i < length;i++) {
            height[i] = NodeList.getChildHeight(node.get(i), node.get(i).getPos());
            if(height[i] > height[max])
                max = i;
            sum+=height[i];
        }
//		 left[0] = max;
        node.get(max).setPos(0);
        double nowsum = height[max];
        for(int i = 0;i < length;i++) {
            if(i!=max) {
                if(height[i]+nowsum<=sum/2+42) {
                    node.get(i).setPos(0);
                    nowsum+=height[i];
                }
                else node.get(i).setPos(1);
            }
        }
    }

}