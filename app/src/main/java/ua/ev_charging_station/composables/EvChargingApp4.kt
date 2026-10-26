package ua.ev_charging_station.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ua.ev_charging_station.forms.LoginForm
import ua.ev_charging_station.forms.RegistrationForm

@Composable
fun EvChargingApp4() {
    var isRegisterMode by remember { mutableStateOf(false) }


    val mainBgColor = Color(0xFFA2DCC0)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(mainBgColor)
            .windowInsetsPadding(WindowInsets.systemBars)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (isRegisterMode) {
                RegistrationForm(
                    onRegister = { name, email, password, confirmPassword ->
                        when {
                            name.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank() -> "Заповніть усі поля"
                            password != confirmPassword -> "Паролі не співпадають"
                            else -> "Реєстрація успішна!"
                        }
                    },
                    onSwitchToLogin = { isRegisterMode = false }
                )
            } else {
                LoginForm(
                    onLogin = { login, password ->
                        if (login.isBlank() || password.isBlank()) {
                            "Заповніть усі поля"
                        } else {
                            "Авторизація успішна!"
                        }
                    },
                    onSwitchToRegister = { isRegisterMode = true }
                )
            }
        }
    }
}