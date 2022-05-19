package alex.pakshin.ru.netology.nmedia.data

import alex.pakshin.ru.netology.nmedia.Post
import androidx.lifecycle.LiveData

interface PostRepository {
    val data: LiveData<List<Post>>

    fun like(postId:Long)

    fun share(postId:Long)
}