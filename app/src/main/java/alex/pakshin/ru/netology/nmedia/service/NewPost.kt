package alex.pakshin.ru.netology.nmedia.service

import com.google.gson.annotations.SerializedName

class NewPost(
    @SerializedName("userId")
    val userId: Long,
    @SerializedName("userName")
    val userName: String,
    @SerializedName("postId")
    val postId: Long,
    @SerializedName("postContent")
    val postContent: String
)