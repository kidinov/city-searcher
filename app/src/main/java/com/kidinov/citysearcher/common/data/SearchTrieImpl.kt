package com.kidinov.citysearcher.common.data

import java.util.LinkedList

interface SearchTrie<T> {
    fun search(prefix: String): MutableList<T>
    fun insert(word: String, value: T)
}

/**
 * Classic data structure for a quick prefix search.
 * O(m*n) search complexity where m word length, n amount of the words
 */
class SearchTrieImpl<T> : SearchTrie<T> {
    private val root = Node<T>()

    override fun search(prefix: String): MutableList<T> {
        fun findBottomNode(): Node<T>? {
            var cur: Node<T>? = root

            for (c in prefix) {
                cur = requireNotNull(cur).children[c]
                if (cur == null) return null
            }
            return cur
        }

        fun findAllWordsBelow(bottom: Node<T>): MutableList<T> {
            val result = LinkedList<T>()
            val q = LinkedList<Node<T>>()
            q.push(bottom)
            while (q.isNotEmpty()) {
                val cur = q.pollLast()
                if (cur.values?.isNotEmpty() == true) result.addAll(cur.values!!)
                for (node in cur.children) {
                    q.push(node.value)
                }
            }
            return result
        }

        return findAllWordsBelow(findBottomNode() ?: return mutableListOf())
    }

    override fun insert(word: String, value: T) {
        var cur: Node<T>? = root

        for (c in word.toLowerCase()) {
            if (cur!!.children[c] == null) {
                cur.children[c] = Node()
            }
            cur = cur.children[c]
        }

        if (requireNotNull(cur).values == null) cur.values = LinkedList()
        cur.values!!.add(value)
    }

    private class Node<T> {
        var values: LinkedList<T>? = null
        val children = HashMap<Char, Node<T>?>()
    }
}