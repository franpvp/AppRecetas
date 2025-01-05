package com.example.semana01

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.navigation.compose.rememberNavController
import com.example.semana01.utils.UserManager
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.LocalContext

@Composable
fun Registro(onRegisterSuccess: () -> Unit) {
    val nombre = remember { mutableStateOf("") }
    val apellido = remember { mutableStateOf("") }
    val correo = remember { mutableStateOf("") }
    val celular = remember { mutableStateOf("") }
    val fechaNacimiento = remember { mutableStateOf("") }
    val contrasena = remember { mutableStateOf("") }
    val confirmarContrasena = remember { mutableStateOf("") }

    val correoError = remember { mutableStateOf(false) }
    val fechaNacimientoError = remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Registro",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(bottom = 16.dp),
            color = MaterialTheme.colorScheme.primary
        )

        // Campos de formulario
        TextField(value = nombre.value, onValueChange = { nombre.value = it }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = apellido.value, onValueChange = { apellido.value = it }, label = { Text("Apellido") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = correo.value,
            onValueChange = {
                correo.value = it
                correoError.value = !isValidEmail(it)
            },
            label = { Text("Correo electrónico") },
            isError = correoError.value,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = celular.value, onValueChange = { celular.value = it }, label = { Text("Celular") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = fechaNacimiento.value,
            onValueChange = {
                fechaNacimiento.value = it
                fechaNacimientoError.value = !isValidDate(it)
            },
            label = { Text("Fecha de Nacimiento (dd/MM/yyyy)") },
            isError = fechaNacimientoError.value,
            modifier = Modifier.fillMaxWidth()
        )
        if (fechaNacimientoError.value) {
            Text(
                text = "Formato inválido. Ejemplo: 15/04/1995",
                color = androidx.compose.ui.graphics.Color.Red,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = contrasena.value, onValueChange = { contrasena.value = it }, label = { Text("Contraseña") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = confirmarContrasena.value, onValueChange = { confirmarContrasena.value = it }, label = { Text("Confirmar Contraseña") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (correoError.value || fechaNacimientoError.value) {
                    // Mostrar mensaje si hay errores
                } else if (contrasena.value == confirmarContrasena.value) {
                    // Registrar el nuevo usuario en UserManager
                    UserManager.addUser(context, correo.value, contrasena.value)
                    onRegisterSuccess() // Callback para indicar éxito
                } else {
                    // Mostrar mensaje de error en contraseñas
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }
    }
}

// Función para validar correo
fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
    return emailRegex.matches(email)
}

// Función para validar fecha en formato dd/MM/yyyy
fun isValidDate(date: String): Boolean {
    val dateRegex = "^\\d{2}/\\d{2}/\\d{4}$".toRegex()
    return dateRegex.matches(date)
}