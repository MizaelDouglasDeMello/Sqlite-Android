package br.com.mizaeldouglas.sqlite_android.model.produto

interface IProdutoDAO {
    fun create(produto: Produto): Boolean
    fun update(produto: Produto): Boolean
    fun delete(idProduto: Int): Boolean
    fun findAll(): List<Produto>
}