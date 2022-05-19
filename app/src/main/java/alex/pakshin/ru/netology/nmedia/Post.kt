package alex.pakshin.ru.netology.nmedia

data class Post(
    val id:Long,
    val author:String,
    val content:String,
    val published:String,
    var liked:Boolean = false,
    var likeCount:Int = 0,
    var shareCount:Int = 0,
)
