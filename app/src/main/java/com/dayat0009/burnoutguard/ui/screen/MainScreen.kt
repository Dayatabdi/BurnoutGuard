package com.dayat0009.burnoutguard.ui.screen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dayat0009.burnoutguard.R
import com.dayat0009.burnoutguard.navigation.Screen

data class BurnoutResult(val level: String, val deskripsi: String, val saran: String, val gambar: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    var jamTidur by remember { mutableStateOf("") }
    var stressLevel by remember { mutableStateOf("Rendah") }
    var g1 by remember { mutableStateOf(false) }
    var g2 by remember { mutableStateOf(false) }
    var g3 by remember { mutableStateOf(false) }
    var g4 by remember { mutableStateOf(false) }
    var g5 by remember { mutableStateOf(false) }
    var g6 by remember { mutableStateOf(false) }

    val context = LocalContext.current
    var showResult by remember { mutableStateOf(false) }
    var skor by remember { mutableIntStateOf(0) }
    var showError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.About.route) }) {
                        Icon(Icons.Outlined.Info, contentDescription = stringResource(id=R.string.tentang_aplikasi))
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!showResult) {
                Text(stringResource(R.string.inline1), style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)

                OutlinedTextField(
                    value = jamTidur,
                    onValueChange = { jamTidur = it },
                    label = { Text(stringResource(R.string.jamtidur)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    CheckboxItem(stringResource(R.string.mudah_lelah), g1) { g1 = it }
                    CheckboxItem(stringResource(R.string.sulit_fokus), g2) { g2 = it }
                    CheckboxItem(stringResource(R.string.susah_tidur), g3) { g3 = it }
                    CheckboxItem(stringResource(R.string.mudah_marah), g4) { g4 = it }
                    CheckboxItem(stringResource(R.string.tidak_bersemangat), g5) { g5 = it }
                    CheckboxItem(stringResource(R.string.overwhelmed), g6) { g6 = it }
                }
                Text(stringResource(R.string.stres), style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)

                Row(modifier = Modifier.fillMaxWidth()) {
                    RadioButtonOption(stringResource(R.string.stres_rendah), stressLevel) { stressLevel = "Rendah" }
                    RadioButtonOption(stringResource(R.string.stres_sedang), stressLevel) { stressLevel = "Sedang" }
                    RadioButtonOption(stringResource(R.string.stres_tinggi), stressLevel) { stressLevel = "Tinggi" }
                }

                if (showError) {
                    Text(stringResource(R.string.input_invalid), color = MaterialTheme.colorScheme.error)
                }

                Button(
                    onClick = {
                        if (jamTidur.isBlank()) {
                            showError = true
                        } else {
                            showError = false
                            var currentSkor = 0
                            val jam = jamTidur.toFloatOrNull() ?: 0f
                            if (jam < 6f) currentSkor += 30
                            if (g1) currentSkor += 15
                            if (g2) currentSkor += 15
                            if (g3) currentSkor += 15
                            if (g4) currentSkor += 10
                            if (g5) currentSkor += 10
                            if (g6) currentSkor += 15
                            skor = if (currentSkor > 100) 100 else currentSkor
                            showResult = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.tombol_hitung))
                }
            } else {
                val result = when {
                    skor >= 70 -> BurnoutResult(stringResource(R.string.level_high), stringResource(R.string.deskripsi_high), stringResource(R.string.saran_high), R.drawable.highstress)
                    skor >= 40 -> BurnoutResult(stringResource(R.string.level_medium), stringResource(R.string.deskripsi_medium), stringResource(R.string.saran_medium), R.drawable.mediumstrees)
                    else -> BurnoutResult(stringResource(R.string.level_low), stringResource(R.string.deskripsi_low), stringResource(R.string.saran_low), R.drawable.selfcare)
                }

                Image(painter = painterResource(result.gambar), contentDescription = null, modifier = Modifier.size(200.dp))
                Text(stringResource(R.string.skor_burnout, skor), style = MaterialTheme.typography.headlineMedium)
                Text(result.level, style = MaterialTheme.typography.titleLarge)
                Text(result.deskripsi, textAlign = TextAlign.Center)

                HorizontalDivider(  modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = Color.White)

                Text(stringResource(R.string.saran_title), style = MaterialTheme.typography.titleMedium)
                Text(result.saran)

                val message = stringResource(R.string.bagikan_template, skor, result.level, result.deskripsi)
                Button(
                    onClick = { shareResult(context,message)   },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.bagikan))
                }

                OutlinedButton(
                    onClick = {
                        showResult = false
                        jamTidur = ""
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.hitung_ulang))
                }
            }
        }
    }
}

@Composable
fun CheckboxItem(text: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        Text(text = text, modifier = Modifier.padding(start = 8.dp))
    }
}

@Composable
fun RadioButtonOption(label: String, selected: String, onSelect: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(selected = selected == label, onClick = onSelect)
        Text(text = label, modifier = Modifier.padding(start = 4.dp))
    }
}
private fun shareResult(context: Context, message: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    context.startActivity(Intent.createChooser(intent, "Bagikan Hasil"))
}
