package com.colegium.newsapp.newsapp.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.colegium.newsapp.R
import com.colegium.newsapp.data.model.local.Article
import com.colegium.newsapp.newsapp.adapters.ArticlesPagedListAdapter
import com.colegium.newsapp.newsapp.utils.SwipeToDeleteCallback
import com.colegium.newsapp.viewmodels.ArticlesListViewModel
import com.colegium.newsapp.viewmodels.dataclasses.ERROR
import com.colegium.newsapp.viewmodels.dataclasses.LOADING
import com.colegium.newsapp.viewmodels.dataclasses.SUCCESS
import com.colegium.newsapp.viewmodels.dataclasses.UNKNOWN
import kotlinx.android.synthetic.main.fragment_articles_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class ArticlesListFragment() : Fragment() {

    private val viewModel: ArticlesListViewModel by viewModel()
    private var articlesPagedListAdapter: ArticlesPagedListAdapter? = null
    private var articlesList: PagedList<Article>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_articles_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        activity?.let {activityContext ->
            recyclerArticlesListFragment.setHasFixedSize(true)
            recyclerArticlesListFragment.itemAnimator = null

            articlesPagedListAdapter ?: run {
                articlesPagedListAdapter = ArticlesPagedListAdapter()
                viewModel.getAllArticles()
            }

            recyclerArticlesListFragment.layoutManager = LinearLayoutManager(context)
            recyclerArticlesListFragment.adapter = articlesPagedListAdapter
            setupSwipeAnimation()

            viewModel.getLiveData().observe(viewLifecycleOwner, Observer {
                when(it?.status) {
                    LOADING -> togleProgressDialog(true)
                    ERROR -> {
                        togleProgressDialog(false)
                        if (!it?.error?.type.equals(UNKNOWN)) {
                            Toast.makeText(context,it?.error.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                    SUCCESS ->{
                        togleProgressDialog(false)
                        when(it?.request) {
                            //TODO change to constants
                            "request_all_articles" -> {
                                togleProgressDialog(true)
                                articlesList = (it.data as? PagedList<Article>)?.apply {
                                    articlesPagedListAdapter?.submitList(this)
                                }
                                togleProgressDialog(false)
                            }
                            "reload" -> {
                                togleProgressDialog(false)
                            }
                        }
                    }
                    else -> {
                        togleProgressDialog(false)
                    }
                }
            })
        }

        swipeToRefreshArticlesListFragment.setOnRefreshListener {
            viewModel.reloadArticles()
        }
    }


    private fun setupSwipeAnimation() {
        val swipeHandler = object : SwipeToDeleteCallback(requireActivity().applicationContext) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = recyclerArticlesListFragment.adapter
                articlesList?.get(viewHolder.adapterPosition)?.id?.let {
                    viewModel.removeArticleFromlist(it)
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerArticlesListFragment)
    }

    private fun togleProgressDialog(show: Boolean) {
        swipeToRefreshArticlesListFragment?.apply {
            if (show) {
                isRefreshing = true
            }
            else {
                Handler().postDelayed({
                    isRefreshing = false
                }, //TODO constant
                     1500)
            }
        }
    }
}