package gecko.ui.theme

import androidx.compose.ui.graphics.Color
import com.google.android.material.color.ColorRoles

data class CustomColor(
    val name: String,
    val color: Color,
    val harmonized: Boolean,
    var roles: ColorRoles
)