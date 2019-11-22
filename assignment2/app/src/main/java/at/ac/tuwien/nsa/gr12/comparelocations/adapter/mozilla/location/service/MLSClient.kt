package at.ac.tuwien.nsa.gr12.comparelocations.adapter.mozilla.location.service

import at.ac.tuwien.nsa.gr12.comparelocations.adapter.mozilla.location.service.model.MLSRequest
import at.ac.tuwien.nsa.gr12.comparelocations.adapter.mozilla.location.service.model.MLSResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface MLSClient {

    @POST("v1/geolocate")
    fun location(
        @Body mlsRequest: MLSRequest,
        @Query("key") key: String
    ): Call<MLSResponse>
}