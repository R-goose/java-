package Deque;

import DrawPane.DrawPane;
import Tree.TreeNode;

/**
 * 计算树形结构节点位置
 */


public class NodePos {
    public static int marginX=50;   //表示节点在X轴上的边距
    public static int marginY=20;   //表示节点在Y轴上的边距

    //用于计算节点在X轴上的位置
    public static void posX(TreeNode node){
        //判断是否为根节点，如果不是根节点
        //则获取其父节点并根据节点在父节点中的位置确认当前节点在X轴上的位置
        if(node.getPid()!=0){
            TreeNode p=NodeList.getParent(node);
            if(node.getPos()==1){
                node.setLeft(p.getLeft()+p.getWidth()+marginX);
            }else{
                node.setLeft(p.getLeft()-node.getWidth()-marginX);
            }
        }else{
            //如果是根节点，
            //则将当前节点的左边界位置设置为画板宽度的一半减去当前节点宽度的一半，即居中显示
            node.setLeft(DrawPane.drawPane.getWidth()/2-node.getWidth()/2);
        }
        //对当前节点的子节点进行递归调用，计算他们在X轴上的位置
        for(int i=0;i<node.getNodeChildren().size();i++){
            posX(node.getNodeChildren().get(i));
        }
    }

    //计算节点在Y轴上的位置
    public static void posY(TreeNode node){
        if(node.getPid()!=0){ //判断是否为根节点
            TreeNode p=NodeList.getParent(node); //获取父节点
            //第一个孩子
            if(NodeList.isFirstChild(node)){ //判断是否为父节点的第一个孩子
                double pchildHeight=NodeList.getChildHeight(p,node.getPos());    //获取父节点和当前父节点中的高度
                double childHeight=NodeList.getChildHeight(node, node.getPos()); //获取当前节点在父节点中的高度
                System.out.println("p1+"+pchildHeight);
                System.out.println("c2+"+childHeight);
                //如果当前节点是父节点的最后一个孩子，则将其上边界设置为父节点中向上偏移当前节点高度一半
                if(node.getNid()==p.getNodeChildren().get(p.getNodeChildren().size()-1).getNid()){
                    node.setTop(p.getTop()+p.getHeight()/2-node.getHeight()/2);
                }else{ //否则，计算当前节点上边界位置，时期处于父节点和前一个孩子节点之间
                    node.setTop(p.getTop()+p.getHeight()/2-(pchildHeight-childHeight+node.getHeight())/2);
                }
            }else{//剩下的孩子
                //获取前一个孩子节点
                TreeNode preChild =NodeList.getPreChild(node);
                //计算前一个孩子节点和当前节点在父节点中的高度
                double preChildHeight=NodeList.getChildHeight(preChild,node.getPos());
                double childHeight=NodeList.getChildHeight(node,node.getPos());
                //计算当前节点上边界位置，使其处于前一个孩子节点下方
                node.setTop(preChild.getTop()+preChild.getHeight()/2+(preChildHeight+childHeight-node.getHeight()/2+marginY));
            }
        }else{ //设置根节点的位置
            //将根节点的上边界位置设置为花瓣高度的一般减去根节点高度的一般，即垂直居中显示
            node.setTop(DrawPane.drawPane.getHeight()/2-node.getHeight()/2);
        }
        //对当前节点的子节点进行递归调用，计算他们在Y轴上的位置
        for (int i = 0; i < node.getNodeChildren().size(); i++) {
            posY(node.getNodeChildren().get(i));
        }
    }

}
