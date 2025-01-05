package com.example.semana01.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.navigation.NavController
import com.example.semana01.R

@Composable
fun Home(navController: NavController) {
    var selectedTab by remember { mutableStateOf(0) }

    // Datos de la minuta semanal
    val minutaSemanal = arrayOf(
        Receta(
            nombre = "Ensalada de Quinoa",
            ingredientes = listOf("Quinoa", "Pepino", "Tomate", "Aguacate", "Limón", "Aceite de oliva"),
            instrucciones = "Cocina la quinoa, mezcla con los vegetales picados y aliña con aceite de oliva y limón.",
            recomendacionesNutricionales = "Alta en fibra, proteínas vegetales y grasas saludables."
        ),
        Receta(
            nombre = "Pollo a la plancha con verduras",
            ingredientes = listOf("Pechuga de pollo", "Zanahoria", "Brócoli", "Aceite de oliva", "Especias"),
            instrucciones = "Cocina el pollo a la plancha y acompáñalo con las verduras al vapor.",
            recomendacionesNutricionales = "Fuente rica en proteínas magras y vegetales ricos en vitaminas."
        ),
        Receta(
            nombre = "Sopa de lentejas",
            ingredientes = listOf("Lentejas", "Zanahoria", "Apio", "Cebolla", "Caldo de verduras"),
            instrucciones = "Cocina las lentejas con las verduras y el caldo hasta que estén tiernas.",
            recomendacionesNutricionales = "Rica en hierro, fibra y proteínas vegetales."
        ),
        Receta(
            nombre = "Tacos de pescado",
            ingredientes = listOf("Filete de pescado", "Tortillas de maíz", "Lechuga", "Tomate", "Cebolla"),
            instrucciones = "Cocina el pescado y sirve en tortillas con lechuga, tomate y cebolla.",
            recomendacionesNutricionales = "Bajo en grasas, fuente de omega-3 y vitaminas."
        ),
        Receta(
            nombre = "Batido de frutas y avena",
            ingredientes = listOf("Frutas (fresas, plátano)", "Avena", "Yogur natural", "Miel"),
            instrucciones = "Licúa las frutas con avena, yogur y miel hasta obtener una mezcla suave.",
            recomendacionesNutricionales = "Rico en antioxidantes, fibra y probióticos."
        )
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // Slideable Content
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .pointerInput(Unit) {
//                    detectHorizontalDragGestures { _, dragAmount ->
//                        boxOffset += dragAmount
//                    }
//                }
//        ) {
//            Box(
//                modifier = Modifier
//                    .offset(x = boxOffset.dp)
//                    .fillMaxWidth()
//                    .height(200.dp)
//                    .padding(16.dp)
//                    .background(MaterialTheme.colorScheme.primary)
//            ) {
//                Text(
//                    text = "Slide Me!",
//                    fontSize = 24.sp,
//                    color = Color.White,
//                    modifier = Modifier.align(Alignment.Center)
//                )
//            }
//        }

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())  // Agregar el scroll vertical aquí
        ) {
            Text(
                text = "Minuta Semanal",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            minutaSemanal.forEach { receta ->
                RecetaCard(receta = receta)
            }
        }

        // Tab Bar at the Bottom
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
                        .size(40.dp)
                        .clickable { selectedTab = 0 }
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Mensajes",
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
                            navController.navigate("menu") // Navega al menú (componente Menu)
                        }
                )
            }
        }
    }
}

@Composable
fun RecetaCard(receta: Receta) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(8.dp)
            .background(MaterialTheme.colorScheme.surface),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = receta.nombre, style = MaterialTheme.typography.bodyLarge, fontSize = 20.sp, color = MaterialTheme.colorScheme.onSurface)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Ingredientes:",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = receta.ingredientes.joinToString(", "), style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Instrucciones:",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = receta.instrucciones, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Recomendaciones Nutricionales:",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = receta.recomendacionesNutricionales, style = MaterialTheme.typography.bodySmall)
        }
    }
}

data class Receta(
    val nombre: String,
    val ingredientes: List<String>,
    val instrucciones: String,
    val recomendacionesNutricionales: String
)