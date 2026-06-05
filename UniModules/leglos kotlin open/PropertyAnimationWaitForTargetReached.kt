import de.th_koeln.basicstage.Actor
import de.th_koeln.basicstage.motion.Target
import de.th_koeln.basicstage.coordinatesystem.Location
import de.th_koeln.basicstage.coordinatesystem.Size


/**
 * Defines an animation for properties of an Actor,
 * including location, size, opacity and rotation
 * The interface is like an iterator:
 * hasMoreSteps -> true if more animation steps to do
 *                 Implementations must not have side effects!
 * nextStep -> executes the next step by orchestreting
 *             other property animations, setting a property
 *             or even executing a lambda
 *  reset   -> resets the property animation, after calling
 *             it, hasMoreSteps and nextStep should behave
 *             af if the object was just created
 *             this is needed, as a property animation might
 *             be part of composite or sequence that can
 *             be reused (for example in a loop)
 *
 *
 *  Propery animations can be added to an Actor's animation queue.
 *  They could also be used manually in the tickReation of
 *  of an Actor, but that's not recommended. The animation queue does all the tricks.
 *
 */

interface  PropertyAnimation {
    fun hasMoreSteps () : Boolean
    fun nextStep()
    fun reset()
}

/**
 * Takes a list of property animations.
 * The composite has more steps as long as any of its
 * childs has still more steps.
 * Next steps executes all next steps of its childs which
 * have more steps.
 */
class ProperteryAnimationComposite (
    val propertyAnimations : List<PropertyAnimation>
) : PropertyAnimation {
    override fun hasMoreSteps(): Boolean = propertyAnimations.any { it.hasMoreSteps() }

    override fun nextStep() = propertyAnimations.forEach {
        if (it.hasMoreSteps()) {
            it.nextStep()
        }
    }

    // resets all childs
    override fun reset() {
        for (pa in propertyAnimations) {
            pa.reset()
        }
    }

}


/**
 * Defines a sequence of property animations.
 * As soon as one propertyAnimation is done, the next
 * one starts.
 * Caution: before using a sequence, it must be reset.
 */
class PropertyAnimationSequence (val propertyAnimations: List<PropertyAnimation>  ) : PropertyAnimation {

    private var index = 0

    override fun reset() {
        index = 0
        for (pa in propertyAnimations) {
            pa.reset()
        }
    }


    override fun hasMoreSteps(): Boolean {

        while (index<propertyAnimations.size &&
            !propertyAnimations[index].hasMoreSteps() ) {
            index++
        }

        return index<propertyAnimations.size && propertyAnimations[index].hasMoreSteps()

    }


    override fun nextStep() {
        propertyAnimations[index].nextStep()

    }


}


/**
 *  Animates a property from its start value to
 *  its target values in numberOfSteps.
 *  The property name (opacity, x,y ...) needs
 *  to be specified by an AnimatableProperties value.
 *  Note: at the moment location and size need to be animated
 *  in parallel using a composite.
 *
 */

class PropertyAnimationValueChange  (
    _start:Int,
    _end : Int,
    val numberOfSteps : Int,
    val actor : Actor,
    propertyName : AnimatableProperties
) : PropertyAnimation {

    private val executeStep : (Int)->Unit = when (propertyName) {
        AnimatableProperties.ROTATION -> { value ->
            actor.rotation = value
        }
        AnimatableProperties.OPACITY -> { value ->
            actor.opacity = value
        }
        AnimatableProperties.WIDTH -> { value ->
            val d = actor.size
            d.width = value
            actor.size = d  // for size 2x... ineffecient
        }
        AnimatableProperties.HEIGHT -> { value ->
            val d = actor.size
            d.height = value
            actor.size = d  // for size 2x... ineffecient
        }
        AnimatableProperties.X -> { value ->
            val p = actor.location
            p.x = value
            actor.location = p
        }
        AnimatableProperties.Y -> { value ->
            val p = actor.location
            p.y = value
            actor.location = p
        }
    }

    private val start = _start * INTERNAL_SCALE
    private val end = _end * INTERNAL_SCALE
    private val stepSize = (end - start) / numberOfSteps
    var step = 0
    var _currentValue = start
    val currentValue
        get() = _currentValue / INTERNAL_SCALE

    override fun hasMoreSteps():Boolean = step < numberOfSteps
    override fun nextStep() {
        _currentValue += stepSize
        executeStep(currentValue)
        step++
    }

    override fun reset() {
        step = 0
        _currentValue = start
    }

    companion object{
        const val INTERNAL_SCALE=1000
    }
}

/**
 * Can be used in an animation sequence or the animation queue of
 * an actor to delay the next property animation for a number of ticks.
 *
 * Does nothing but waitng for number of ticks to pass.
 * nextStep will be executed for waitTicks times without any
 * property changes or side effects.
 * After waitTicks steps the hasMoreSteps will return false
 */
class PropertyAnimationWait ( val waitTicks : Int) :PropertyAnimation {

    private var remainingWaitTicks = waitTicks

    override fun hasMoreSteps(): Boolean = remainingWaitTicks>0

    override fun nextStep() {
        remainingWaitTicks--
        // do nothing, as we wait this tick
    }

    override fun reset() {
        remainingWaitTicks = waitTicks
    }
}


/**
 * If added to a sequence or the animation queue, this
 * PropertyAnimation will execute the action lambda 1x
 * when nextStep() is invoked for the first time.
 */
class PropertyAnimationSingleAction (
    val action : ()->Unit
) : PropertyAnimation {

    private var consumed = false

    override fun hasMoreSteps(): Boolean = !consumed

    override fun nextStep() {
        consumed = true
        action()
    }

    override fun reset() {
        consumed = false
    }
}

