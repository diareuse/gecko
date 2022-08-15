package gecko

import gecko.util.loadService

interface TextCompressor {

    fun compress(data: ByteArray): ByteArray

    companion object : TextCompressor {

        override fun compress(data: ByteArray): ByteArray {
            return loadService<TextCompressor>().compress(data)
        }

    }

}