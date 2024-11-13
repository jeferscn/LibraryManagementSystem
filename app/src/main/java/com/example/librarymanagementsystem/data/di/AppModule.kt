package com.example.librarymanagementsystem.data.di

import com.example.librarymanagementsystem.ui.book.BookAdapter
import com.example.librarymanagementsystem.ui.borrow.BorrowAdapter
import com.example.librarymanagementsystem.ui.user.UserAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideBookAdapter(): BookAdapter = BookAdapter(emptyList())

    @Provides
    fun provideUserAdapter(): UserAdapter = UserAdapter(emptyList())

    @Provides
    fun provideBorrowAdapter(): BorrowAdapter = BorrowAdapter(emptyList())
}