/**
 * Stops when the specified target is reached
 * Caution: NEVER manually set a target for an Actor when
 * this is used. Otherwise, this will supspend the queue forever.
 *
 * This is the most complex [and untested...] animation and
 * is different to the other ones because it depend on the
 * automatic motion of an Actor.
 * It will suspend further execution of animation sequences or
 * the animation queue as long as the target is not reached.
 * This needs to be improved.
 * If the client programmer manually sets a motion target for
 * an actor, then the observerd target of this object will
 * NEVER be reached, or at least this object will never learn
 * about it. Because the messaging mechanism at the moment is
 * a hack: the Motion.move() method will set the targetReached property
 * This hack is used at the moment to ensure, that the targetReached event
 * is fired. By optimizing the execution sequence in move() we may get
 * a better messaging. TODO!
 * Also, I don't like the class's name.
 */

class PropertyAnimationWaitForTargetReached (
    val actor:Actor,
    val target : de.th_koeln.basicstage.motion.Target
) : PropertyAnimation {

    private val targetInAnimationWrapper = TargetInAnimationWrapper()
    inner class TargetInAnimationWrapper : de.th_koeln.basicstage.motion.Target {

        override val location: Location
            get() = target.location

        var targetReached = false

    }

    private var waitingForFirstStep = true


    private fun onStartAnimation () {
        actor.motion.target = targetInAnimationWrapper
        targetInAnimationWrapper.targetReached = false
    }

    override fun hasMoreSteps(): Boolean {
        return waitingForFirstStep || !this.targetInAnimationWrapper.targetReached
    }


    override fun nextStep() {
        if (waitingForFirstStep) {
            onStartAnimation()
            waitingForFirstStep = false
        }
        // do nothing
    }

    override fun reset() {
        waitingForFirstStep = true
        targetInAnimationWrapper.targetReached =false
    }


}


/**
 * Loops a over an animation sequence for loops times.
 */
class PropertyAnimationLoop (
    loops : Int ,
    val animation : PropertyAnimationSequence
): PropertyAnimation {

    private var loopCountDown = loops-1 // we run 1 round anyway
    // TBD!!!
    override fun hasMoreSteps(): Boolean {
        return   animation.hasMoreSteps() || loopCountDown>0
    }

    override fun nextStep() {
        if (!animation.hasMoreSteps() ) {
            if (loopCountDown>0) {
                loopCountDown--
                animation.reset()
            }
        }
        if (animation.hasMoreSteps()) {
            animation.nextStep()
        }

    }

    override fun reset() {
        animation.reset()
    }

}

internal object EmptyPropertyAnimation : PropertyAnimation {
    override fun hasMoreSteps(): Boolean = false
    override fun nextStep() { }
    override fun reset() {}

}

/**
 * Template for property animations that delegate
 * to another property animation (e.g., a composite) and
 * need to be setup on first step() - in order to
 * take the current values of the actor when starting the animation
 * into account
 */
abstract class PropertyAnimationDelegate internal constructor() : PropertyAnimation {

    private var firstCall = true
    protected var animation:PropertyAnimation = EmptyPropertyAnimation

    protected abstract fun initAnimation ()

    override fun hasMoreSteps(): Boolean = firstCall || animation.hasMoreSteps()

    override fun nextStep() {
        if (firstCall) {
            initAnimation() // set up for composite
            firstCall = false
        }
        if (animation.hasMoreSteps()) {
            animation.nextStep()
        }
    }

    override fun reset() {
        firstCall = true
    }


}

class Resize (
    private val actor : Actor,
    private val size : Size,
    private val steps : Int = 20
) : PropertyAnimationDelegate() {

    override fun initAnimation () {
        val widthDelta = size.width - actor.width
        val heightDelta = size.height - actor.height

        // keep center, relocate accoring to size delta
        val newLocation = Location(actor.x - widthDelta/2,actor.y - heightDelta/2)

        animation = ProperteryAnimationComposite(
            listOf<PropertyAnimation>(

                PropertyAnimationValueChange(actor.x, newLocation.x, steps, actor, AnimatableProperties.X),
                PropertyAnimationValueChange(actor.y, newLocation.y, steps, actor, AnimatableProperties.Y),
                PropertyAnimationValueChange(actor.width, size.width, steps, actor, AnimatableProperties.WIDTH),
                PropertyAnimationValueChange(actor.height, size.height, steps, actor, AnimatableProperties.HEIGHT)

            )
        )

    }


}

class Spin (
    private val actor:Actor,
    private val degrees : Int ,
    private val steps : Int
) : PropertyAnimationDelegate() {

    override fun initAnimation() {
        animation =  PropertyAnimationValueChange(
            _start = actor.rotation ,
            _end = actor.rotation + degrees,
            steps,
            actor,
            AnimatableProperties.ROTATION
        )
    }
}

class FadeOutMoveUp(
    private val actor: Actor,
    private val steps: Int = 10,
    private val distance: Int = 20
) : PropertyAnimationDelegate() {
    override fun initAnimation() {
        animation = ProperteryAnimationComposite(
            listOf<PropertyAnimation>(
                PropertyAnimationValueChange(
                    _start = actor.y ,_end = actor.y-distance,
                    steps, actor,
                    AnimatableProperties.Y
                ),
                PropertyAnimationValueChange(
                    _start = 100 ,_end = 0,
                    steps, actor,
                    AnimatableProperties.OPACITY
                )
            )
        )
    }
}


enum class  AnimatableProperties {
    ROTATION, OPACITY , WIDTH , HEIGHT , X , Y
}