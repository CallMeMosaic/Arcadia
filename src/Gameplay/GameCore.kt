package Gameplay

import Backend.TimesToChoose
import de.th_koeln.basicstage.animation.TimePassedReaction
import kotlin.random.Random

// Tarot Card Class
data class TarotCard(
    val name: String,
    val description: String,
    val baseValue: Int,
    var isUpright: Boolean = true
) {
    /**
     * Getter for final score
     * Checks if the card is upright or not, if upright returns the base value, if not returns the base value but negative
     */
    val finalScore: Int
        get() = if (isUpright) baseValue else -baseValue
}

// End of Tarot Card Class

// Tarot Game Engine Class
class TarotGameEngine {
    val deck = mutableListOf<TarotCard>()
    val circularLayout = mutableListOf<TarotCard>()

    var playerScore = 0
    var dealerScore = 0
    var selectedTime: String = "PAST"

    var playerWin = 0
    var dealerWin = 0

    init {
        setupDeck()
        shuffleIntoCircle()
    }

    private fun setupDeck() {
        val names = listOf(
            "The Fool", "The Magician", "The High Priestess", "The Empress", "The Emperor",
            "The Hierophant", "The Lovers", "The Chariot", "Strength", "The Hermit",
            "Wheel of Fortune", "Justice", "The Hanged Man", "Death", "Temperance",
            "The Devil", "The Tower", "The Star", "The Moon", "The Sun", "Judgement", "The World"
        )
        for (i in names.indices) {
            deck.add(TarotCard(names[i], "Description", i))
        }
    }

    private fun shuffleIntoCircle() {
        val shuffled = deck.shuffled()
        // Go through the shuffled list and randomly assert the orientation
        for (card in shuffled) {
            card.isUpright = Random.nextBoolean()
            circularLayout.add(card)
        }
    }

    fun drawCardAt(index: Int, dealerTurn: Boolean): Pair<TarotCard, String> {
        val card = circularLayout[index]
        if (dealerTurn) {
            var timeSelected = inverseTime(selectedTime)
            if (timeSelected == "PAST") {
                dealerScore += card.finalScore
            } else if (timeSelected == "FUTURE") {
                dealerScore += card.finalScore*2
            }
        } else {
            if (selectedTime == "PAST") {
                playerScore += card.finalScore
            } else if (selectedTime == "FUTURE") {
                playerScore += card.finalScore*2
            }

        }

        // Fool-Rule as he ends the game immediately
        if (card.baseValue == 0) {
            return Pair(card, if (card.isUpright) "INSTANT_WIN" else "INSTANT_LOSS")
        }
        return Pair(card, "CONTINUE")
    }

    fun inverseTime(time: String): String {
        return if (time == "PAST") "FUTURE" else "PAST"
    }

    fun reset(){
        playerScore = 0
        dealerScore = 0
        selectedTime = ""
    }
}
