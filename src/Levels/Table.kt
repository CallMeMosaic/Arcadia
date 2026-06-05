package Levels

import Gameplay.TarotGameEngine
import Interfaces.LevelFramework.Scene
import de.th_koeln.basicstage.ButtonActor
import de.th_koeln.basicstage.Stage
import de.th_koeln.basicstage.coordinatesystem.WorldConstants
import de.th_koeln.imageprovider.Assets
import java.util.Random
import kotlin.math.cos

class Table(stage: Stage, val gameEngine: TarotGameEngine): Scene(stage) {
    var selectedCardsByPlayer = mutableListOf<ButtonActor>()
    var selectedCardsByDealer = mutableListOf<ButtonActor>()
    var allCards = mutableListOf<ButtonActor>()
    var WhosTurn = "DEALER"
    var round = 1

    //var dealerTime = gameEngine.inverseTime(gameEngine.selectedTime)

    init {
        var gameEngine = gameEngine
        // Find centers of screen
        val centerX = WorldConstants.STAGE_WIDTH / 2
        val centerY = WorldConstants.STAGE_HEIGHT / 2

        // Radius in which to assort cards
        val radius = 280

        // Total numbers of cards in the Major Arcana
        val totalCards = gameEngine.circularLayout.size


        for (i in 0 until totalCards) {
            // 1. Calculate the slice angle of this specific card in radians
            // Full circle is 2 * PI, so dividing by totalCards gives the slice angle
            val angle = (2 * Math.PI / totalCards) * i

            // 2. Trigonometry (Ewwww): Calculate the x and y offsets from the center
            val cardX = centerX + (radius * cos(angle)).toInt()
            val cardY = centerY + (radius * Math.sin(angle)).toInt()
            // I did not do the math here... don't even think I'd know that

            //3. Create a Button Actor placeholder for the card being face down
            val cardButton = ButtonActor("Card_$i")
            cardButton.text.textBackground = Assets.TextBackgrounds.STONE
            allCards.add(cardButton)
            cardButton.text.xOffset = 5
            cardButton.text.yOffset = 15

            // Adjust the coords so the center of the card sits perfectly on the radius line
            cardButton.width = 50
            cardButton.height = 75
            cardButton.x = cardX - (cardButton.width / 2)
            cardButton.y = cardY - (cardButton.height / 2)

            // Initial placeholder text showing it's a mystery card
            cardButton.text.content = "?"
            cardButton.opacity = 80

            addActor(cardButton)

            // 4. Set up the mouse click listener for the card so it can be selected
            cardButton.reactionForMouseClick = {
                println("You selected: Card $i")
                cardButton.visible = false
                selectedCardsByPlayer.add(cardButton)
                handleCardDraw(i,cardButton)
                WhosTurn = "DEALER"
            }
        }

        // Add Counters
        val playerScore = ButtonActor("PlayerScore")
        playerScore.text.content = "Player Score: ${gameEngine.playerScore}"
        playerScore.x = WorldConstants.STAGE_WIDTH - playerScore.width - 10
        playerScore.y = 10
        addActor(playerScore)
        playerScore.animation.everyNsteps.reactionForTimePassed = {
            playerScore.text.content = "Player Score: ${gameEngine.playerScore}"
        }
        playerScore.text.textBackground = Assets.TextBackgrounds.GREEN_BANNER_2
        playerScore.text.xOffset = 10
        playerScore.text.yOffset = -5

        val dealerScore = ButtonActor("DealerScore")
        dealerScore.text.content = "Dealer Score: ${gameEngine.dealerScore}"
        dealerScore.x = -WorldConstants.STAGE_WIDTH + dealerScore.width + 10
        dealerScore.y = 10
        addActor(dealerScore)
        dealerScore.animation.everyNsteps.reactionForTimePassed = {
            dealerScore.text.content = "Dealer Score: ${gameEngine.dealerScore}"
        }
        dealerScore.text.textBackground = Assets.TextBackgrounds.GREEN_BANNER_2
        dealerScore.text.xOffset = 10
        dealerScore.text.yOffset = -5
    }

    private fun handleCardDraw(selectedIndex: Int, button: ButtonActor, dealersTurn: Boolean = false) {
        // Precent clicking or drawing the exact same card twice
        if (!button.visible) {
            //println("Drawing card...")

            // Draw card from game logic, assigns the val to the function return value (Pair) of drawCardAt()
            val (card, gameStatus) = gameEngine.drawCardAt(selectedIndex,dealersTurn)

            // Show orientation string (Upright or Reversed, which I had an enum for, but that didn't work...)
            val orientation = if (card.isUpright) "Upright" else "Reversed"

            // Change the Buttons look to actually make it seem like the cards is being turned
            button.text.content = "$orientation ${card.name} ($orientation: ${card.finalScore})"
            button.opacity = 100 // Highlight revealed card
            if (dealersTurn) {
                println("Dealer has selected: ${card.name} | Points added: ${card.finalScore} | Current Score: ${gameEngine.dealerScore}")
            } else {
                println("You drew: ${card.name} | Points added: ${card.finalScore} | Current Score: ${gameEngine.playerScore}")
            }

            // Check for the Fool
            if (checkMatchStatus(gameStatus,dealersTurn)){
                System.exit(0)
                }
            }

            // Switch turn to oponent
            if (WhosTurn == "DEALER") {
                executeOpponentTurn()

        }
    }

    private fun executeOpponentTurn(){
        WhosTurn = "PLAYER"
        var cardToDraw = Random().nextInt(0, gameEngine.circularLayout.size)
        if (allCards[cardToDraw].visible) {
            //println("Opponent is choosing card...")
            println("Opponent has selected: Card $cardToDraw")
            selectedCardsByDealer.add(allCards[cardToDraw])
            allCards[cardToDraw].visible = false
            handleCardDraw(cardToDraw, allCards[cardToDraw], true)
            if (round < 3) {
                round++
            } else {
                println("Game Over!")
                if (gameEngine.playerScore > gameEngine.dealerScore) {
                    println("You won!")
                    System.exit(0)
                } else {
                    println("You lost!")
                    System.exit(0)
                }
            }
            }
        else {
            WhosTurn = "DEALER"
            executeOpponentTurn()
        }


    }

    private fun checkMatchStatus(status: String, dealersTurn: Boolean): Boolean {
        if (dealersTurn) {
            if (status == "INSTANT_WIN") {
                println("Fool reversed has been drawn!")
                println("Dealer wins!")
                return true
            }
            if (status == "INSTANT_LOOSE") {
                println("Fool upright has been drawn!")
                println("Player wins!")
                return true
            }
        }else if (!dealersTurn) {
            if (status == "INSTANT_WIN") {
                println("Fool reversed has been drawn!")
                println("Player wins!")
                return true
            }
            if (status == "INSTANT_LOOSE") {
                println("Fool upright has been drawn!")
                println("Dealer wins!")
                return true
            }
        }
        return false
    }
}