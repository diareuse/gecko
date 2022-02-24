package gecko.ui.screen.detail

import gecko.android.GeckoSource
import gecko.android.getMetadataSuspendCatching
import gecko.ui.presentation.detail.DetailWorker

class DetailContentLoader(
    private val source: GeckoSource
) : DetailWorker {

    override fun present(model: DetailViewModel): suspend () -> Unit = outer@{
        val id = model.id.toLongOrNull() ?: return@outer
        source.getMetadataSuspendCatching(id).onSuccess {
            model.submitMetadata(it)
        }
    }

}