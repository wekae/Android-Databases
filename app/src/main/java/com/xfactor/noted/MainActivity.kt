package com.xfactor.noted

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.xfactor.noted.database.AppDatabase
import com.xfactor.noted.database.migrations.MIGRATION_1_2
import kotlin.collections.List

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the DB - allow main thread queries for small apps
        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "noted-database")
            .allowMainThreadQueries()
            .addMigrations(
                MIGRATION_1_2
            )
            .build()

        // Setting ActionBar logo
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setLogo(R.drawable.ic_logo)
        supportActionBar?.setDisplayUseLogoEnabled(true)

        setContentView(R.layout.activity_main)

        // Setup navigation
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_delete, R.id.navigation_listcontainer, R.id.navigation_newlist))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // access the database
        val listDao = db.listDao()
        val lists : List<com.xfactor.noted.database.List> = listDao.getAll()

        Log.e("lists", lists.toString())

        // Update primary key value by incrementing value of the last item
        listDao.insertAll(com.xfactor.noted.database.List(uid = lists.last().uid +1, title = "Test List Thre"))

        listDao.delete(lists[0])

        Log.e("lists", listDao.getAll().toString())

    }
}


