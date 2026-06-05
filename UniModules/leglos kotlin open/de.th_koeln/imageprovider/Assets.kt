package de.th_koeln.imageprovider

import de.th_koeln.basicstage.uicomponents.CustomBubble
import de.th_koeln.basicstage.uicomponents.CustomButton
import de.th_koeln.basicstage.uicomponents.ImageButton

/**
 * Provide included images as assets
 */
class Assets {

    class TextIcons internal constructor () {
        companion object {
            val STAR = UIIconAppearance("asset:imgassets/ui/star_icon.png")
            val COINS = UIIconAppearance("asset:imgassets/ui/coin_icon.png")
            val MONEY = UIIconAppearance("asset:imgassets/ui/money_icon.png")
            val DIAMOND = UIIconAppearance("asset:imgassets/ui/diamond_icon.png")
            val HEART = UIIconAppearance("asset:imgassets/ui/heart_icon.png")

        }
    }

    class TextBackgrounds internal constructor () {
        companion object {
            val SIMPLE_BUBBLE = CustomBubble()
            val SIMPLE_BUTTON = CustomButton()
            val BLUE_BUTTON = ImageButton(UIAppearance("asset:imgassets/ui/blue_btn_on.png" ,
                "asset:imgassets/ui/blue_btn_off.png"
            ),15,6)

            val GREEN_BANNER = ImageButton(
                UIAppearance("asset:imgassets/ui/green_banner.png"  ,"asset:imgassets/ui/green_banner.png"
                ),15,6)

            val GREEN_BANNER_2 = ImageButton(
                UIAppearance("asset:imgassets/ui/green_flow.png"  ,"asset:imgassets/ui/green_flow.png"
                ),15,6)

            val MENU1 = ImageButton(
                UIAppearance("asset:imgassets/ui/menu.png"  ,"asset:imgassets/ui/menu.png"
                ),15,6)

            val MENU2 = ImageButton(
                UIAppearance("asset:imgassets/ui/menu2.png"  ,"asset:imgassets/ui/menu2.png"
                ),15,6)

            val MENU3 = ImageButton(
                UIAppearance("asset:imgassets/ui/menu3.png"  ,"asset:imgassets/ui/menu3.png"
                ),15,6)

            val QUIZ = ImageButton(
                UIAppearance("asset:imgassets/ui/quiz.png"  ,"asset:imgassets/ui/quiz.png"
                ),15,6)

            val STONE = ImageButton(
                UIAppearance("asset:imgassets/ui/stone.png"  ,"asset:imgassets/ui/stone.png"
                ),15,6)


            val BLUE_BANNER = ImageButton(
                UIAppearance("asset:imgassets/ui/blue_banner.png"  ,"asset:imgassets/ui/blue_banner.png"
                ),15,6)

            val GOLD_BANNER = ImageButton(
                UIAppearance("asset:imgassets/ui/gold_banner.png"  ,"asset:imgassets/ui/gold_banner.png"
                ),15,6)

            val ICE_BANNER = ImageButton(
                UIAppearance("asset:imgassets/ui/ice_banner.png"  ,"asset:imgassets/ui/ice_banner.png"
                ),15,6)


            val BUBBLE1 = ImageButton(
                UIAppearance("asset:imgassets/ui/bubble1.png"  ,"asset:imgassets/ui/bubble1.png"
                ),15,6)

            val BUBBLE2 = ImageButton(
                UIAppearance("asset:imgassets/ui/bubble2.png"  ,"asset:imgassets/ui/bubble2.png"
                ),15,6)

            val BUBBLE3 = ImageButton(
                UIAppearance("asset:imgassets/ui/bubble3.png"  ,"asset:imgassets/ui/bubble3.png"
                ),15,6)




        }
    }


