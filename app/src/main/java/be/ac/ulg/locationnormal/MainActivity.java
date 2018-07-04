package be.ac.ulg.locationnormal;

import android.Manifest;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private final String LOG_TAG = "TestAppNormal";
    private TextView txtOutput;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtOutput = (TextView) findViewById(R.id.txtOutput);

        int permissionCheck= ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCheck==-1) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            permissionCheck= ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if(permissionCheck==0){
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
            Log.i(LOG_TAG,"Inicio requestLocationUpdate");
        }
        Log.i(LOG_TAG,"Fin onCreate");
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i(LOG_TAG,location.toString());
        txtOutput.setText(Double.toString(location.getLatitude()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.i(LOG_TAG,"onStatusChanged");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.i(LOG_TAG,"onProviderEnabled");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.i(LOG_TAG,"onProviderDisabled");
    }
}
