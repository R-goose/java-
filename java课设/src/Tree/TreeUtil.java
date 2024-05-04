package Tree;

import Deque.NodeList;
import DrawPane.DrawPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import DrawPane.*;
/**
 * 树节点管理工具类，用于创建，添加和删除树节点
 */
public class TreeUtil {
    //静态变量，用于生成节点的唯一标识id
    private static int id=1;

    //创建根节点的方法
    public static void creatRoot(){
        //创建根节点对象，并设置唯一标识和标题
        TreeNode node = new TreeNode(0,"");
        //设置节点的唯一标识id并自增
        node.setNid(id++);
        //将根节点天机道绘图面板中，以便显示在页面上
        DrawPane.g.getChildren().add(node);
        //应用CSS样式到该对象及其子节点
        DrawPane.g.applyCss();
        //重新计算和布局对象及其子节点的位置和大小
        DrawPane.g.layout();

        //将根节点添加到节点列表中，永固管理所有节点
        NodeList.list.add(node);
        //控制面板更新，以便在界面上反映节点的变化
        CheckPane.controlPane();
    }
}
