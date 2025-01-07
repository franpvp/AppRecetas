package com.example.semana01.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard

import androidx.navigation.NavController
import com.example.semana01.R
@Composable
fun Home(navController: NavController) {
    var selectedTab by remember { mutableIntStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Título del Home
            Text(
                text = "Accesibilidad para Todos",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Tarjetas con funcionalidades
            FuncionalidadCard(
                title = "Reconocimiento de Texto (OCR)",
                description = "Extrae texto de documentos como PDFs e imágenes para convertirlo en audio o texto accesible.",
                imageResId = R.drawable.ic_ocr,  // Agregar imagen relevante
                onClick = { /* Acción al hacer clic, por ejemplo, navegar a una pantalla de OCR */ }
            )
            FuncionalidadCard(
                title = "Clasificación Automática",
                description = "Organiza documentos en categorías como contratos, recibos y más, para facilitar su gestión.",
                imageResId = R.drawable.ic_classification,  // Imagen para clasificación
                onClick = { /* Acción al hacer clic */ }
            )
            FuncionalidadCard(
                title = "Accesibilidad Total",
                description = "Interfaz optimizada para lectores de pantalla y comandos de voz, garantizando la independencia del usuario.",
                imageResId = R.drawable.ic_accessibility,  // Imagen de accesibilidad
                onClick = { /* Acción al hacer clic */ }
            )
            FuncionalidadCard(
                title = "Traducción Automática",
                description = "Convierte documentos en otros idiomas a tu idioma preferido para una comprensión más fácil.",
                imageResId = R.drawable.ic_translation,  // Imagen para traducción
                onClick = { /* Acción al hacer clic */ }
            )
            FuncionalidadCard(
                title = "Validación de Firmas Digitales",
                description = "Permite verificar la autenticidad de documentos con firmas digitales.",
                imageResId = R.drawable.ic_signature,  // Imagen de firma digital
                onClick = { /* Acción al hacer clic */ }
            )
            FuncionalidadCard(
                title = "Seguridad y Privacidad",
                description = "Encriptación de datos y autenticación segura para proteger tu información personal.",
                imageResId = R.drawable.ic_security,  // Imagen de seguridad
                onClick = { /* Acción al hacer clic */ }

            )
            Spacer(modifier = Modifier.height(64.dp))
        }
        // Tab
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
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
                        .size(20.dp)  // Tamaño modificado
                        .clickable { selectedTab = 0 }
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_email),
                    contentDescription = "Mensajes",
                    tint = if (selectedTab == 1) MaterialTheme.colorScheme.primary else Color.Gray,
                    modifier = Modifier
                        .size(20.dp)  // Tamaño modificado
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
                        .size(20.dp)  // Tamaño modificado
                        .clickable {
                            selectedTab = 2
                            navController.navigate("menu")
                        }
                )
            }
        }
    }
}

// Composable para la tarjeta de funcionalidad con imagen
@Composable
fun FuncionalidadCard(
    title: String,
    description: String,
    imageResId: Int,  // Nuevo parámetro para la imagen
    onClick: () -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Imagen de la tarjeta
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "Imagen funcionalidad",
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Título y descripción
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
        }
    }
}
