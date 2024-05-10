package sample;

import Deque.*;
import DrawPane.*;
import Deque.NodeList;;
import DrawPane.*;
import Tree.*;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;


public class Controller {

    public static Group g = new Group();
    public static TreeNode mouseNode = new TreeNode();
    public static TreeNode rootNode = new TreeNode();
    public static int item = 1;

    @FXML
    private Button fillingColor;

    @FXML
    private Button undo;

    @FXML
    private Button deleteNode;

    @FXML
    private Button openOne;

    @FXML
    private Button center_layout;

    @FXML
    private Button rightHide;


    @FXML
    private Button left_layout;

    @FXML
    private Button leftHide;


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
    public  Pane drawPane;

    @FXML
    public AnchorPane ap;

    @FXML
    public ScrollPane sp;

    @FXML
    private Button fontSize;

    @FXML
    private Text text;

    @FXML
    private Button fontColor;

    private static Controller controller;
    public SerializableNode serializableNode;
    private File openedFile;//当前保存的思维导图目标文件，下次保存直接覆盖该文件

    //记录鼠标按下时的坐标
    private double offsetX;
    private double offsetY;

    public static Rectangle selectRect = new Rectangle();

    @FXML
    private Pane basePane;
    static int num=0;
    //按钮：新建页面
    @FXML
    void GetNewOne(ActionEvent event) throws Exception{
        NodeList.list.clear();
        CheckPane.controlPane(drawPane,sp);
        TreeUtil.deleteNode(rootNode);
        draw();
        GetNewOne.setDisable(true);
        // 获取当前的 Stage
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        // 创建一个新的 Stage
        Stage newStage = new Stage();
        // 加载新的FXML文件
        Parent root = FXMLLoader.load(getClass().getResource("fxml/sample.fxml"));
        Scene scene = new Scene(root, 1260, 840);
        // 修改新的 Stage 的属性
        titleBarController.setStage(newStage);
        newStage.setScene(scene);
        newStage.show();
    }

    //按钮：打开
    @FXML
    void openOne(ActionEvent event) {
        openOne.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                Stage stage=new Stage();
                FileChooser fc=new FileChooser();

                fc.setTitle("打开文件");
                fc.setInitialDirectory(new File(System.getProperty("user.home")));
                fc.getExtensionFilters().addAll(
                        //new FileChooser.ExtensionFilter("所有","*.*"),
                        new FileChooser.ExtensionFilter("LinkMind", "*.LinkMind")
                );
                File file=fc.showOpenDialog(stage);
                if(file==null) return;
                System.out.println(file.getAbsolutePath());
                ReadTree.read(file,Main.main_stage);

                Controller.redraw();
                CheckPane.controlPane(drawPane,sp);
                Controller.SelectItem();
                posX(NodeList.getRoot(),drawPane);
                posY(NodeList.getRoot(),drawPane);

                Controller.draw();
                try {
                    setTreeView(ap);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }
    //按钮：撤销，返回上一步
    @FXML
    void undo(ActionEvent event) {
        List<TreeNode> list = Deque.undo();
        if(list!=null) {
            NodeList.list = list;
            try {
                setTreeView(ap);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            redraw();
            CheckPane.controlPane(drawPane,sp);
        }
        son.setDisable(true);
        brother.setDisable(true);
        if(NodeList.list.isEmpty()){
            newNode.setDisable(false);
            undo.setDisable(true);
        }
    }

    //按钮：保存
    @FXML
    void saving(ActionEvent event) {
        saving.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                if(NodeList.list.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("注意");
                    alert.setHeaderText(null);
                    alert.setContentText("你还没有绘制思维导图");
                    alert.show( );
                    return;
                }
                for(int i = 0;i<NodeList.list.size();i++) {
                    TreeNode node = new TreeNode();
                    node = NodeList.list.get(i);
                    node.setClick(false);
                }
                Stage stage=new Stage();
                FileChooser fc=new FileChooser();
                fc.setTitle("保存文件");
                fc.setInitialFileName("思维导图");
                text.setText(SetTitle.showTitle());
                fc.setInitialDirectory(new File(System.getProperty("user.home")));
                fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("LinkMind","*.LinkMind"));
                File file=fc.showSaveDialog(stage);
                if(file==null) return;
                System.out.println(file.getAbsolutePath());
                WriteTree.write(file,text);
                stage.setTitle("思维导图");
            }
        });
    }


