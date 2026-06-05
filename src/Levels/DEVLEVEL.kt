package Levels

import Interfaces.LevelFramework.LevelSwitch
import Interfaces.LevelFramework.Menu
import Interfaces.LevelFramework.Scene
import de.th_koeln.basicstage.Actor
import de.th_koeln.basicstage.ButtonActor
import de.th_koeln.basicstage.Stage
import de.th_koeln.basicstage.coordinatesystem.WorldConstants
import de.th_koeln.imageprovider.ActorAppearance
import de.th_koeln.imageprovider.Assets

class DEVLEVEL(stage: Stage): Scene(stage) {
    val levelSwitch = LevelSwitch(this, Menu(stage))

    // FOR TESTING PURPOSES ONLY
    init {
        // Exit Button
        val exitButton = ButtonActor(
            "Exit")
        exitButton.x = WorldConstants.STAGE_WIDTH / 2
        exitButton.y = WorldConstants.STAGE_HEIGHT - WorldConstants.STAGE_HEIGHT / 2
        addActor(exitButton)
        //iterableActorEnsemble.add(exitButton)

        exitButton.text.content = "TEST"
        exitButton.text.textBackground = Assets.TextBackgrounds.STONE
        exitButton.width = WorldConstants.STAGE_WIDTH / 100 * 20
        exitButton.text.xOffset = (exitButton.width / 4).toInt() + 5
        exitButton.text.yOffset = (exitButton.height / 4).toInt() - 5
        exitButton.x = exitButton.x - exitButton.width / 2
        exitButton.opacity = 60
        exitButton.reactionForMouseClick = {
            java.lang.System.exit(0)
        }

        // Exit Button
        val TEMPButton = Actor(
            "Exit"
        )
        TEMPButton.text.content = "Exit"
        addActor(TEMPButton)
        TEMPButton.appearance = ActorAppearance("./src/Assets/NONE.png")

    }
}