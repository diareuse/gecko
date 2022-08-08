package gecko.android

import android.content.Context
import androidx.room.Room
import java.lang.ref.WeakReference

internal class GeckoDatabaseFactoryDefault private constructor(
    private val context: WeakReference<Context>
) : GeckoDatabaseFactory {

    constructor(context: Context) : this(WeakReference(context))

    override fun getDatabase(): GeckoDatabase {
        val context = requireNotNull(context.get())
        val type = GeckoDatabase::class.java
        val name = context.packageName + ".gecko"
        return Room
            .databaseBuilder(context, type, name)
            .fallbackToDestructiveMigration()
            .build()
    }

}