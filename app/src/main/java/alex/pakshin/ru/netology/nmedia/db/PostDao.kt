package alex.pakshin.ru.netology.nmedia.db

import alex.pakshin.ru.netology.nmedia.data.PostRepository
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostDao {
    @Query("SELECT * FROM posts ORDER BY id DESC")
    fun getAll(): LiveData<List<PostEntity>>

    @Query("UPDATE posts SET content = :content WHERE id = :id")
    fun updateContentById(id: Long, content: String)

    @Insert
    fun insert(post: PostEntity)

    @Query(
        """
            UPDATE posts SET
                likeCount = likeCount + CASE WHEN liked THEN -1 ELSE 1 END,
                liked = CASE WHEN liked THEN 0 ELSE 1 END
                WHERE id = :id
        """
    )
    fun likeById(id: Long)

    @Query("UPDATE posts SET shareCount = shareCount + 1 WHERE id = :id")
    fun shareById(id: Long)

    @Query("DELETE FROM posts WHERE id = :id")
    fun removeById(id: Long)
}