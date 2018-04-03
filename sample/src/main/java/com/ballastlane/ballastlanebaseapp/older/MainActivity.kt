package com.ballastlane.ballastlanebaseapp.older

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.view.View
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem

import com.ballastlane.android.baselibrary.common.base.BaseActivity

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.ballastlane.ballastlanebaseapp.R.layout.activity_main)
        val toolbar = findViewById(com.ballastlane.ballastlanebaseapp.R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById(com.ballastlane.ballastlanebaseapp.R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val drawer = findViewById(com.ballastlane.ballastlanebaseapp.R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, com.ballastlane.ballastlanebaseapp.R.string.navigation_drawer_open, com.ballastlane.ballastlanebaseapp.R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(com.ballastlane.ballastlanebaseapp.R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val drawer = findViewById(com.ballastlane.ballastlanebaseapp.R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(com.ballastlane.ballastlanebaseapp.R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == com.ballastlane.ballastlanebaseapp.R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == com.ballastlane.ballastlanebaseapp.R.id.nav_camera) {
            // Handle the camera action
        } else if (id == com.ballastlane.ballastlanebaseapp.R.id.nav_gallery) {

        } else if (id == com.ballastlane.ballastlanebaseapp.R.id.nav_slideshow) {

        } else if (id == com.ballastlane.ballastlanebaseapp.R.id.nav_manage) {

        } else if (id == com.ballastlane.ballastlanebaseapp.R.id.nav_share) {

        } else if (id == com.ballastlane.ballastlanebaseapp.R.id.nav_send) {

        }

        val drawer = findViewById(com.ballastlane.ballastlanebaseapp.R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun initVars() {

    }

    override fun initViews() {

    }

    override fun initListeners() {

    }
}
