package at.ac.tuwien.nsa.gr12.comparelocations.adapter.mozilla.location.service

import at.ac.tuwien.nsa.gr12.comparelocations.adapter.mozilla.location.service.mapper.RequestMapper
import at.ac.tuwien.nsa.gr12.comparelocations.adapter.mozilla.location.service.mapper.ResponseMapper
import at.ac.tuwien.nsa.gr12.comparelocations.adapter.mozilla.location.service.model.MLSRequest
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.AccessPoint
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.CellTower
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Location
import at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces.LocationServiceInterface
import org.mapstruct.factory.Mappers
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory

class MozillaLocationServiceAdapter : LocationServiceInterface {

    private val requestMapper = Mappers.getMapper(RequestMapper::class.java)
    private val responseMapper = Mappers.getMapper(ResponseMapper::class.java)

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://location.services.mozilla.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val mlsKey = "test"

    override suspend fun get(
        accessPoints: List<AccessPoint>,
        cellTowers: List<CellTower>
    ): Location {
        val mlsAccessPoints = requestMapper.mapAccessPoints(accessPoints)
        val mlsCellTowers = requestMapper.mapCellTowers(cellTowers)
        val mlsRequest = MLSRequest(mlsAccessPoints, mlsCellTowers)


        val mlsClient = retrofit.create(MLSClient::class.java)

        val response = mlsClient.location(mlsRequest, mlsKey).await()

        return responseMapper.map(response)
    }
}