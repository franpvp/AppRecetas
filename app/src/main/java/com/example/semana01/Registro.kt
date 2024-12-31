package com.example.semana01

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

@Composable
fun Registro(onRegisterSuccess: () -> Unit) {
    val nombre = remember { mutableStateOf("") }
    val apellido = remember { mutableStateOf("") }
    val correo = remember { mutableStateOf("") }
    val celular = remember { mutableStateOf("") }
    val fechaNacimiento = remember { mutableStateOf("") }
    val contrasena = remember { mutableStateOf("") }
    val confirmarContrasena = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = nombre.value, onValueChange = { nombre.value = it }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = apellido.value, onValueChange = { apellido.value = it }, label = { Text("Apellido") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = correo.value, onValueChange = { correo.value = it }, label = { Text("Correo electrónico") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = celular.value, onValueChange = { celular.value = it }, label = { Text("Celular") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = fechaNacimiento.value, onValueChange = { fechaNacimiento.value = it }, label = { Text("Fecha de Nacimiento") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = contrasena.value, onValueChange = { contrasena.value = it }, label = { Text("Contraseña") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = confirmarContrasena.value, onValueChange = { confirmarContrasena.value = it }, label = { Text("Confirmar Contraseña") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (contrasena.value == confirmarContrasena.value) {
                onRegisterSuccess()
            } else {
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Registrar")
        }
    }
}