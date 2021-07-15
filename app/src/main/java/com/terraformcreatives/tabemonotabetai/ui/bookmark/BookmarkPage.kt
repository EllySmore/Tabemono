package com.terraformcreatives.tabemonotabetai.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.terraformcreatives.tabemonotabetai.R
import com.terraformcreatives.tabemonotabetai.network.respository.Repository
import dagger.hilt.EntryPoints
import kotlinx.coroutines.*

class BookmarkPage : Fragment() {
    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bookmark_page, container, false)
        val repository: Repository = EntryPoints.get(
            requireContext().applicationContext,
            Repository.Interface::class.java
        ).repository

        val refrestButton: Button = view.findViewById(R.id.button)
        val deleteButton: Button = view.findViewById(R.id.delete)
        val textView: TextView = view.findViewById(R.id.bookmarkedText)
        deleteButton.setOnClickListener {
            scope.launch {
                withContext(Dispatchers.IO) {
                    repository.deleteAll()
                }
            }
            textView.text = ""

        }
        refrestButton.setOnClickListener {
            scope.launch {
                textView.text = ""
                val data = withContext(Dispatchers.IO) {
                    repository.queryAll()
                }
                var dataString = ""
                data.forEach {
                    dataString += "- ${it.label} \n"
                }

                textView.text = dataString
            }
        }

        return view
    }
}