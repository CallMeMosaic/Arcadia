package Levels

import Interfaces.LevelFramework.LevelSwitch
import Interfaces.LevelFramework.Menu
import Interfaces.LevelFramework.Scene
import MenuControls.Selector
import de.th_koeln.basicstage.ButtonActor
import de.th_koeln.basicstage.Stage
import de.th_koeln.basicstage.coordinatesystem.WorldConstants
import de.th_koeln.imageprovider.Assets
import Gameplay.TarotGameEngine
import de.th_koeln.basicstage.Actor

class SelectionScreen(stage: Stage,val nextSceneFactory: () -> Scene, val gameEngine: TarotGameEngine): Menu(stage) {
    lateinit var levelSwitch : LevelSwitch
    val Game = gameEngine

    init {
        // Define all Actors


        // Exit Button
        val pastButton = ButtonActor(
            "Past")
        pastButton.x = WorldConstants.STAGE_WIDTH / 2
        pastButton.y = WorldConstants.STAGE_HEIGHT / 2
        addActor(pastButton)
        iterableActorEnsemble.add(pastButton)

        pastButton.text.content = "Past"
        pastButton.text.textBackground = Assets.TextBackgrounds.STONE
        pastButton.width = WorldConstants.STAGE_WIDTH / 100 * 25
        pastButton.text.xOffset = (pastButton.width / 4).toInt() + 15
        pastButton.text.yOffset = (pastButton.height / 4).toInt() - 5
        pastButton.x = pastButton.x - pastButton.width / 2
        pastButton.opacity = 60




        // Play Button
        val futureButton = ButtonActor(
            "Future"
        )
        futureButton.x = WorldConstants.STAGE_WIDTH / 2
        futureButton.y = WorldConstants.STAGE_HEIGHT / 2 + 100
        addActor(futureButton)
        iterableActorEnsemble.add(futureButton)

        futureButton.text.content = "Future"
        futureButton.text.textBackground = Assets.TextBackgrounds.STONE
        futureButton.width = WorldConstants.STAGE_WIDTH / 100 * 25
        futureButton.text.xOffset = (futureButton.width / 4).toInt() + 10
        futureButton.text.yOffset = (futureButton.height / 4).toInt() - 5
        futureButton.x = futureButton.x - futureButton.width / 2
        futureButton.y = futureButton.y - futureButton.height / 2
        futureButton.opacity = 60


        // Selector
        val selectorSS = Selector(this)
        addActor(selectorSS)
        selectorSS.activate()

        // Add some text, but not add it to the actorEnsemble so it cannot be selected
        val title = Actor("Select a Time", WorldConstants.STAGE_WIDTH / 2, WorldConstants.STAGE_HEIGHT / 10 * 2)
        title.text.content = "Select a Time"
        title.text.textBackground = Assets.TextBackgrounds.STONE
        title.width = WorldConstants.STAGE_WIDTH / 100 * 45
        title.text.xOffset = (title.width / 4).toInt() + 40
        title.text.yOffset = (title.height / 4).toInt() - 8
        title.x = title.x - title.width / 2
        title.y = title.y - title.height / 2
        title.opacity = 100
        addActor(title)


        // The Mouse clicked listeners for the buttons go last, as they need the selector to be initialized
        pastButton.reactionForMouseClick = {
            Game.selectedTime = "PAST"
            selectorSS.deactivate()
            val nextScene = nextSceneFactory()
            println("You selected: Past")
            LevelSwitch(this as Scene, nextScene).switchLevel()
        }
        futureButton.reactionForMouseClick = {
            Game.selectedTime = "FUTURE"
            selectorSS.deactivate()
            val nextScene = nextSceneFactory()
            println("You selected: Future")
            LevelSwitch(this as Scene, nextScene).switchLevel()

        }
    }
}