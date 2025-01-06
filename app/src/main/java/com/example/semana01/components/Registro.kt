package com.example.semana01.components

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import com.example.semana01.R
import com.example.semana01.utils.UserManager
import java.text.SimpleDateFormat
import java.util.*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Registro(onRegisterSuccess: () -> Unit, navController: NavController) {
    val nombre = remember { mutableStateOf("") }
    val apellido = remember { mutableStateOf("") }
    val correo = remember { mutableStateOf("") }
    val celular = remember { mutableStateOf("") }
    val fechaNacimiento = remember { mutableStateOf("") }
    val contrasena = remember { mutableStateOf("") }
    val confirmarContrasena = remember { mutableStateOf("") }
    val showPassword = remember { mutableStateOf(false) }
    val showConfirmPassword = remember { mutableStateOf(false) }
    val correoError = remember { mutableStateOf(false) }

    // Estado para mostrar el DatePickerDialog
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Icono de Volver
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.TopStart)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "Volver",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        // Título del Registro
        Text(
            text = "Registro",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campos de entrada
        TextField(
            value = nombre.value,
            onValueChange = { nombre.value = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = apellido.value,
            onValueChange = { apellido.value = it },
            label = { Text("Apellido") },
            modifier = Modifier.fillMaxWidth()
        )
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
        if (correoError.value) {
            Text(
                text = "Correo inválido",
                color = Color.Red,
                modifier = Modifier.align(Alignment.Start)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = celular.value,
            onValueChange = { celular.value = it },
            label = { Text("Celular") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Campo de Fecha de Nacimiento
        OutlinedTextField(
            value = fechaNacimiento.value,
            onValueChange = { },
            label = { Text("Fecha de Nacimiento") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = !showDatePicker }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select date"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        )

        // Mostrar DatePicker en Popup
        if (showDatePicker) {
            Popup(
                onDismissRequest = { showDatePicker = false },
                alignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .shadow(elevation = 4.dp)
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                    ) {
                        DatePicker(
                            state = datePickerState,
                            showModeToggle = true
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        // Botón de confirmación
                        Button(
                            onClick = {
                                fechaNacimiento.value = convertMillisToDate(datePickerState.selectedDateMillis ?: 0L)
                                showDatePicker = false // Cerrar el DatePicker
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Confirmar Fecha")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Contraseña y Confirmar Contraseña
        PasswordField(
            value = contrasena.value,
            onValueChange = { contrasena.value = it },
            label = "Contraseña",
            showPassword = showPassword.value,
            onTogglePasswordVisibility = { showPassword.value = !showPassword.value }
        )
        Spacer(modifier = Modifier.height(8.dp))

        PasswordField(
            value = confirmarContrasena.value,
            onValueChange = { confirmarContrasena.value = it },
            label = "Confirmar Contraseña",
            showPassword = showConfirmPassword.value,
            onTogglePasswordVisibility = { showConfirmPassword.value = !showConfirmPassword.value }
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Botón Registrarse
        Button(
            onClick = {
                if (correoError.value || contrasena.value != confirmarContrasena.value) {
                    // Mostrar errores
                } else {
                    //UserManager.addUser(correo.value, contrasena.value)
                    onRegisterSuccess()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrarse")
        }
    }
}

@Composable
fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    showPassword: Boolean,
    onTogglePasswordVisibility: () -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = onTogglePasswordVisibility) {
                Icon(
                    painter = painterResource(
                        id = if (showPassword) R.drawable.ic_visibility else R.drawable.ic_visibility_off
                    ),
                    contentDescription = "Toggle Password Visibility"
                )
            }
        }
    )
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}

fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
    return emailRegex.matches(email)
}
