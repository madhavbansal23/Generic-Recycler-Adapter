package com.maddy.genericrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.maddy.genericrecyclerview.databinding.ActivityMainBinding
import com.maddy.genericrecyclerview.databinding.ItemRvBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), RecyclerClickInterface<Articles> {

    private var articleList = ArrayList<Articles>()
    private lateinit var adapter: GenericRecyclerAdapter<Articles, ItemRvBinding>
    private lateinit var mBinding: ActivityMainBinding
    private var sortBoolean: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        addList(sortBoolean)
        adapter = GenericRecyclerAdapter(articleList, R.layout.item_rv, genericRecyclerInterface)
        mBinding.rvNews.adapter = adapter

        mBinding.bSort.setOnClickListener {
            sortBoolean = !sortBoolean
            addList(sortBoolean)
            adapter.updateList(articleList)
        }
    }

    private val genericRecyclerInterface: GenericRecyclerInterface<ItemRvBinding, Articles> =
        object : GenericRecyclerInterface<ItemRvBinding, Articles> {
            override fun bindData(binder: ItemRvBinding, model: Articles, pos: Int) {
                binder.articles = model
                binder.pos = pos
                binder.handler = this@MainActivity
            }
        }

    override fun onClick(v: View?, model: Articles, pos: Int) {
        Toast.makeText(this, "$pos", Toast.LENGTH_LONG).show()
    }

    private fun addList(boolean: Boolean) {
        articleList = ArrayList()
        if (boolean) {
            for (i in 0..5) {
                articleList.add(Articles(i, "Article $i"))
            }
        } else {
            for (i in 5 downTo 0) {
                articleList.add(Articles(i, "Article $i"))
            }
        }
    }

}
