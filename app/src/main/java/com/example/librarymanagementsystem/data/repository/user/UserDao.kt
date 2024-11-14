package com.example.librarymanagementsystem.data.repository.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.librarymanagementsystem.data.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Query("SELECT * FROM user WHERE id = :userId")
    fun findById(userId: Int?): User?

    @Query("DELETE FROM user WHERE id = :userId")
    fun deleteById(userId: Int?)

    @Query("DELETE FROM user")
    fun truncate()
}