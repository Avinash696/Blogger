package com.example.blogger.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.blogger.data.Constant
import com.example.blogger.data.Repositary
import com.example.blogger.data.blogModels.blogModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BlogViewModel(val repositary: Repositary) : ViewModel() {

    init {
        GlobalScope.launch {
            repositary.getAllBlogRepositary(Constant.key)
        }
    }

    val allBlogLiveData: LiveData<blogModel> = repositary.blogLiveData

}