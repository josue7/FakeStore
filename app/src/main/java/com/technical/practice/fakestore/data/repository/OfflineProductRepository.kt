package com.technical.practice.fakestore.data.repository

import android.net.http.HttpException
import android.util.Log
import androidx.compose.foundation.layout.size
import com.technical.practice.fakestore.data.apiservice.FakeStoreApiService
import com.technical.practice.fakestore.data.apiservice.ProductAPI
import com.technical.practice.fakestore.data.database.product.Product
import com.technical.practice.fakestore.data.database.product.ProductDao
import kotlinx.coroutines.flow.Flow
import java.io.IOException

class OfflineProductRepository (
    private val  productDao: ProductDao,
    private val fakeStoreApiService: FakeStoreApiService
): ProductRepositoryLocal {

    override fun getAllProducts(): Flow< List<Product> > = productDao.getAllProducts()

    override suspend fun getProductsSync(): List<Product> = productDao.getProductsSync()

    override fun getProductsByCategory(category: String): Flow<List<Product>>  = productDao.getProductByCategory(category)

    override fun getProductById(productId: Int): Flow<Product> = productDao.getProductById(productId)

    override fun getFavoriteProducts(): Flow<List<Product>> = productDao.getFavoriteProducts()

    override suspend fun updateFavoriteStatus(idProduct: Int) = productDao.updateFavoriteStatus(idProduct)

    override suspend fun insertProduct(product: List<Product>) = productDao.insertProduct(product)

    override suspend fun refreshProducts() {
        Log.d("Variable INIT", "Iniciando refreshProducts")
        try {
            // 1. Obtener productos de la API
            val remoteProductsDto = fakeStoreApiService.getProducts()
            Log.d("Variable API", "Productos obtenidos de la API: ${remoteProductsDto.size}")

            val localProducts = productDao.getProductsSync()
            Log.d("Variable Locales", "Productos locales: ${localProducts.size}")

            val localProductIDs = localProducts.associateBy { it.idProduct }

            val productsToInsert = mutableListOf<Product>()
            val productsToUpdate = mutableListOf<Product>()

            for (dto in remoteProductsDto) {
                Log.i("Variable dto", "El valor es: ${dto.id}")
                val existingLocalProduct = localProductIDs[dto.id]
                Log.i("Variable existing", "El valor es: $existingLocalProduct")
                val isFavorite = existingLocalProduct?.isFavorite ?: false

                val productFromApi = Product(
                    idProduct = dto.id,
                    title = dto.title,
                    price = dto.price,
                    description = dto.description,
                    category = dto.category,
                    image = dto.image,
                    isFavorite = isFavorite
                )

                if (existingLocalProduct == null) {
                    productsToInsert.add(productFromApi)
                    Log.d("Variable insert", "Para insertar: ${productFromApi.title}")
                } else if (hasProductChanged(existingLocalProduct, productFromApi)){
                    productsToUpdate.add(productFromApi)
                    Log.d("Variable update", "Para actualizar: ${productFromApi.title}")
                }

                if (productsToInsert.isNotEmpty() || productsToUpdate.isNotEmpty()) {
                    val allProduct = productsToInsert + productsToUpdate
                    productDao.insertProduct(allProduct)

                }
            }//END for
            Log.i("Variable inser & update", "El tamaño es in ${productsToInsert.size} y up ${productsToUpdate.size}")

        } catch (e: IOException) {
            Log.e("ProductRepository", "Error de red al refrescar productos: ${e.message}", e)
            throw e
        } catch (e: Exception) {
            Log.e("ProductRepository", "Error inesperado al refrescar productos: ${e.message}", e)
            throw e
        }
    } //END refreshProducts

    private fun hasProductChanged(local: Product, remote: Product): Boolean {
        return local.title != remote.title ||
                local.price != remote.price ||
                local.description != remote.description ||
                local.category != remote.category ||
                local.image != remote.image
    }
}