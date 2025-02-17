package com.example.myfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.myfinal.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.btnConfirm.setOnClickListener{
//            val userName = binding.editName.text.toString()
//            val userPhone = binding.editPhone.text.toString()
//
//            if (userName.length == 0 || userPhone.length == 0){
//                AlertDialog.Builder(this)
//                    .setTitle("資料未填完整")
//                    .setMessage("請確認聯絡人資料是否填寫完整")
//                    .setNegativeButton("我知道了"){ dialog, which->
//                    }
//                    .show()
//            } else{
//                intent.putExtra("userName", userName)
//                intent.putExtra("userPhone", userPhone)
//
//                setResult(RESULT_OK, intent)
//                finish()
//            }
//        }
//
//        binding.btnCancel.setOnClickListener{
//            AlertDialog.Builder(this)
//                .setTitle("再次確認")
//                .setMessage("您確定要取消嗎?")
//                .setPositiveButton("是"){ dialog, which->
//                    setResult(RESULT_CANCELED)
//                    finish()
//                }
//                .setNegativeButton("否"){ dialog, which->
//                }
//                .show()
//        }

    }
}