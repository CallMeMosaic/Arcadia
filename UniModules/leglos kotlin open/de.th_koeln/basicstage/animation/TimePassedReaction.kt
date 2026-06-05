package de.th_koeln.basicstage.animation

import PropertyAnimation
import PropertyAnimationSingleAction
import PropertyAnimationWait
import de.th_koeln.basicstage.Actor
import de.th_koeln.basicstage.controller.ReactionCodeBlock

/**
 * Encapsulates code block for time passed and time span into
 * one object.
 * reactionTimePressed will be exectued every timeSpan ticks
 */
class TimePassedReaction internal constructor(){
    var reactionForTimePassed : (()->Unit)?   = null
    var timeSpan = 1
}

/**
 * Queues animations for an actor and provides a TimePassedReation
 * to define client's action as a code block that is executed frequently
 * after a passed time span (number of ticks).
 *
 * Objects of this class are only used for Actor's animation property.
 *
 *
 */
class ActorAnimation internal constructor ( actor : Actor) {

    /**
     * An actor can be animated by manually changing its properties,
     * such as location, size, opacity, after a set time span.
     * everyNSteps encapsulates both the reactionCode and the timeSpan.
     */
    val everyNsteps = TimePassedReaction()

    /**
     * Provides a convenient way to add animation steps to the
     * animation queue.
     *
     * It basically makes the execution of motion steps and
     * property changes asynchronous. That is, instead of
     * executing a command immediately, it will be added to the queue.
     *
     * Think of this scenario: you want to change the x value of
     * an actor in 10 steps. One could write this code in one of
     * the reactTo... code blocks:
     * for (i in 1..10) {
     *   myActor.x = i * 20
     * }
     *
     * What's wrong with this?
     * The x value will change immediately. Thus, if you run the show
     * you will only see the last set x value!
     *
     * What you probably want is, that each setting of x is actually
     * visible to the user. To achieve this, the actual setting of
     * setting each of the different x values needs to wait for the
     * next time tick. Adding the code myActor.x = i * 20 to the
     * animation queue would do the trick:
     * for (i in 1..10) {
     *   queue.addSingleActionCode { myActor.x = i * 20 }
     * }
     *
     * Now the for loops adds 10 code blocks to the
     * animation queue, and they are executed one after another
     * after a time tick.
     *
     * The turtleControl makes adding such actions even easier
     * as it adds code to the animation queue for moving an
     * actor to the right or forward or rotating it:
     *   turtleControl.moveRightBy(20)
     *   turtleControl.forward(20)
     *   turtleControl.turnRight(30)
     *
     * If you are familiar with Scratch, just think of the
     * animation queue as the code you can define for
     * a Sprite. And the turtleControl provides commands
     * to control the motion of the Actor.
     */
    val turtleControl = Turtle(actor)

    /**
     * The animation queue has a list of
     * PropertyAnimation objects.
     *
     * On each step() of the animation object, the
     * current animation will proceed one step.
     * If there are no more steps, the next animation from
     * the queue will be taken.
     *
     * This is like animation pane (Animationsbereich) in
     * PowerPoint. Each animation is executed after another.
     *
     * Another analogy is are command blocks in Scratch that
     * are executed one after another.
     *
     */
    val queue = PropertyAnimationQueue()

    /**
     *  Count the time passed to invoke reactionForTimePassed
     *  when needed
     */
    private var stepCounter = 0

    /**
     * Executes
     *   - the reactionForTimePassed if the time has passed
     *   - the next step from the queue
     * invoked by Actor's step() method
     */
    internal fun step() {
        stepCounter++
        if (stepCounter>=everyNsteps.timeSpan) {
            everyNsteps.reactionForTimePassed?.invoke()
            stepCounter = 0
        }

        // execute next animation step from queue
        queue.step()

    }

    /**
     * A queue of property animations.
     * Each property animation is an iterator that provides
     * hasMoreSteps, nextStep and reset methods.
     * Each property animation is run as long as it hasMoreSteps.
     * Running meahs that nextStep is executed on each time tick.
     * If the current property animation is exhausted (hasMoreSteps is false),
     * then the next property animation from the queue is taken.
     *
     * This class stores the actual list of property animations
     * and provides some convenienc methods to add specific
     * property animations.
     *
     * To learn more about Property Animation have a look at
     * the PropertyAnimation interface and its implementations:
     * PropertyAnimationValueChange: Changes a property value from start to end over time
     * PropertyAnimationComposite: runs multiple property animations at the same time, i.e. to
     * set x,y and opacity a the same time
     * PropertyAnimationSequence: a sequence of PropertyAnimations that are executed ony by one
     * PropertyAnimationLoop: runs a PropertyAnimationSequence n times
     * PropertyAnimationWait: waits n time step (does nothing for n time ticks)
     * PropertyAnimaationWaitForTargetReached: moves an actor towards its target. it is
     * active until the target is reached or the motion target is changed by some other code
     * PropertayAnimationSingleAction: has only one step and executes a client defined
     * code block in that step. This is useful to manually set properties
     * between two animations, e.g. change the rotation before the next animation starts
     * it is also useful to do some business logic after an animation
     * was executed.
     */
    class PropertyAnimationQueue internal constructor()  {

        // Stores the property animations
        private val animationQueue = mutableListOf<PropertyAnimation>()

        // currently active animation if any
        private var currentAnimation : PropertyAnimation? = null

        /**
         * Add a property animation to the queue.
         * It will be added at the end and executed after
         * all other animations that are already in the queue.
         */
        fun addPropertyAnimation ( propertyAnimation: PropertyAnimation) {
            animationQueue.add(propertyAnimation)
        }

        /**
         * Adds a code block to the animation queue.
         * If the animation queue gets to the action,
         * it will be invoked.
         *
         * Convenience method to wrap a code block into
         * a PropertyAnimationSingleAction and add it
         * to the queue.
         *
         * @param action the code block to be executed when the animation queue has reached
         * the single action property animation
         */
        fun addSingleActionCode(action : ()->Unit ) {
            addPropertyAnimation(PropertyAnimationSingleAction(action))
        }

        // ONLY JAVA
        fun addSingleActionCocde (action: ReactionCodeBlock) {
            addSingleActionCode { action.invoke() }
        }

        /**
         * Add a time delay to the animation queue.
         * Convenience method to add a PropertyAnimationWait
         * @param numberOfSteps time steps to wait before the animation queue proceeds
         */
        fun addWait ( numberOfSteps:Int) {
            addPropertyAnimation(PropertyAnimationWait(numberOfSteps))
        }

        /**
         * Clear all animations from the queue. Stops all animations fromt queue.
         */
        fun clear () {
            animationQueue.clear()
            currentAnimation = null
        }

        /**
         * Execute the next step from the current animation.
         */
        internal fun step() {
            // If currentAnimation is exhausted, set it to null...
            val hasMoreSteps = currentAnimation?.hasMoreSteps() ?: false
            if (!hasMoreSteps) {
                currentAnimation = null
            }

            // ...and try to get a new currentAnimation from the queue
            if (currentAnimation == null && animationQueue.size > 0) {
                currentAnimation = animationQueue.removeAt(0)
            }

            currentAnimation?.nextStep()  // execute animation if there is any

        }

    }

}