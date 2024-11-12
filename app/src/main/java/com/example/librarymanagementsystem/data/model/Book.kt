package com.example.librarymanagementsystem.data.model

data class Book(
    var id: String? = null,
    var title: String,
    var description: String?,
    var imageUrl: String?
)