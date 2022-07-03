package alex.pakshin.ru.netology.nmedia.db

import alex.pakshin.ru.netology.nmedia.data.Post
import android.database.Cursor

fun PostEntity.toPost() = Post(
    id = id,
    author = author,
    content = content,
    published = published,
    liked = liked,
    likeCount = likeCount,
    shareCount = shareCount,
    url = url
)

fun Post.toEntity() = PostEntity(
    id = id,
    author = author,
    content = content,
    published = published,
    liked = liked,
    likeCount = likeCount,
    shareCount = shareCount,
    url = url
)