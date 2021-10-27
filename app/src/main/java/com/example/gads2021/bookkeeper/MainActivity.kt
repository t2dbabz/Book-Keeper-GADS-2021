package com.example.gads2021.bookkeeper

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gads2021.bookkeeper.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: BookViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val bookRepository = BookRepository(BookRoomDatabase(this))
        val viewModelProviderFactory = BookViewModelFactory(bookRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(BookViewModel::class.java)

        val bookListAdapter = BookListAdapter()

        binding.content.recyclerView.adapter = bookListAdapter
        binding.content.recyclerView.layoutManager = LinearLayoutManager(this)

       viewModel.getAllBooks().observe(this, { bookList ->
           bookListAdapter.setBooks(bookList)
       })

        val startForResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK ) {
                val intent = result.data
                val id = UUID.randomUUID().toString()
                val author = intent?.getStringExtra(NewBookActivity.NEW_AUTHOR)!!
                val bookName = intent.getStringExtra(NewBookActivity.NEW_BOOK)!!

                val book = Book(id, author, bookName)

                viewModel.insertBook(book)

                Toast.makeText(applicationContext, "Saved", Toast.LENGTH_LONG).show()

            }
        }

        binding.fab.setOnClickListener {
            val intent = Intent(this, NewBookActivity::class.java)
            startForResult.launch(intent)

        }
    }


}