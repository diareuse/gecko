package gecko.android

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun GeckoSource.getMetadataSuspendCatching(id: Long) = runCatching {
    getMetadataSuspend(id)
}

suspend fun GeckoSource.getMetadataSuspend(id: Long) = withContext(Dispatchers.IO) {
    getMetadata(id)
}