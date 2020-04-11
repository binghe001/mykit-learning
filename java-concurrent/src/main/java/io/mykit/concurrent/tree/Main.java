package io.mykit.concurrent.tree;

import java.util.List;

/**
 * @author binghe
 * @version 1.0.0
 * @description 二叉树
 */
public class Main {

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<Integer>();
        tree.insert(8);
        tree.insert(6);
        tree.insert(9);
        tree.insert(7);
        tree.insert(5);
        tree.insert(1);
        tree.insert(30);
        tree.insert(20);
        tree.insert(15);
        tree.insert(40);
        tree.insert(35);
        tree.insert(50);
        tree.insert(21);

        tree.delete(30);
        tree.delete(30);

        List<BinaryTree.Node<Integer>> postTraversal = tree.postTraversal();
        postTraversal.stream().forEach(e->{
            System.out.println(e.data);
        });
        List<BinaryTree.Node<Integer>> preTraversal = tree.preTraversal();
        preTraversal.stream().forEach(e->{
            System.out.println(e.data);
        });
    }
}