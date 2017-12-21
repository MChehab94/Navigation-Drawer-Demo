package mchehab.com.navigationdrawer;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    private CameraFragment cameraFragment = new CameraFragment();
    private GalleryFragment galleryFragment = new GalleryFragment();
    private SlideShowFragment slideShowFragment = new SlideShowFragment();
    private ToolsFragment toolsFragment = new ToolsFragment();
    private ShareFragment shareFragment = new ShareFragment();
    private SendFragment sendFragment = new SendFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        if(savedInstanceState == null){
            addFragment(cameraFragment);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragmentSelected = null;
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            fragmentSelected = cameraFragment;
        } else if (id == R.id.nav_gallery) {
            fragmentSelected = galleryFragment;
        } else if (id == R.id.nav_slideshow) {
            fragmentSelected = slideShowFragment;
        } else if (id == R.id.nav_manage) {
            fragmentSelected = toolsFragment;
        }else if(id == R.id.nav_share){
            fragmentSelected = shareFragment;
        }else if(id == R.id.nav_send){
            fragmentSelected = sendFragment;
        }

        replaceFragment(fragmentSelected);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void addFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, fragment).commit();
    }

    private void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment)
                .addToBackStack(null).commit();
    }
}