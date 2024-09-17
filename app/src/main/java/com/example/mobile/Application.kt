package com.example.mobile

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val firestore = FirebaseFirestore.getInstance()

        val settings = FirebaseFirestoreSettings.Builder()
            .build()

        firestore.firestoreSettings = settings
    }
}

