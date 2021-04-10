package com.example.wechatclone


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment

class GridFragment : Fragment() {
    private var playerImages = intArrayOf(R.drawable.ronaldo, R.drawable.felix, R.drawable.bernado, R.drawable.andre)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_grid, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val gridView = activity?.findViewById<GridView>(R.id.grid_view)
        val mainAdapter = this.context?.let { ImageAdapter(it, playerImages) }
        if (gridView != null) {
            gridView.adapter = mainAdapter
        }
    }
}
