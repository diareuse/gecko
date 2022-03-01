package gecko.ui.theme

import androidx.compose.ui.graphics.Color
import com.google.android.material.color.ColorRoles

internal data class CustomColor(
    val name: String,
    val color: Color,
    val harmonized: Boolean,
    var roles: ColorRoles
)