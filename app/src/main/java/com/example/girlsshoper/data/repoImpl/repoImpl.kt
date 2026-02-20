package com.example.girlsshoper.data.repoImpl

import android.util.Log
import com.example.girlsshoper.comman.CATEGORY
import com.example.girlsshoper.comman.MainState
import com.example.girlsshoper.comman.PRODUCTS
import com.example.girlsshoper.comman.USERS
import com.example.girlsshoper.domain.module.categoryModel
import com.example.girlsshoper.domain.module.productModel
import com.example.girlsshoper.domain.module.userModel
import com.example.girlsshoper.domain.repo.repo
import com.google.firebase.auth.FirebaseAuth
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
                it.toObject(categoryModel::class.java)
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



    override fun searchCategoryByQuery(searchQuery: String): Flow<MainState<List<categoryModel>>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateFromToken(userID : String){
        firebaseMessaging.token.addOnCompleteListener {
            if (it.isSuccessful){
                val token = it.result
                firebaseFirestore.collection("USERS TOKEN").document(userID).set(mapOf("token" to token))
            }
        }

    }




}