package com.example.girlsshoper.data.repoImpl

import android.net.Uri
import android.util.Log
import com.example.girlsshoper.comman.BANNER_POSTS
import com.example.girlsshoper.comman.CATEGORY
import com.example.girlsshoper.comman.MainState
import com.example.girlsshoper.comman.PRODUCTS
import com.example.girlsshoper.comman.PRODUCT_CART
import com.example.girlsshoper.comman.SPACIFICUSER_CART
import com.example.girlsshoper.comman.USERS
import com.example.girlsshoper.domain.module.CartModel
import com.example.girlsshoper.domain.module.bannerPostsModel
import com.example.girlsshoper.domain.module.categoryModel
import com.example.girlsshoper.domain.module.productModel
import com.example.girlsshoper.domain.module.userModel
import com.example.girlsshoper.domain.repo.repo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class repoImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseAuth : FirebaseAuth,
    private val firebaseMessaging: FirebaseMessaging
) : repo {
    override fun getAllCategoryRepo(): Flow<MainState<List<categoryModel>>> = callbackFlow {
        trySend(MainState.Loading)
        firebaseFirestore.collection(CATEGORY).get().addOnSuccessListener {
            val categoryData = it.documents.mapNotNull {
                val category = it.toObject(categoryModel::class.java)
                category?.categoryId = it.id
                category
            }
            trySend(MainState.Success(categoryData))
        }.addOnFailureListener {
            trySend(MainState.Error(it.toString()))
        }
        awaitClose {
            close()
        }

    }

    override fun getAllProductRepo(): Flow<MainState<List<productModel>>> = callbackFlow {
        trySend(MainState.Loading)

        firebaseFirestore.collection(PRODUCTS).get().addOnSuccessListener {
            val productData = it.documents.mapNotNull {
                val product = it.toObject(productModel::class.java)
                product?.productId = it.id
                product
            }
            trySend(MainState.Success(productData))
        }.addOnFailureListener {
            trySend(MainState.Error(it.toString()))
        }
        awaitClose {
            close()
        }

    }

    override fun registerUserEmailPass(userData : userModel): Flow<MainState<String>>  = callbackFlow {
        trySend(MainState.Loading)
        firebaseAuth.createUserWithEmailAndPassword(userData.email, userData.password).addOnSuccessListener {
            firebaseFirestore.collection("USERS").document(it.user?.uid.toString()).set(userData).addOnSuccessListener {
                trySend(MainState.Success("User Registered"))
            }.addOnFailureListener {
                trySend(MainState.Error(it.toString()))
            }

            launch {
                updateFromToken(userID =firebaseAuth.currentUser?.uid.toString())
            }

        }.addOnFailureListener {
            trySend(MainState.Error(it.toString()))
        }
        awaitClose {
            close()
        }
    }

    override fun loginwithemailpass(
        email: String,
        password: String
    ): Flow<MainState<String>> = callbackFlow {
        trySend(MainState.Loading)
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
            trySend(MainState.Success("User Login"))
            launch {
                updateFromToken(userID =firebaseAuth.currentUser?.uid.toString())
            }
        }.addOnFailureListener {
            trySend(MainState.Error(it.toString()))
        }
        awaitClose {
            close()
        }



    }

    override fun getProductById(productId: String): Flow<MainState<productModel>> = callbackFlow {
        trySend(MainState.Loading)
        firebaseFirestore.collection(PRODUCTS).document(productId).get()
            .addOnSuccessListener { document ->
                val product = document.toObject(productModel::class.java)
                product?.productId = document.id
                trySend(MainState.Success(product!!))
            }.addOnFailureListener {
                trySend(MainState.Error(it.message.toString()))
            }
        awaitClose {
            close()
        }
    }

    override fun getSpacUserById(userId: String): Flow<MainState<userModel>> = callbackFlow {
        trySend(MainState.Loading)
        firebaseFirestore.collection(USERS).document(userId).get()
            .addOnSuccessListener {
                val userSpacdata = it.toObject(userModel::class.java).apply {
                    this?.uid = it.id
                }
                trySend(MainState.Success(userSpacdata!!))
            }.addOnFailureListener {
                trySend(MainState.Error(it.message.toString()))
            }
        awaitClose {
            close()
        }

    }

    override fun updateUserProfile(userData: userModel): Flow<MainState<String>> = callbackFlow {
        trySend(MainState.Loading)
        firebaseFirestore.collection(USERS).document(userData.uid).set(userData).addOnSuccessListener {
            trySend(MainState.Success("User Profile Updated"))
        }.addOnFailureListener {
            trySend(MainState.Error(it.message.toString()))
        }
        awaitClose {
            close()
        }
    }

    override fun searchProductByQuery(searchQuery: String): Flow<MainState<List<productModel>>> = callbackFlow {
        Log.d("searchQuery", searchQuery)
        trySend(MainState.Loading)
        firebaseFirestore.collection(PRODUCTS).orderBy("productTitle")
            .startAt(searchQuery).get()
            .addOnSuccessListener {
                val searchProducts = it.documents.mapNotNull {
                    it.toObject(productModel::class.java)?.apply {
                        productId = it.id
                    }
                }
                trySend(MainState.Success(searchProducts))
            }
            .addOnFailureListener {
                trySend(MainState.Error(it.message.toString()))
            }
        awaitClose {
            close()
        }
    }



    override fun searchCategoryByQuery(searchCategoryQuery: String): Flow<MainState<List<categoryModel>>> = callbackFlow {
        trySend(MainState.Loading)
        firebaseFirestore.collection(CATEGORY).orderBy("name")
            .startAt(searchCategoryQuery).get()
            .addOnSuccessListener {
                val searchCategorys = it.documents.mapNotNull {
                    it.toObject(categoryModel::class.java)?.apply {
                        categoryId = it.id
                    }
                }
                trySend(MainState.Success(searchCategorys))
            }.addOnFailureListener {
                trySend(MainState.Error(it.message.toString()))
            }
        awaitClose {
            close()
        }

    }

    override fun getBannerPostsRepo(): Flow<MainState<List<bannerPostsModel>>> = callbackFlow {
        trySend(MainState.Loading)
        firebaseFirestore.collection(BANNER_POSTS).get().addOnSuccessListener {
            val bannnerData = it.documents.mapNotNull {
                it.toObject(bannerPostsModel::class.java)
            }
            trySend(MainState.Success(bannnerData))
        }.addOnFailureListener {
            trySend(MainState.Error(it.message.toString()))
        }
        awaitClose {
            close()
        }
    }

    override suspend fun updateFromToken(userID : String){
        firebaseMessaging.token.addOnCompleteListener {
            if (it.isSuccessful){
                val token = it.result
                firebaseFirestore.collection("USERS TOKEN").document(userID).set(mapOf("token" to token))
            }
        }

    }

    override fun getProductByCategory(categoryName: String): Flow<MainState<List<productModel>>> = callbackFlow {
        trySend(MainState.Loading)
        firebaseFirestore.collection(PRODUCTS)
            .whereEqualTo("category", categoryName).get().addOnSuccessListener {
                val product = it.documents.mapNotNull {
                    it.toObject(productModel::class.java)?.apply {
                        productId = it.id
                    }
                }
                trySend(MainState.Success(product))
            }.addOnFailureListener {
                trySend(MainState.Error(it.message.toString()))
            }
        awaitClose {
            close()
        }

    }

    private fun getCartRef() =
         firebaseFirestore.collection(PRODUCT_CART)
            .document(firebaseAuth.currentUser?.uid ?: "")
            .collection(SPACIFICUSER_CART)


    override fun addtoCart(cartModel: CartModel): Flow<MainState<String>> = callbackFlow {
        trySend(MainState.Loading)
        val docRef = getCartRef().document(cartModel.productId)
        docRef.get().addOnSuccessListener { snapShot ->
            if (snapShot.exists()){
                val existing = snapShot.toObject(CartModel::class.java)
                val newQty = (existing?.quantity ?: 0) + 1

                docRef.update("quantity", newQty)
                    .addOnSuccessListener {
                        trySend(MainState.Success("quantity Updated"))
                        close()
                    }
                    .addOnFailureListener {
                        trySend(MainState.Error(it.message.toString()))
                        close()
                    }

            }else {
                docRef.set(cartModel)
                    .addOnSuccessListener {
                        trySend(MainState.Success("Added to cart"))
                        close()
                    }
                    .addOnFailureListener {
                        trySend(MainState.Error(it.message.toString()))
                        close()
                    }

            }
        }.addOnFailureListener {
            trySend(MainState.Error(it.message.toString()))
            close()
        }
        awaitClose {
            close()
        }


    }

    override fun removeFromCart(productid: String): Flow<MainState<String>> = callbackFlow {
        trySend(MainState.Loading)
        getCartRef().document(productid)
            .delete()
            .addOnSuccessListener {
                trySend(MainState.Success("delete successfully"))
                close()
            }
            .addOnFailureListener {
                trySend(MainState.Error(it.message.toString()))
                close()
            }
        awaitClose {
            close()
        }

    }

    override fun updateCartQuantity(productid: String, increses : Boolean): Flow<MainState<String>> = callbackFlow {
        trySend(MainState.Loading)
        val docRef = getCartRef().document(productid)
        docRef.get().addOnSuccessListener { snapShot ->
            val current = snapShot.toObject(CartModel::class.java)
            if (current != null){
                val newQty = if (increses){
                    current.quantity + 1
                }else {
                    (current.quantity - 1).coerceAtLeast(1)
                }
                docRef.update("quantity", newQty)
                    .addOnSuccessListener {
                        trySend(MainState.Success("Quantity Updated"))
                        close()
                    }
                    .addOnFailureListener {
                        trySend(MainState.Error(it.message.toString()))
                        close()
                    }
            }
        }.addOnFailureListener {
            trySend(MainState.Error(it.message.toString()))
            close()
        }
        awaitClose {
            close()
        }

    }

    override fun isProductinCartorNot(productid: String): Flow<MainState<Boolean>> = callbackFlow {
        trySend(MainState.Loading)
        getCartRef().document(productid)
            .get()
            .addOnSuccessListener {
                trySend(MainState.Success(it.exists()))
                close()
            }
            .addOnFailureListener {
                trySend(MainState.Error(it.message.toString()))
                close()
            }
        awaitClose {
            close()
        }
    }

    override fun getCartProduct(): Flow<MainState<List<CartModel>>> = callbackFlow {
        trySend(MainState.Loading)
        val listner = getCartRef().addSnapshotListener { snapshots, error ->
            if(error != null){
                trySend(MainState.Error(error.message.toString()))
                return@addSnapshotListener
            }
            val data = snapshots?.documents?.mapNotNull {
                val cart = it.toObject(CartModel::class.java)
                cart?.productId = it.id
                cart
            } ?: emptyList()

            trySend(MainState.Success(data))
        }
        awaitClose {
            close()
        }

    }

    override fun updateUserInfo(userModel: userModel): Flow<MainState<String>> = callbackFlow {

    }

    override fun changeUserImage(imageUri: Uri): Flow<MainState<String>> = callbackFlow {

    }


}