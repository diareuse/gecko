package gecko.ui.screen.detail

import gecko.android.GeckoSource
import gecko.android.getMetadataSuspendCatching
import gecko.ui.presentation.detail.DetailWorker

internal class DetailContentLoader(
    private val source: GeckoSource
) : DetailWorker {

    override suspend fun execute(model: DetailViewModel) {
        val id = model.id.toLongOrNull() ?: return
        source.getMetadataSuspendCatching(id).onSuccess {
            model.submitMetadata(it)
        }
    }

}