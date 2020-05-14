package com.colegium.newsapp.newsapp.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.colegium.newsapp.R
import com.colegium.newsapp.data.model.local.Article
import com.colegium.newsapp.newsapp.utils.KEY_EXTRA_URL_ARTICLE

class ArticlesPagedListAdapter() :  PagedListAdapter<Article, RecyclerView.ViewHolder>(DIFF_CALLBACK){

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldArticle: Article,
                                         newArticle: Article) = oldArticle.url == newArticle.url
            override fun areContentsTheSame(oldArticle: Article,
                                            newArticle: Article) = oldArticle == newArticle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_layout_paged_list_adapter, parent, false)
        val holder = ArticleHolder(v)
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            (holder as ArticleHolder).bind(it)
        }?: kotlin.run {
            //holder.
        }
    }

    inner class ArticleHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        private val txtRowTitle: TextView = view.findViewById<View>(R.id.txtRowTitle) as TextView
        private val txtRowContent: TextView = view.findViewById<View>(R.id.txtRowContent) as TextView
        private val constraintLayoutRowPost : ConstraintLayout =  view.findViewById<View>(R.id.constraintLayoutRow) as ConstraintLayout

        //propeties for recycling the listeners
        private var bundleToNavigateDetails = Bundle()

        fun bind(article: Article) {
            bundleToNavigateDetails.putString(KEY_EXTRA_URL_ARTICLE, article.url)
            txtRowTitle.text =  article.title
            txtRowContent.text = "xx="+article.id+"=fecha: "+ article.created_at + "au: " + article.author + "-" +article.page + " " + article.url
            setNavigationListener()
        }

        private fun setNavigationListener() {
            val navToDetailsListener = Navigation.createNavigateOnClickListener(
                R.id.action_articlesListFragment_to_webArticleFragment, bundleToNavigateDetails)
            constraintLayoutRowPost.setOnClickListener(navToDetailsListener)
        }

    }

}