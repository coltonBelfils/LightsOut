package lightsOut

import kotlin.random.Random

class LightMap(xSize: Int, ySize: Int) {
    private val xSize: Int = if(xSize in 1..10) xSize else 5
    private val ySize: Int = if(ySize in 1..10) ySize else 5
    var rand: Random? = Random(System.currentTimeMillis())
    private lateinit var currentSelection: Node

    private inner class Node {
        var xPos: Int? = null
        var yPos: Int? = null
        var lightOn: Boolean = rand?.nextBoolean() ?: false
            private set
        var upper: Node? = null
            set(value) {
                if(value != null) field = value
            }
        var right: Node? = null
            set(value) {
                if(value != null) field = value
            }
        var lower: Node? = null
            set(value) {
                if(value != null) field = value
            }
        var left: Node? = null
            set(value) {
                if(value != null) field = value
            }

        fun toggleLight() {
            lightOn = !lightOn
            upper?.lightOn = upper?.lightOn?.not() ?: false
            right?.lightOn = right?.lightOn?.not() ?: false
            lower?.lightOn = lower?.lightOn?.not() ?: false
            left?.lightOn = left?.lightOn?.not() ?: false
        }

        override fun toString(): String {
            return if(lightOn) "O" else "X"
        }
    }

    private var map = Array<Array<Node>>(this.ySize) {
        Array<Node>(this.xSize) {
            Node()
        }
    }

    init {
        generate()
    }

    fun generate() {
        map = Array<Array<Node>>(this.ySize) {
            Array<Node>(this.xSize) {
                Node()
            }
        }
        for(a in map.indices) {
            for(n in map[a].indices) {
                val cur = map[a][n]
                cur.xPos = n
                cur.yPos = a
                cur.upper = map.getOrNull(a - 1)?.get(n)
                cur.right = map[a].getOrNull(n + 1)
                cur.lower = map.getOrNull(a + 1)?.get(n)
                cur.left = map[a].getOrNull(n - 1)
            }
        }
        currentSelection = if(this::currentSelection.isInitialized) {
            val x = currentSelection.xPos ?: 0
            val y = currentSelection.yPos ?: 0
            map[y][x]
        } else {
            map[0][0]
        }
    }

    fun currentSelectionUp(): Boolean {
        return if(currentSelection.upper != null) {
            currentSelection = currentSelection.upper!!
            true
        } else {
            false
        }
    }

    fun currentSelectionRight(): Boolean {
        return if(currentSelection.right != null) {
            currentSelection = currentSelection.right!!
            true
        } else {
            false
        }
    }

    fun currentSelectionDown(): Boolean {
        return if(currentSelection.lower != null) {
            currentSelection = currentSelection.lower!!
            true
        } else {
            false
        }
    }

    fun currentSelectionLeft(): Boolean {
        return if(currentSelection.left != null) {
            currentSelection = currentSelection.left!!
            true
        } else {
            false
        }
    }

    fun toggleLight() {
        currentSelection.toggleLight()
    }

    override fun toString(): String {
        val str = StringBuilder()
        for(a in map.indices) {
            for(n in map[a].indices) {
                val cur = map[a][n]
                when (cur) {
                    currentSelection -> str.append("[$cur]")
                    else -> str.append(" $cur ")
                }
            }
            str.append("\n")
        }
        return str.toString()
    }
}