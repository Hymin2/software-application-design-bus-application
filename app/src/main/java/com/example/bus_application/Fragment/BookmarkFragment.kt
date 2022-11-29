package com.example.bus_application.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bus_application.Adapter.BookmarkAdapter
import com.example.bus_application.R
import com.example.bus_application.databinding.FragmentBookmarkBinding
/* 이거 지워
* 이거 지워
* 이거 지워
* 이거 지워
* 이거 지워
* 이거 지워1*/
class BookmarkFragment : Fragment() {

    private lateinit var binding : FragmentBookmarkBinding
    private lateinit var BookmarkAdapter : BookmarkAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentBookmarkBinding.inflate(layoutInflater)
        BookmarkAdapter = BookmarkAdapter()

        binding.rvBookmark.apply{
            adapter = BookmarkAdapter()
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView= inflater.inflate(R.layout.fragment_bookmark,container,false)

    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            BookmarkFragment().apply {
            }
    }
}