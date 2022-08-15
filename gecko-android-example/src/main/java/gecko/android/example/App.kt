package gecko.android.example

import android.app.Application
import gecko.Gecko
import gecko.android.example.generator.Generator
import gecko.android.example.generator.networkMetadataGenerator
import gecko.model.NetworkMetadata

internal class App : Application(), Generator<NetworkMetadata> by networkMetadataGenerator() {

    override fun onCreate() {
        super.onCreate()

        Thread {
            repeat(10) {
                Gecko.process(generate())
            }
        }.start()
    }

}