package lightsOut

import java.util.*

class LightsOut {

    private val map = LightMap(5, 5)
    private var quit = false

    fun run() {
        print(map)
        val input = Scanner(System.`in`)
        while(!quit) {
            when(input.nextLine().toLowerCase()) {
                "w" -> map.currentSelectionUp()
                "d" -> map.currentSelectionRight()
                "s" -> map.currentSelectionDown()
                "a" -> map.currentSelectionLeft()
                "q" -> quit = true
                "e" -> map.toggleLight()
            }
            print(map)
        }
    }
}