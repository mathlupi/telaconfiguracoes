package com.example.telaconfiguracoes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val HeaderTextStyle = TextStyle(
    fontSize = 26.sp,
    fontWeight = FontWeight.Bold
)

@Composable
fun SettingsContainer(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SettingsHeader()

        Spacer(modifier = Modifier.height(16.dp))

        SettingsSection {
            SettingsImage()
        }

        SettingsSection {
            SettingsCheckbox()
            SettingsSwitch()
        }

        SettingsSection {
            SettingsSlider()
        }

        SettingsSection {
            SettingsRadioButtons()
        }

        Spacer(modifier = Modifier.height(20.dp))

        SettingsAlertDialog()
    }
}

@Composable
fun SettingsSection(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF5F5F5))
            .padding(12.dp)
    ) {
        content()
    }
}

@Composable
fun SettingsHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = HeaderTextStyle
        )
        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = null,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun SettingsImage() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(stringResource(id = R.string.settings_profile_image))

        Image(
            painter = painterResource(id = R.drawable.sunflower),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .clickable { }
        )
    }
}

@Composable
fun SettingsCheckbox() {
    var checked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(stringResource(id = R.string.settings_consent))
        Checkbox(checked = checked, onCheckedChange = { checked = it })
    }
}

@Composable
fun SettingsSwitch() {
    var checked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(stringResource(id = R.string.settings_mobile_data))
        Switch(checked = checked, onCheckedChange = { checked = it })
    }
}

@Composable
fun SettingsSlider() {
    var value by remember { mutableStateOf(0.5f) }

    Column {
        Text(stringResource(id = R.string.settings_text_size) + " ${(value * 100).toInt()}%")
        Slider(value = value, onValueChange = { value = it })
    }
}

@Composable
fun SettingsRadioButtons() {
    var selected by remember { mutableStateOf("PayPal") }

    Column {
        Text(stringResource(id = R.string.payment_method))

        listOf("PayPal", "Credit Card", "Bank Transfer").forEach {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selected == it,
                    onClick = { selected = it }
                )
                Text(it)
            }
        }
    }
}

@Composable
fun SettingsAlertDialog() {
    var showDialog by remember { mutableStateOf(false) }

    Button(
        onClick = { showDialog = true },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(stringResource(id = R.string.sign_out))
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(stringResource(id = R.string.alert_title)) },
            text = { Text(stringResource(id = R.string.alert_message)) },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(stringResource(id = R.string.ok))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(stringResource(id = R.string.cancel))
                }
            }
        )
    }
}