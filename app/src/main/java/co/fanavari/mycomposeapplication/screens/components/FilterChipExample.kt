package co.fanavari.mycomposeapplication.screens.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FilterChipExample(selected: Boolean, onSelectedChange: () -> Unit) {
    val internalSelected = remember { mutableStateOf(selected) }
    FilterChip(
        onClick = {
            internalSelected.value = !internalSelected.value
            onSelectedChange()
        },
        label = { Text("Filter chip") },
        selected = internalSelected.value,
        leadingIcon = if (internalSelected.value) {
            { Icon(imageVector = Icons.Filled.Done, contentDescription = "Done icon", modifier = Modifier.size(
                FilterChipDefaults.IconSize)) }
        } else null,
        modifier = Modifier.padding(8.dp)
    )
}