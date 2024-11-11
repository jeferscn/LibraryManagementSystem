package com.example.librarymanagementsystem

import com.example.librarymanagementsystem.ui.user.UserViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Test

class UserUnitTest {

    @Test
    fun getItemsWithValues() {
        val userViewModel = UserViewModel()
        val items = userViewModel.getItems()
        assertEquals(true, items.isNotEmpty())
    }

    @Test
    fun getItemsWithoutValues() {
        val userViewModel = UserViewModel()
        val items = userViewModel.getItems()
        items.clear()
        assertEquals(false, items.isNotEmpty())
    }

    @Test
    fun addItem() {
        val userViewModel = UserViewModel()
        val items = userViewModel.getItems()
        items.clear()
        userViewModel.addItem("TitleTest", "DescriptionTest") {
            assertEquals(print("Failure"), "Failure")
        }
        assertEquals("TitleTest", items[0].first)
        assertEquals("DescriptionTest", items[0].second)
    }

    @Test
    fun addItemWithEmptyValues() {
        val userViewModel = UserViewModel()
        val items = userViewModel.getItems()
        items.clear()
        userViewModel.addItem("", "") {
            assertEquals(true, userViewModel.getItems().isEmpty())
        }
    }

    @Test
    fun addItemWithTitleOnly() {
        val userViewModel = UserViewModel()
        val items = userViewModel.getItems()
        items.clear()
        userViewModel.addItem("TitleTest", "") {
            assertEquals(true, userViewModel.getItems().isEmpty())
        }
    }

    @Test
    fun addItemWithDescriptionOnly() {
        val userViewModel = UserViewModel()
        val items = userViewModel.getItems()
        items.clear()
        userViewModel.addItem("", "DescriptionTest") {
            assertEquals(true, userViewModel.getItems().isEmpty())
        }
    }
}