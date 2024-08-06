package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

// BEGIN
@Component
public class ProductSpecification {
    public Specification<Product> build(ProductParamsDTO params) {
        return withCategoryId(params.getCategoryId())
                .and(withTitleContains(params.getTitleCont()))
                .and(withPriceLesserThan(params.getPriceLt()))
                .and(withPriceGreaterThan(params.getPriceGt()))
                .and(withRatingGreaterThan(params.getRatingGt()));
    }

    private Specification<Product> withRatingGreaterThan(Double ratingGt) {
        return (root, query, criteriaBuilder) -> ratingGt == null ? criteriaBuilder.conjunction() :
                criteriaBuilder.ge(root.get("rating"), ratingGt);
    }

    private Specification<Product> withPriceGreaterThan(Integer priceGt) {
        return (root, query, criteriaBuilder) -> priceGt == null ? criteriaBuilder.conjunction() :
                criteriaBuilder.greaterThan(root.get("price"), priceGt);
    }

    private Specification<Product> withPriceLesserThan(Integer priceLt) {
        return (root, query, criteriaBuilder) -> priceLt == null ? criteriaBuilder.conjunction() :
                criteriaBuilder.le(root.get("price"), priceLt);
    }

    private Specification<Product> withCategoryId(Long categoryId) {
        return ((root, query, criteriaBuilder) -> categoryId == null ? criteriaBuilder.conjunction() :
                criteriaBuilder.equal(root.get("category").get("id"), categoryId));
    }

    private Specification<Product> withTitleContains(String titleCont) {
        return ((root, query, criteriaBuilder) -> titleCont == null ? criteriaBuilder.conjunction() :
                criteriaBuilder.like(root.get("title"), titleCont));
    }
}
// END
