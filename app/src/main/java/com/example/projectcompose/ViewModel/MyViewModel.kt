package com.example.projectcompose.ViewModel


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectcompose.DataMadels.ProductData
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

    private val _data = MutableLiveData<String>()
    val data: LiveData<String> = _data


    fun setData(value: String) {
        _data.value = value
    }

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

    fun getLikeList():MutableState<Result<ArrayList<ProductData>>>{

        val mutableLiveData   = mutableStateOf<Result<ArrayList<ProductData>>>(value = Result.failure(
            Throwable()
        ))
        val ref  =  databasse.getReference(user!!.uid)
        ref.child("like").addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                mutableLiveData.value =   Result.success( snapshot.value as ArrayList<ProductData>)
            }

            override fun onCancelled(error: DatabaseError) {
                mutableLiveData.value = Result.failure(Throwable())
            }
        })
        return mutableLiveData
    }








}

