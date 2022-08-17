package gecko.model

/**
 * Describes ordered set of stages through which the ByteData will be pushed.
 * */
enum class ByteDataStage {

    Compression, Encoding, PostProcessing, Finalizing

}