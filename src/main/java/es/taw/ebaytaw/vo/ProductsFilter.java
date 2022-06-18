package es.taw.ebaytaw.vo;

public class ProductsFilter {
    private String titleAndDescription;
    private String[] categoriesArray;

    public ProductsFilter() {
        this.titleAndDescription = "";
    }

    public String getTitleAndDescription() {
        return titleAndDescription;
    }

    public void setTitleAndDescription(String titleAndDescription) {
        this.titleAndDescription = titleAndDescription;
    }

    public String[] getCategoriesArray() {
        return categoriesArray;
    }

    public void setCategoriesArray(String[] categoriesArray) {
        this.categoriesArray = categoriesArray;
    }
}
