package com.example.librarymanagementsystem

import com.example.librarymanagementsystem.models.User
import com.example.librarymanagementsystem.ui.user.UserViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Test

class UserUnitTest {

    @Test
    fun getItemsWithValues() {
        val userViewModel = UserViewModel()
        val items = userViewModel.getItems()
        items.add(User(1, "TitleTest", "DescriptionTest"))
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
        assertEquals("TitleTest", items[0].name)
        assertEquals("DescriptionTest", items[0].surname)
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

    @Test
    fun removeItemAfterAdding() {
        val userViewModel = UserViewModel()
        assertEquals(true, userViewModel.getItems().isEmpty())
        userViewModel.addItem(name = "Jefs", surname = "Pereira") {
            assertEquals(true, userViewModel.getItems().isNotEmpty())
        }
        assertEquals(true, userViewModel.getItems().isNotEmpty())
        userViewModel.removeItem(0) {}
        assertEquals(true, userViewModel.getItems().isEmpty())
    }

    @Test
    fun removeItemAfterAddingWithFailure() {
        val userViewModel = UserViewModel()
        assertEquals(true, userViewModel.getItems().isEmpty())
        userViewModel.addItem(name = "Fulano", surname = "Detal") {
            assertEquals(true, userViewModel.getItems().isNotEmpty())
        }
        assertEquals(true, userViewModel.getItems().isNotEmpty())
        userViewModel.removeItem(50) {
            assertEquals(true, userViewModel.getItems().count() == 1)
        }
    }

    @Test
    fun removeItemWithEmptyList() {
        val userViewModel = UserViewModel()
        assertEquals(true, userViewModel.getItems().isEmpty())
        userViewModel.removeItem(0) {
            assertEquals(true, userViewModel.getItems().isEmpty())
        }
    }

    @Test
    fun removeItemWithInvalidPosition() {
        val userViewModel = UserViewModel()
        assertEquals(true, userViewModel.getItems().isEmpty())
        userViewModel.removeItem(-1) {
            assertEquals(true, userViewModel.getItems().isEmpty())
        }
    }
}