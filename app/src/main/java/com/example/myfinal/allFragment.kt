package com.example.myfinal

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private val images = intArrayOf(
    R.drawable.icon_1, R.drawable.icon_2,
    R.drawable.icon_3, R.drawable.icon_4,
    R.drawable.icon_5, R.drawable.icon_6,
    R.drawable.icon_7, R.drawable.icon_8,
    R.drawable.icon_9, R.drawable.icon_10
)

/**
 * A simple [Fragment] subclass.
 * Use the [allFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class allFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_all, container, false)
        val lstView = rootView.findViewById<ListView>(R.id.lstView)

        // 取得全部聯絡人資料
        val dbHelper = DatabaseManager(requireContext())
        val cursor = dbHelper.getAllData()

        val items = ArrayList<Item>()

        if (cursor != null && cursor.moveToFirst()) {
            do {
                val id = cursor.getLong(cursor.getColumnIndex(DatabaseManager.COLUMN_ID))
                val name = cursor.getString(cursor.getColumnIndex(DatabaseManager.COLUMN_NAME))
                val phone = cursor.getString(cursor.getColumnIndex(DatabaseManager.COLUMN_PHONE))
                val birth = cursor.getString(cursor.getColumnIndex(DatabaseManager.COLUMN_BIRTH))

                // 建立 Item 物件並加入 items 列表中
                items.add(Item(id, R.drawable.icon_2, name, phone, birth))
            } while (cursor.moveToNext())
        }

        // 關閉 Cursor 和資料庫
        cursor?.close()
        dbHelper.close()

        lstView.setOnItemClickListener { adapterView, view, pos, id ->
            // 取得點擊的項目
//            val selectedItem = items[pos]
//
//            // 建立 Intent 物件
//            val intent = Intent(requireContext(), AddActivity:: class.java)
//
//            // 將資料放入 Intent 中
//            intent.putExtra("id", selectedItem.id)
//            intent.putExtra("name", selectedItem.name)
//            intent.putExtra("phone", selectedItem.phone)
//            intent.putExtra("birth", selectedItem.birth)

            // 啟動另一個 Activity
//            startActivity(intent)

//            val intent = Intent(requireActivity(), AddActivity::class.java)
//            requireActivity().startActivity(intent)
        }


        val myAdapter = MyAdapter(requireContext(), items, R.layout.adapter_item)
        lstView.adapter = myAdapter

        return rootView
    }



//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_all, container, false)
//    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment allFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            allFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}