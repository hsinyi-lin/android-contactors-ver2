package com.example.myfinal

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseManager(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "my_database.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "my_table"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_PHONE = "phone"
        const val COLUMN_BIRTH = "birth"
    }

    // 建立資料表
    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery =
            "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_NAME TEXT, $COLUMN_PHONE TEXT, $COLUMN_BIRTH TEXT)"
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // 在資料庫版本升級時，可以根據需要進行資料遷移或其他操作
        // 這裡的範例是刪除舊資料表並重新建立新資料表
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // 新增聯絡人資料
    fun insertData(name: String, phone: String, birth: String): Long {
        val values = ContentValues()
        values.put(COLUMN_NAME, name)
        values.put(COLUMN_PHONE, phone)
        values.put(COLUMN_BIRTH, birth)

        val db = writableDatabase
        val id = db.insert(TABLE_NAME, null, values)
        db.close()

        return id
    }

    // 更新聯絡人資料
    fun updateData(id: Long, name: String, phone: String, birth: String): Int {
        val values = ContentValues()
        values.put(COLUMN_NAME, name)
        values.put(COLUMN_PHONE, phone)
        values.put(COLUMN_BIRTH, birth)

        val db = writableDatabase
        val rowsAffected = db.update(TABLE_NAME, values, "$COLUMN_ID=?", arrayOf(id.toString()))
        db.close()

        return rowsAffected
    }

    // 刪除聯絡人資料
    fun deleteData(id: Long): Int {
        val db = writableDatabase
        val rowsAffected = db.delete(TABLE_NAME, "$COLUMN_ID=?", arrayOf(id.toString()))
        db.close()

        return rowsAffected
    }

    fun deleteTable(): Int {
        val db = writableDatabase
        val rowsAffected = db.delete(TABLE_NAME, null, null)
        db.close()

        return rowsAffected
    }

    // 取得單一聯絡人資料
    fun getSingleData(id: Long): Cursor {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = ?"
        val cursor = db.rawQuery(query, arrayOf(id.toString()))

        return cursor
    }

    // 取得全部聯絡人資料
    fun getAllData(): Cursor {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        return cursor
    }
}
