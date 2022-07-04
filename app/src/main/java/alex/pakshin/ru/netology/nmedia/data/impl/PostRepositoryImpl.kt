package alex.pakshin.ru.netology.nmedia.data.impl

import alex.pakshin.ru.netology.nmedia.data.Post
import alex.pakshin.ru.netology.nmedia.data.PostRepository
import alex.pakshin.ru.netology.nmedia.db.PostDao
import alex.pakshin.ru.netology.nmedia.db.toEntity
import alex.pakshin.ru.netology.nmedia.db.toPost
import androidx.lifecycle.map

class PostRepositoryImpl(
    private val dao: PostDao
) : PostRepository {

    override val data = dao.getAll().map { entities ->
        entities.map { it.toPost() }
    }


    override fun like(postId: Long) = dao.likeById(postId)

    override fun share(postId: Long) = dao.shareById(postId)

    override fun save(post: Post) {
        if (post.id == PostRepository.NEW_POST_ID) dao.insert(post.toEntity())
        else dao.updateContentById(post.id, post.content)
    }

    override fun delete(postId: Long) = dao.removeById(postId)

}