package gecko

import gecko.util.loadService

/**
 * Text compression class used to minify byte data. The default implementation is [TextCompressorGzip].
 * @see TextCompressorGzip
 * */
interface TextCompressor {

    /**
     * Method should use compression mechanism on the data coming from [data] and return its minified version.
     * The compression should be performed within this body method as it will be most probably run off main threads.
     * Returned data may or may not be retained until it's written to loggers or further processed.
     *
     * Although it's possible to minify the source data in place, it's discouraged as other parts of the program may
     * depend on the original array to keep in its original form.
     *
     * @param data original data subjecting to minification
     * @return minified data
     * */
    fun compress(data: ByteArray): ByteArray

    companion object : TextCompressor {

        override fun compress(data: ByteArray): ByteArray {
            return loadService<TextCompressor>().compress(data)
        }

    }

}