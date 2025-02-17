package com.example.myfinal

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [userFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class userFragment : Fragment() {
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

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_user, container, false)
        val btnComfirm1 = rootView.findViewById<Button>(R.id.btnComfirm1)
        val dbHelper = DatabaseManager(requireContext())

        btnComfirm1.setOnClickListener {
            // 取得輸入內容
            val editTextName1 = rootView.findViewById<EditText>(R.id.editTextName1)
            val editTextPhone1 = rootView.findViewById<EditText>(R.id.editTextPhone1)
            val editTextBirth1 = rootView.findViewById<EditText>(R.id.editTextBirth1)

            val name = editTextName1.text.toString()
            val phone = editTextPhone1.text.toString()
            val birth = editTextBirth1.text.toString()

            // 呼叫 insertData 函式新增資料
            val insertedId = dbHelper.insertData(name, phone, birth)
            if (insertedId != -1L) {
                // 資料新增成功
                editTextName1.text.clear()
                editTextPhone1.text.clear()
                editTextBirth1.text.clear()

//                dbHelper.deleteTable()

                Snackbar.make(it, "新增成功", Snackbar.LENGTH_LONG)
                    .setAction("關閉") {}
                    .show()
            } else {
                // 資料新增失敗
                Snackbar.make(it, "新增失敗", Snackbar.LENGTH_LONG)
                    .setAction("關閉") {}
                    .show()
            }
        }

        val btnCancel1 = rootView.findViewById<Button>(R.id.btnCancel1)

        btnCancel1.setOnClickListener {
            val editTextName1 = rootView.findViewById<EditText>(R.id.editTextName1)
            val editTextPhone1 = rootView.findViewById<EditText>(R.id.editTextPhone1)
            val editTextBirth1 = rootView.findViewById<EditText>(R.id.editTextBirth1)

            editTextName1.text.clear()
            editTextPhone1.text.clear()
            editTextBirth1.text.clear()

            Snackbar.make(it, "已取消", Snackbar.LENGTH_LONG)
                .setAction("關閉") {}
                .show()
        }

        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment userFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            userFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}