package hansung.bsms.menutest;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

/**
 * Created by user on 2016-05-17.
 */
public class MenuActivity extends Activity {
    private String value;

    private Firebase firebase;

    private TextView textViewName;
    private Button btnSchedule;
    private Button btnTel;
    private Button btnCampusMap;
    private Button btnDirections;
    private Button btnWebsite;

    private Context context = this;

    private LocationManager locationManager;
    private LocationListener locationListener;

    private double srcLatitude = 0.0;
    private double srcLongitude = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        textViewName = (TextView) findViewById(R.id.textViewName);
        btnSchedule = (Button) findViewById(R.id.btnSchedule);
        btnTel = (Button) findViewById(R.id.btnTel);
        btnCampusMap = (Button) findViewById(R.id.btnCampusMap);
        btnDirections = (Button) findViewById(R.id.btnDirections);
        btnWebsite = (Button) findViewById(R.id.btnWebsite);

        btnSchedule.setText("전형일정");
        btnTel.setText("연락처");
        btnCampusMap.setText("캠퍼스맵");
        btnDirections.setText("오시는길");
        btnWebsite.setText("홈페이지");

        value = getIntent().getExtras().getString("UnityValue");

        Firebase.setAndroidContext(this);

        firebase = new Firebase("https://bsms.firebaseio.com/");
    }

    @Override
    protected void onStart() {
        super.onStart();

        firebase = firebase.getRoot().child("univ");

        firebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot.getValue(Univ.class).getEngName().equals(value)) {
                    Univ univ = dataSnapshot.getValue(Univ.class);

                    setTextViewName(univ.getKorName());

                    setButtonSchedule(univ.getSchedule());
                    setButtonWebsite(univ.getWebsite());
                    setButtonTel(univ.getTel());
                    setButtonCampusMap(univ.getCampusMap());
                    setButtonDirections(univ.getLatitude(), univ.getLongitude());
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (locationManager != null) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.removeUpdates(locationListener);
            }
        }
    }

    private void setTextViewName(String value) {
        textViewName.setText(value);
    }

    private void setButtonSchedule(String value) {
        final String schedule = value;

        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ScheduleActivity.class);
                intent.putExtra("schedule", schedule);
                context.startActivity(intent);
            }
        });
    }

    private void setButtonWebsite(String value) {
        final String website = value;

        btnWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(website)));
            }
        });
    }

    private void setButtonTel(String value) {
        final String tel = value;
        btnTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel)));
            }
        });
    }

    private void setButtonCampusMap(String value) {
        final String campusMap = value;

        btnCampusMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(campusMap)));
//                if (locationManager != null) {
//                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
//                            || ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0, locationListener);
//                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 0, locationListener);
//                    }
//                }
//
//                try {
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("daummaps://look?p=" + latitude + "," + longitude));
//                    context.startActivity(intent);
//                } catch (Exception e) {
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=net.daum.android.map"));
//                    context.startActivity(intent);
//                }
            }
        });
    }

    private void setButtonDirections(double value1, double value2) {
        final String destLatitude = Double.toString(value1);
        final String destLongitude = Double.toString(value2);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (locationManager != null) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if(location == null) {
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                }
                if(location != null) {
                    srcLongitude = location.getLongitude();
                    srcLatitude = location.getLatitude();
                }
            }
        }

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                srcLatitude = location.getLatitude();
                srcLongitude = location.getLongitude();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        btnDirections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (locationManager != null) {
                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                            || ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0, locationListener);
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 0, locationListener);
                    }
                }

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("daummaps://route?sp=" + srcLatitude + "," + srcLongitude + "&ep=" + destLatitude + "," + destLongitude + "&by=CAR"));
                    context.startActivity(intent);
                } catch (Exception e) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=net.daum.android.map"));
                    context.startActivity(intent);
                }
            }
        });
    }

}
