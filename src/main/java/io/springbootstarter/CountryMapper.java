package cam.walmart.payment.model.mapper;

import cam.walmart.payment.model.entity.Country;
import cam.walmart.payment.model.response.CountryResponse;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Slf4j
@Mapper(componentModel = "spring")
public abstract class CountryMapper {

    @Mappings(
            @Mapping(source = "countryName", target = "countryId"))
    public abstract List<CountryResponse> mapCountryToCountryResponse(List<Country> country);
}