    class MiscAssets internal constructor() {
        companion object {
            const val BALL = "asset:imgassets/misc/ball.png"
            const val BASKET = "asset:imgassets/misc/basket.png"
            const val BOWL_BLACK = "asset:imgassets/misc/bowl_purple.png"
            const val BOWL_BLUE_DARK = "asset:imgassets/misc/bowl_blue.png"
            const val BOWL_BLUE_LIGHT = "asset:imgassets/misc/bowl_blue_light.png"
            const val BOWL_GREEN = "asset:imgassets/misc/bowl_green.png"
            const val BOWN_PINK = "asset:imgassets/misc/bowl_pink.png"
            const val BOWL_PURPLE = "asset:imgassets/misc/bowl_purple.png"
            const val BOWL_RED = "asset:imgassets/misc/bowl_red.png"
            const val BOWL_YELLOW = "asset:imgassets/misc/bowl_yellow.png"
            const val DROP_ZONE = "asset:imgassets/misc/dropzone.png"
            const val FLAG = "asset:imgassets/misc/flag.png"
            const val GOAL = "asset:imgassets/misc/goal.png"
            const val LEVEL_UP = "asset:imgassets/misc/levelup.png"
            const val PACMAN = "asset:imgassets/misc/pacman.png"
            const val SHELF = "asset:imgassets/misc/shelf.png"
            const val SHOP = "asset:imgassets/misc/shop.png"
            const val SPACECRAFT = "asset:imgassets/misc/spacecraft.png"
            const val SPACECRAFT2 = "asset:imgassets/misc/spacecraft2.png"
            const val TURTLE = "asset:imgassets/misc/turtle1.png"
            const val TURTLE2 = "asset:imgassets/misc/turtle2.png"
            const val UFO = "asset:imgassets/misc/ufo1.png"
            const val UFO2 = "asset:imgassets/misc/ufo2.png"
        }
    }

    class BubbleAssets internal constructor() {
        companion object {
            const val BOOM = "asset:imgassets/bubbles/boom.png"
            const val CONFUSION = "asset:imgassets/bubbles/confusion.png"
            const val OK = "asset:imgassets/bubbles/ok.png"
            const val OMG = "asset:imgassets/bubbles/omg.png"
            const val OOPS = "asset:imgassets/bubbles/oops.png"
            const val POW = "asset:imgassets/bubbles/pow.png"
            const val YES = "asset:imgassets/bubbles/yes.png"
        }
    }

    class ToysAssets internal constructor() {
        companion object {

            const val BALLONS = "asset:imgassets/toys/ballons.png"
            const val BOAT = "asset:imgassets/toys/boat.png"
            const val DRUMS = "asset:imgassets/toys/drums.png"
            const val HORSE = "asset:imgassets/toys/horse.png"
            const val RUBBER_DUCK = "asset:imgassets/toys/rubberDuck.png"
            const val SPINNER = "asset:imgassets/toys/spinner.png"
            const val TEDDY = "asset:imgassets/toys/teddy.png"
            const val TICTACTOE_X = "asset:imgassets/toys/tictactoe_X.png"
            const val TICTACTOE_O = "asset:imgassets/toys/tictactoe_O.png"
            const val TICTACTOE_EMPTY = "asset:imgassets/toys/tictactoe_empty.png"
            const val TRAIN = "asset:imgassets/toys/train.png"
            const val BALL = "asset:imgassets/misc/ball.png"

        }
    }

    class CatAssets internal constructor() {
        companion object {
            const val ANNOYED = "asset:imgassets/cat/annoyed.png"
            const val CATCHING = "asset:imgassets/cat/catching.png"
            const val CHASING = "asset:imgassets/cat/chasing.png"
            const val DANCING = "asset:imgassets/cat/dancing.png"
            const val HAPPY = "asset:imgassets/cat/happy.png"
            const val LYING = "asset:imgassets/cat/lying.png"
            const val PLAYING = "asset:imgassets/cat/playing.png"
            const val SAD = "asset:imgassets/cat/sad.png"
            const val SITTING = "asset:imgassets/cat/sitting.png"
            const val SLEEPING = "asset:imgassets/cat/sleeping.png"

        }
    }

