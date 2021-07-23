package com.example.apiandroidtask

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.example.apiandroidtask.DI.App
import com.example.apiandroidtask.adapter.Adapter
import com.example.apiandroidtask.model.Cryptocurrency
import com.example.apiandroidtask.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main), Adapter.OnItemClickListener {

    private lateinit var recyclerViewAdapter: Adapter
    private var cryptList = arrayListOf<Cryptocurrency>()

    @Inject
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        App.appComponent.inject(this)

        super.onCreate(savedInstanceState)

        initializeRecycler()
        initializeList()
    }

    private fun initializeRecycler() {
        recycler.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerViewAdapter = Adapter(this, listOf(), this)
        recycler.adapter = recyclerViewAdapter
    }

    private fun initializeList() {
        viewModel.cryptList()
            .observe(this, Observer {
                if (it != null) {
                    cryptList = it
                    recyclerViewAdapter.setDataList(cryptList)
                }
            })
    }


    override fun onItemClick(position: Int, v: View?) {
        val clickedItem = cryptList[position]

        val intent = Intent(this, AdditionalInfoActivity::class.java)

        intent.putExtra("id", clickedItem.id)
        intent.putExtra("name", clickedItem.name)
        intent.putExtra("symbol", clickedItem.symbol)

        if (v != null) {
            v.isClickable = false
        }
        startActivity(intent)
    }


    override fun onResume() {
        recycler.adapter?.notifyDataSetChanged()
        super.onResume()
    }
}

