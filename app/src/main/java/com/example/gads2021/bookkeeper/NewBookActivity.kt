package com.example.gads2021.bookkeeper

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.example.gads2021.bookkeeper.databinding.ActivityNewBinding

class NewBookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val resultIntent = Intent()

            if (TextUtils.isEmpty(binding.authorNameEditText.text) || TextUtils.isEmpty(binding.bookNameEditText.text)) {
                setResult(Activity.RESULT_CANCELED, resultIntent)
            } else {

                val author = binding.authorNameEditText.text.toString()
                val bookName = binding.bookNameEditText.text.toString()

                resultIntent.putExtra(NEW_AUTHOR, author)
                resultIntent.putExtra(NEW_BOOK, bookName)
                setResult(Activity.RESULT_OK, resultIntent)

            }
            finish()
        }

        binding.cancelButton.setOnClickListener {
            finish()
        }

    }

    companion object {
        const val NEW_AUTHOR = "new_author"
        const val NEW_BOOK = "new_book"
    }
}