package alex.pakshin.ru.netology.nmedia.data.impl

import alex.pakshin.ru.netology.nmedia.Post
import alex.pakshin.ru.netology.nmedia.data.PostRepository
import androidx.lifecycle.MutableLiveData

class InMemoryPostRepository : PostRepository {
    override val data =
        MutableLiveData(Post(0, "Alex", "Very important message", "18.05.22", false, 999,1999))

    override fun like() {
        val currentPost = checkNotNull(data.value) {
            "Data value should not be null"
        }
        val post = currentPost.copy(
            liked = !currentPost.liked,
            likeCount = if (!currentPost.liked) currentPost.likeCount + 1 else currentPost.likeCount - 1
        )
        data.value = post
    }

    override fun share() {
        val currentPost = checkNotNull(data.value) {
            "Data value should not be null"
        }
        val post = currentPost.copy(
            shareCount = currentPost.shareCount + 1
        )
        data.value = post
    }
}