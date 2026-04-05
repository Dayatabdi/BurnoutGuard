package com.dayat0009.burnoutguard.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dayat0009.burnoutguard.R
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dayat0009.burnoutguard.navigation.Screen
import com.dayat0009.burnoutguard.ui.theme.BurnoutGuardTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen( navController: NavHostController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.About.route)
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(id = R.string.app_name),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        ScreenContent(Modifier.padding(innerPadding), navController
        )
    }
}

@Composable
fun ScreenContent(modifier: Modifier = Modifier, navController: NavHostController){
    val context = LocalContext.current
    var jamTidur by remember { mutableStateOf("0") }
    var stressLevel by remember { mutableStateOf("-") }

    var gejala1 by remember { mutableStateOf(false) }
    var gejala2 by remember { mutableStateOf(false) }
    var gejala3 by remember { mutableStateOf(false) }
    var gejala4 by remember { mutableStateOf(false) }
    var gejala5 by remember { mutableStateOf(false) }
    var gejala6 by remember { mutableStateOf(false) }

    var showError by remember { mutableStateOf(false) }
    Column(
        modifier = modifier.fillMaxSize().
        verticalScroll(rememberScrollState()).
        padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.inline1),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = stringResource(R.string.inline2),
            style = MaterialTheme.typography.bodyMedium
        )
        OutlinedTextField(
            value = jamTidur,
            onValueChange = { jamTidur = it },
            label = { Text(stringResource(R.string.jamtidur)) },
            placeholder = { Text(stringResource(R.string.jamtidurEx)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Text(
            text = stringResource(R.string.gejala),
            style = MaterialTheme.typography.titleMedium
        )
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            CheckboxItem(
                text = stringResource(R.string.mudah_lelah),
                checked = gejala1,
                onCheckedChange = { gejala1 = it }
            )
            CheckboxItem(
                text = stringResource(R.string.sulit_fokus),
                checked = gejala2,
                onCheckedChange = { gejala2 = it }
            )
            CheckboxItem(
                text = stringResource(R.string.susah_tidur),
                checked = gejala3,
                onCheckedChange = { gejala3 = it }
            )
            CheckboxItem(
                text = stringResource(R.string.tidak_bersemangat),
                checked = gejala4,
                onCheckedChange = { gejala4 = it }
            )
            CheckboxItem(
                text = stringResource(R.string.mudah_marah),
                checked = gejala5,
                onCheckedChange = { gejala5 = it }
            )
            CheckboxItem(
                text = stringResource(R.string.overwhelmed),
                checked = gejala6,
                onCheckedChange = { gejala6 = it }
            )
        }
        Text(
            text = stringResource(R.string.stres),
            style = MaterialTheme.typography.titleMedium
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            RadioButtonOption("Rendah", stressLevel) { stressLevel = "Rendah" }
            RadioButtonOption("Sedang", stressLevel) { stressLevel = "Sedang" }
            RadioButtonOption("Tinggi", stressLevel) { stressLevel = "Tinggi" }
        }

        Spacer(modifier = Modifier.height(8.dp))
    }



}

@Composable
fun CheckboxItem(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        Text(
            text = text,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
@Composable
fun RadioButtonOption(
    label: String,
    selected: String,
    onSelect: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = selected == label,
            onClick = onSelect
        )
        Text(text = label, modifier = Modifier.padding(start = 4.dp))
    }
}





@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES,    showBackground = true)
@Composable
fun GreetingPreview() {
    BurnoutGuardTheme {
        MainScreen(rememberNavController())
    }
}