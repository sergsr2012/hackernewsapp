package com.colegium.newsapp.newsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.colegium.newsapp.R
import com.colegium.newsapp.newsapp.utils.KEY_EXTRA_URL_ARTICLE
import kotlinx.android.synthetic.main.fragment_web_article.*

class WebArticleFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_web_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let {
            it.getString(KEY_EXTRA_URL_ARTICLE)?.let { url ->
                when (url) {
                    "NA" -> showWarningWeb()
                    else -> webViewDetailsFragment.loadUrl(url)
                }
            }
        }
    }

    private fun showWarningWeb() {
        activity?.let {
            val text = "Enlace Web no disponible"
            val duration = Toast.LENGTH_LONG
            val toast = Toast.makeText(it.applicationContext, text, duration)
            toast.show()
        }
    }
}