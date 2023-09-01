package com.example.applemarket.Main

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applemarket.MainActivity
import com.example.applemarket.R
import com.example.applemarket.adapter.HomeAdapter
import com.example.applemarket.data.GoodsDB
import com.example.applemarket.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    lateinit var homeAdapter: HomeAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeAdapter = HomeAdapter(GoodsDB.goodsList)
        initView()
        alarm()
    }

    private fun initView() = with(binding) {
        rvHomeSaleList.layoutManager = LinearLayoutManager(context)
        rvHomeSaleList.adapter = homeAdapter
    }

    private fun alarm() = with(binding) {
        btnAlarm.setOnClickListener {
            val manager =requireContext().getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager

            val builder: NotificationCompat.Builder

            //오레오버전 26버전 이상
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                val channelId = "one-channel"
                val cannelName = "My Channel One"

                val channel = NotificationChannel(
                    channelId,
                    cannelName,
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    description = "My Channal Discription"
                    setShowBadge(true)
                    val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                    val audioAttribute = AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .setUsage(AudioAttributes.USAGE_ALARM)
                        .build()
                    //알람 울릴 시 오디오 소리 나기
                    setSound(uri, audioAttributes)
                    //알람 울릴 시 진동
                    enableVibration(true)
                }
                //채널을 notificationManager에 등록함
                manager.createNotificationChannel(channel)

                //채널을 이용하여 builder todtjd
                builder = NotificationCompat.Builder(requireContext(), channelId)
            } else {
                //26버전 이하
                builder = NotificationCompat.Builder(requireContext())
            }

            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_logo)
            val intent = Intent(requireContext(), MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            val pendingIntent = PendingIntent.getActivities(
                requireContext(),
                0,
                arrayOf(intent),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            //알림 기본 정보
            builder.run {
                setSmallIcon(R.drawable.ic_logo)
                setWhen(System.currentTimeMillis())
                setContentTitle("키워드 알림")
                setContentText("키워드 알림이 도착했습니다!")
                setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(bitmap)
                        .bigLargeIcon(null)
                )
                setLargeIcon(bitmap)
                addAction(R.drawable.ic_logo, "이동하기", pendingIntent)
            }


            manager.notify(11, builder.build())


        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}