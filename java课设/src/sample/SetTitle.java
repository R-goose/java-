package sample;

import Deque.NodeList;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SetTitle {
    static String title=null;
    public static void setTitle(String name) {
        title=name.replace(".LinkMind", "");
    }

    public static String getTitle() {
        return title;
    }

    public static void changeTitle(Text text) {

        if(NodeList.list.isEmpty()==true) {
            text.setText("思维导图");
        }

        else if((NodeList.list.isEmpty()!=true)&&(title==null)) {
            text.setText("思维导图-未命名");
        }

        else {
            text.setText(getTitle());
        }
    }
    public static String showTitle(){
        return getTitle();
    }

}