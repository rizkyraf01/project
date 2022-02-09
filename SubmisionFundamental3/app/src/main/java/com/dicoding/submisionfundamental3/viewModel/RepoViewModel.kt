package com.dicoding.submisionfundamental3.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.submisionfundamental3.data.UserData
import com.dicoding.submisionfundamental3.fragment.ReposFragment
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

class RepoViewModel: ViewModel() {
    private val userNonMutable = ArrayList<UserData>()
    private val userMutable = MutableLiveData<ArrayList<UserData>>()

    val listRepo : LiveData<ArrayList<UserData>> get() = userMutable

    fun getUser(): LiveData<ArrayList<UserData>> {
        return userMutable
    }

    fun getUserRepo(username: String, context: Context){
        val client = AsyncHttpClient()
        val url = "https://api.github.com/users/$username/repos"
        client.addHeader("Authorization", "token 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")
        client.addHeader("user-agent", "request")
        client.get(url, object: AsyncHttpResponseHandler(){
            override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>,
                    responseBody: ByteArray
            ) {
                val result = String(responseBody)
                Log.d(ReposFragment.TAG, result)
                try {
                    val jsonArray = JSONArray(result)

                    for (i in 0 until jsonArray.length()){
                        Log.d("Halooooooooooooo", userNonMutable.toString())
                        val jsonObject = jsonArray.getJSONObject(i)

                        val repoName: String = jsonObject.getString("name").toString()
                        val repoDesc: String = jsonObject.getString("description").toString()

                        userNonMutable.add(UserData(
                                repoName = repoName,
                                repoDesc = repoDesc))
                        userMutable.postValue(userNonMutable)
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