package at.ac.tuwien.nsa.gr12.comparelocations.adapter.mozilla.location.service.model

class MLSRequest(
    val wifiAccessPoints: List<MLSWifiAccessPoint>,
    val cellTowers: List<MLSCellTower>
)