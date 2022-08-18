package gecko.interceptor

import com.google.common.truth.Truth.assertThat
import gecko.model.ByteData
import gecko.model.ByteDataStage
import gecko.test.TestBlueprint
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ByteDataInterceptorDomainInjectingTest : TestBlueprint {

    private lateinit var interceptor: ByteDataInterceptorDomainInjecting

    @BeforeEach
    override fun prepare() {
        super.prepare()
        interceptor = ByteDataInterceptorDomainInjecting()
    }

    @Test
    fun `matches stage`() {
        assertThat(interceptor.stage).isEqualTo(ByteDataStage.PostProcessing)
    }

    @Test
    fun `returns url data`() {
        val query = ByteData.from("query")
        val result = interceptor.intercept(query)
        assertThat(result.value.decodeToString()).isEqualTo("https://diareuse.github.io/gecko/?q=query")
    }

}