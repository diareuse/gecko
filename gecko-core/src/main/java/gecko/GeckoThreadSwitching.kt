package gecko

import gecko.model.NetworkMetadata
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Abstract class that permits handing off execution to [executor] supplied in constructor.
 * By default it uses [Executors.newCachedThreadPool], that said consumer can swap it for their own pool.
 * */
abstract class GeckoThreadSwitching(
    private val executor: Executor = Executors.newCachedThreadPool()
) : Gecko {

    final override fun process(metadata: NetworkMetadata) {
        executor.execute {
            processAsync(metadata)
        }
    }

    /**
     * Similar to [process] however executes off original thread.
     * */
    protected abstract fun processAsync(metadata: NetworkMetadata)

}