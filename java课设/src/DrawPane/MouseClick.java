package DrawPane;

import Deque.NodeList;
import Tree.TreeNode;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;

public class MouseClick {
    public static TreeNode node = new TreeNode();
    //鼠标双击事件：监听节点，双击出现文本输入框，编辑节点文字内容
    public static void DoubleClick() {
        DrawPane.drawPane.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount() == 2){
                    double x = event.getSceneX();
                    double y = event.getSceneY();
//				     System.out.println(x+","+y);
                    for(int i = 0; i< NodeList.list.size(); i++) {
                        TreeNode node = new TreeNode();
                        node = NodeList.list.get(i);
                        Bounds b = node.getLayoutBounds();
                        Bounds bd =node.localToScene(b);
                        //	node.setClick(false);
//							System.out.println(node.getLeft()+","+node.getWidth());
//							System.out.println(node.getTop()+","+node.getHeight());
                        if(bd.getMinX()<=x&&bd.getMaxX()>=x&&bd.getMinY()<=y&&bd.getMaxY()>=y
//									node.getLeft()<= x&& node.getLeft()+node.getWidth()>=x
//							     &&node.getTop()<=y && node.getTop()+node.getHeight()>=y
                        ) {
                            //	node.setClick(true);
                            MyTextArea.showTextArea(node);
                        }
                        //	MyDrawPane.draw(); 另起方法画框
                    }
                }
            }
        });
    }
}
