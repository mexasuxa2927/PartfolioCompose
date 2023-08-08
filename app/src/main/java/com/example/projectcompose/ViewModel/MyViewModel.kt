package com.example.projectcompose.ViewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectcompose.DataMadels.ProductData
import com.example.projectcompose.Repository.Repository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repository: Repository):ViewModel() {

    private  var liveDataSignin  = MutableLiveData<Task<AuthResult>>()
    private  var livedataLogin =  MutableLiveData<Task<AuthResult>>()
    var productData  =  MutableLiveData<Result<List<ProductData>>>()

    fun signUpUser(email:String,password:String):MutableLiveData<Task<AuthResult>>{
        repository.getFirebase().auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            liveDataSignin.postValue(it)
        }
        return liveDataSignin
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

    fun getAllProduct():MutableLiveData<Result<List<ProductData>>>{
        viewModelScope.launch {

            var myflow  = flow {emit(repository.getRemoteControl().getAllProduct()) }

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


}

