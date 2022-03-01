package gecko.ui.screen.dashboard

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import gecko.ui.GeckoActivity
import gecko.ui.presentation.dashboard.DashboardWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class DashboardNameLoader(
    private val context: Context
) : DashboardWorker {

    override suspend fun execute(model: DashboardViewModel) {
        val manager = context.packageManager
        val intent = Intent(Intent.ACTION_MAIN)
            .addCategory(Intent.CATEGORY_LAUNCHER)
            .setPackage(context.packageName)
        val activity = manager.queryActivities(intent)
            .firstOrNull { it.activityInfo.name != GeckoActivity::class.java.name }
            ?.activityInfo ?: return
        model.submitAppName(activity.loadLabel(manager).toString())
    }

    private suspend fun PackageManager.queryActivities(intent: Intent) =
        withContext(Dispatchers.IO) {
            queryIntentActivities(intent, 0)
        }

}