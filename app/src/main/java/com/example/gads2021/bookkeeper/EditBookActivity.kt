package com.example.gads2021.bookkeeper

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gads2021.bookkeeper.databinding.ActivityNewBinding

class EditBookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewBinding
    var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras

        bundle?.let {
            id = bundle.getString("id")
            val book = bundle.getString("book")
            val author= bundle.getString("author")

            binding.authorNameEditText.setText(author)
            binding.bookNameEditText.setText(book)
        }

        binding.saveButton.setOnClickListener {
            val updatedAuthor = binding.authorNameEditText.text.toString()
            val updatedBook = binding.bookNameEditText.text.toString()

            val resultIntent = Intent()
            resultIntent.putExtra(ID, id)
            resultIntent.putExtra(UPDATED_AUTHOR, updatedAuthor)
            resultIntent.putExtra(UPDATED_BOOK, updatedBook)
            setResult(Activity.RESULT_OK, resultIntent)

            finish()
        }

        binding.cancelButton.setOnClickListener {
            finish()
        }
    }

    companion object {
        const val ID = "book_id"
        const val UPDATED_AUTHOR = "author_name"
        const val UPDATED_BOOK = "book_name"
    }
}