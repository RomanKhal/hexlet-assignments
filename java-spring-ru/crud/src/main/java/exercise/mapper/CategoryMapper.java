package exercise.mapper;

import exercise.dto.CategoryCreateDTO;
import exercise.dto.CategoryDTO;
import exercise.model.Category;
import org.mapstruct.*;

// BEGIN
@Mapper(uses = {JsonNullableMapper.class, ReferenceMapper.class},
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
componentModel = MappingConstants.ComponentModel.SPRING,
unmappedTargetPolicy = ReportingPolicy.IGNORE)

public abstract class CategoryMapper {
//    @Mapping(target = "name")
    public abstract Category map(CategoryCreateDTO data);

//    @Mapping(target = "name")
    public abstract CategoryDTO map (Category entity);
}
// END
