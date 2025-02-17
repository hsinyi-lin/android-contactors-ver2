package com.example.myfinal

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView


data class Item(
    val id: Long,
    val photo: Int,
    val name: String,
    val phone: String,
    val birth: String
)

class MyAdapter(context: Context, private val data: ArrayList<Item>, val layoutId: Int) :
    ArrayAdapter<Item>(context, layoutId, data) {

    override fun getCount() = data.size

    override fun getItem(position: Int) = data[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = View.inflate(context, layoutId, null)
        val item = getItem(position)
        view.findViewById<ImageView>(R.id.imageView).setImageResource(item.photo)
        view.findViewById<TextView>(R.id.txtName).text = item.name
        view.findViewById<TextView>(R.id.txtPhone).text = item.phone
        view.findViewById<TextView>(R.id.txtBirth).text = item.birth
        return view
    }
}


class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPagerAdapter = ViewPagerAdapter(this)
        val viewPager = findViewById<ViewPager2>(R.id.mViewPageNav)
        val btmNav = findViewById<BottomNavigationView>(R.id.btmNav)
        viewPager.adapter = viewPagerAdapter
        viewPager.currentItem = 1
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                btmNav.selectedItemId = when (position) {
                    0 -> R.id.btnLeft
                    1 -> R.id.btnRight
                    else -> R.id.btnLeft
                }
            }
        })

        findViewById<BottomNavigationView>(R.id.btmNav).setOnItemSelectedListener(
            NavigationBarView.OnItemSelectedListener {
                when (it.itemId) {
                    R.id.btnLeft -> {
                        viewPager.currentItem = 0
                        return@OnItemSelectedListener true
                    }
                    R.id.btnRight -> {
                        viewPager.currentItem = 1
                        return@OnItemSelectedListener true
                    }
                }
                false
            }
        )
        viewPager.currentItem = 1
        btmNav.selectedItemId = R.id.btnRight

    }
}


class ViewPagerAdapter(activity: MainActivity): FragmentStateAdapter(activity){
    override fun getItemCount() = 3

    override fun createFragment(position: Int)=
        when(position){
            0 -> userFragment.newInstance("新增","")
            1 -> allFragment.newInstance("所有資料","")
            2 -> renewFragment.newInstance("檢視/更新","")
            else -> userFragment.newInstance("這邊是不可能執行的","")
        }
}
