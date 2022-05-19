package alex.pakshin.ru.netology.nmedia

import alex.pakshin.ru.netology.nmedia.databinding.ActivityMainBinding
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

       viewModel.data.observe(this){
           binding.render(it)
       }

       binding.likeIcon.setOnClickListener {
           viewModel.onLikeClicked()
        }

        binding.shareIcon.setOnClickListener {
           viewModel.onShareClicked()
        }
    }

    private fun ActivityMainBinding.render(post: Post) {
        authorName.text = post.author
        date.text = post.published
        content.text = post.content
        likeIcon.setImageResource(getLikeIcon(post.liked))
        likeCount.text = getDecimalFormat(post.likeCount)
        shareCount.text = getDecimalFormat(post.shareCount)
    }

    private fun getLikeIcon(liked: Boolean) =
        if (liked) R.drawable.ic_liked_24dp else R.drawable.ic_like_24dp

    private fun getDecimalFormat(count: Int): String = when {
        count < 1000 -> count.toString()
        (count<10000 && count%1000/100==0) || count in 10000..1000000 -> "${(count / 1000)}K"
        count < 10000 ->  "${(count / 100 /10.0)}K"
        (count<10000000 && count%1000000/100000==0) || count in 10000000..1000000000 -> "${(count / 1000000)}M"
        count < 10000000 ->  "${(count / 100000 /10.0)}M"
        else -> ""
    }

}