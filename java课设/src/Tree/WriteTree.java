package Tree;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Deque.NodeList;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.SetTitle;
import sample.SetTitle;

public class WriteTree {
    public static void write( File file, Text text)  {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(NodeList.list);
            oos.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(oos!=null) {
                try {
                    oos.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String name=file.getName();
        SetTitle.setTitle(name);
        SetTitle.changeTitle(text);
    }
}
