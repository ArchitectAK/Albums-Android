package com.ankkumar.albums.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ankkumar.albums.R
import com.ankkumar.albums.helper.CheckNetwork
import com.ankkumar.albums.model.AlbumEntity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var mainViewModel: MainViewModel

    private lateinit var albumAdapter: AlbumAdapter
    private var albumList: ArrayList<AlbumEntity> = arrayListOf()
    private lateinit var mLinearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialiseViewModel()
        observeHomeViewModel()
        initialiseRecyclerView()
    }

    private fun initialiseViewModel() {
        mainViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

    private fun observeHomeViewModel() {
        if (CheckNetwork.isNetworkConnected(this)) {
            mainViewModel.getPosts()
            mainViewModel.getPostsObserver().observe(this, Observer {
                if (it != null) {
                    if (it.isNotEmpty())
                        defaultText.visibility = View.GONE
                    albumAdapter.addList(it)
                    mainViewModel.saveAlbumsToDB(it)
                }
            })
        } else {
            mainViewModel.getPostsFromDB().observe(this, Observer {
                if (it != null) {
                    if (it.isNotEmpty())
                        defaultText.visibility = View.GONE
                    albumAdapter.addList(it)
                }
            })
        }
    }

    private fun initialiseRecyclerView() {
        albumAdapter = AlbumAdapter(this, albumList)
        mLinearLayoutManager = LinearLayoutManager(this)
        recyclerViewLocal.layoutManager = mLinearLayoutManager
        recyclerViewLocal.adapter = albumAdapter

    }

}