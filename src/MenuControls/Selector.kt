package MenuControls

import Interfaces.LevelFramework.Menu
import de.th_koeln.basicstage.Actor
import de.th_koeln.basicstage.ButtonActor
import java.awt.event.KeyEvent
import java.awt.KeyEventPostProcessor
import java.awt.KeyboardFocusManager

// Implementing KeyEventPostProcessor allows us to listen to ALL key actions in the Java app
class Selector(var scene: Menu) : Actor(), KeyEventPostProcessor {
    var currentIndex = 0
    private var isActived = false

    init {
        // Keep the selector actor invisible on screen
        this.opacity = 0
        this.visible = false
    }

    override fun postProcessKeyEvent(e: KeyEvent?): Boolean {
        // Only trigger our logic when the key is initially pressed down (not released)
        if (e?.id == KeyEvent.KEY_PRESSED) {
            if (scene.iterableActorEnsemble.isEmpty()) return false

            // Dim the previously selected element back to standard opacity
            scene.iterableActorEnsemble.elementAt(currentIndex).opacity = 60

            when (e.keyCode) {
                KeyEvent.VK_UP -> {
                    // Navigate up or wrap around to the bottom item
                    currentIndex = if (currentIndex > 0) currentIndex - 1 else scene.iterableActorEnsemble.size - 1
                    handleSelection()
                    return true // Key event handled/consumed
                }
                KeyEvent.VK_DOWN -> {
                    // Navigate down or wrap around to the top item
                    currentIndex = if (currentIndex < scene.iterableActorEnsemble.size - 1) currentIndex + 1 else 0
                    handleSelection()
                    return true // Key event handled/consumed
                }

                KeyEvent.VK_ENTER -> {
                    // Get the currently selected actor from the scene
                    val currentActor = scene.iterableActorEnsemble.elementAt(currentIndex)

                    // Check if it is a ButtonActor
                    if (currentActor is ButtonActor){
                        // Execute the action associated with mouse click
                        currentActor.reactionForMouseClick?.invoke()
                    }
                }
            }
        }
        return false
    }

    fun handleSelection() {
        if (scene.iterableActorEnsemble.isNotEmpty()) {
            // Highlight the newly selected element
            scene.iterableActorEnsemble.elementAt(currentIndex).opacity = 100
        }
    }

    // IMPORTANT: Call this when switching scenes to avoid lingering background listeners!
    fun destroy() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventPostProcessor(this)
    }
    fun activate() {
        if (!isActived) {
            KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventPostProcessor(this)
            isActived = true

            // Highlight the current button
            handleSelection()
        }
    }
    fun deactivate() {
        if (isActived) {
            KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventPostProcessor(this)
            isActived = false
        }
    }
}
