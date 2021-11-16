package ib.ganz.u_care.manager

import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessaging : FirebaseMessagingService() {

    override fun onNewToken(s: String) {
        super.onNewToken(s)
        SessionManager.setToken(s)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(Intent("reload"))

        val data = remoteMessage.data
        val title = data["title"] ?: "Notifikasi baru"
        val body = data["body"] ?: "Anda mendapatkan notifikasi"
//        when (data["type"]) {
//            "notification" -> {
//                val n = Gxon.fromJsonObject(data["data"], NotificationData::class.java)
//
//                if (n.action == "confirmation") {
//                    val anak = Gxon.fromJsonObject(data["data2"], AnakData::class.java)
//                    SessionManager.addAnak(anak)
//                }
//
//                NotifManager.createNotification(
//                        this,
//                        n.id.toInt(),
//                        PendingIntent.getActivity(
//                            this,
//                            n.id.toInt(),
//                            MainActivity.openNotification(this, n),
//                            PendingIntent.FLAG_UPDATE_CURRENT
//                        ),
//                        title,
//                        body
//                )
//            }
//        }
    }
}