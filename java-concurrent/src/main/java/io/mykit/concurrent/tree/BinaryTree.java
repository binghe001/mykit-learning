package io.mykit.concurrent.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author binghe
 * @version 1.0.0
 * @description 二叉树
 */
public class BinaryTree<E> {
    /**
     * 父结点
     */
    Node<E> root;

    int size = 0;

    /**
     * 插入一个新结点
     *
     * @param e
     */
    public boolean insert(E e) {
        Node<E> newNode = new Node<E>(e);
        if (isEmpty()) {
            root = newNode;
            size++;
        } else {
            Node<E> curr = root;
            while (true) {
                if (newNode.data.hashCode() > curr.data.hashCode()) {
                    if (curr.right == null) {
                        curr.right = newNode;
                        size++;
                        return true;
                    }
                    curr = curr.right;
                } else if (newNode.data.hashCode() < curr.data.hashCode()) {
                    if (curr.left == null) {
                        curr.left = newNode;
                        size++;
                        return true;
                    }
                    curr = curr.left;
                } else {
                    throw new IllegalArgumentException("repetition node!");
                }
            }
        }
        return false;
    }


    /**
     * 删除结点
     *
     * @param e
     */
    public boolean delete(E e) {
        if (isEmpty()) {
            throw new IllegalArgumentException("no any node!");
        }
        Node<E> curr = root;
        Node<E> parent;
        while (true) {
            parent = curr;
            if (e.hashCode() > curr.data.hashCode()) {
                curr = curr.right;
                if (curr == null) {
                    return false;
                }
                // 如果找到结点
                if (e.hashCode() == curr.data.hashCode()) {
                    // 再分4种情况
                    if (curr.left == null && curr.right == null) {
                        //1. 结点没有子结点
                        delNodeWithNoSon(parent, curr);
                    } else if (curr.left != null && curr.right == null) {
                        //2. 结点只有左子结点
                        delRightNodeWithLeftSon(parent, curr);
                    } else if (curr.left == null) {
                        //3. 结点只有右子结点
                        delRightNodeWithRightSon(parent, curr);
                    } else {
                        //4. 结点同时包含左右子结点
                        delRightNodeWithTwoSon(parent, curr);
                    }
                }
            } else if (e.hashCode() < curr.data.hashCode()) {
                curr = curr.left;
                if (curr == null) {
                    return false;
                }
                // 如果找到结点
                if (e.hashCode() == curr.data.hashCode()) {
                    // 再分4种情况
                    if (curr.left == null && curr.right == null) {
                        //1. 结点没有子结点
                        delNodeWithNoSon(parent, curr);
                    } else if (curr.left != null && curr.right == null) {
                        //2. 结点只有左子结点
                        delLeftNodeWithLeftSon(parent, curr);
                    } else if (curr.left == null) {
                        //3. 结点只有右子结点
                        delLeftNodeWithRightSon(parent, curr);
                    } else {
                        //4. 结点同时包含左右子结点
                        delLeftNodeWithTwoSon(parent, curr);
                    }
                }
            } else {
                return true;
            }
        }
    }

    private void delNodeWithNoSon(Node<E> parent, Node<E> e) {
        parent.left = null;
        parent.right = null;
    }

    private void delLeftNodeWithLeftSon(Node<E> parent, Node<E> e) {
        parent.left = e.left;
    }

    private void delLeftNodeWithRightSon(Node<E> parent, Node<E> e) {
        parent.left = e.right;
    }

    private void delRightNodeWithLeftSon(Node<E> parent, Node<E> e) {
        parent.right = e.left;
    }

    private void delRightNodeWithRightSon(Node<E> parent, Node<E> e) {
        parent.right = e.right;
    }

    private void delLeftNodeWithTwoSon(Node<E> parent, Node<E> e) {
        parent.left = getSuccessor(e);
    }

    private void delRightNodeWithTwoSon(Node<E> parent, Node<E> e) {
        parent.right = getSuccessor(e);
    }

    /**
     * 获取结点的中序遍历后继结点
     *
     * @param e 源结点
     * @return 中序遍历后继结点
     */
    private Node<E> getSuccessor(Node<E> e) {
        //
        Node<E> curr = e.right;
        Node<E> parent = curr;
        while (curr.left != null) {
            curr = curr.left;
        }
        //
        // 当结点恰好为待删结点的右结点时，直接替换不必赋值了。需要结合动图理解
        if (curr != e.right) {
            parent.left = null;
            curr.right = e.right;
        }
        curr.left = e.left;
        return curr;
    }

    /**
     * 查找结点
     *
     * @param e
     */
    public Node<E> find(E e) {
        if (isEmpty()) {
            throw new IllegalArgumentException("no any node!");
        }
        Node<E> curr = root;
        while (true) {
            if (e.hashCode() > curr.data.hashCode()) {
                curr = curr.right;
            } else if (e.hashCode() < curr.data.hashCode()) {
                curr = curr.left;
            } else {
                return curr;
            }
            if (curr == null) {
                throw new IllegalArgumentException("no target node!");
            }
        }
    }

    /**
     * 中序遍历
     *
     * @return
     */
    public List<Node<E>> inorderTraversal() {
        List<Node<E>> list = new ArrayList<>();
        if (isEmpty()) {
            return new ArrayList<>();
        }
        inorderTraversalHelper(list, root);
        return list;
    }

    private void inorderTraversalHelper(List<Node<E>> list, Node<E> root) {
        if (root == null) {
            return;
        }
        inorderTraversalHelper(list, root.left);
        list.add(root);
        inorderTraversalHelper(list, root.right);
    }

    /**
     * 后序遍历
     *
     * @return
     */
    public List<Node<E>> postTraversal() {
        List<Node<E>> list = new ArrayList<>();
        if (isEmpty()) {
            return new ArrayList<>();
        }
        postTraversalHelper(list, root);
        return list;
    }

    private void postTraversalHelper(List<Node<E>> list, Node<E> root) {
        if (root == null) {
            return;
        }
        postTraversalHelper(list, root.left);
        postTraversalHelper(list, root.right);
        list.add(root);
    }

    /**
     * 前序遍历
     *
     * @return
     */
    public List<Node<E>> preTraversal() {
        List<Node<E>> list = new ArrayList<>();
        if (isEmpty()) {
            return new ArrayList<>();
        }
        preTraversalHelper(list, root);
        return list;
    }

    private void preTraversalHelper(List<Node<E>> list, Node<E> root) {
        if (root == null) {
            return;
        }
        list.add(root);
        preTraversalHelper(list, root.left);
        preTraversalHelper(list, root.right);
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public static class Node<E> {
        /**
         * 数据域
         */
        E data;
        /**
         * 左结点
         */
        Node<E> left;
        /**
         * 右结点
         */
        Node<E> right;

        public Node(E e) {
            if (e == null) {
                throw new IllegalArgumentException("e can not be null!");
            }
            this.data = e;
        }
    }
}
