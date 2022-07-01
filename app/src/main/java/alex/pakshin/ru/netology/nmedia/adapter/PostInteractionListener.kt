package alex.pakshin.ru.netology.nmedia.adapter

import alex.pakshin.ru.netology.nmedia.Post

interface PostInteractionListener {
    fun onLikeClicked(post:Post)
    fun onShareClicked(post:Post)
    fun onRemoveClicked(post:Post)
    fun onEditClicked(post:Post)
}