package com.binasphere.hymmnosdict.act;

import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.binasphere.hymmnosdict.R;
import com.binasphere.hymmnosdict.common.LogUtil;
import com.binasphere.hymmnosdict.common.RxProvider;
import com.binasphere.hymmnosdict.db.DBInfo;
import com.binasphere.hymmnosdict.frag.FragmentFactory;
import com.binasphere.hymmnosdict.widget.AlertImDialog;

import java.lang.reflect.Method;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.vp_home)
    ViewPager vp;
    @Bind(R.id.tl_home)
    TabLayout tl;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.appbarlayout)
    AppBarLayout appbarlayout;
    private boolean toolbar_hidden;
    private MyPagerAdapter adapter;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        LogUtil.d("Hymmnos", "onConfig");
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        initData(DBInfo.ORDER_ALPHA);
        appbarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                LogUtil.d("Hymmnos", verticalOffset + " Offset");
                TypedArray styledAttributes = getTheme().obtainStyledAttributes(
                        new int[]{android.R.attr.actionBarSize});
                float pixelvalue = styledAttributes.getDimensionPixelSize(0, 0);
                float dpValue = pixelvalue / getResources().getDisplayMetrics().density;
                LogUtil.d("Hymmnos", dpValue + " ActionBarSize");
            }
        });


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);


        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initData(final int order) {
        RxProvider.getTabListObservable(order)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> strings) {
                        if (adapter == null) {
                            adapter = new MyPagerAdapter(getSupportFragmentManager());
                            adapter.setTablist(strings);
                            adapter.setOrder(order);
                            vp.setAdapter(adapter);
                            tl.setupWithViewPager(vp);
                        } else {
                            adapter.setTablist(strings);
                            adapter.setOrder(order);
                            adapter.notifyDataSetChanged();
                            tl.setupWithViewPager(vp);
                        }
                    }
                });

    }

    @OnClick(R.id.fab)
    public void fabOnClick(View view) {
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {
        private List<String> tablist;
        private int order;


        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public void setTablist(List<String> data) {
            tablist = data;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentFactory.newInstance(order, tablist.get(position));
        }

        @Override
        public long getItemId(int position) {
            return tablist.get(position).hashCode();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tablist.get(position);
        }

        @Override
        public int getCount() {
            return tablist.size();
        }

    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
            return true;
        
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_alphabet) {
            initData(DBInfo.ORDER_ALPHA);
        } else if (id == R.id.nav_dialect) {
            initData(DBInfo.ORDER_DIALECT);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
