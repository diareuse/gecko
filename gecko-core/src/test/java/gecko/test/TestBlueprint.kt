package gecko.test

import org.junit.jupiter.api.BeforeEach
import org.mockito.MockitoAnnotations

interface TestBlueprint {

    @BeforeEach
    fun prepare() {
        MockitoAnnotations.openMocks(this).close()
    }

}