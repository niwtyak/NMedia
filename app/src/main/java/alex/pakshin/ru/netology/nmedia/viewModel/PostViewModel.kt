package alex.pakshin.ru.netology.nmedia.viewModel

import alex.pakshin.ru.netology.nmedia.data.PostRepository
import alex.pakshin.ru.netology.nmedia.data.impl.InMemoryPostRepository
import androidx.lifecycle.ViewModel

class PostViewModel:ViewModel() {
    private val repository:PostRepository = InMemoryPostRepository()

    val data by repository::data

    fun onLikeClicked() = repository.like()

    fun onShareClicked() = repository.share()
}