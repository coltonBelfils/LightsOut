package view

import javafx.application.Application
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.event.EventType
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.layout.StackPane
import javafx.scene.text.Font
import javafx.stage.Stage
import lightsOut.LightMap
import java.util.*

class MainGui : Application() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(MainGui::class.java)
        }
    }

    override fun start(stage: Stage) {
        var map = LightMap(5, 5)
        var label = Label()
        label.font = Font.font("monospace", 40.0)

        stage.title = "Title"

        label.text = map.toString()

        val btn = Button()
        btn.text = "Toggle"
        btn.onAction = EventHandler {
            map.toggleLight()
            label.text = map.toString()
        }

        val stackPane = StackPane()
        stackPane.children.add(label)
        //stackPane.children.add(btn)

        val scene = Scene(stackPane, 400.0, 400.0)
        stage.scene = scene
        stage.sizeToScene()
        stage.minHeight = scene.height
        stage.minWidth = scene.width

        stage.show()

        scene.onKeyPressed = EventHandler<KeyEvent> {
            when(it.code) {
                KeyCode.UP, KeyCode.W -> {
                    map.currentSelectionUp()
                    label.text = map.toString()
                }
                KeyCode.RIGHT, KeyCode.D -> {
                    map.currentSelectionRight()
                    label.text = map.toString()
                }
                KeyCode.DOWN, KeyCode.S -> {
                    map.currentSelectionDown()
                    label.text = map.toString()
                }
                KeyCode.LEFT, KeyCode.A -> {
                    map.currentSelectionLeft()
                    label.text = map.toString()
                }
                KeyCode.Q -> {
                    stage.close()
                }
                KeyCode.E, KeyCode.ENTER -> {
                    map.toggleLight()
                    label.text = map.toString()
                }
                KeyCode.R -> {
                    map.generate()
                    label.text = map.toString()
                }
            }
        }
    }
}

/*
when(it.code) {
                    KeyCode.UP -> {
                        map.currentSelectionUp()
                        println("up")
                        label.text = map.toString()
                    }
                    KeyCode.KP_RIGHT -> {
                        map.currentSelectionRight()
                        label.text = map.toString()
                    }
                    KeyCode.KP_DOWN -> {
                        map.currentSelectionDown()
                        label.text = map.toString()
                    }
                    KeyCode.KP_LEFT -> {
                        map.currentSelectionLeft()
                        label.text = map.toString()
                    }
                    KeyCode.Q -> {
                        stage?.close()
                    }
                    KeyCode.E -> {
                        map.toggleLight()
                        label.text = map.toString()
                    }
                }
 */