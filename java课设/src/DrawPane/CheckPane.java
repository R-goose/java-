package DrawPane;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import sample.*;
public class CheckPane {

    public static double width = 0;
    public static double height = 0;


    public static void controlPane(Pane drawPane, ScrollPane sp) {
        //控制画板的大小
        double width = 1000+DeriveGraph.getMaxWidth()*2;
        double height = 1000+DeriveGraph.getMaxHeight();
        drawPane.setPrefWidth(width);
        drawPane.setPrefHeight(height);


//        Main.pane.applyCss();
//        Main.pane.layout();
//        //设置滚轮为中间值
        sp.setVvalue(0.5);
        sp.setHvalue(0.5);

    }

}
