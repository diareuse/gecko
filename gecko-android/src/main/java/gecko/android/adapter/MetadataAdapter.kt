package gecko.android.adapter

import gecko.android.model.CallMetadataView
import gecko.android.model.CallView
import gecko.android.model.GeckoData
import gecko.android.model.GeckoMetadata

internal interface MetadataAdapter {

    fun adapt(view: CallView): GeckoData
    fun adapt(view: CallMetadataView): GeckoMetadata

}