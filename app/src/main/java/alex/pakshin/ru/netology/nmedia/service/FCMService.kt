package alex.pakshin.ru.netology.nmedia.service

import alex.pakshin.ru.netology.nmedia.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import kotlin.random.Random

class FCMService : FirebaseMessagingService() {
    private val gson = Gson()

    override fun onMessageReceived(message: RemoteMessage) {
        val data = message.data
        val serializedAction = data[Action.KEY] ?: return
        val action = Action.values().find { it.key == serializedAction }

        when (action) {
            Action.Like -> handleLikeAction(data[CONTENT_KEY] ?: return)
            Action.NewPost -> handleNewPostAction(data[CONTENT_KEY] ?: return)
        }
    }


    override fun onNewToken(token: String) {
        Log.d("onNewToken", token)
    }
    //f5ShdhedQcGkLIQvFQZRin:APA91bExDR8FSJUtMAIWYJ3yyeeN_G4hZZNr-HVyMb0pkrb2PHtNQveuieo69phvPM_FXfkkDkaKsSuqH2rdhFj7Ol5cbxUjGN-H4HszNVoY0oyImjjo95-5Xhs_yQl4OAyNM0i4LBQh


    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_remote_name)
            val descriptionText = getString(R.string.channel_remote_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    private fun handleLikeAction(serializedContent: String) {
        val likeContent = gson.fromJson(serializedContent, Like::class.java)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(
                getString(
                    R.string.notification_user_liked,
                    likeContent.userName,
                    likeContent.postAuthor
                )
            )
            .setSmallIcon(R.drawable.ic_notification)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        NotificationManagerCompat.from(this)
            .notify(Random.nextInt(100_000), notification)
    }

    private fun handleNewPostAction(serializedContent: String) {
        val postContent = gson.fromJson(serializedContent, NewPost::class.java)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(
                getString(
                    R.string.notification_user_new_post,
                    postContent.userName
                )
            )
            .setContentText(postContent.postContent)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(postContent.postContent)
            )
            .setSmallIcon(R.drawable.ic_notification)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        NotificationManagerCompat.from(this)
            .notify(Random.nextInt(100_000), notification)
    }

    companion object {
        const val CONTENT_KEY = "content"
        const val CHANNEL_ID = "remote"
    }

}