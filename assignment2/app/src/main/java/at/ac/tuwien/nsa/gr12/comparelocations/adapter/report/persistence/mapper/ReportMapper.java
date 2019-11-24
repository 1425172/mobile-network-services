package at.ac.tuwien.nsa.gr12.comparelocations.adapter.report.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import at.ac.tuwien.nsa.gr12.comparelocations.adapter.report.persistence.entities.ReportEntity;
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Report;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ReportMapper {

    ReportEntity toEntity(Report report);

    @InheritInverseConfiguration
    Report toModel(ReportEntity entity);
}
