package com.example.librarymanagementsystem.data.di

import android.content.Context
import com.example.librarymanagementsystem.data.repository.AppDatabase
import com.example.librarymanagementsystem.data.repository.book.BookInterface
import com.example.librarymanagementsystem.data.repository.book.BookRepository
import com.example.librarymanagementsystem.data.repository.borrow.BorrowInterface
import com.example.librarymanagementsystem.data.repository.borrow.BorrowRepository
import com.example.librarymanagementsystem.data.repository.book.BookDao
import com.example.librarymanagementsystem.data.repository.borrow.BorrowDao
import com.example.librarymanagementsystem.data.repository.user.UserDao
import com.example.librarymanagementsystem.data.repository.user.UserRepository
import com.example.librarymanagementsystem.data.repository.user.UserInterface
import com.example.librarymanagementsystem.ui.book.BookAdapter
import com.example.librarymanagementsystem.ui.borrow.BorrowAdapter
import com.example.librarymanagementsystem.ui.user.UserAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ): AppDatabase = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideBookDatabase(database: AppDatabase): BookDao = database.getBookDao()

    @Singleton
    @Provides
    fun provideUserDatabase(database: AppDatabase): UserDao = database.getUserDao()

    @Singleton
    @Provides
    fun provideBorrowDatabase(database: AppDatabase): BorrowDao = database.getBorrowDao()

    @Provides
    fun provideBookRepository(
        database: BookDao,
        borrowRepository: BorrowInterface
    ): BookInterface = BookRepository(database, borrowRepository)

    @Provides
    fun provideUserRepository(
        database: UserDao,
        borrowRepository: BorrowInterface
    ): UserInterface = UserRepository(database, borrowRepository)

    @Provides
    fun provideBorrowRepository(database: BorrowDao): BorrowInterface = BorrowRepository(database)

    @Provides
    fun provideBookAdapter(): BookAdapter = BookAdapter(emptyList())

    @Provides
    fun provideUserAdapter(): UserAdapter = UserAdapter(emptyList())

    @Provides
    fun provideBorrowAdapter(): BorrowAdapter = BorrowAdapter(emptyList())
}
