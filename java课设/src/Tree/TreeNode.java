package Tree;

import javafx.scene.control.OverrunStyle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;
//用来创建树节点

public class TreeNode extends TreeData{
    private static final long serialVersionUID =1L;
    private int pid;                 //表示节点的父节点的id
    private int nid;                 //表示当前节点的唯一标识id
    private int pos=1;               //节点的位置，在左边还是在右边
    private double left=0;           //节点在界面上的左侧位置
    private double top=0;            //节点在界面上的顶部位置
    private boolean click=false;     //表示当前节点是否被选中
    private List<TreeNode> nodeChildren =new ArrayList<>();   //存储节点的子节点列表

    public TreeNode(int pid,String text){
        //将传入的文本设置为节点的内容
        super(text);
        //将传入的父节点赋值给当前节点的pid属性
        this.pid=pid;
        //设置节点的样式
        this.setStyle(
                "-fx-background-color:#FAEBD7;"	+
                        "-fx-background-radius:10;"+
                        "-fx-padding:10;"
        );
        //设置节点的最大尺寸
        this.setMaxSize(200, 80);
        //允许文本自动换行
        this.setWrapText(true);
        //设置文本对齐方式为居中
        this.setTextAlignment(TextAlignment.CENTER);
        //设置文本溢出样式为省略号
        this.setTextOverrun(OverrunStyle.ELLIPSIS);
        //设置文本颜色为白色
        this.setTextFill(Color.WHITE);
        //设置文本字体与大小
        this.setFont(new Font("Arial",20));
    }

    //无参构造方法，用于创建节点对象
    public TreeNode(){
        super();
        this.setStyle(
                "-fx-background-color:#FAEBD7;"+
                        "-fx-background-radius:10;"+
                        "-fx-padding:10;"
        );
        //设置节点的最大尺寸
        this.setMaxSize(200, 80);
        //允许文本自动换行
        this.setWrapText(true);
        //设置文本对齐方式为居中
        this.setTextAlignment(TextAlignment.CENTER);
        //设置文本溢出样式为省略号
        this.setTextOverrun(OverrunStyle.ELLIPSIS);
        //设置文本颜色为白色
        this.setTextFill(Color.WHITE);
        //设置文本字体与大小
        this.setFont(new Font("Arial",20));

    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public double getLeft() {
        return left;
    }

    public void setLeft(double left) {
        this.left = left;
    }

    public double getTop() {
        return top;
    }

    public void setTop(double top) {
        this.top = top;
    }

    public boolean isClick() {
        return click;
    }

    public void setClick(boolean click) {
        this.click = click;
    }

    public List<TreeNode> getNodeChildren() {
        return nodeChildren;
    }

}
