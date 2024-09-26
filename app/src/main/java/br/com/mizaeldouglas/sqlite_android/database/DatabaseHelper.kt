package br.com.mizaeldouglas.sqlite_android.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "loja.db", null, 2) {

    companion object{
        const val TABELA_PRODUTOS = "produtos"
        const val ID_PRODUTO = "id_produtos"
        const val TITLE_PRODUTOS = "title_produto"
        const val DESCRICAO_PRODUTOS = "descricao_produto"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        Log.i("info_db" , "Executou onCreate")
        val sql = "CREATE TABLE if NOT EXISTS ${TABELA_PRODUTOS}(" +
                "  $ID_PRODUTO INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "  $TITLE_PRODUTOS VARCHAR(100)," +
                "  $DESCRICAO_PRODUTOS TEXT" +
                ")"

        try {
            db?.execSQL(sql)
            Log.i("info_db" , "Sucesso ao criar a tabela")
        }catch (ex: Exception){
            ex.printStackTrace()
            Log.i("info_db" , "Erro ao criar a tabela")
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        Log.i("info_db" , "Executou onUpgrade")
        TODO("Not yet implemented")
    }

}