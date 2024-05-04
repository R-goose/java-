package Tree;

import javafx.scene.control.Label;

/**
 * 用于储存文本内容和图片路径
 */

//实现序列化接口，可以将对象序列化为字节流进行传输或持久化储存
public class TreeData extends Label implements java.io.Serializable {
    private static final long serialVersionUID=1L;
    private String txt;           //用于储存标签文本内容
    private String imagPath;       //用于存储图片路径

    //构造方法，接受一个字符串参数作为文本内容，并调用父类的构造方法
    public TreeData(String text){
        super(text);      //调用父类Label的构造方法，传入文本内容作为参数
        this.txt=text;    //将文本内容赋值给类成员变量txt
    }

    //无参构造方法
    public TreeData(){

    }


    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getImagPath() {
        return imagPath;
    }

    public void setImagPath(String imaPath) {
        this.imagPath = imaPath;
    }
}
