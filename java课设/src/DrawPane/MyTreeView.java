package DrawPane;

import Tree.*;
import Deque.*;
import java.io.IOException;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;

/**
 * 树形视图
 * 用于显示树形结构的数据
 */

public class MyTreeView extends TreeView<Label> {

    // 创建 AnchorPane 实例用于存放树形视图
    public static AnchorPane ap = new AnchorPane();

    // 构造方法
    public MyTreeView() throws IOException {
        setTreeView();
    }

    // 设置树形视图方法
    public static void setTreeView() throws IOException {
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

        // 如果根节点有图片路径，则在标签文本后添加图片提示
        if (node.getImagPath() != null) {
            label.setText(node.getTxt() + "\n" + "(图片)");
            label.setTextAlignment(TextAlignment.CENTER); // 设置文本居中对齐
        }
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
        treeview.setMinHeight(570);

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
}
