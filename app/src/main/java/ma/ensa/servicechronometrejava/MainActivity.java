package ma.ensa.servicechronometrejava;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;

public class MainActivity extends AppCompatActivity {

    private TextView tvTemps;
    private MaterialButton btnStart, btnStop, btnReset;
    private CircularProgressIndicator progressRing;
    private ChronometreService chronometreService;
    private boolean isBound = false;

    // Handler and Runnable for real-time UI updates
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable updateUI = new Runnable() {
        @Override
        public void run() {
            if (isBound && chronometreService != null) {
                int secondes = chronometreService.getSecondes();
                // mm:ss format
                tvTemps.setText(String.format("%02d:%02d", secondes / 60, secondes % 60));
                // Circular progress (0-100 based on the current minute)
                progressRing.setProgress((secondes % 60) * 100 / 60, true);
                handler.postDelayed(this, 1000);
            }
        }
    };

    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ChronometreService.LocalBinder binder = (ChronometreService.LocalBinder) service;
            chronometreService = binder.getService();
            isBound = true;
            // Start pulling data from service once connected
            handler.post(updateUI);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
            handler.removeCallbacks(updateUI);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTemps = findViewById(R.id.tvTemps);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnReset = findViewById(R.id.btnReset);
        progressRing = findViewById(R.id.cpIndicator);

        btnStart.setOnClickListener(v -> startService());
        btnStop.setOnClickListener(v -> stopService());
        btnReset.setOnClickListener(v -> {
            stopService();
            tvTemps.setText("00:00");
            progressRing.setProgress(0, false);
        });
    }

    private void startService() {
        Intent intent = new Intent(this, ChronometreService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent);
        } else {
            startService(intent);
        }
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private void stopService() {
        Intent intent = new Intent(this, ChronometreService.class);
        intent.setAction("STOP");
        startService(intent);

        if (isBound) {
            unbindService(connection);
            isBound = false;
        }
        handler.removeCallbacks(updateUI);
    }

    @Override
    protected void onDestroy() {
        if (isBound) {
            unbindService(connection);
            isBound = false;
        }
        handler.removeCallbacks(updateUI);
        super.onDestroy();
    }
}
