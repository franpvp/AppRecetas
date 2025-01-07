package com.example.semana01.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon

import androidx.compose.foundation.background
import androidx.compose.material3.Text


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.semana01.R

@Composable
fun Menu(navController: NavController) {
    var selectedTab by remember { mutableStateOf(0) }
    var showDialog by remember { mutableStateOf(false) } // Estado para mostrar el pop-up

    // Función para cerrar sesión (puedes personalizarla según tu lógica)
    val handleLogout = {
        navController.navigate("login")
    }

    // Contenedor principal
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())  // Añadido scroll para mayor flexibilidad
        ) {
            // Foto de perfil
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = "Foto de Perfil",
                    modifier = Modifier
                        .size(100.dp)
                        .background(MaterialTheme.colorScheme.secondary, shape = MaterialTheme.shapes.medium)
                        .padding(8.dp)
                        .border(2.dp, MaterialTheme.colorScheme.primary, shape = MaterialTheme.shapes.medium)
                )
            }

            // Opciones del menú
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Opciones del menú como botones
                val buttonModifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
                    .height(48.dp)

                Button(
                    onClick = { navController.navigate("perfil") },
                    modifier = buttonModifier
                ) {
                    Text(text = "Mi Perfil", style = MaterialTheme.typography.bodySmall)
                }

                Button(
                    onClick = { navController.navigate("forgotPassword") },
                    modifier = buttonModifier
                ) {
                    Text(text = "Cambio de Contraseña", style = MaterialTheme.typography.bodySmall)
                }

                Button(
                    onClick = { navController.navigate("contacto") },
                    modifier = buttonModifier
                ) {
                    Text(text = "Contacto", style = MaterialTheme.typography.bodySmall)
                }

                // Botón para cerrar sesión que muestra el pop-up
                Button(
                    onClick = { showDialog = true },
                    modifier = buttonModifier
                ) {
                    Text(text = "Cerrar Sesión", style = MaterialTheme.typography.bodySmall)
                }
            }
        }

        // Barra de navegación inferior
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_home),
                    contentDescription = "Home",
                    tint = if (selectedTab == 0) MaterialTheme.colorScheme.primary else Color.Gray,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            selectedTab = 0
                            navController.navigate("home")
                        }
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_email),
                    contentDescription = "Search",
                    tint = if (selectedTab == 1) MaterialTheme.colorScheme.primary else Color.Gray,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            selectedTab = 1
                            navController.navigate("mensajes")
                        }
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "Menu",
                    tint = if (selectedTab == 2) MaterialTheme.colorScheme.primary else Color.Gray,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            selectedTab = 2
                            navController.navigate("menu")
                        }
                )
            }
        }

        // Pop-up para confirmar el cierre de sesión
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "¿Cerrar sesión?") },
                text = { Text("¿Estás seguro de que deseas cerrar sesión?") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            handleLogout() // Acción de cerrar sesión
                            showDialog = false // Cerrar el pop-up
                        }
                    ) {
                        Text("Sí")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { showDialog = false } // Cerrar el pop-up sin hacer nada
                    ) {
                        Text("No")
                    }
                }
            )
        }
    }
}