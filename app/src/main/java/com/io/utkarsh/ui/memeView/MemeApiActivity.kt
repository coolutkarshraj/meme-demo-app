package com.io.utkarsh.ui.memeView

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import com.io.utkarsh.MEMEApplication
import com.io.utkarsh.R
import com.io.utkarsh.data.model.MemeApiResponseModel
import com.io.utkarsh.databinding.ActivityMemeApiBinding
import com.io.utkarsh.di.component.DaggerActivityComponent
import com.io.utkarsh.di.module.ActivityModule
import com.io.utkarsh.ui.base.UiState

import javax.inject.Inject

class MemeApiActivity : AppCompatActivity() {

    @Inject
    lateinit var memeViewModel: MemeViewModel

    @Inject
    lateinit var adapter: MemesAdapter

    private lateinit var binding: ActivityMemeApiBinding
    private var memeListImages: ArrayList<MemeApiResponseModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityMemeApiBinding.inflate(layoutInflater)
        memeListImages = ArrayList()
        memeViewModel.fetchMemeApi()
        setContentView(binding.root)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                memeViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            if(!memeListImages.isNullOrEmpty()){
                                if(!memeListImages!!.contains(it.data)){
                                    memeListImages?.add(it.data)
                                    memeListImages?.let { it1 -> renderList(it1) }
                                }
                            }else{
                                memeListImages?.add(it.data)
                                memeListImages?.let { it1 -> renderList(it1) }
                            }
                            binding.recyclerView.visibility = View.VISIBLE
                        }
                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }
                        is UiState.Error -> {
                            //Handle Error
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@MemeApiActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(memeList: ArrayList<MemeApiResponseModel>) {
        adapter.addData(memeList)
        adapter.notifyDataSetChanged()
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as MEMEApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId ){
            R.id.refresh -> memeViewModel.fetchMemeApi()
        }
        return super.onOptionsItemSelected(item)
    }

}
