import Interfaces.MainMenu
import Levels.SelectionScreen
import Levels.Table
import Gameplay.TarotGameEngine
import de.th_koeln.basicstage.Stage

class Game(stage: Stage) {
    val gameEngine = TarotGameEngine()
    //val MainMenu = MainMenu(stage) { SelectionScreen(stage, { Table(stage,gameEngine)}, gameEngine) }
    val stage = stage

    //var scneneEnsemble = mutableListOf<Scene>()

    fun buildMenu(): MainMenu{
        return MainMenu(stage,{
            SelectionScreen(stage, {
                Table(stage,gameEngine){ buildMenu()} //First try to loop back to main menu
            }, gameEngine)
        },gameEngine)
    }

    val MainMenu = buildMenu()

    fun runGame() {
        MainMenu.runScene()



    }
}