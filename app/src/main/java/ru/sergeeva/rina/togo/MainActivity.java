package ru.sergeeva.rina.togo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;

import android.support.design.widget.NavigationView;

public class MainActivity extends AppCompatActivity {

    public static int LAYOUT = R.layout.activity_main;

    @BindView(R.id.navigation_drawer)
    NavigationView navigationDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        ButterKnife.bind(this);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, MapFragment.newInstance())
                .commit();
        navigationDrawer.getMenu().getItem(0)
                .setChecked(true);


        navigationDrawer.setNavigationItemSelectedListener(menuItem -> {

            switch (menuItem.getItemId()) {
                case R.id.nav_logout: {
                    mAuth.signOut();
                    preferences.edit().putBoolean("log", false).apply();
                    LoginActivity.start(this);
                    finish();
                    break;
                }
                case R.id.nav_profile: {
                    menuItem.setChecked(true);
                    mDrawerLayout.closeDrawers();
                    setTitle("Профиль");
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fl_fragment_container, ProfileFragment.newInstance())
                            .commit();
                    break;
                }
                case R.id.nav_my_events: {
                    menuItem.setChecked(true);
                    mDrawerLayout.closeDrawers();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fl_fragment_container, EventsFragment.newInstance())
                            .commit();
                    break;
                }
                case R.id.nav_map: {
                    menuItem.setChecked(true);
                    mDrawerLayout.closeDrawers();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fl_fragment_container, MapFragment.newInstance())
                            .commit();
                    break;
                }
            }

            return true;
        });

    }
}
