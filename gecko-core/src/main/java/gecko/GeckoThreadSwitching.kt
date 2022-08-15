package gecko

import gecko.model.NetworkMetadata
import java.util.concurrent.Executor
import java.util.concurrent.Executors

abstract class GeckoThreadSwitching(
    private val executor: Executor = Executors.newCachedThreadPool()
) : Gecko {

    final override fun process(metadata: NetworkMetadata) {
        executor.execute {
            processAsync(metadata)
        }
    }

    protected abstract fun processAsync(metadata: NetworkMetadata)

}