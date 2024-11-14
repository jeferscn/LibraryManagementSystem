package com.example.librarymanagementsystem.data.repository

class Database {
    private lateinit var instance: AppDatabase

    fun get() = instance

    fun set(instance: AppDatabase) {
        this.instance = instance
    }
}