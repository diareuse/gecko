package gecko

import com.google.auto.service.AutoService
import gecko.model.ByteData
import gecko.model.NetworkMetadata

@AutoService(Gecko::class)
class GeckoDefault : GeckoThreadSwitching() {

    override fun processAsync(metadata: NetworkMetadata) {
        try {
            val data = ByteData.from(metadata)
            val output = data.value
            require(output.isNotEmpty())
        } catch (e: Throwable) {
            println("Could not process Gecko request")
            e.printStackTrace()
        }
    }

}