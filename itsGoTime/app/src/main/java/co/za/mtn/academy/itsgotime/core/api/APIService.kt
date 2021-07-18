package co.za.mtn.academy.itsgotime.core.api

import co.za.mtn.academy.itsgotime.core.model.User
import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    //Interface to fetch data from the service
    @GET("/facilitators/?format=json")
    fun getAllFacilitators() : Call<List<User>>
}



