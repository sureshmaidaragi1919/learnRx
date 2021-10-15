package com.mobile.practice.longwork

import java.util.*


fun main() {
    print(sort(7, arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)))

    var node = Node(7)
    node.left = Node(1)
    node.right = Node(3)
    node.left!!.left = Node(4)

    println(searchTargetInTree(1, node))
    println(searchTargetInTreeUsingIterator(1, node))
}

fun sort(target: Int, arr: Array<Int>): Int {

    var left = 0
    var right = arr.size - 1


    while (left <= right) {

        val mid = (left + right) / 2

        if (arr[mid] == target) {
            return mid
        } else if (arr[mid] > target) {
            right = mid - 1
        } else
            left = mid + 1
    }

    return -1

}

class Node(
    data: Int
) {
    var data: Int = data
    var left: Node? = null
    var right: Node? = null


}

fun searchTargetInTree(target: Int, node: Node): Boolean {

    if (node == null)
        return false;
    if (node.data == target)
        return true;
    return searchTargetInTree(target, node.left!!) || searchTargetInTree(target, node.right!!)

}

fun searchTargetInTreeUsingIterator(target: Int, node: Node): Boolean {

    val queue = LinkedList<Node>()
    queue.push(node)

    while (queue != null) {
        var value = queue.pop()

        if (value.data == target)
            return true

        if (value.left != null)
            queue.push(value.left)

        if (value.right != null)
            queue.push(value.right)
    }

    return false
}