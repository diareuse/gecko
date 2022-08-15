package gecko

import com.google.auto.service.AutoService
import gecko.model.ByteData
import gecko.model.NetworkMetadata
import org.jetbrains.annotations.TestOnly

@AutoService(Gecko::class)
class GeckoLogging : GeckoThreadSwitching() {

    internal var logger: Logger = Logger
        @TestOnly set

    override fun processAsync(metadata: NetworkMetadata) {
        logger.log(ByteData.from(metadata).value.decodeToString())
    }

}