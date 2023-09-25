
package com.example.projectcompose.ViewModel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectcompose.DataMadels.ProductData
import com.example.projectcompose.DataMadels.ProductOrder
import com.example.projectcompose.Repository.Repository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repository: Repository):ViewModel() {

    private  var liveDataSignin  = MutableLiveData<Task<AuthResult>>()
    private  var livedataLogin =  MutableLiveData<Task<AuthResult>>()
    private var productData  =  MutableLiveData<Result<List<ProductData>>>()
    private var user : FirebaseUser? = firebaseGetAuth().currentUser
    private var databasse  =  FirebaseDatabase.getInstance()




    fun signUpUser(email:String,password:String):MutableLiveData<Task<AuthResult>>{
        repository.getFirebase().auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            liveDataSignin.postValue(it)
        }
        return liveDataSignin
    }

    init {
        fetchProduct()

    }

    fun logInUser(email: String,password: String):MutableLiveData<Task<AuthResult>>{
        repository.getFirebase().auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            livedataLogin.postValue(it)
        }
        return livedataLogin
    }

    fun firebaseGetAuth():FirebaseAuth{
       return repository.getFirebase().auth
    }

    fun getAllProduct(string: String):MutableLiveData<Result<List<ProductData>>>{
        viewModelScope.launch {

            var myflow  = flow {emit(repository.getRemoteControl().getAllProduct(string)) }

            myflow.catch {
                productData.postValue(Result.failure(it))
            }.collect{
                if(it.isSuccessful){
                    productData.postValue(Result.success(it.body()?: emptyList()))
                }
                else{
                    productData.postValue(Result.failure(Throwable("ERROR")))
                }
            }

        }

        return productData

    }

    fun fetchProduct():MutableLiveData<Result<List<ProductData>>>{
        viewModelScope.launch {

            var myflow  = flow {emit(repository.getRemoteControl().getAllProduct("")) }

            myflow.catch {
                productData.postValue(Result.failure(it))
            }.collect{
                if(it.isSuccessful){
                    productData.postValue(Result.success(it.body()?: emptyList()))
                }
                else{
                    productData.postValue(Result.failure(Throwable("ERROR")))
                }
            }

        }

        return productData

    }


    fun setLIke(productData: ProductData){
        val ref  =  databasse.getReference(user!!.uid)
        ref.child("like").child(productData.id.toString()).setValue(productData)
    }
    fun removeLike(productData: ProductData){
        val ref  = databasse.getReference(user!!.uid)
        ref.child("like").child(productData.id.toString()).removeValue()
    }


    fun checkLike(productData: ProductData):LiveData<Boolean>{
        val ref  = databasse.getReference(user!!.uid)
        val checkLike  = MutableLiveData<Boolean>()
        ref.child("like").child(productData.id.toString()).addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.getValue<ProductData>().let {
                    if(it?.id==productData.id)
                    {
                        checkLike.value = true
                    }
                    else{
                        checkLike.value = false
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        return checkLike
    }

    val list  = MutableLiveData<Result<ArrayList<ProductData>>>()
    fun getLikeList(): MutableLiveData<Result<ArrayList<ProductData>>> {
        val ref  = databasse.getReference(user!!.uid)
        val arrayList: ArrayList<ProductData> = ArrayList()

        ref.child("like").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                arrayList.clear()
                snapshot.children.forEach {
                    val data  = it.getValue<ProductData>()
                    arrayList.add(data!!)
                }
                Log.d("@@@@", "onDataChange: ${arrayList.size}")
                list.postValue(Result.success(arrayList))
            }

            override fun onCancelled(error: DatabaseError) {
                list.postValue(Result.failure(Throwable()))
            }

        })


        return list
    }


    fun addOrder(productOrder: ProductOrder){
        val ref  = databasse.getReference(user!!.uid)
        ref.child("order").child(productOrder.productData.id.toString()).setValue(productOrder)
    }

    fun addCount(count :Int, productID:Int){
        val ref  =  databasse.getReference(user!!.uid)
        ref.child("order").child(productID.toString()).child("count").setValue(count)
    }


    val listorder  = MutableLiveData<Result<ArrayList<ProductOrder>>>()


    fun readOrder(): MutableLiveData<Result<ArrayList<ProductOrder>>> {
        val ref  = databasse.getReference(user!!.uid)
        val arrayList: ArrayList<ProductOrder> = ArrayList()

        ref.child("order").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                arrayList.clear()
                snapshot.children.forEach {
                    var count :Int  = it.child("count").getValue<Int>()!!
                    var productData  = it.child("productData").getValue<ProductData>()
                    var data   = ProductOrder(productData!!,count)
                    arrayList.add(data!!)
                }
                Log.d("@@@@", "onDataChange: ${arrayList.size}")
                listorder.postValue(Result.success(arrayList))
            }

            override fun onCancelled(error: DatabaseError) {
                listorder.postValue(Result.failure(Throwable()))
            }

        })

        return listorder

    }


    fun removeOrder(productOrder: ProductOrder){
        val ref =  databasse.getReference(user!!.uid)
        ref.child("order").child(productOrder.productData.id.toString()).removeValue()
    }
    
}



