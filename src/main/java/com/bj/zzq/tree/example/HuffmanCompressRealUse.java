package com.bj.zzq.tree.example;


import sun.text.normalizer.ICUBinary;

import java.util.*;
import java.util.function.BinaryOperator;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/4/12
 * @Description: 哈夫曼压缩方法
 */
public class HuffmanCompressRealUse {
    private HashMap<Character, String> map = new HashMap<>();//码表
    private Node huffmanTree;//哈夫曼树

    public String encode(String originText) {
        Set<Node> nodes = extractMap(originText);
        Queue<Node> queue = convertPriorityQueue(nodes);
        generateHuffmanTree(queue);
        generateCodeTable(huffmanTree);
        char[] chars = originText.toCharArray();
        String result = "";
        for (int i = 0; i < chars.length; i++) {
            result += map.get(chars[i]);
        }
        return result;
    }


    public String decode(String encode) {
        char[] chars = encode.toCharArray();
        String result = "";
        Node current = huffmanTree;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '0') {
                current = current.getLeftChild();
            } else {
                current = current.getRightChild();
            }
            if (current.getLeftChild() == null && current.getRightChild() == null) {
                result += current.getCh();
                current = huffmanTree;
            }
        }
        return result;
    }

    /**
     * 生成huffman树
     *
     * @param queue
     * @return huffman树root
     */
    private void generateHuffmanTree(Queue<Node> queue) {
        Node leftChild;
        Node rightChild;
        while (queue.size() > 1) {
            leftChild = queue.remove();
            rightChild = queue.remove();
            Node node = new Node();
            node.setLeftChild(leftChild);
            node.setRightChild(rightChild);
            node.setCount(leftChild.getCount() + rightChild.getCount());
            queue.add(node);
        }
        huffmanTree = queue.remove();
    }


    /**
     * 生成码表
     *
     * @param node haffman树
     */
    private void generateCodeTable(Node node) {
        recGenerateCodeTable("", node);
    }

    private void recGenerateCodeTable(String prefixCode, Node node) {
        if (node.getLeftChild() == null && node.getRightChild() == null) {
            map.put(node.getCh(), prefixCode);
            return;
        }

        if (node.getLeftChild() != null) {
            recGenerateCodeTable(prefixCode + "0", node.getLeftChild());
        }
        if (node.getRightChild() != null) {
            recGenerateCodeTable(prefixCode + "1", node.getRightChild());
        }
    }

    /**
     * 将节点集合转换成优先级队列
     *
     * @param countMap
     * @return
     */
    private Queue<Node> convertPriorityQueue(Set<Node> countMap) {
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.getCount() - o1.getCount());
        queue.addAll(countMap);
        return queue;
    }

    /**
     * 统计字符频率
     */
    private Set<Node> extractMap(String originText) {
        HashMap<Character, Node> charCountMap = new HashMap<Character, Node>();
        char[] chars = originText.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            Character aChar = chars[i];
            Node node = charCountMap.get(aChar);
            if (node == null) {
                Node nodeNew = new Node();
                nodeNew.setCh(aChar);
                nodeNew.setCount(1);
                charCountMap.put(aChar, nodeNew);
            } else {
                node.setCount(node.getCount() + 1);
            }
        }
        return new HashSet<>(charCountMap.values());
    }


    public static void main(String[] args) {
        //todo 位操作
        int b = 0xff;
        int i = b & 0x80;
        System.out.println((byte) b >> 4);
        System.out.println(i);

    }

    /**
     * @param origin 当前byte
     * @param nth    第几位
     * @param ch     是字符0，还是1
     */
    public void convertBit(byte origin, int nth, char ch) {
//        if (ch == '0') {
//            origin &
//        }
    }

    public void find(int nth) {
        byte temp;
        switch (nth) {
            case 0:

        }

    }

}
