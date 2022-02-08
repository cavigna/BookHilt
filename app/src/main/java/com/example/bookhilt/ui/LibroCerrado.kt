package com.example.bookhilt.ui

import com.example.bookhilt.model.models.BookDetail
import com.example.bookhilt.model.models.Books

sealed class LibroCerrado<T>(val data: T? = null){
    //class Libros : LibroCerrado<Books>()
    data class Libros( val listadoLibros : List<Books>) : LibroCerrado<List<Books>>()
    class LibrosDetalles(data:BookDetail) : LibroCerrado<BookDetail>(data)
}


/*
sealed class LibroCerrado{
    object Libros : LibroCerrado()
    object LibroFavorito
}
 */

/*
sealed class LibroCerrado<T>(val data: T? = null){
    //class Libros : LibroCerrado<Books>()
    data class Libros( val listadoLibros : List<Books>) : LibroCerrado<Books>()
    class LibrosDetalles(data:BookDetail) : LibroCerrado<BookDetail>(data)
}
 */

/*
sealed class LibroCerrado{
    //class Libros : LibroCerrado<Books>()
    object Libro : LibroCerrado()
    data class Libros( val listadoLibros : List<Books>) : LibroCerrado()
    data class LibroDetalle( val libroDetalle:BookDetail) : LibroCerrado()
    //class LibrosDetalles(data:BookDetail) : LibroCerrado<BookDetail>(data)
}
 */