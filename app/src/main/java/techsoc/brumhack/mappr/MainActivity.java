package techsoc.brumhack.mappr;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.HeatmapTileProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
public class MainActivity  extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {
    private static final int REQUEST_RESOLVE_ERROR = 1001;
    private static final String DIALOG_ERROR = "dialog_error";
    private static final String STATE_RESOLVING_ERROR = "resolving_error";
    private boolean mResolvingError = false;
    private LatLng latlng = new LatLng(0, 0);
    private boolean MapReady = false;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    HashMap<Long, MarkerOptions> markers = new HashMap<Long, MarkerOptions>();
    long markerCount = 0;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        buildGoogleApiClient();
        mGoogleApiClient.connect();
        setContentView(R.layout.activity_main);
        LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_friends");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
<<<<<<< HEAD
                this, drawer,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
=======
                this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
>>>>>>> 69cb75b426f3550013698f834746a89497969cf5
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        mResolvingError = savedInstanceState != null &&
                savedInstanceState.getBoolean(STATE_RESOLVING_ERROR, false);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_RESOLVING_ERROR, mResolvingError);
    }

    @Override
    protected void onStart(){
        super.onStart();
        if(!mResolvingError)
            mGoogleApiClient.connect();
    }

    @Override
    protected void onStop(){
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    protected synchronized void buildGoogleApiClient(){
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener(){
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent viewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/events/924176117663346/"));
                startActivity(viewIntent);
                }
            });
        mMap.setMyLocationEnabled(true);
        CameraUpdate position = CameraUpdateFactory.newLatLng(latlng);
        mMap.moveCamera(position);
        MapReady = true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
        } else if (id == R.id.nav_slideshow) {
        } else if (id == R.id.nav_manage) {
        } else if (id == R.id.nav_share) {
        } else if (id == R.id.nav_send) {
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onConnected(Bundle connectionHint) {
        ArrayList<LatLng> latlngs = new ArrayList<LatLng>();

        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        latlng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        latlng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        if(MapReady) {
            CameraUpdate position = CameraUpdateFactory.newLatLngZoom(latlng, (float)15.0);
            mMap.animateCamera(position);
        }
        for(int i = 0; i < 100; i++)
            latlngs.add(create_lat_lng(mLastLocation.getLatitude(), mLastLocation.getLongitude(), 0.05));

        for(LatLng l : latlngs){
            MarkerOptions m = new MarkerOptions()
                    .position(l)
                    .alpha(0)
                    .title("Brum Hack")
                    .snippet("Attendees: 39\r\nDate:23rd - 25th Oct");
            markers.put(markerCount, m);
            mMap.addMarker(m);
            markerCount++;
        }

        HeatmapTileProvider mProvider = new HeatmapTileProvider.Builder()
                .data(latlngs)
                .radius(40)
                .build();

        TileOverlay mOverlay = mMap.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));


        for(LatLng l : latlngs){
            mMap.addMarker(new MarkerOptions()
                    .position(l)
                    .alpha(0));
        }

    }

    @Override
    public void onConnectionSuspended(int cause) {
        // Need to disable UI components that depend on Google APIs until
        // onConnected is called
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if(mResolvingError){
            return;
        } else if(connectionResult.hasResolution()){
            try {
                mResolvingError = true;
                connectionResult.startResolutionForResult(
                        this, REQUEST_RESOLVE_ERROR);
            } catch (IntentSender.SendIntentException e) {
                mGoogleApiClient.connect();
            }
        }
        else{
            showErrorDialog(connectionResult.getErrorCode());
            mResolvingError = true;
        }
        // This callback is important for handling errors that
        // may occur while attempting to connect with Google.
        //
        // More about this in the 'Handle Connection Failures' section.
    }

    private void showErrorDialog(int errorCode){
        ErrorDialogFragment dialogFragment = new ErrorDialogFragment();
        Bundle args = new Bundle();
        args.putInt(DIALOG_ERROR, errorCode);
        dialogFragment.setArguments(args);
        dialogFragment.show(getFragmentManager(),"tag");
    }

    public void onDialogDismissed(){
        mResolvingError = false;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_RESOLVE_ERROR){
            mResolvingError = false;
            if (resultCode == RESULT_OK){
                if(!mGoogleApiClient.isConnecting() &&
                        !mGoogleApiClient.isConnected()){
                    mGoogleApiClient.connect();
                }
            }
        }
    }

    public static class ErrorDialogFragment extends DialogFragment {
        public ErrorDialogFragment(){ }

        public Dialog onCreateDialog(Bundle savedInstanceState){
            int errorCode = this.getArguments().getInt(DIALOG_ERROR);
            return GoogleApiAvailability.getInstance().getErrorDialog(
                    this.getActivity(), errorCode, REQUEST_RESOLVE_ERROR);
        }

        @Override
        public void onDismiss(DialogInterface dialog){
            ((MainActivity) getActivity()).onDialogDismissed();
        }
    }

    public LatLng create_lat_lng(double lat_seed, double long_seed, double range){
        double result_a, result_b;
        Random random = new Random();
        result_a = (random.nextFloat() % range) - (range / 2);
        result_b = (random.nextFloat() % range) - (range / 2);
        return new LatLng(lat_seed + result_a, long_seed + result_b);
    }
}