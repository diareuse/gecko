package gecko.builder

import com.google.common.truth.Truth.assertThat
import gecko.Base64Encoder
import gecko.Gecko
import gecko.Logger
import gecko.MetadataAdapterAscii
import gecko.model.NetworkMetadata
import gecko.model.Tail
import gecko.test.TestBlueprint
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class GeckoBuilderTest : TestBlueprint {

    private lateinit var builder: GeckoBuilder

    @BeforeEach
    override fun prepare() {
        super.prepare()
        builder = GeckoBuilder()
    }

    @Test
    fun `requires value for domain`() {
        assertThrows<UninitializedPropertyAccessException> {
            println(builder.domain)
        }
    }

    @Test
    fun `requires value for encoder`() {
        assertThrows<UninitializedPropertyAccessException> {
            println(builder.encoder)
        }
    }

    @Test
    fun `requires value for adapter`() {
        assertThrows<UninitializedPropertyAccessException> {
            println(builder.adapter)
        }
    }

    @Test
    fun `requires value for logger`() {
        assertThrows<UninitializedPropertyAccessException> {
            println(builder.logger)
        }
    }

    @Test
    fun `builds successfully with required values`() {
        builder.domain = ""
        builder.encoder = Base64Encoder { it }
        builder.adapter = MetadataAdapterAscii()
        builder.logger = Logger {}
        val gecko = builder.build()
        assertThat(gecko).isNotNull()
    }

    @Test
    fun `wraps with step`() {
        builder.apply(::geckoDefaults)
        builder.addStep { TestGecko() }
        val gecko = builder.build()
        assertThat(gecko).isInstanceOf(TestGecko::class.java)
    }

    class TestGecko : Gecko {
        override fun process(metadata: NetworkMetadata): Tail {
            TODO("Not yet implemented")
        }
    }

}