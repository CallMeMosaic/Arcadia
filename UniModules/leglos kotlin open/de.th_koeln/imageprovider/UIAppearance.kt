package de.th_koeln.imageprovider

import java.awt.image.BufferedImage


/**
 * Provides two alternative images, each reprenting
 * a passive or active state.
 * UIAppearance is used by TextBackground to
 * render the text background depending on the
 * mouseHighlighting state. (use different images
 * depending on whether the mouse is over the actor or not,
 * good for highlighting of buttons)
 */
class UIAppearance (
    private val activeImageFilname:String ,
    private val passiveImageFileName:String
) {

    val passiveImage : BufferedImage?
        get() =  ImageResourceManager.getImage(passiveImageFileName)


    val activeImage : BufferedImage?
        get() {
            val img = ImageResourceManager.getImage(activeImageFilname)
            return if (img == null) passiveImage else img
        }




}