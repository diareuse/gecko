package gecko.android

import android.util.expect
import android.util.unexpect
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.random.Random.Default.nextBytes

class LoggerAndroidTest {

    private lateinit var logger: LoggerAndroid

    @BeforeEach
    fun prepare() {
        logger = LoggerAndroid()
    }

    @AfterEach
    fun tearDown() {
        unexpect()
    }

    @Test
    fun `logs values`() {
        val expected = "hello!"
        expect("Gecko!", expected)
        logger.log(expected)
    }

    @Test
    fun `splits values`() {
        val message = (0 until 7000).joinToString(separator = "") { nextBytes(1).decodeToString() }
        expect(
            "Gecko!" to message.take(4000),
            "Gecko!" to message.drop(4000)
        )
        logger.log(message)
    }

}