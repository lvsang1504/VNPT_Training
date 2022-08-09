package com.devpro.mapboxvnpt.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.devpro.mapboxvnpt.R;
import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.CircleAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.CircleAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.CircleAnnotationOptions;

public class ShowPointInMapActivity extends AppCompatActivity {

    MapView mapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_point_in_map);

        mapView = findViewById(R.id.mapView);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS);

        Point point = Point.fromLngLat(106.694490, 10.785847);

        createCircleAnnotation(mapView, point);
    }

    public void createCircleAnnotation(MapView mapView, Point point) {

        AnnotationPlugin annotationApi = AnnotationPluginImplKt.getAnnotations(mapView);
        CircleAnnotationManager circleAnnotationManager = CircleAnnotationManagerKt.createCircleAnnotationManager(annotationApi, new AnnotationConfig());

        // circle annotations options
        CircleAnnotationOptions circleAnnotationOptions = new CircleAnnotationOptions()
                .withPoint(point)
                .withCircleRadius(8.0)
                .withCircleColor("#ee4e8b")
                .withCircleBlur(0.5)
                .withCircleStrokeWidth(2.0)
                .withCircleStrokeColor("#ffffff");

        circleAnnotationManager.create(circleAnnotationOptions);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}