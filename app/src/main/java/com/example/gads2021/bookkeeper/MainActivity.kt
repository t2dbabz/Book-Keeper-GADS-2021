package com.example.gads2021.bookkeeper

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gads2021.bookkeeper.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), BookListAdapter.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: BookViewModel
    private lateinit var startForEditBook: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val bookRepository = BookRepository(BookRoomDatabase(this))
        val viewModelProviderFactory = BookViewModelFactory(bookRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(BookViewModel::class.java)

        val bookListAdapter = BookListAdapter(this)

        binding.content.recyclerView.adapter = bookListAdapter
        binding.content.recyclerView.layoutManager = LinearLayoutManager(this)

       viewModel.getAllBooks().observe(this, { bookList ->
           bookListAdapter.setBooks(bookList)
       })

        val startForNewBook = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK ) {
                val intent = result.data
                val id = UUID.randomUUID().toString()
                val author = intent?.getStringExtra(NewBookActivity.NEW_AUTHOR)!!
                val bookName = intent.getStringExtra(NewBookActivity.NEW_BOOK)!!

                val book = Book(id, author, bookName)

                viewModel.insertBook(book)

                Toast.makeText(applicationContext, R.string.saved, Toast.LENGTH_LONG).show()
            }
        }

        startForEditBook = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
                if (result.resultCode == Activity.RESULT_OK ) {
                    val intent = result.data
                    val id = intent!!.getStringExtra(EditBookActivity.ID)!!
                    val authorName = intent.getStringExtra(EditBookActivity.UPDATED_AUTHOR)!!
                    val bookName = intent.getStringExtra(EditBookActivity.UPDATED_BOOK)!!

                    val book = Book(id, authorName, bookName)

                    // Code to update
                    viewModel.updateBook(book)

                    Toast.makeText(applicationContext, R.string.updated, Toast.LENGTH_LONG).show()
                }
            }


        binding.fab.setOnClickListener {
            val intent = Intent(this, NewBookActivity::class.java)
            startForNewBook.launch(intent)
        }
    }

    override fun onDeleteClickListener(book: Book) {
        viewModel.deleteBook(book)
        Toast.makeText(applicationContext, R.string.deleted, Toast.LENGTH_LONG).show()
    }

    override fun onEditClickListener(book: Book) {
        val intent = Intent(this, EditBookActivity::class.java)
        intent.putExtra("id", book.id)
        intent.putExtra("author", book.author)
        intent.putExtra("book", book.bookName)
        startForEditBook.launch(intent)
    }


}