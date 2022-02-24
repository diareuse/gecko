package gecko.ui.screen.dashboard

import android.content.Context
import android.content.Intent
import gecko.ui.GeckoActivity
import gecko.ui.presentation.dashboard.DashboardWorker

class DashboardNameLoader(
    private val context: Context
) : DashboardWorker {

    override fun present(model: DashboardViewModel): suspend () -> Unit = block@{
        val manager = context.packageManager
        val intent = Intent(Intent.ACTION_MAIN)
            .addCategory(Intent.CATEGORY_LAUNCHER)
            .setPackage(context.packageName)
        val activity = manager.queryIntentActivities(intent, 0)
            .firstOrNull { it.activityInfo.name != GeckoActivity::class.java.name }
            ?.activityInfo ?: return@block
        model.submitAppName(activity.loadLabel(manager).toString())
    }

}