package de.th_koeln.imageprovider

import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import java.lang.IllegalArgumentException
import javax.imageio.ImageIO


/**
 * Handles all loading of image resources as a helper class
 */
class ImageResourceManager {


    companion object {

        // make sure images are loaded only once, even if used multiple times
        private val fileImageMap : MutableMap<String, BufferedImage?> = mutableMapOf<String, BufferedImage?>()

        /**
         * Get the buffered image based on a file name
         * Trys to find the image in the fileImageMap, or
         * trys to load the image added to the fileImageMap
         *
         * and then returns the buffered image.
         *
         */
        fun getImage (fileName : String) : BufferedImage? {
            if (fileImageMap.containsKey(fileName)) {
                // println ("Reuse image file: $fileName")
                return fileImageMap.get(fileName)
            } else {
                var img : BufferedImage? = null

                if (fileName.startsWith("asset:")) {
                    val assetName = fileName.removeRange(0,6)
                    println ("Asset: $assetName")
                    try {
                        val url = ImageResourceManager::class.java.getResource(assetName)
                        println ("IMG-URL:" + url)
                        img = ImageIO.read(url)

                    } catch (e: IllegalArgumentException) {
                        println ("Could not init the asset...")
                        println ("File path of asset is:")
                        println (fileName)
                    }

                } else {
                    val f = File(fileName)
                    try {
                        img = ImageIO.read(f)
                        //  println ("Loaded the file happily")
                        //  println (f.absoluteFile)
                    } catch (e: IOException) {
                        println ("Could not init the graphic...")
                        println ("File path is:")
                        println (f.absoluteFile)
                    }
                }


                fileImageMap.set(fileName, img )

                return img
            }
        }


    }


}