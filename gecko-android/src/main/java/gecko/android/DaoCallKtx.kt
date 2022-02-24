package gecko.android

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal suspend fun DaoCall.selectSuspendCatching(offset: Int, limit: Int) = runCatching {
    selectSuspend(offset, limit)
}

internal suspend fun DaoCall.selectSuspend(offset: Int, limit: Int) = withContext(Dispatchers.IO) {
    select(offset, limit)
}