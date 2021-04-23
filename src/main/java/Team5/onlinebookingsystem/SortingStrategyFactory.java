package Team5.onlinebookingsystem;

public class SortingStrategyFactory {

    public SortingStrategy getStrategy(String type) {
        if (type.equals("Sort by Price Ascending")) {
            return new SortByPriceAscending();
        }
        else if (type.equals("Sort by Price Descending")) {
            return new SortByPriceDescending();
        }
        return null;
    }
}
