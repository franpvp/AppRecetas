package com.example.semana01.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object UserManager {

    private const val PREF_NAME = "user_prefs"
    private const val KEY_USERS = "users"

    // Obtener SharedPreferences
    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    // Convertir la lista de usuarios a JSON para almacenarla
    private fun saveUsersToPrefs(context: Context, users: List<Pair<String, String>>) {
        val sharedPreferences = getPreferences(context)
        val gson = Gson()
        val usersJson = gson.toJson(users)
        sharedPreferences.edit().putString(KEY_USERS, usersJson).apply()
    }

    // Obtener los usuarios almacenados en SharedPreferences
    private fun getUsersFromPrefs(context: Context): List<Pair<String, String>> {
        val sharedPreferences = getPreferences(context)
        val usersJson = sharedPreferences.getString(KEY_USERS, null)
        val gson = Gson()

        return if (usersJson != null) {
            // Usamos TypeToken para indicar el tipo exacto
            val type = object : TypeToken<List<Pair<String, String>>>() {}.type
            gson.fromJson(usersJson, type)
        } else {
            emptyList()
        }
    }

    // Agregar un nuevo usuario
    fun addUser(context: Context, email: String, password: String) {
        val users = getUsersFromPrefs(context).toMutableList()
        users.add(email to password)
        saveUsersToPrefs(context, users)
    }

    // Validar si un usuario existe
    fun validateUser(context: Context, email: String, password: String): Boolean {
        val users = getUsersFromPrefs(context)
        return users.any { it.first == email && it.second == password }
    }
}