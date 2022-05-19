package alex.pakshin.ru.netology.nmedia.viewModel

import alex.pakshin.ru.netology.nmedia.Post
import alex.pakshin.ru.netology.nmedia.data.PostRepository
import alex.pakshin.ru.netology.nmedia.data.impl.InMemoryPostRepository
import androidx.lifecycle.ViewModel

class PostViewModel:ViewModel() {
    private val repository:PostRepository = InMemoryPostRepository()

    val data by repository::data

    fun onLikeClicked(post: Post) = repository.like(post.id)

    fun onShareClicked(post: Post) = repository.share(post.id)
}