//按钮：导出
    @FXML
    void deriving(ActionEvent event) {
        deriving.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                if(NodeList.list.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("注意");
                    alert.setHeaderText(null);
                    alert.setContentText("当前思维导图画板为空！");
                    alert.show( );
                    return;
                }
                Stage stage=new Stage();
                FileChooser fc=new FileChooser();
                fc.setTitle("保存为png图片或jpg图片");
                fc.setInitialFileName("思维导图");
                fc.setInitialDirectory(new File(System.getProperty("user.home")));
                fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG","*.png"));
                fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG","*.jpg"));
                File file=fc.showSaveDialog(stage);
                if(file==null) return;
                try {
                    Zoom.recover(drawPane,sp,ap);
                    DeriveGraph.deriveGraph(file,drawPane);
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println(NodeList.list);
    }

    //按钮：新增节点
    @FXML
    void newNode(ActionEvent event) {
        if (NodeList.list.isEmpty()) {
            rootNode = TreeUtil.creatRoot(drawPane,sp);
            CheckPane.controlPane(drawPane,sp);
            draw();
            try {
                setTreeView(ap);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            newNode.setDisable(true);
            son.setDisable(true);
            brother.setDisable(true);
            undo.setDisable(false);
        }
    }

    @FXML
    void deleteNode(ActionEvent event) {
        son.setDisable(true);
        brother.setDisable(true);
        deleteNode.setDisable(true);
        TreeNode node=mouseNode;
        TreeUtil.deleteNode(node);
        if(NodeList.list.isEmpty()){
            newNode.setDisable(false);
            son.setDisable(true);
            brother.setDisable(true);
            deleteNode.setDisable(true);
            undo.setDisable(true);
        }
        SelectItem();
        CheckPane.controlPane(drawPane,sp);
        posX(NodeList.getRoot(),drawPane);
        posY(NodeList.getRoot(),drawPane);
        try {
            setTreeView(ap);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        draw();
    }

    @FXML
    void left_layout(ActionEvent event) {
        item = 2;
        SelectItem();
        posX(NodeList.getRoot(),drawPane);
        posY(NodeList.getRoot(),drawPane);
        CheckPane.controlPane(drawPane,sp);
        draw();
    }

    @FXML
    void right_layout(ActionEvent event) {
        item = 3;
        SelectItem();
        posX(NodeList.getRoot(),drawPane);
        posY(NodeList.getRoot(),drawPane);
        CheckPane.controlPane(drawPane,sp);
        draw();
    }

    @FXML
    void center_layout(ActionEvent event) {
        item = 1;
        SelectItem();
        CheckPane.controlPane(drawPane,sp);
        posX(NodeList.getRoot(),drawPane);
        posY(NodeList.getRoot(),drawPane);
        draw();
    }

    @FXML
    void son(ActionEvent event) {
        System.out.println("增加子节点");
        TreeNode node=mouseNode;
//        System.out.println(node.getNid());
//        System.out.println(node.getPid());
        TreeUtil.addNode(node);
        SelectItem();
        CheckPane.controlPane(drawPane,sp);
        posX(NodeList.getRoot(),drawPane);
        posY((NodeList.getRoot()),drawPane);
        draw();
        try {
            setTreeView(ap);
        } catch (Exception e1) {
            e1.printStackTrace();
        }


    }

    //增加同级节点
    @FXML
    void brother(ActionEvent event) {
        System.out.println("增加同级节点");
        TreeNode node=mouseNode;
        TreeNode p=NodeList.getParent(node);
        TreeUtil.addNode(p);
        SelectItem();
        CheckPane.controlPane(drawPane,sp);
        posX(NodeList.getRoot(),drawPane);
        posY(NodeList.getRoot(),drawPane);
        try {
            setTreeView(ap);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        draw();
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

    public void initialize() {
        drawPane.setOnKeyPressed(event -> {
            if (event.isControlDown()) {
                if(event.getCode() == KeyCode.EQUALS||event.getCode() == KeyCode.PLUS) {
                    Zoom.enlarge(drawPane,ap);
                }
                if(event.getCode() == KeyCode.MINUS) {
                    Zoom.reduce(drawPane,ap);
                }
                if(event.getCode() == KeyCode.DIGIT0) {
                    Zoom.recover(drawPane,sp,ap);
                }
            }
        });
        sp.setContent(drawPane);
        drawPane.setMinWidth(1500);
        drawPane.setMinHeight(1000);
        draw();
        sp.setHvalue(0.5);
        sp.setVvalue(0.5);
        drawPane.getChildren().add(g);
        Click();
        doubleClick();
        Zoom.zoom(drawPane,ap);
        if(NodeList.list.isEmpty()){
            son.setDisable(true);
            brother.setDisable(true);
            deleteNode.setDisable(true);
            undo.setDisable(true);
        }
    }

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
            System.out.println("调用了line方法");
            line1.startXProperty().bind(p.layoutXProperty().add(p.widthProperty()));
            System.out.println("line+"+p.layoutXProperty());
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
            System.out.println("调用了line方法");
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
        line1.setStrokeWidth(2);
        line2.setStrokeWidth(2);
        line3.setStrokeWidth(2);

        //将连线添加到绘图面板中
        g.getChildren().addAll(line1, line2, line3);
    }

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

    public static int marginX = 50;   //表示节点在X轴上的边距
    public static int marginY = 20;   //表示节点在Y轴上的边距

    //用于计算节点在X轴上的位置
    public static void posX(TreeNode node,Pane drawPane) {
        //判断是否为根节点，如果不是根节点
        //则获取其父节点并根据节点在父节点中的位置确认当前节点在X轴上的位置
        if (node.getPid() != 0) {
            TreeNode p = new TreeNode();
            p=NodeList.getParent(node);
            if (node.getPos() == 1) {
                node.setLeft(p.getLeft() + p.getWidth() + marginX);
            } else {
                node.setLeft(p.getLeft() - node.getWidth() - marginX);
            }
        } else {
            node.setLeft(drawPane.getWidth() / 2 - node.getWidth() / 2);
        }
        //对当前节点的子节点进行递归调用，计算他们在X轴上的位置
        for (int i = 0; i < node.getNodeChildren().size(); i++) {
            posX(node.getNodeChildren().get(i),drawPane);
        }
    }

    //计算节点在Y轴上的位置
    public static void posY(TreeNode node,Pane drawPane) {
        if (node.getPid() != 0) { //判断是否为根节点
            TreeNode p = NodeList.getParent(node); //获取父节点
            //第一个孩子
            if (NodeList.isFirstChild(node)) { //判断是否为父节点的第一个孩子
                double pchildHeight = NodeList.getChildHeight(p, node.getPos());    //获取父节点和当前父节点中的高度
                double childHeight = NodeList.getChildHeight(node, node.getPos()); //获取当前节点在父节点中的高度
                System.out.println("p1+" + pchildHeight);
                System.out.println("c2+" + childHeight);
                if (node.getNid() == p.getNodeChildren().get(p.getNodeChildren().size() - 1).getNid()) {
                    node.setTop(p.getTop() + p.getHeight() / 2 - node.getHeight() / 2);
                } else {
                    node.setTop(p.getTop() + p.getHeight() / 2 - (pchildHeight - childHeight + node.getHeight()) / 2);
                }
            } else {
                TreeNode preChild = NodeList.getPreChild(node);
                double preChildHeight = NodeList.getChildHeight(preChild, node.getPos());
                double childHeight = NodeList.getChildHeight(node, node.getPos());
                node.setTop(preChild.getTop() + preChild.getHeight() / 2 + (preChildHeight + childHeight - node.getHeight() )/ 2 + marginY);
            }
        } else {
            node.setTop(drawPane.getHeight() / 2 - node.getHeight() / 2);
        }
        for (int i = 0; i < node.getNodeChildren().size(); i++) {
            posY(node.getNodeChildren().get(i),drawPane);
        }
    }

    public void doubleClick() {
        drawPane.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    double x = event.getSceneX();
                    double y = event.getSceneY();
                    for (int i = 0; i < NodeList.list.size(); i++) {
                        TreeNode node = new TreeNode();
                        node = NodeList.list.get(i);
                        Bounds b = node.getLayoutBounds();
                        Bounds bd = node.localToScene(b);
                        if (bd.getMinX() <= x && bd.getMaxX() >= x && bd.getMinY() <= y && bd.getMaxY() >= y) {
                            showTextArea(node);
                        }
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
                undo.setDisable(false);
            }
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
                    CheckPane.controlPane(drawPane,sp);
                    posX(NodeList.getRoot(),drawPane);
                    posY(NodeList.getRoot(),drawPane);
                    draw();

                    // 更新TreeView
                    try {
                         setTreeView(ap);
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

    @FXML
    void leftHide(ActionEvent event) {
        leftHide.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {
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

    @FXML
    void rightHide(ActionEvent event) {
        rightHide.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {
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

    public static Controller getInstance(Pane drawPane){
        if (drawPane==null) {
            return null;
        }
        return controller;
    }

    public static void setTreeView(AnchorPane ap) throws IOException {
        // 创建 TreeView 实例
        TreeView<Label> treeview = new TreeView<Label>();

        // 获取根节点
        TreeNode node = NodeList.getRoot();

        // 创建根节点的标签
        Label label = new Label(node.getTxt());
        label.setMaxWidth(150); // 设置最大宽度
        label.setPrefWidth(100); // 设置首选宽度
        label.setTextOverrun(OverrunStyle.ELLIPSIS); // 设置文本溢出样式
        // 创建根 TreeItem
        TreeItem<Label> root = new TreeItem<Label>(label);
        treeview.setRoot(root); // 设置根节点

        label.setTextOverrun(OverrunStyle.CENTER_WORD_ELLIPSIS); // 设置文本溢出样式为居中单词省略
        walk(root, node); // 递归构建树形视图
        if (NodeList.list.isEmpty()) {
            root = null; // 若节点列表为空，则将根节点置为 null
            treeview.setRoot(root); // 设置根节点
        }

        // 创建标签提示
        Tooltip tooltip = new Tooltip(node.getText());
        Tooltip.install(label, tooltip);

        // 设置 TreeView 的宽度和最小高度
        treeview.setPrefWidth(200);
        treeview.setMinHeight(620);
        treeview.setStyle("-fx-background-color:#f9f9f9");
        ap.getChildren().addAll(treeview); // 将 TreeView 添加到 AnchorPane 中
    }

    // 递归构建树形视图方法
    public static void walk(TreeItem<Label> root, TreeNode node) {
        for (int i = 0; i < node.getNodeChildren().size(); i++) {
            TreeNode childnode = node.getNodeChildren().get(i);
            Label label = new Label(childnode.getTxt());
            label.setPrefWidth(100); // 设置标签宽度
            label.setPrefHeight(30); // 设置标签高度
            label.setTextOverrun(OverrunStyle.CENTER_WORD_ELLIPSIS); // 设置文本溢出样式为居中单词省略
            if (childnode.getImagPath() != null) {
                label.setText(childnode.getTxt() + "\n" + "(图片)");
                label.setTextAlignment(TextAlignment.CENTER); // 设置文本居中对齐
            }
            Tooltip tooltip = new Tooltip(childnode.getText());
            Tooltip.install(label, tooltip); // 设置标签提示
            TreeItem<Label> child = new TreeItem<Label>(label);
            root.getChildren().add(child); // 将子 TreeItem 添加到父节点
            root.setExpanded(true); // 展开父节点
            walk(child, childnode); // 递归构建树形视图
        }
    }


    /**
     * 键盘操作按钮
     */





}













