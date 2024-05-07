package Tree;

import Deque.*;
import DrawPane.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import DrawPane.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import sample.Controller;

/**
 * 树节点管理工具类，用于创建，添加和删除树节点
 */
public class TreeUtil {
    //静态变量，用于生成节点的唯一标识id
    private static int id=1;

    //创建根节点的方法
    public static void creatRoot(Pane drawPane, ScrollPane sp){
        //创建根节点对象，并设置唯一标识和标题
        TreeNode node = new TreeNode(0,"双击输入");
        //设置节点的唯一标识id并自增
        node.setNid(id++);
        //将根节点天机道绘图面板中，以便显示在页面上
        Controller.g.getChildren().add(node);
        //应用CSS样式到该对象及其子节点
        Controller.g.applyCss();
        //重新计算和布局对象及其子节点的位置和大小
        Controller.g.layout();

        //将根节点添加到节点列表中，永固管理所有节点
        NodeList.list.add(node);
        //控制面板更新，以便在界面上反映节点的变化
        CheckPane.controlPane(drawPane,sp);
        node.setLeft(drawPane.getWidth() / 2 - node.getWidth() / 2);
        node.setTop(drawPane.getHeight() / 2 - node.getHeight() / 2);
    }
    public static void addNode(TreeNode p) {
        TreeNode node = new TreeNode(p.getNid(),"分支主题");
        node.setNid(id++);
        NodeList.list.add(node);
        p.getNodeChildren().add(node);
        Controller.g.getChildren().add(node);
        Controller.g.applyCss();
        Controller.g.layout();
        if(p.getPos()==1) {
            node.setLeft(p.getLeft() + p.getWidth() + NodePos.marginX);
        }else{
            node.setLeft(p.getLeft() - node.getWidth() - NodePos.marginX);
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
        }

    }

}
