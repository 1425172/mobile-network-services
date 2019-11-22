package at.ac.tuwien.nsa.gr12.comparelocations.adapter.mozilla.location.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import at.ac.tuwien.nsa.gr12.comparelocations.adapter.mozilla.location.service.model.MLSResponse;
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Location;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ResponseMapper {

    @Mappings({
            @Mapping(target = "latitude", source = "location.lat"),
            @Mapping(target = "longitude", source = "location.lng"),
            @Mapping(target = "accuracy", source = "accuracy")
    })
    Location map(MLSResponse response);
}
