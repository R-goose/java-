package Deque;

import Tree.*;
import sample.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于管理和存储所有的节点的信息
 * 管理树节点的列表
 */


public class NodeList {
    //用于储存所有的树节点。用于储存和管理所有的节点信息
    public static List<TreeNode> list=new ArrayList<>();
    private static double height=0;

    //获取根节点，遍历节点列表找到pid为0的节点，作为根节点返回
    public static TreeNode getRoot(){
        TreeNode r=new TreeNode();
        for(int i=0;i<NodeList.list.size();i++){
            if(NodeList.list.get(i).getPid()==0){
                r=NodeList.list.get(i);
            }
        }
        return r;
    }

    //获取给定节点的父节点
    public static TreeNode getParent(TreeNode node){
        TreeNode p=new TreeNode();
        for(int i=0;i<NodeList.list.size();i++){
            if(NodeList.list.get(i).getNid()==node.getPid()){
                p=NodeList.list.get(i);
                return p;
            }
        }
        return p;
    }

    //获取给定节点的前一个子节点
    public static TreeNode getPreChild(TreeNode node){
        TreeNode p=NodeList.getParent(node);
//        System.out.println(NodeList.getParent(node));
//        System.out.println("********");
        //父节点的第一个节点，父节点的子节点的保存方式用的是list
        TreeNode pre=p.getNodeChildren().get(0);
        for(int i=1;i<p.getNodeChildren().size();i++){
            //找到当前节点，则退出循环
            if(node.getNid()==p.getNodeChildren().get(i).getNid()){
                break;
            }
            //逐个比较节点的位置和当前节点的位置
            if(node.getPos()==p.getNodeChildren().get(i).getPos()){
                pre=p.getNodeChildren().get(i);
            }
        }
        return pre;
    }

    //判断给定节点是否是父节点的第一个节点
    public static boolean isFirstChild(TreeNode node){
        TreeNode p=NodeList.getParent(node);
        TreeNode l=new TreeNode();
        TreeNode r=new TreeNode();
        for (int i = 0; i < p.getNodeChildren().size(); i++) {
            if(p.getNodeChildren().get(i).getPos()==0){
                l=p.getNodeChildren().get(i);
                break;
            }
        }
        for (int i = 0; i < p.getNodeChildren().size(); i++) {
            if(p.getNodeChildren().get(i).getPos()==1){
                r=p.getNodeChildren().get(i);
                break;
            }
        }
        if(node.getNid()==l.getNid()||node.getNid()==r.getNid()){
            return true;
        }
        return false;
    }

    //计算子节点的高度
    public static double getChildHeight(TreeNode p,int pos){
        height=0;
        if(p.getPid()==0){//根节点需要特殊处理，左右布局的影响
            List<TreeNode> list =new ArrayList<>();
            for(int i=0;i<p.getNodeChildren().size();i++){
                if(p.getNodeChildren().get(i).getPos()==pos){
                    list.add(p.getNodeChildren().get(i));
                }
            }
            for(int i=0;i<list.size();i++){
                walkNode(list.get(i));
                if(i<list.size()-1)
                    height+= Controller.marginY;
            }
        }else{//如果当前节点不是根节点，则直接调用方法计算高度
            walkNode(p);
        }
        return height;
    }

    //递归计算节点的高度
    private static void walkNode(TreeNode p){
        //遍历给定节点的子节点的列表
        for (int i = 0; i < p.getNodeChildren().size(); i++) {
            //获取当前子节点
            TreeNode node=new TreeNode();
            node=p.getNodeChildren().get(i);
            //如果当前子节点还有子节点
            if(!node.getNodeChildren().isEmpty()){
                //递归调用继续遍历
                walkNode(node);
            }else{
                //如果是叶子节点，累加该叶子节点的高度到总高度中
                height+=node.getHeight();
            }
            //在累加完一个子节点的高度后，考虑节点的间距
            if(i<p.getNodeChildren().size()-1){
                height+=Controller.marginY;
            }
        }
        //如果当前节点没有子节点，将当前节点的高度加到总高度中
        if(p.getNodeChildren().isEmpty()){
            height+=p.getHeight();
        }else if(height<p.getHeight()){
            //更新高度
            height=p.getHeight();
        }

    }

}
