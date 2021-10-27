package com.example.gads2021.bookkeeper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gads2021.bookkeeper.databinding.ListItemBinding

class BookListAdapter(private  val onClickListener: OnClickListener ): RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {

    interface OnClickListener {
        fun onDeleteClickListener(book: Book)
        fun onEditClickListener(book: Book)
    }

    private var bookList: List<Book> = mutableListOf()

    inner class BookViewHolder(private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {
        private var pos: Int = 0

        fun setData(author: String, book: String, position: Int) {
            binding.authorName.text = author
            binding.bookName.text = book
            this.pos = position
        }

        fun setupListener() {
            binding.rowDeleteImageView.setOnClickListener {
                onClickListener.onDeleteClickListener(bookList[pos])
            }

            binding.rowEditImageView.setOnClickListener {
                onClickListener.onEditClickListener(bookList[pos])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val adapterLayout = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]
        holder.setData(book.author, book.bookName, position)
        holder.setupListener()
    }

    override fun getItemCount(): Int {
        return  bookList.size
    }

    fun setBooks(books: List<Book>) {
        bookList = books
        notifyDataSetChanged()
    }
}