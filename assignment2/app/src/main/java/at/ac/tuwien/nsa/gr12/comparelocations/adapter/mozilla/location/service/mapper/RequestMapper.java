package at.ac.tuwien.nsa.gr12.comparelocations.adapter.mozilla.location.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

import at.ac.tuwien.nsa.gr12.comparelocations.adapter.mozilla.location.service.model.MLSCellTower;
import at.ac.tuwien.nsa.gr12.comparelocations.adapter.mozilla.location.service.model.MLSWifiAccessPoint;
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.AccessPoint;
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.CellTower;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface RequestMapper {

    List<MLSWifiAccessPoint> mapAccessPoints(List<AccessPoint> accessPoints);

    List<MLSCellTower> mapCellTowers(List<CellTower> cellTowers);
}
