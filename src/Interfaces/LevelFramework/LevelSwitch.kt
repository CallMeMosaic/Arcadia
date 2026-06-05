package Interfaces.LevelFramework



class LevelSwitch(OriginScene : Scene, DestinationScene : Scene) {
    val originScene = OriginScene
    val destinationScene = DestinationScene

    fun switchLevel() {
        originScene.switchScene(destinationScene)
    }

}