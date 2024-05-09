package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class SerializableNode implements Serializable {
    private String text; //保存思维导图结点文本信息
    private String mapType; //保存思维导图的布局类型
    private String fontFamily;
    private boolean isBold;
    private boolean isUnderline;//保存思维导图结点字体样式
    private double fontSize; //保存字体大小
    private int level; //保存结点层数，用于恢复TreeView
    private double r1, g1, b1; //保存结点颜色信息
    private double r2, g2, b2; //保存字体颜色信息
    private double r3, g3, b3; //保存结点边框颜色信息
    private double radius;


    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }


    private ArrayList<SerializableNode> leaves; //保存子节点

    public SerializableNode() {
        leaves = new ArrayList<>();
    }
    public void setNodeColor(double r, double g, double b) {
        this.r1 = r;
        this.g1 = g;
        this.b1 = b;
    }

    public void setFontColor(double r, double g, double b) {
        this.r2 = r;
        this.g2 = g;
        this.b2 = b;
    }

    public void setEdgeColor(double r, double g, double b) {
        this.r3 = r;
        this.g3 = g;
        this.b3 = b;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public boolean getBold() {
        return isBold;
    }

    public void setBold(boolean bold) {
        isBold = bold;
    }

    public boolean getUnderline() {
        return isUnderline;
    }

    public void setUnderline(boolean Underline) {
        isUnderline = Underline;
    }

    public double getFontSize() {
        return fontSize;
    }

    public void setFontSize(double fontSize) {
        this.fontSize = fontSize;
    }

    public String getMapType() {
        return mapType;
    }

    public void setMapType(String mapType) {
        this.mapType = mapType;
    }

    public double getR1() { return r1; }

    public double getG1() { return g1; }

    public double getB1() { return b1; }

    public double getR2() { return r2; }

    public double getG2() { return g2; }

    public double getB2() { return b2; }

    public double getR3() {
        return r3;
    }

    public double getG3() {
        return g3;
    }

    public double getB3() {
        return b3;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ArrayList<SerializableNode> getLeaves() {
        return leaves;
    }

}
