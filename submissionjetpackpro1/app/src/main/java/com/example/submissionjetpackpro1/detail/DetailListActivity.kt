package com.example.submissionjetpackpro1.detail

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.submissionjetpackpro1.R
import com.example.submissionjetpackpro1.databinding.ActivityDetailListBinding
import com.example.submissionjetpackpro1.databinding.ContentDetailListBinding

class DetailListActivity : AppCompatActivity() {

    private lateinit var detailContentBinding: ContentDetailListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailCourseBinding = ActivityDetailListBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailCourseBinding.detailContent

        setContentView(activityDetailCourseBinding.root)

        setSupportActionBar(activityDetailCourseBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }
