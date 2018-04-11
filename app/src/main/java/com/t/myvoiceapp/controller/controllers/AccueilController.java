package com.t.myvoiceapp.controller.controllers;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.t.myvoiceapp.R;
import com.t.myvoiceapp.controller.utils.SuperDialog;
import com.t.myvoiceapp.view.AccueilActivity;

public class AccueilController {

    private AccueilActivity aa;
    private Toolbar tb;
    private FloatingActionButton fab;
    private DrawerLayout dl;
    private NavigationView nv;
    private ActionBarDrawerToggle abdt;

    public AccueilController(AccueilActivity aa) {
        this.aa = aa;
        tb = this.aa.findViewById(R.id.toolbar);
        this.fab = this.aa.findViewById(R.id.fab);
        this.dl = this.aa.findViewById(R.id.drawer_layout);
        this.nv = this.aa.findViewById(R.id.nav_view);
    }

    public void config() {
        this.aa.setSupportActionBar(tb);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        this.nv.setNavigationItemSelectedListener(this.aa);
        abdt = new ActionBarDrawerToggle(this.aa, this.dl, this.tb, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        this.dl.addDrawerListener(this.abdt);
        this.abdt.syncState();
    }

    public boolean backPress(){
        if (this.dl.isDrawerOpen(GravityCompat.START)) {
            this.dl.closeDrawer(GravityCompat.START);
            return true;
        } else {
            return false;
        }
    }

    public void nav_classes() {
        SuperDialog.createToastMessage(this.aa, "abrete classes");
    }

    public void action_settings() {
        SuperDialog.createToastMessage(this.aa, "abrete settings");
    }
}
