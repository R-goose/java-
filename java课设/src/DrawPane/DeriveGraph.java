package DrawPane;


import java.awt.image.BufferedImage;
import java.io.File;
import Tree.*;
import Deque.*;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.WritableImage;

// 从树状图中导出图形并保存为图片文件
public class DeriveGraph {
    // 设置图形与边界的间距
    public static int padding = 50;
    // 初始化左侧和右侧节点
    public static TreeNode left = new TreeNode();
    public static TreeNode right = new TreeNode();
    // 初始化顶部和底部节点
    private static TreeNode top = new TreeNode();
    private static TreeNode bottom = new TreeNode();

    // 计算出图形的起始点坐标以及图形的宽度和高度
    public static void deriveGraph(File file) throws Exception {
        // 重置节点的点击状态
        for (int i = 0; i < NodeList.list.size(); i++) {
            TreeNode node = NodeList.list.get(i);
            node.setClick(false);
        }
        // 重新绘制树状图
        DrawPane.redraw();

        // 获取顶部节点
        getTop();
        // 计算起始点坐标
        int x = (int) left.getLeft() - DeriveGraph.padding;
        int y = (int) top.getTop() - DeriveGraph.padding;

        // 获取可视区域截图
        WritableImage wi = DrawPane.g.snapshot(null, null);
        // 计算图形的实际高度和宽度
        double height = wi.getHeight() + DeriveGraph.padding * 2;
        double width = wi.getWidth() + DeriveGraph.padding * 2;

        // 从整个绘图区域中截取图形部分
        WritableImage wi1 = DrawPane.drawPane.snapshot(null, null);
        WritableImage wi2 = new WritableImage(wi1.getPixelReader(), x, y, (int) width, (int) height);

        // 将截取的图形转换为BufferedImage并保存为png文件
        BufferedImage bi = SwingFXUtils.fromFXImage(wi2, null);
        ImageIO.write(bi, "png", file);
    }

    // 计算最大宽度
    public static double getMaxWidth() {
        if (NodeList.list.size() != 0) {
            // 初始化左侧和右侧节点为列表中的第一个节点
            left = right = NodeList.list.get(0);
            // 遍历节点列表，找到最左侧和最右侧的节点
            for (int i = 0; i < NodeList.list.size(); i++) {
                TreeNode node = NodeList.list.get(i);
                if (left.getLeft() - node.getLeft() > 0) {
                    left = node;
                }
                if (right.getLeft() + right.getWidth() - (node.getLeft() + node.getWidth()) < 0) {
                    right = node;
                }
            }
            // 计算最大宽度
            return right.getLeft() + right.getWidth() - left.getLeft();
        } else {
            return 0;
        }
    }

    // 计算最大高度
    public static double getMaxHeight() {
        if (NodeList.list.size() != 0) {
            // 获取根节点
            TreeNode r = NodeList.getRoot();
            if (NodeList.list.size() == 1) {
                return r.getHeight();
            } else {
                // 计算左侧子树和右侧子树的高度，取最大值作为树的高度
                double leftHeight = NodeList.getChildHeight(r, 0);
                double rightHeight = NodeList.getChildHeight(r, 1);
                if (leftHeight > rightHeight) {
                    return leftHeight;
                }
                return rightHeight;
            }
        } else {
            return 0;
        }
    }

    // 找到顶部位置最高和底部位置最低的节点
    public static void getTop() {
        if (!NodeList.list.isEmpty()) {
            top = bottom = NodeList.list.get(0);
            for (int i = 0; i < NodeList.list.size(); i++) {
                TreeNode node = NodeList.list.get(i);
                if (top.getTop() - node.getTop() > 0) {
                    top = node;
                }
                if (bottom.getTop() + bottom.getHeight() - (node.getTop() + node.getHeight()) < 0) {
                    bottom = node;
                }
            }
        }
    }

}
