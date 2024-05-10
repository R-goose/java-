package Deque;

import Tree.*;
import java.util.ArrayList;
import java.util.List;
import DrawPane.*;
import sample.*;

public class Deque {
    //定义队列的最大容量
    private static int maxSize = 20;
    //储存节点信息的列表数组
    private static List<TreeNode>[] arr = new List[maxSize];
    //表示目前队列是为空的状态
    //sp是一个指示当前状态的指针变量，跟踪当前状态在数组中的位置，帮助代码实现状态的管理和撤销功能
    //sp是一个索引，表示当前状态在数组中的位置
    private static int sp = -1;
    //队列的大小
    private static int size = 0;

    //插入到新的状态队列中
    public static void insert(List<TreeNode> list) {
        //队列已满 进行处理
        if(isFull()) {
            //sp指向的状态还有下一个状态时，执行右侧删除再插入操作
            if(sp<size-1) {
                remove_right();
                //循环添加放到了getList函数里面，list删除和插入操作需要涉及到数组元素的移动操作
                arr[++sp] = getList(list);
                size++;
            }else {
                //否则执行左侧删除再插入操作
                remove_left();
                arr[sp] = getList(list);
            }
        }else {
            //队列未满时直接插入新状态
            if(sp<size-1) {
                remove_right();
                arr[++sp] = getList(list);
                size++;
            }else {
                arr[++sp] = getList(list);
                size++;
            }

        }

    }
    //左侧删除最初的状态
    public static void remove_left() {
        //删掉最初的状态
        for(int i =0;i<size-1;i++) {
            arr[i] = arr[i+1];
        }
    }
    //右侧删除所有状态直到当前状态
    public static void remove_right() {
        //清空右边的状态
        while(size-1>sp) {
            arr[size-1] = null;
            size--;
        }
    }

    //    //撤销操作
//    public static List<TreeNode> undo(){
//        //用于临时储存节点信息或者作为返回结果
//        //List为TreeNode类型的动态数组
//        List<TreeNode> list = new ArrayList<>();
//        if(sp>0) {
//            //撤销时，sp-1，并将对应位置的状态数组赋给list
//            sp--;
//            list = arr[sp];
//            //如果list为空 则按钮不可用，即不可以撤销
//            if(list.isEmpty()) {
//                MyButtonBar.b1.setDisable(false);
//                MyButtonBar.b2.setDisable(true);
//                MyButtonBar.b3.setDisable(true);
//                MyButtonBar.b4.setDisable(true);
//                MyButtonBar.b5.setDisable(true);
//            }else {
//                MyButtonBar.b1.setDisable(true);
//            }
//            //System.out.println("获得撤销状态");
//            return getList(list);
//
//        }
//        return null;
//    }
    //重做：再次执行之前被撤销的操作
//    public static List<TreeNode> redo(){
//        List<TreeNode> list = new ArrayList<>();
//        if(sp<size-1) {
//            sp++;
//            list = arr[sp];
//            //System.out.println("获得重做状态");
//            if(list.isEmpty()) {
//                MyButtonBar.b1.setDisable(false);
//                MyButtonBar.b2.setDisable(true);
//                MyButtonBar.b3.setDisable(true);
//                MyButtonBar.b4.setDisable(true);
//                MyButtonBar.b5.setDisable(true);
//            }else {
//                MyButtonBar.b1.setDisable(true);
//            }
//            return getList(list);
//        }
//        return null;
//    }
    //将节点列表复制到新的列表中，并添加到画布上
    private static List<TreeNode> getList(List<TreeNode> list){
        //创建一个新的空列表l，用于存储复制后的节点信息
        List<TreeNode> l =  new ArrayList<>();
        //对每个节点进行复制和属性设置
        for(int i = 0;i<list.size();i++) {
            TreeNode n = new TreeNode();
            n.setText(list.get(i).getText());
            n.setNid(list.get(i).getNid());
            n.setPid(list.get(i).getPid());
            n.setLeft(list.get(i).getLeft());
            n.setTop(list.get(i).getTop());
            n.setTxt(list.get(i).getTxt());
            n.setPos(list.get(i).getPos());
            n.setImagPath(list.get(i).getImagPath());
            //将节点添加到画布上
            Controller.g.getChildren().add(n);
            Controller.g.applyCss();
            Controller.g.layout();
            //避免出现界面混乱或重叠的情况，有利于保持界面的清晰性和整洁性
            //注意一下这段代码的逻辑，为什么需要将子节点进行清空
            //MyDrawPane.g.getChildren().clear();
            l.add(n);

        }
        //匹配并添加节点
        //将子节点添加到新的列表中
        //对子节点的匹配和添加操作的实现
        for(int i = 0;i < l.size();i++) {
            for(int j = 0;j<list.get(i).getNodeChildren().size();j++) {
                TreeNode node = list.get(i).getNodeChildren().get(j);
                for(int k = 0;k<l.size();k++) {
                    if(node.getNid() == l.get(k).getNid())
                        l.get(i).getNodeChildren().add(l.get(k));
                }

            }
        }
        return l;
    }
    //判断队列是否已满
    private static  boolean isFull() {//判满
        return size == maxSize;
    }

}