    class DogAssets internal constructor() {
        companion object {
            const val AGREE = "asset:imgassets/dog/agree.png"
            const val ANGRY = "asset:imgassets/dog/angry.png"
            const val BARKING = "asset:imgassets/dog/barking.png"
            const val EXHAUSTED = "asset:imgassets/dog/exhausted.png"
            const val GRATEFUL = "asset:imgassets/dog/grateful.png"
            const val HAPPY = "asset:imgassets/dog/happy.png"
            const val HIDE = "asset:imgassets/dog/hide.png"
            const val SAD = "asset:imgassets/dog/sad.png"
            const val SLEEPING = "asset:imgassets/dog/sleeping.png"
        }
    }

    class FlowerAssets internal constructor(){
        companion object {
            const val FLOWER1 = "asset:imgassets/flowers/flower1.png"
            const val FLOWER2 = "asset:imgassets/flowers/flower2.png"
            const val FLOWER3 = "asset:imgassets/flowers/flower3.png"
            const val FLOWER4 = "asset:imgassets/flowers/flower4.png"
            const val FLOWER5 = "asset:imgassets/flowers/flower5.png"
            const val FLOWER6 = "asset:imgassets/flowers/flower6.png"
            const val FLOWER7 = "asset:imgassets/flowers/flower7.png"

        }
    }

    class GhostAssets internal constructor() {
        companion object {
            const val BOO = "asset:imgassets/ghosts/boo.png"
            const val COMPLAINING = "asset:imgassets/ghosts/complaining.png"
            const val DAYDREAMING = "asset:imgassets/ghosts/daydreaming.png"
            const val FLYING = "asset:imgassets/ghosts/flying.png"
            const val FLYING2 = "asset:imgassets/ghosts/flying2.png"
            const val HAPPY = "asset:imgassets/ghosts/happy.png"
            const val LAUGHING = "asset:imgassets/ghosts/laughing.png"
            const val LISTENING = "asset:imgassets/ghosts/listening.png"
            const val NAUGHTY = "asset:imgassets/ghosts/naughty.png"
            const val SCARRY = "asset:imgassets/ghosts/scarry.png"
            const val SHOUTING = "asset:imgassets/ghosts/shouting.png"

        }
    }

    class KodeeAssets internal constructor() {
        companion object {
            const val ANGRY = "asset:imgassets/kodee/kodee-angry.png"
            const val BROKEN_HEARTED = "asset:imgassets/kodee/kodee-broken-hearted.png"
            const val ELECTRIFIED = "asset:imgassets/kodee/kodee-electrified.png"
            const val EXCITED = "asset:imgassets/kodee/kodee-excited.png"
            const val FRIGHTENED = "asset:imgassets/kodee/kodee-frightened.png"
            const val LOVING = "asset:imgassets/kodee/kodee-loving.png"
            const val NAUGHTY = "asset:imgassets/kodee/kodee-naughty.png"
            const val WELCOMING = "asset:imgassets/kodee/kodee-welcoming.png"

        }
    }

    class MonsterAssets internal constructor() {
        companion object {
            const val GHOST_BLUE = "asset:imgassets/monster/ghost_blue.png"
            const val GHOST_GREEN = "asset:imgassets/monster/ghost_green.png"
            const val GHOST_RED = "asset:imgassets/monster/ghost_red.png"
            const val GHOST_YELLOW = "asset:imgassets/monster/ghost_yellow.png"
            const val MONSTER1 = "asset:imgassets/monster/monster1.png"
            const val MONSTER2 = "asset:imgassets/monster/monster2.png"
            const val MONSTER3 = "asset:imgassets/monster/monster3.png"
            const val MONSTER4 = "asset:imgassets/monster/monster4.png"
            const val MONSTER5 = "asset:imgassets/monster/monster5.png"
            const val MONSTER6 = "asset:imgassets/monster/monster6.png"
            const val MONSTER7 = "asset:imgassets/monster/monster7.png"
            const val MONSTER8 = "asset:imgassets/monster/monster8.png"

        }
    }

    class RobotAssets internal constructor() {
        companion object {
            const val ACTION = "asset:imgassets/robot/action.png"
            const val ANGRY = "asset:imgassets/robot/angry.png"
            const val DANCING = "asset:imgassets/robot/dancing.png"
            const val HAPPY = "asset:imgassets/robot/happy.png"
            const val PROUD = "asset:imgassets/robot/proud.png"
            const val SAD = "asset:imgassets/robot/sad.png"

        }
    }

