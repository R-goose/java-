package DrawPane;

import Deque.*;
import Tree.*;
import javafx.scene.Group;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.layout.Pane;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import java.awt.*;

//实现绘制节点和连线的功能
public class DrawPane extends ScrollPane {
    public static int fontsize=20;
    public static Pane drawPane =new Pane();        //用于绘制节点和连线，绘图面板
    public static ScrollPane sp=new ScrollPane();   //用于实现滚动功能，滚动面板
    public static Group g=new Group();              //一个Group对象，用于添加节点到其中，并进行相应的管理，用于组织节点和连线的群组

    //创建一个可以滚动的窗口
    public DrawPane(){
        super(drawPane);   //调用父类构造方法，传入绘图面板
        drawPane.setMinWidth(1500);   //设置绘图面板的最小宽度
        drawPane.setMinHeight(1000);  //设置绘图面板的最小高度
        sp=this;  //将当前滚动面板赋值给变量sp
//        draw();
        this.setVvalue(0.5);             //垂直滚动条位置
        this.setHvalue(0.5);             //水平滚动条位置
        drawPane.getChildren().add(g);   //将绘制内容添加到绘制图版
    }

    //绘图方法
    //在绘制面板上绘制结点和连线，处理结点的样式和布局
    public static void draw(Pane drawPane){
        //清空群组中的所有子节点
        DrawPane.g.getChildren().clear();
        //将节点列表中的节点插入双端队列中
        Deque.insert(NodeList.list);

        for(int i=0;i<NodeList.list.size();i++){
            TreeNode node=NodeList.list.get(i);   //获取节点
            node.setTxt(node.getTxt());           //设置节点文本

            //设置节点样式
            node.setStyle(
                     "-fx-background-color:#d6ecf0;"+
                     "-fx-background-radius:10;"+
                     "-fx-padding:10;"
            );
            node.setMaxSize(200, 80);
            node.setWrapText(true);
            node.setTextAlignment(TextAlignment.CENTER);
            node.setTextOverrun(OverrunStyle.ELLIPSIS);
            node.setTextFill(Color.BLACK);
            node.setFont(new Font("Arial",20));


            if(node.isClick()) {
                node.setStyle(
                        "-fx-background-color:#faff72;"	+
                        "-fx-background-radius:10;"+
                        "-fx-padding:10;"
                );
            }

            DrawPane.g.getChildren().add(node);   //将节点添加到Group中
            DrawPane.g.applyCss();                //确保更新样式
            DrawPane.g.layout();                  //确保更新节点的位置和大小

            node.setLayoutX(node.getLeft());     //设置节点的X坐标
            node.setLayoutY(node.getTop());      //设置节点的Y坐标

            //绘制节点之间的连线
            if(node!=NodeList.getRoot()){
                line(node);
            }

            //如果节点有图片路径，则添加图片到节点
            if(node.getImagPath()!=null){
                ImageView imageView=new ImageView(node.getImagPath());   //创建ImageView对象
                Label label=node;                                        //将节点转换为Label对象
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
        if(node.getPos()==1) {   //如果当前节点在父节点的左侧
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
        }
        else { //如果当前节点在父节点的右侧
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
        DrawPane.g.getChildren().addAll(line1,line2,line3);
    }

    //重新绘制整个图形界面
    public static void redraw() {
        //清空绘图面板中的所有节点和连线
        DrawPane.g.getChildren().clear();

        //遍历节点列表
        for(int i = 0; i<NodeList.list.size();i++) {
            //获取当前节点
            TreeNode node = NodeList.list.get(i);

            //设置节点大小、换行、文本对齐方式等属性
            node.setText(node.getTxt());
            node.setStyle(
                    "-fx-background-color:#d6ecf0;"	+
                            "-fx-background-radius:10;"+
                            "-fx-padding:10;"
            );

            //设置节点大小、换行、文本对齐方式等属性
            node.setMaxSize(200, 80);
            node.setWrapText(true);
            node.setTextAlignment(TextAlignment.CENTER);
            node.setTextOverrun(OverrunStyle.ELLIPSIS);
            node.setTextFill(Color.BLACK);
            node.setFont(new Font("Arial",20));

            //将节点添加到绘图面板中
            DrawPane.g.getChildren().add(node);
            DrawPane.g.applyCss();
            DrawPane.g.layout();

            //设置节点的布局位置为预先计算好的位置
            node.setLayoutX(node.getLeft());
            node.setLayoutY(node.getTop());

            //如果当前节点不是根节点，则会之与父节点的连线
            if(node != NodeList.getRoot()) {
                line(node);
            }

            //如果当前节点有图片路径，则添加图片到节点中
            if(node.getImagPath()!=null) {
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
}
