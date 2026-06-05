import de.th_koeln.basicstage.Actor
import de.th_koeln.basicstage.ButtonActor
import de.th_koeln.basicstage.DisplayActor
import de.th_koeln.basicstage.Stage
import de.th_koeln.basicstage.coordinatesystem.Location
import de.th_koeln.basicstage.coordinatesystem.Size
import de.th_koeln.basicstage.ensembles.*
import de.th_koeln.basicstage.geoevents.IntersectionEventListener
import de.th_koeln.imageprovider.Assets
import kotlin.random.Random



object GameUI {
    val kodee = Actor(Assets.kodee.WELCOMING)
    // ...
}

fun main()  {


    println ("Hello Gummersbach")

    val stage = Stage ()


    // create Buttons:
    val btnPlaySoccer = ButtonActor("Play Soccer")
    val btnRun = ButtonActor("Run")
    val btnBakeCookies = ButtonActor("Bake cookies")

//   btnRun.alignBelow(btnPlaySoccer)
//   btnBakeCookies.alignBelow(btnRun)

    val displayHealth = DisplayActor("Health:")
    val displayHappieness = DisplayActor("Happiness:")
    displayHealth.useHeartIcon()
    displayHappieness.useStarIcon()
//   displayHappieness.alignNextTo(displayHealth)



    stage.addActor(btnPlaySoccer)
    stage.addActor(btnRun)
    stage.addActor(btnBakeCookies)
    stage.addActor(displayHealth)
    stage.addActor(displayHappieness)


    val kodee = Actor(Assets.KODEE)
    val ball = Actor(Assets.toys.BALL)

    kodee.x = 300
    kodee.y = 200

    stage.addActor(kodee)
    stage.addActor(ball)

    ball.width = 20
    ball.height = 20

    kodee.dragging.enabled = true

    btnPlaySoccer.reactionForMousePressed = {
        val x = kodee.x +kodee.width
        val y = kodee.y + kodee.height-20
        ball.y = y
        ball.animation.queue.addPropertyAnimation(
            PropertyAnimationValueChange(x,x+100,20,ball,AnimatableProperties.X)
        )
    }

    btnRun.reactionForMousePressed =  {
        val x = kodee.x
        val y = kodee.y

        kodee.animation.queue.addPropertyAnimation(
            PropertyAnimationValueChange(x,x+150,20,kodee,AnimatableProperties.X)
        )

        kodee.animation.turtleControl.spin(360,40)

        kodee.animation.queue.addPropertyAnimation(
            PropertyAnimationValueChange(x+150,x,20,kodee,AnimatableProperties.X)
        )

    }

    btnBakeCookies.reactionForMousePressed = {
        val cookie = Actor(Assets.snacks.COOKIE3)
        cookie.x = Random.nextInt(300)
        cookie.y = Random.nextInt(300) +200
        cookie.width = 30
        cookie.height = 30
        stage.addActor(cookie)
    }



}

fun demoButtons( stage : Stage ) {

    val btn1 = ButtonActor("Button 1")
    val btn2 = ButtonActor("Button 2")
    btn1.location = Location(5, 5)
    btn2.alignNextTo(btn1)

    val display = DisplayActor("Display Text")
    display.useStarIcon()



    stage.addActor(btn1)
    stage.addActor(btn2)




}

enum class EnsembleSelect {
    DANCE , UFO, PACMAN, DRAG_N_DROP , ANIMATION, SPACE , ALL
}


fun stageWithDemoEnsemble ( which : EnsembleSelect) {
    val stage = Stage()
    when (which) {
        EnsembleSelect.UFO -> stage.addEnsemble( UfoEnsemble() )
        EnsembleSelect.PACMAN -> stage.addEnsemble( PacManEnsemble())
        EnsembleSelect.DRAG_N_DROP -> stage.addEnsemble(DragAndDropEnsemble())
        EnsembleSelect.ANIMATION -> stage.addEnsemble(AnimationEnsemble())
        EnsembleSelect.SPACE ->  stage.addEnsemble(SpaceEnsemble())
        EnsembleSelect.DANCE -> stage.addEnsemble(TurtleEnsemble())
        EnsembleSelect.ALL -> {
            stage.addEnsemble( UfoEnsemble() )
            stage.addEnsemble( PacManEnsemble())
            stage.addEnsemble(DragAndDropEnsemble())
            stage.addEnsemble(AnimationEnsemble())
            stage.addEnsemble(SpaceEnsemble())

        }


    }
}