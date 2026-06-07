package Interfaces

import Interfaces.LevelFramework.LevelSwitch
import Interfaces.LevelFramework.Menu
import Interfaces.LevelFramework.Scene
import Levels.DEVLEVEL
import Levels.SelectionScreen
import MenuControls.Selector
import de.th_koeln.basicstage.Actor
import de.th_koeln.basicstage.ButtonActor
import de.th_koeln.basicstage.Stage
import de.th_koeln.basicstage.coordinatesystem.WorldConstants
import de.th_koeln.imageprovider.ActorAppearance
import de.th_koeln.imageprovider.Assets


class MainMenu(stage: Stage, val nextSceneFactory: () -> Scene) : Menu(stage) {
    lateinit var levelSwitch: LevelSwitch
    lateinit var devSwitch: LevelSwitch


    init {
        // Define all Actors


        // Exit Button
        val exitButton = ButtonActor(
            "Exit")
        exitButton.x = WorldConstants.STAGE_WIDTH / 2
        exitButton.y = WorldConstants.STAGE_HEIGHT - WorldConstants.STAGE_HEIGHT / 2
        addActor(exitButton)
        iterableActorEnsemble.add(exitButton)

        exitButton.text.content = "Exit"
        exitButton.text.textBackground = Assets.TextBackgrounds.STONE
        exitButton.width = WorldConstants.STAGE_WIDTH / 100 * 20
        exitButton.text.xOffset = (exitButton.width / 4).toInt() + 5
        exitButton.text.yOffset = (exitButton.height / 4).toInt() - 5
        exitButton.x = exitButton.x - exitButton.width / 2
        exitButton.opacity = 60
        exitButton.reactionForMouseClick = {
                java.lang.System.exit(0)
            }



        // Play Button
        val playButton = ButtonActor(
            "Play"
        )
        playButton.x = WorldConstants.STAGE_WIDTH / 2
        playButton.y = WorldConstants.STAGE_HEIGHT / 10 * 4
        addActor(playButton)
        iterableActorEnsemble.add(playButton)

        playButton.text.content = "Play"
        playButton.text.textBackground = Assets.TextBackgrounds.STONE
        playButton.width = WorldConstants.STAGE_WIDTH / 100 * 20
        playButton.text.xOffset = (playButton.width / 4).toInt() + 5
        playButton.text.yOffset = (playButton.height / 4).toInt() - 5
        playButton.x = playButton.x - playButton.width / 2
        playButton.y = playButton.y - playButton.height / 2
        playButton.opacity = 60



        // Selector
        val selectorMM = Selector(this)
        addActor(selectorMM)
        selectorMM.activate()

        //The button events
        playButton.reactionForMouseClick = {
            val nextScene = nextSceneFactory()
            selectorMM.deactivate()
            LevelSwitch(this as Scene, nextScene).switchLevel()

        }


        // Add some text, but not add it to the actorEnsemble so it cannot be selected
        val title = Actor("Arcadia", WorldConstants.STAGE_WIDTH / 2, WorldConstants.STAGE_HEIGHT / 10 * 2)
        title.text.content = "Arcadia"
        title.text.textBackground = Assets.TextBackgrounds.STONE
        title.width = WorldConstants.STAGE_WIDTH / 100 * 45
        title.text.xOffset = (title.width / 4).toInt() + 40
        title.text.yOffset = (title.height / 4).toInt() - 8
        title.x = title.x - title.width / 2
        title.y = title.y - title.height / 2
        title.opacity = 100
        addActor(title)
        title.reactionForMouseClick = {
            LevelSwitch(this as Scene, DEVLEVEL(stage)).switchLevel()
            Selector(this).deactivate()
        }


    }

    override fun switchScene(Scene: Scene) {
        super.switchScene(Scene)
        
    }


}