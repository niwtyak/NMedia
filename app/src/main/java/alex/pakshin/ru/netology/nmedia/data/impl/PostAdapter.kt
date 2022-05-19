package alex.pakshin.ru.netology.nmedia.data.impl

import alex.pakshin.ru.netology.nmedia.Post
import alex.pakshin.ru.netology.nmedia.R
import alex.pakshin.ru.netology.nmedia.databinding.PostBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


internal class PostAdapter(
    private val onLikeClicked: (Post) -> Unit,
    private val onShareClicked: (Post) -> Unit,
) : ListAdapter<Post, PostAdapter.ViewHolder>(DiffCallback) {

    inner class ViewHolder(private val binding: PostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var post:Post

        init {
            binding.likeIcon.setOnClickListener {
                onLikeClicked(post)
            }
            binding.shareIcon.setOnClickListener {
                onShareClicked(post)
            }
        }

        fun bind(post: Post) {
            this.post = post
            with(binding) {
                authorName.text = post.author
                date.text = post.published
                content.text = post.content
                likeIcon.setImageResource(getLikeIcon(post.liked))
                likeCount.text = getDecimalFormat(post.likeCount)
                shareCount.text = getDecimalFormat(post.shareCount)

            }
        }

        private fun getLikeIcon(liked: Boolean) =
            if (liked) R.drawable.ic_liked_24dp else R.drawable.ic_like_24dp

        private fun getDecimalFormat(count: Int): String = when {
            count < 1000 -> count.toString()
            (count < 10000 && count % 1000 / 100 == 0) || count in 10000..1000000 -> "${(count / 1000)}K"
            count < 10000 -> "${(count / 100 / 10.0)}K"
            (count < 10000000 && count % 1000000 / 100000 == 0) || count in 10000000..1000000000 -> "${(count / 1000000)}M"
            count < 10000000 -> "${(count / 100000 / 10.0)}M"
            else -> ""
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private object DiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
            oldItem == newItem
    }
}