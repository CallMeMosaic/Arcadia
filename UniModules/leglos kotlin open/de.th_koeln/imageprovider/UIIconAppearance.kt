package de.th_koeln.imageprovider

import java.awt.image.BufferedImage

/**
 * defines a a configuration for drawing
 * an icon for the text rendering of an Actor
 */
class UIIconAppearance (
    private val fileName : String,
    val width : Int = 30,
    val height : Int = 30,
    val xOffset:Int = 20,
    val yOffset:Int = 4,
    val preferredTextOffset : Int = 40
    ) {

    val image : BufferedImage?  = ImageResourceManager.getImage(fileName)

}