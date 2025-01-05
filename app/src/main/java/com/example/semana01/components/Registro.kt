package com.example.semana01.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.semana01.R
import com.example.semana01.utils.UserManager
import androidx.compose.ui.platform.LocalContext

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
    val fechaNacimientoError = remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Botón de volver en la esquina superior izquierda
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.TopStart) // Posicionar en la esquina superior izquierda
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "Volver",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            // Contenido de registro
            Column(
                modifier = Modifier.align(Alignment.Center)
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
                        color = Color.Red,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                // Campo de contraseña
                TextField(
                    value = contrasena.value,
                    onValueChange = { contrasena.value = it },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { showPassword.value = !showPassword.value }) {
                            Icon(
                                painter = painterResource(
                                    id = if (showPassword.value) R.drawable.ic_visibility else R.drawable.ic_visibility_off
                                ),
                                contentDescription = if (showPassword.value) "Ocultar contraseña" else "Mostrar contraseña"
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Campo de confirmar contraseña
                TextField(
                    value = confirmarContrasena.value,
                    onValueChange = { confirmarContrasena.value = it },
                    label = { Text("Confirmar Contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = if (showConfirmPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { showConfirmPassword.value = !showConfirmPassword.value }) {
                            Icon(
                                painter = painterResource(
                                    id = if (showConfirmPassword.value) R.drawable.ic_visibility else R.drawable.ic_visibility_off
                                ),
                                contentDescription = if (showConfirmPassword.value) "Ocultar contraseña" else "Mostrar contraseña"
                            )
                        }
                    }
                )
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