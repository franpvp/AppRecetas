import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ForgotPasswordForm() {
    var email by remember { mutableStateOf("") }
    var isEmailSent by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Recuperar Contraseña",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Ingresa tu correo electrónico registrado. Te enviaremos un enlace para restablecer tu contraseña.",
            fontSize = 14.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Campo de Correo Electrónico
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo Electrónico") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Botón de Enviar
        Button(
            onClick = { isEmailSent = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mensaje de Confirmación
        if (isEmailSent) {
            Text(
                text = "Se ha enviado un enlace a tu correo electrónico.",
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview
@Composable
fun PreviewForgotPasswordForm() {
    MaterialTheme {
        Surface {
            ForgotPasswordForm()
        }
    }
}