package ua.ev_charging_station.forms

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegistrationForm(
    onRegister: (String, String, String, String) -> String,
    onSwitchToLogin: () -> Unit
) {
    // Створюємо змінні стану для зберігання значень полів форми
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    // Відображаємо заголовок форми реєстрації
    Text(
        text = "Реєстрація: Зарядна станція EV",
        fontSize = 24.sp,
        modifier = Modifier.padding(bottom = 16.dp)
    )

    // Поле для введення імені користувача
    OutlinedTextField(
        value = name,
        onValueChange = { name = it },
        label = { Text("Ім'я") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    // Поле для введення email
    OutlinedTextField(
        value = email,
        onValueChange = { email = it },
        label = { Text("Email") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    // Поле для введення пароля
    OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Пароль") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    // Поле для підтвердження пароля
    OutlinedTextField(
        value = confirmPassword,
        onValueChange = { confirmPassword = it },
        label = { Text("Підтвердження пароля") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Кнопка для виконання реєстрації
    Button(
        onClick = { message = onRegister(name, email, password, confirmPassword) },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Зареєструватися")
    }

    Spacer(modifier = Modifier.height(8.dp))

    // Кнопка для переходу до форми входу
    TextButton(onClick = onSwitchToLogin) {
        Text("Вже є акаунт? Увійти")
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Відображаємо повідомлення про результат реєстрації
    Text(
        text = message,
        color = if (message.contains("успішна")) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
    )
}