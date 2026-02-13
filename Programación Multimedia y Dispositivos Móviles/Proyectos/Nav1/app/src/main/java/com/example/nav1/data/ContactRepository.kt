package com.example.nav1.data

import com.example.nav1.model.Person

object ContactRepository {

    fun getContacts(): Map<Char, List<Person>> {

        val contactos = listOf(
            Person("Ana", "Alonso", 28, "España"),
            Person("Luis", "Almeida", 34, "Brasil"),
            Person("María", "Benítez", 22, "Argentina"),
            Person("Carlos", "Borges", 30, "Portugal"),
            Person("Sara", "Cruz", 27, "Chile"),
            Person("Pedro", "Domínguez", 45, "México"),
            Person("Laura", "Estevez", 31, "España"),
            Person("Omar", "Fernández", 29, "Colombia"),
            Person("Lucía", "García", 20, "España")
        )

        return contactos
            .sortedBy { it.apellidos }
            .groupBy { it.apellidos.first() }
    }
}
