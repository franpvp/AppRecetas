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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.navigation.NavController
import com.example.semana01.R

@Composable
fun Perfil(navController: NavController) {
    var selectedTab by remember { mutableStateOf(0) }

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
                    painter = painterResource(id = R.drawable.ic_email),
                    contentDescription = "Search",
                    tint = if (selectedTab == 1) MaterialTheme.colorScheme.primary else Color.Gray,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { selectedTab = 1 }
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = "User Profile",
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

