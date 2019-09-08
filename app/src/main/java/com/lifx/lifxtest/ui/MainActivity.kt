package com.lifx.lifxtest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.lifx.lifxtest.R


class MainActivity : AppCompatActivity() {

    lateinit var adapter: ListAdapter
    lateinit var viewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        setupRecyclerView()
        initObservers()
    }

    private fun setupRecyclerView(){
        adapter = ListAdapter()
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(applicationContext)

        val dividerItemDecoration = DividerItemDecoration(this, VERTICAL)
        list.addItemDecoration(dividerItemDecoration)
    }

    private fun initObservers(){
        viewModel.list.observe(this, Observer {
            adapter.setData(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJobs()
    }
}


