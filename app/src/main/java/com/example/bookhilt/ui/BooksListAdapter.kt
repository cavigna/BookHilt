package com.example.bookhilt.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.bookhilt.R
import com.example.bookhilt.databinding.ItemRowBinding
import com.example.bookhilt.model.models.Books


class BooksListAdapter(private val extraerID: ExtraerId) : ListAdapter<Books, BookViewHolder>(Comparador()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = getItem(position)

        with(holder.binding) {
            imageViewLogoRow.load(book.imageLink)
            tvTituloRow.text = book.title
            tvAuthorRow.text = book.author
            tvLanguageRow.text = book.language



            cardView.setOnClickListener {
                extraerID.alHacerClick(id = book.id)
                Navigation.findNavController(holder.itemView).navigate(R.id.action_homeFragment_to_detailsFragment)
            }
        }
    }

    interface ExtraerId{
        fun alHacerClick(id:Int)
    }
}

class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = ItemRowBinding.bind(itemView)

    companion object {
        fun create(parent: ViewGroup): BookViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemRowBinding.inflate(layoutInflater, parent, false)
            return BookViewHolder(binding.root)
        }
    }

}

class Comparador : DiffUtil.ItemCallback<Books>() {
    override fun areItemsTheSame(oldItem: Books, newItem: Books): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Books, newItem: Books): Boolean {
        return oldItem.id == newItem.id
    }


}
/*
  override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        when  {
            is LibroCerrado.Libros -> {
                val book = getItem(position)

                with(holder.binding) {
                    imageViewLogoRow.load(book.imageLink)
                    tvTituloRow.text = book.title
                    tvAuthorRow.text = book.author
                    tvLanguageRow.text = book.language


                }


            }
            is LibroDetalle ->{

            }

        }
    }
 */