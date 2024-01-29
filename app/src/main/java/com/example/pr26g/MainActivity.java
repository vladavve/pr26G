package com.example.pr26g;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private int counter = 1;
    private static String CHANNEL_ID = "Напоминашка";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    CharSequence name = "Напоминание";
                    String description = "Канал для напоминания долгов";
                    int importance = NotificationManager.IMPORTANCE_DEFAULT;
                    NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
                    channel.setDescription(description);
                    NotificationManager notificationManager = getSystemService(NotificationManager.class);
                    notificationManager.createNotificationChannel(channel);

                }

                Intent notificationIntent = new Intent(MainActivity.this, MainActivity.class);
                PendingIntent contentIntent = PendingIntent.getActivity(MainActivity.this,
                        0, notificationIntent,
                        PendingIntent.FLAG_IMMUTABLE);


                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,
                        0, notificationIntent,
                        PendingIntent.FLAG_IMMUTABLE);

                String bigText = "Люди больше не услышат\\n\" +\n" +
                        "                                        \"Наши юные смешные голоса\\n\" +\n" +
                        "                                        \"Теперь их слышат только небеса" + " Люди никогда не вспомнят\\n\" +\n" +
                        "                                        \"Наши звонкие смешные имена\\n\" +\n" +
                        "                                        \"Теперь их помнит только тишина";

                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                                .setSmallIcon(R.drawable.bell)
                                .setContentTitle("Люди больше не услышат\n" +
                                        "Наши юные смешные голоса\n" +
                                        "Теперь их слышат только небеса")
                                .setContentText("Люди никогда не вспомнят\n" +
                                        "Наши звонкие смешные имена\n" +
                                        "Теперь их помнит только тишина")
                                .setStyle(new NotificationCompat.BigTextStyle().bigText(bigText))
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                .addAction(R.drawable.bell, "Открыть", pendingIntent)
                                .addAction(R.drawable.bell, "Отказаться", pendingIntent)
                                .setContentIntent(contentIntent)
                                .setAutoCancel(true)
                                // необязательные настройки
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.push));

                NotificationManagerCompat notificationManager =
                        NotificationManagerCompat.from(MainActivity.this);
                notificationManager.notify(counter++, builder.build());


            }
        });
    }
}