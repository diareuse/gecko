package gecko.android.example

import android.app.Application
import gecko.Gecko
import gecko.android.builder.geckoAndroid
import gecko.android.example.generator.Generator
import gecko.android.example.generator.networkMetadataGenerator
import gecko.model.NetworkMetadata

class App : Application(), Generator<NetworkMetadata> by networkMetadataGenerator() {

    override fun onCreate() {
        super.onCreate()
        gecko = geckoAndroid()

        Thread {
            repeat(10) {
                gecko.process(generate())
            }
        }.start()
    }

    companion object {
        lateinit var gecko: Gecko
    }

}