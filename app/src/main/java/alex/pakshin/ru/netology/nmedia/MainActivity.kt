package alex.pakshin.ru.netology.nmedia

import alex.pakshin.ru.netology.nmedia.data.impl.PostAdapter
import alex.pakshin.ru.netology.nmedia.databinding.ActivityMainBinding
import alex.pakshin.ru.netology.nmedia.databinding.PostBinding
import alex.pakshin.ru.netology.nmedia.viewModel.PostViewModel
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<PostViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PostAdapter(viewModel::onLikeClicked,viewModel::onShareClicked)
        binding.postRecycleView.adapter = adapter

       viewModel.data.observe(this){
         adapter.submitList(it)
       }
    }
}