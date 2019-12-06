package at.ac.tuwien.nsa.gr12.comparelocations.adapter.cell.towers

import android.annotation.SuppressLint
import android.content.Context
import android.telephony.*
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.CellTower
import at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces.CellTowersInterface
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred

class CellTowersAdapter(context: Context) : CellTowersInterface {

    private val telephonyManager =
        context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

    @SuppressLint("MissingPermission")
    override fun getAsync(): Deferred<List<CellTower>> {
        val results = telephonyManager.allCellInfo
        val cellTowers = results.mapNotNull { mapToCellTower(it) }

        val deferred: CompletableDeferred<List<CellTower>> = CompletableDeferred()
        deferred.complete(cellTowers)
        return deferred
    }

    private fun mapToCellTower(cellInfo: CellInfo): CellTower? {
        if (!cellInfo.isRegistered) {
            return null
        }
        return when (cellInfo) {
            is CellInfoLte -> {
                mapLteToCellTower(cellInfo)
            }
            is CellInfoGsm -> {
                mapGsmToCellTower(cellInfo)
            }
            is CellInfoWcdma -> {
                mapWcdmaToCellTower(cellInfo)
            }
            else -> {
                null
            }
        }
    }

    private fun mapLteToCellTower(cellInfoLte: CellInfoLte): CellTower {
        val radioType = "lte"
        val mobileCountryCode = cellInfoLte.cellIdentity.mcc
        val mobileNetworkCode = cellInfoLte.cellIdentity.mnc
        val locationAreaCode = cellInfoLte.cellIdentity.tac
        val cellId = cellInfoLte.cellIdentity.ci
        val signalStrength = cellInfoLte.cellSignalStrength.dbm

        return CellTower(
            radioType,
            mobileCountryCode,
            mobileNetworkCode,
            locationAreaCode,
            cellId,
            signalStrength
        )
    }

    private fun mapGsmToCellTower(cellInfoGsm: CellInfoGsm): CellTower {
        val radioType = "gsm"
        val mobileCountryCode = cellInfoGsm.cellIdentity.mcc
        val mobileNetworkCode = cellInfoGsm.cellIdentity.mnc
        val locationAreaCode = cellInfoGsm.cellIdentity.lac
        val cellId = cellInfoGsm.cellIdentity.cid
        val signalStrength = cellInfoGsm.cellSignalStrength.dbm

        return CellTower(
            radioType,
            mobileCountryCode,
            mobileNetworkCode,
            locationAreaCode,
            cellId,
            signalStrength
        )
    }

    private fun mapWcdmaToCellTower(cellInfoWcdma: CellInfoWcdma): CellTower {
        val radioType = "wcdma"
        val mobileCountryCode = cellInfoWcdma.cellIdentity.mcc
        val mobileNetworkCode = cellInfoWcdma.cellIdentity.mnc
        val locationAreaCode = cellInfoWcdma.cellIdentity.lac
        val cellId = cellInfoWcdma.cellIdentity.cid
        val signalStrength = cellInfoWcdma.cellSignalStrength.dbm

        return CellTower(
            radioType,
            mobileCountryCode,
            mobileNetworkCode,
            locationAreaCode,
            cellId,
            signalStrength
        )
    }
}