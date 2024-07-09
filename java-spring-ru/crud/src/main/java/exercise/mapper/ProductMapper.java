package exercise.mapper;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.model.Product;
import org.mapstruct.*;

// BEGIN
@Mapper(uses = {JsonNullableMapper.class, ReferenceMapper.class},
        componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public abstract class ProductMapper {
    @Mapping(target = "category", source = "categoryId")
    public abstract Product map(ProductCreateDTO data);

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")
    public abstract ProductDTO map(Product model);

    @Mapping(target = "category", source = "categoryId")
    public abstract void update(ProductUpdateDTO data, @MappingTarget Product model);
}
// END
