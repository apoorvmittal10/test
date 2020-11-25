package com.test.ds;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSAverage {

  private static class Node {
    public int value;
    public Node left;
    public Node right;
  }

  public static void main(String[] args) {
    Node node = getNewNode(10);

    node.left = getNewNode(12);
    node.right = getNewNode(15);

    node.left.left = getNewNode(8);
    node.right.left = getNewNode(16);

    node.right.left.left = getNewNode(6);

    List<Double> resultList = getLevelAverage(node);
    for (Double d : resultList) {
      System.out.println(d);
    }

    resultList = getLevelAverageSingleQueue(node);

    for (Double d : resultList) {
      System.out.println(d);
    }
  }

  private static List<Double> getLevelAverage(Node root) {
    List<Double> resultList = new ArrayList<>();

    Queue<Node> queue = new LinkedList<>();
    Queue<Node> levelQueue = new LinkedList<>();
    queue.add(root);
    resultList.add((double) root.value);

    while (!queue.isEmpty()) {
      Node node = queue.poll();

      if (node.left != null) {
        levelQueue.add(node.left);
      }

      if (node.right != null) {
        levelQueue.add(node.right);
      }

      if (queue.isEmpty() && !levelQueue.isEmpty()) {
        queue = levelQueue;

        int sum = 0;
        for (Node n : levelQueue) {
          sum += n.value;
        }

        double result = (double)sum / (double)levelQueue.size();
        resultList.add(result);
        levelQueue = new LinkedList<>();
      }
    }

    return resultList;
  }

  private static List<Double> getLevelAverageSingleQueue(Node root) {
    List<Double> resultList = new ArrayList<>();

    Queue<Node> queue = new LinkedList<>();
    queue.add(root);
    queue.add(null);

    resultList.add((double) root.value);

    while (queue.size() > 1) {
      Node node = queue.poll();

      if (node == null) {
        int sum = 0;
        for (Node n : queue) {
          sum += n.value;
        }

        double result = (double)sum / (double)queue.size();
        resultList.add(result);
        queue.add(null);

        continue;
      }

      if (node.left != null) {
        queue.add(node.left);
      }

      if (node.right != null) {
        queue.add(node.right);
      }
    }

    return resultList;
  }

  private static Node getNewNode(int value) {
    Node node = new Node();
    node.value = value;

    return node;
  }
}
