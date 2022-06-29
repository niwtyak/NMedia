package alex.pakshin.ru.netology.nmedia.data.impl

import alex.pakshin.ru.netology.nmedia.Post
import alex.pakshin.ru.netology.nmedia.data.PostRepository
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class InMemoryPostRepository : PostRepository {
    override val data =
        MutableLiveData(
            List(10) {
                Post(
                    it + 1L,
                    "Author",
                    "Very important message $it",
                    "18.05.22",
                    false,
                    999,
                   999
                )
            })

    private val posts
        get() = checkNotNull(data.value) {
            "Data value should not be null"
        }

    override fun like(postId: Long) {
        data.value =
            posts.map {
                if (it.id == postId)
                    it.copy(
                        liked = !it.liked,
                        likeCount = if (!it.liked) it.likeCount + 1 else it.likeCount - 1
                    ) else it
            }
    }

    override fun share(postId: Long) {
        data.value = posts.map {
            if (it.id == postId)
                it.copy(shareCount = it.shareCount + 1) else it
        }
    }
}