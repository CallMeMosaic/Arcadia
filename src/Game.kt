import Interfaces.MainMenu
import Levels.SelectionScreen
import Levels.Table
import Gameplay.TarotGameEngine
import de.th_koeln.basicstage.Stage

class Game(stage: Stage) {
    val gameEngine = TarotGameEngine()
    val MainMenu = MainMenu(stage) { SelectionScreen(stage, { Table(stage,gameEngine)}, gameEngine) }

    //var scneneEnsemble = mutableListOf<Scene>()

    fun runGame() {
        MainMenu.runScene()



    }
}