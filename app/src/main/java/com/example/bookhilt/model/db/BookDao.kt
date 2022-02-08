package com.example.bookhilt.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookhilt.model.models.BookDetail
import com.example.bookhilt.model.models.Books
import kotlinx.coroutines.flow.Flow


@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBooks(books: List<Books>)

    @Query("SELECT * FROM books_table")
    fun selectAllBooks(): LiveData<List<Books>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailBook(book:BookDetail)

    @Query("SELECT * FROM book_details_table WHERE id=:id")
    fun selectBookDetail(id:Int): LiveData<BookDetail>

    @Query("SELECT * FROM book_details_table WHERE id=:id")
    fun selectBookDetailFlow(id:Int): Flow<BookDetail>


}