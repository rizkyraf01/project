package com.dicoding.consumerapp.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.consumerapp.data.UserData
import com.dicoding.consumerapp.fragment.FollowingFragment
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

class FollowingViewModel: ViewModel() {

    private val userNonMutable = ArrayList<UserData>()
    private val userMutable = MutableLiveData<ArrayList<UserData>>()

    fun getUser(): LiveData<ArrayList<UserData>> {
        return userMutable
    }

    fun userDetail(username: String, context: Context){
        val client = AsyncHttpClient()
        val url = "https://api.github.com/users/$username"
        client.addHeader("Authorization", "token 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")
        client.addHeader("user-agent", "request")
        client.get(url, object: AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray
            ) {
                val result = String(responseBody)
                Log.d(FollowingFragment.TAG, result)
                try {
                    val responseObject = JSONObject(result)

                    val userName: String = responseObject.getString("login").toString()
                    val name: String = responseObject.getString("name").toString()
                    val imageUser: String = responseObject.getString("avatar_url").toString()
                    val userCompany: String = responseObject.getString("company").toString()
                    val userLocation: String = responseObject.getString("location").toString()
                    val repository: String = responseObject.getString("public_repos")
                    val followers: String = responseObject.getString("followers")
                    val following: String = responseObject.getString("following")

                    userNonMutable.add(
                        UserData(
                            userName,
                            name,
                            imageUser,
                            userCompany,
                            userLocation,
                            repository,
                            followers,
                            following
                        )
                    )
                    userMutable.postValue(userNonMutable)
                }
                catch (e: Exception){
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray,
                error: Throwable
            ) {
                val errorMessage = when(statusCode){
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode :Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
                Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
            }
        }
        )
    }

    fun getUserListFollowing(username: String, context: Context){
        val client = AsyncHttpClient()
        val url = "https://api.github.com/users/$username/following"
        client.addHeader("Authorization", "token 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")
        client.addHeader("user-agent", "request")
        client.get(url, object: AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray
            ) {
                val result = String(responseBody)
                Log.d(FollowingFragment.TAG, result)
                try {
                    val jsonArray = JSONArray(result)
                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        val userName: String = jsonObject.getString("login")
                        userDetail(userName,context)
                    }
                }
                catch (e: Exception){
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray,
                error: Throwable
            ) {
                val errorMessage = when(statusCode){
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode :Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
                Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
            }
        }
        )
    }
}