    class SnackAssets internal constructor() {
        companion object {
            const val BANANAS = "asset:imgassets/snacks/bananas.png"
            const val BOWL = "asset:imgassets/snacks/bowl.png"
            const val BURGER = "asset:imgassets/snacks/burger.png"
            const val COFFEE = "asset:imgassets/snacks/coffee.png"
            const val CUPCAKE1 = "asset:imgassets/snacks/cupcake1.png"
            const val CUPCAKE2 = "asset:imgassets/snacks/cupcake2.png"
            const val CUPCAKE3 = "asset:imgassets/snacks/cupcake3.png"
            const val CUPCAKE4 = "asset:imgassets/snacks/cupcake4.png"
            const val CUPCAKE5 = "asset:imgassets/snacks/cupcake5.png"
            const val CUPCAKE6 = "asset:imgassets/snacks/cupcake6.png"
            const val CUPCAKE7 = "asset:imgassets/snacks/cupcake7.png"
            const val CUPCAKE8 = "asset:imgassets/snacks/cupcake8.png"
            const val DONUT1 = "asset:imgassets/snacks/donut1.png"
            const val DONUT2 = "asset:imgassets/snacks/donut2.png"
            const val DONUT3 = "asset:imgassets/snacks/donut3.png"
            const val DONUT4 = "asset:imgassets/snacks/donut4.png"
            const val DONUT5 = "asset:imgassets/snacks/donut5.png"
            const val DONUT6 = "asset:imgassets/snacks/donut6.png"
            const val DONUT7 = "asset:imgassets/snacks/donut7.png"
            const val DONUT8 = "asset:imgassets/snacks/donut8.png"
            const val COOKIE1 = "asset:imgassets/snacks/cookie1.png"
            const val COOKIE2 = "asset:imgassets/snacks/cookie2.png"
            const val COOKIE3 = "asset:imgassets/snacks/cookie3.png"
            const val COOKIE4 = "asset:imgassets/snacks/cookie4.png"
            const val FORTUNE_COOKIE = "asset:imgassets/snacks/fortuneCookie.png"



            const val FRIES = "asset:imgassets/snacks/fries.png"
            const val ICE_CREAM = "asset:imgassets/snacks/ice_cream.png"
            const val NUTS = "asset:imgassets/snacks/nuts.png"
            const val PINEAPPLE = "asset:imgassets/snacks/pineapple.png"
            const val PIZZA = "asset:imgassets/snacks/pizza.png"
            const val RAMEN = "asset:imgassets/snacks/ramen1.png"
            const val SALAD = "asset:imgassets/snacks/salad.png"
            const val SANDWICH = "asset:imgassets/snacks/sandwich.png"
            const val SHAKE = "asset:imgassets/snacks/shake.png"

        }
    }

    companion object {

        private const val prefix = "img/"

        const val KODEE = "asset:imgassets/kodee.png"
        const val EMPTY = ""

//        const val UFO = "img/ufo.png"
//        const val GIRL = "img/girl.png"
//        const val HERO = "img/hero.png"
//        const val TARGET = "img/target.png"
//        const val FLAG = "img/flag.png"
//        const val BOAT = "img/boat.png"
//        const val PACMAN = "img/pacman.png"
//        const val TURTLE = "img/turtlecool.png"
//        const val SPACESHIP = "img/spaceship.png"
//        const val KODEE = "img/kodee.png"
//        const val PARACHUTE = "img/parachute.png"
//        const val DROPZONE = "img/dropzone.png"
//        const val BOOM = "img/boom.png"
//        const val BULLET = "img/bullet2.png"
//        const val GHOST = "img/ghost.png"

        val textBackgrounds = TextBackgrounds
        val textIcons = TextIcons
        val misc = MiscAssets
        val comic = BubbleAssets
        val dog = DogAssets
        val cat = CatAssets
        val flowers = FlowerAssets
        val ghosts = GhostAssets
        val kodee = KodeeAssets
        val monster = MonsterAssets
        val robot = RobotAssets
        val snacks = SnackAssets
        val toys = ToysAssets
    }

}