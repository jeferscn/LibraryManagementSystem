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
    *       a - Tela de Empréstimo
    *       b - Tela de Livros
    *       c - Tela de Usuários
    * */

    /*
    * Ideia do projeto:
    * Temos quatro opções no menu da tela inicial:
    * 1 - Empréstimo, onde será capaz adicionar/remover livros vinculados aos usuários, da lista que constará na mesma tela
    * 2 - Livros, onde será capaz adicionar/remover livros da lista que constará na mesma tela
    * 3 - Usuários, onde será capaz adicionar/remover usuários da lista que constará na mesma tela
    * 4
    * */
}