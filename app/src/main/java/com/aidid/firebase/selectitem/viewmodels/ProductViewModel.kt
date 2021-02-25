package com.aidid.firebase.selectitem.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aidid.firebase.selectitem.models.Product

class ProductViewModel : ViewModel(){
    private var products = MutableLiveData<List<Product>>()
    private var selectedProducts = MutableLiveData<List<Product>>()

    init {
        products.postValue(mutableListOf())
        selectedProducts.postValue(mutableListOf())
    }

    fun fetchDummyProduct(){

        val dummies = mutableListOf<Product>().apply {
            add(Product(1.toString(), "Surgical Mask 3-ply 1Box (50pcs)", 5, "https://images-na.ssl-images-amazon.com/images/I/81zKjnsKyZL._AC_SL1500_.jpg"))
            add(Product(2.toString(), "N95 Mask 1Box (20pcs)", 7, "https://content.pattersondental.com/items/LargeSquare/images/222584.jpg"))
            add(Product(3.toString(), "APD Type 1412 (per pcs)", 20, "https://cf.shopee.co.id/file/6a29e2d183f31b3f8e3c8fd5f9a8dc79"))
            add(Product(4.toString(), "PPE Face Shield (per 20 pcs)", 5, "https://cdn.shopify.com/s/files/1/0013/1655/2764/products/DB-Face-Shild.jpg?v=1593785175"))
            add(Product(5.toString(), "Hospital Bed", 150, "https://ecs7.tokopedia.net/img/cache/700/product-1/2017/1/19/15782036/15782036_6f26aa1c-634e-48a0-9f97-4b12fd9668d8_1454_1025.jpg"))
            add(Product(6.toString(), "Reintair by Casals Air Purifier Covid 19", 150, "https://www.casals.com/assets/uploads/cat_pdf/normal_1f16e-reintair_300-600.jpg"))
            add(Product(7.toString(), "Hepa Filter Air Flow", 150, "https://labtech-indonesia.com/wp-content/uploads/2017/04/filter.png"))
        }
        products.postValue(dummies)

    }

    fun addSelectedProduct(product: Product){
        val tempSelectedProducts : MutableList<Product> = if (selectedProducts.value == null){
            mutableListOf()
        }else{
            selectedProducts.value as MutableList<Product>
        }
        val sameProduct : Product? = tempSelectedProducts.find { p ->
            p.id == product.id
        }
        sameProduct?.let {
            it.selectedQuantity = it.selectedQuantity?.plus(1)
        } ?: kotlin.run {
            tempSelectedProducts.add(product)

        }
        selectedProducts.postValue(tempSelectedProducts)
    }

    fun decrementQuantityProduct(product: Product){
        val tempSelectedProducts : MutableList<Product> = if (selectedProducts.value == null){
            mutableListOf()
        }else{
            selectedProducts.value as MutableList<Product>
        }
        val sameProduct : Product? = tempSelectedProducts.find { p ->
            p.id == product.id
        }
        sameProduct?.let {
            if(it.selectedQuantity?.minus(1) == 0){
                tempSelectedProducts.remove(it)
            }else{
                it.selectedQuantity = it.selectedQuantity!!.minus(1)
            }
        }
        selectedProducts.postValue(tempSelectedProducts)
    }

    fun incrementQuantityProduct(product: Product){
        val tempSelectedProducts : MutableList<Product> = if (selectedProducts.value == null){
            mutableListOf()
        }else{
            selectedProducts.value as MutableList<Product>
        }
        val sameProduct : Product? = tempSelectedProducts.find { p ->
            p.id == product.id
        }
        sameProduct?.let {
            it.selectedQuantity = it.selectedQuantity!!.plus(1)
        }
        selectedProducts.postValue(tempSelectedProducts)
    }

    fun deleteSelectedProduct(product: Product){
        val tempSelectedProducts : MutableList<Product> = if (selectedProducts.value == null){
            mutableListOf()
        }else{
            selectedProducts.value as MutableList<Product>
        }
        val sameProduct : Product? = tempSelectedProducts.find { p ->
            p.id == product.id
        }
        sameProduct?.let {
            tempSelectedProducts.remove(it)
        }
        selectedProducts.postValue(tempSelectedProducts)
    }
    fun listenToProducts() = products
    fun listenToSelectedProduct() = selectedProducts
}