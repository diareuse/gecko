package gecko.ui.startup

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import androidx.startup.Initializer
import gecko.ui.GeckoActivity
import gecko.ui.R

class GeckoStartup : Initializer<Unit> {

    override fun create(context: Context) {
        var hasAlternativeLauncher: Boolean = context.hasShortcut
        hasAlternativeLauncher = hasAlternativeLauncher || context.tryAddShortcut()
        context.setLauncherEnabled(!hasAlternativeLauncher)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }

    // ---

    private val Context.hasShortcut
        get() = ShortcutManagerCompat
            .getShortcuts(this, ShortcutManagerCompat.FLAG_MATCH_DYNAMIC)
            .any { it.id == "gecko" }

    private fun Context.tryAddShortcut(): Boolean {
        val intent = Intent(Intent.ACTION_MAIN)
            .setClass(this, GeckoActivity::class.java)
        val shortcut = ShortcutInfoCompat.Builder(this, "gecko")
            .setShortLabel("Gecko!")
            .setLongLabel("Gecko! Network Logging")
            .setIcon(IconCompat.createWithResource(this, R.mipmap.ic_launcher_gecko))
            .setIntent(intent)
            .setRank(0)
            .build()

        return try {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N_MR1)
                throw IllegalStateException("Shortcuts API is not available")
            ShortcutManagerCompat.pushDynamicShortcut(this, shortcut)
        } catch (e: IllegalStateException) {
            e.printStackTrace()
            // launcher activity does not exist
            false
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            // user has defined this shortcut as immutable
            false
        }
    }

    private fun Context.setLauncherEnabled(enabled: Boolean) {
        val component = ComponentName(this, "gecko.ui.GeckoLauncher")
        val state = when (enabled) {
            true -> PackageManager.COMPONENT_ENABLED_STATE_ENABLED
            false -> PackageManager.COMPONENT_ENABLED_STATE_DISABLED
        }
        packageManager.setComponentEnabledSetting(component, state, PackageManager.DONT_KILL_APP)
    }

}