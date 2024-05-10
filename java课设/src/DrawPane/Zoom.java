package DrawPane;

import Deque.NodeList;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.scene.control.ScrollPane;
import sample.Controller;



public class Zoom {

    public static int count=0;

    public static void zoom(Pane drawPane,AnchorPane ap) {
        drawPane.setOnScroll(event -> {
            if (event.isControlDown()) { // 检查是否按下了Ctrl键
                double deltaY = event.getDeltaY();
                if(event.getDeltaY()>0) {
                    enlarge(drawPane,ap);
                }
                if(event.getDeltaY()<0) {
                    reduce(drawPane,ap);
                }
            }
        });
    }

    public static void enlarge(Pane drawPane, AnchorPane ap) {
        if(count<5) {
            count++;
           Controller.g.getTransforms().add(new Scale(1.1,1.1,drawPane.getWidth()/2,drawPane.getHeight()/2));

            WritableImage wi1 = Controller.g.snapshot(null, null);
            double width = 1000 + wi1.getWidth()*2;
            double height = 1000+ wi1.getHeight();
            drawPane.setPrefWidth(width);
            drawPane.setPrefHeight(height);


            Controller.redraw();
            try {
                Controller.setTreeView(ap);
            } catch (Exception e6) {
                // TODO Auto-generated catch block
                e6.printStackTrace();
            }
        }

    }

    public static void reduce(Pane drawPane, AnchorPane ap) {
        if(count>-5) {
            count--;
            Controller.g.getTransforms().add(new Scale(0.9,0.9,drawPane.getWidth()/2,drawPane.getHeight()/2));

            WritableImage wi1 = Controller.g.snapshot(null, null);
            double width = 1000 + wi1.getWidth()*2;
            double height = 1000+ wi1.getHeight();
            drawPane.setPrefWidth(width);
            drawPane.setPrefHeight(height);

            Controller.redraw();
            try {
                Controller.setTreeView(ap);
            } catch (Exception e7) {
                // TODO Auto-generated catch block
                e7.printStackTrace();
            }
        }

    }

    public static void recover(Pane drawPane,ScrollPane sp,AnchorPane ap) {
        count=0;
        Controller.g.getTransforms().clear();
        Controller.posX(NodeList.getRoot(),drawPane);
        Controller.posY(NodeList.getRoot(),drawPane);
        CheckPane.controlPane(drawPane,sp);
        Controller.redraw();
        try {
            Controller.setTreeView(ap);
        } catch (Exception e8) {
            // TODO Auto-generated catch block
            e8.printStackTrace();
        }
    }
}