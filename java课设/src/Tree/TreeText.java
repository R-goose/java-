package Tree;

import javafx.scene.control.Label;

public class TreeText extends Label implements java.io.Serializable{
    //指定类的序列化的版本号
    private static final long serialVersionUID =1L;
    private String txt;        //文本内容
    private String imagPath;   //图像路径


    public TreeText(String text){
        super(text);
        this.txt=text;
    }

    public TreeText() {
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

    public void setImagPath(String imagPath) {
        this.imagPath = imagPath;
    }
}
