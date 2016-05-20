package hansung.bsms.menutest;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;

import net.daum.mf.map.api.MapPoint;

/**
 * Created by user on 2016-05-20.
 */
public class CampusMapActivity extends Activity {
    public static String API_KEY = "ba206f2412e9138944cf418f57ee4b08";
    private net.daum.mf.map.api.MapView mapView;
    private double latitude;
    private double longitude;
    private String korName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campusmap);

        korName = getIntent().getExtras().getString("korName");
        latitude = getIntent().getExtras().getDouble("Latitude");
        longitude = getIntent().getExtras().getDouble("Longitude");

        mapView = new net.daum.mf.map.api.MapView(this);
        mapView.setDaumMapApiKey(API_KEY);

        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude), true);

        mapView.setZoomLevel(3, true);

        mapView.zoomIn(true);
        mapView.zoomOut(true);

    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
