package alex.pakshin.ru.netology.nmedia

import alex.pakshin.ru.netology.nmedia.adapter.PostAdapter
import alex.pakshin.ru.netology.nmedia.databinding.ActivityMainBinding
import alex.pakshin.ru.netology.nmedia.util.hideKeyboard
import alex.pakshin.ru.netology.nmedia.util.showKeyboard
import alex.pakshin.ru.netology.nmedia.viewModel.PostViewModel
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<PostViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PostAdapter(viewModel)
        binding.postRecycleView.adapter = adapter

        viewModel.data.observe(this) {
            adapter.submitList(it)
        }

        binding.saveButton.setOnClickListener {
            with(binding.contentEditText) {
                val content = text.toString()
                viewModel.onSaveButtonClicked(content)
            }
        }

        binding.closeEditButton.setOnClickListener {
            viewModel.onCloseEditClicked()
        }

        viewModel.currentPost.observe(this) { currentPost ->
            with(binding.contentEditText) {
                val content = currentPost?.content
                setText(content)
                if (content != null) {
                    showKeyboard()
                    requestFocus()
                    binding.editGroup.visibility = View.VISIBLE
                    binding.editMessageText.text = content
                } else {
                    clearFocus()
                    hideKeyboard()
                    binding.editGroup.visibility = View.GONE
                }
            }
        }

    }
}