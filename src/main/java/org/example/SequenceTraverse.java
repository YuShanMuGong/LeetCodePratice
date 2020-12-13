package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 层序遍历
 */
public class SequenceTraverse {

    private static class TreeNode{
        private TreeNode left;
        private TreeNode right;
        private int value;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        node1.left = node2;
        node1.right = node3;
        node3.right = node4;

        List<List<String>> sequenceView = sequenceView(node1);
        for (List<String> list : sequenceView){
            System.out.println(list.stream().collect(Collectors.joining(",")));
        }
    }

    public static List<List<String>> sequenceView(TreeNode root){
        List<List<String>> seqResult = new ArrayList<>();
        if(root == null){
            return seqResult;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            // 这里一定只能 取出 size 个 元素，，否则就会取到 其他层的节点；
            int size = queue.size();
            List<String> result = new ArrayList<>();
            while (size > 0){
                TreeNode node = queue.poll();
                if(node == null){
                    continue;
                }
                result.add(node.value + "");
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
                size --;
            }
            seqResult.add(result);
        }
        return seqResult;
    }

}
