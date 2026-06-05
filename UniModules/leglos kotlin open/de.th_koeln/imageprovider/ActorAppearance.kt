package de.th_koeln.imageprovider

import java.awt.image.BufferedImage

/**
 * Maps a fileName to a buffered image and can
 * be used to change an actor's appearance property.
 */
class ActorAppearance(private val fileName : String) {

    val image : BufferedImage?  = ImageResourceManager.getImage(fileName)

}