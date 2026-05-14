package com.mycompany.practica3algodicegame;

import java.util.ArrayList;

/**
 * Clase Node
 * Models a node in an 8 way linked list
 * @author Cecilia M. Curlango Rosas
 * @version 01 2026
 */

// Representa un nodo del tablero con valor y 8 conexiones
public class Node implements Comparable<Node> {

    private int number;
    private Node up;
    private Node down;
    private Node left;
    private Node right;
    private Node downLeft;
    private Node downRight;
    private Node upLeft;
    private Node upRight;

    // Construye un nodo con numero
    public Node(int number) {
        this.number = number;
        up = null;
        down = null;
        left = null;
        right = null;
        downLeft = null;
        downRight = null;
        upLeft = null;
        upRight = null;
    }

    // Elimina todas las conexiones del nodo
    public void delete() {
        if (up != null && up.down == this) up.down = null;
        if (down != null && down.up == this) down.up = null;
        if (left != null && left.right == this) left.right = null;
        if (right != null && right.left == this) right.left = null;

        if (upLeft != null && upLeft.downRight == this) upLeft.downRight = null;
        if (upRight != null && upRight.downLeft == this) upRight.downLeft = null;
        if (downLeft != null && downLeft.upRight == this) downLeft.upRight = null;
        if (downRight != null && downRight.upLeft == this) downRight.upLeft = null;

        up = null;
        down = null;
        left = null;
        right = null;
        downLeft = null;
        downRight = null;
        upLeft = null;
        upRight = null;
    }

    // Verifica si el nodo recibido es vecino
    public boolean isNeighbor(Node input) {
        if (input == null) {
            return false;
        }

        return input == up ||
                input == down ||
                input == left ||
                input == right ||
                input == upLeft ||
                input == upRight ||
                input == downLeft ||
                input == downRight;
    }

    // Verifica si dos nodos concuerdan por valor
    public boolean isMatchValue(Node input) {
        if (input == null) {
            return false;
        }

        return number == input.number || number + input.number == 10;
    }

    // Compara dos nodos usando la regla del juego
    @Override
    public int compareTo(Node otroNodo) {
        if (otroNodo == null) {
            return -1;
        }

        if (isMatchValue(otroNodo)) {
            return 0;
        }

        return -1;
    }

    // Regresa todos los vecinos no nulos
    public ArrayList<Node> getNeighbors() {
        ArrayList<Node> vecinos = new ArrayList<>();

        if (up != null) vecinos.add(up);
        if (down != null) vecinos.add(down);
        if (left != null) vecinos.add(left);
        if (right != null) vecinos.add(right);
        if (upLeft != null) vecinos.add(upLeft);
        if (upRight != null) vecinos.add(upRight);
        if (downLeft != null) vecinos.add(downLeft);
        if (downRight != null) vecinos.add(downRight);

        return vecinos;
    }

    public Node getDown() {
        return down;
    }

    public void setDown(Node down) {
        this.down = down;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getDownLeft() {
        return downLeft;
    }

    public void setDownLeft(Node downLeft) {
        this.downLeft = downLeft;
    }

    public Node getDownRight() {
        return downRight;
    }

    public void setDownRight(Node downRight) {
        this.downRight = downRight;
    }

    public Node getUpLeft() {
        return upLeft;
    }

    public void setUpLeft(Node upLeft) {
        this.upLeft = upLeft;
    }

    public Node getUpRight() {
        return upRight;
    }

    public void setUpRight(Node upRight) {
        this.upRight = upRight;
    }

    public Node getUp() {
        return up;
    }

    public void setUp(Node up) {
        this.up = up;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return number + "";
    }
}