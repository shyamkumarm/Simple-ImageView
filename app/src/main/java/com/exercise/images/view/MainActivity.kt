package com.exercise.images.view


import android.content.Intent
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.exercise.images.adapter.ViewAdapter
import com.exercise.images.base.BaseActivity
import com.exercise.images.databinding.ActivityMainBinding
import com.exercise.images.model.DataObject
import com.exercise.images.utils.ApiConstants
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MainActivity : BaseActivity() {

    private lateinit var viewAdapter: ViewAdapter


   private var selectedItem: DataObject.DataObjectItem? =null



    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun getContentLayout(): View = binding.root
    override fun onCreate() {

        initView()
        initObserver()
    }

    private fun initView() {
        setSupportActionBar(binding.toolbar)
        binding.recyclerView.apply {
           // setRecycledViewPool(RecyclerView.RecycledViewPool().apply {  setMaxRecycledViews(0,50)})
             layoutManager =  StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
           viewAdapter =  ViewAdapter { _, data ->
               selectedItem = data
               Intent(this@MainActivity,ImageViewerActivity::class.java).apply {
                   putExtra(ApiConstants.INTENT_DATA,data)
                   startActivity(this)
               }
           }
            adapter = viewAdapter

        }
    }


    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.items.collectLatest {
                    viewAdapter.update(it)
            }
        }



    }











}


