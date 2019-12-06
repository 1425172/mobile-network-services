package at.ac.tuwien.nsa.gr12.comparelocations.adapter.mail.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import at.ac.tuwien.nsa.gr12.comparelocations.adapter.mail.model.ReportMailEntitiy;
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Report;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface MailMapper {

    @Mappings({
            @Mapping(target = "distance", source = ".", qualifiedByName = "distance")
    })
    ReportMailEntitiy map(Report report);


    @Named("distance")
    default double distance(Report report) {
        return report.distance();
    }
}
