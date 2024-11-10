package com.example.librarymanagementsystem.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.librarymanagementsystem.databinding.ActivityMainBinding
import com.example.librarymanagementsystem.extensions.setSafeOnClickListener
import com.example.librarymanagementsystem.managers.NavigationManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupButtons()
    }

    private fun setupButtons() {
        binding.btnBorrow.setSafeOnClickListener {
            NavigationManager.goToBorrow(this)
        }

        binding.btnBooks.setSafeOnClickListener {
            NavigationManager.goToBooks(this)
        }

        binding.btnUsers.setSafeOnClickListener {
            NavigationManager.goToUsers(this)
        }

        binding.btnRegister.setSafeOnClickListener {
            NavigationManager.goToRegister(this)
        }
    }

    /*
    * Requisitos de um sistema de livraria:
    * 1 - Cadastro de usuários
    * 2 - Cadastro de livros
    * 3 - Cadastro de empréstimos
    * 4 - Cadastro de devoluções
    * */

    /*
    * Telas do sistema:
    *  1 - Tela de Navegação:
    *       a - Tela de Cadastros
    *       b - Tela de lista de livros emprestados
    *       c - Tela de lista de livros disponíveis
    * */
}