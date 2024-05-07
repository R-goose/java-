package DrawPane;


import Tree.TreeNode;
import javafx.stage.Stage;
import Deque.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class ReadTree {

    public static void read(File file, Stage stage) {
        NodeList.list= new ArrayList<>();
        // TODO Auto-generated method stub
        try {
            //读取载入的文件
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            //进行反序列化的原因：通过反序列化，可以有效地将对象持久化到文件中
            //将载入的文件放入放到结点中，将文件的内容反序列化为 <TreeNode>类型，然后载入到List中
            NodeList.list = (List<TreeNode>) ois.readObject();

        }catch(Exception e) {
            //打印异常栈的信息，有助于追踪和处理异常情况
            e.printStackTrace();
        }

        String name=file.getName();
//        SetTitle.setTitle(name);
//        SetTitle.changeTitle(stage);

    }

}
