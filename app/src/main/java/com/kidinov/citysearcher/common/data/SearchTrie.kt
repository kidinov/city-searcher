package com.kidinov.citysearcher.common.data

import java.util.LinkedList
import java.util.TreeMap

/**
 * Classic data structure for a quick prefix search.
 * O(m*n) search complexity where m word length, n amount of the words
 */
class SearchTrie<T> {
    private val root = Node<T>()

    fun search(prefix: String): List<T> {
        fun findBottomNode(): Node<T>? {
            var cur: Node<T>? = root

            for (c in prefix) {
                cur = requireNotNull(cur).children[c]
                if (cur == null) return null
            }
            return cur
        }

        fun findAllWordsBelow(bottom: Node<T>): List<T> {
            val result = LinkedList<T>()
            val q = LinkedList<Node<T>>()
            q.push(bottom)
            while (q.isNotEmpty()) {
                val cur = q.pollLast()
                if (cur.values?.isNotEmpty() == true) result.addAll(cur.values!!)
                for (node in cur.children) {
                    if (node.value?.values?.isNotEmpty() == true) result.addAll(node.value!!.values!!)
                    else q.push(node.value)
                }
            }
            return result
        }

        return findAllWordsBelow(findBottomNode() ?: return ArrayList())
    }

    fun insert(word: String, value: T) {
        var cur: Node<T>? = root

        for (c in word.toLowerCase()) {
            if (cur!!.children[c] == null) {
                cur.children[c] = Node()
            }
            cur = cur.children[c]
        }

        if (requireNotNull(cur).values == null) cur.values = ArrayList()
        cur.values!!.add(value)
    }

    private class Node<T> {
        var values: ArrayList<T>? = null
        val children = TreeMap<Char, Node<T>?>()
    }
}