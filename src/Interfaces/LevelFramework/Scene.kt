package Interfaces.LevelFramework

import de.th_koeln.basicstage.Actor
import de.th_koeln.basicstage.Stage

/**
 * Class that is meant to hold all actors for a scene
 * and provide methods to add, remove or clear an entire level
 * therefore enabling to switch between prefabricated scenes
 *
 */

open class Scene (stage : Stage) {
    val stage = stage
    var actorEnsemble = mutableListOf<Actor>()

    open fun runScene():Any? {
        for (actor in actorEnsemble) {
            actor.visible = true
        }
        return null
    }

    open fun addActor(actor : Actor) {
        actorEnsemble.add(actor)
        stage.addActor(actor)
        actor.visible = false
    }
    open fun removeActor(actor : Actor) {
        actorEnsemble.remove(actor)
        stage.removeActor(actor)
    }
    open fun clear() {
        actorEnsemble.clear()
    }
    open fun switchScene(Scene : Scene){
        for (actor in actorEnsemble) {
            actor.visible = false
        }
        Scene.runScene()
    }
}