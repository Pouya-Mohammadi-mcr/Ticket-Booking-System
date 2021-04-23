package Team5.onlinebookingsystem;

public class SortingStrategyFactory {

    // Private ctor and private field for singleton pattern
    private static SortingStrategyFactory factory;

    private SortingStrategyFactory(){}

    // Lazy Initialization for the Singleton Factory
    // Ensures that only one instance is created and that too only when
    //  an instance is requested(unlike Eager initialization)
    public static SortingStrategyFactory getInstance(){
        if(factory == null){
            factory = new SortingStrategyFactory();
        }
        return factory;
    }

    // Factory returns the appropriate object as per the requested "type"
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
