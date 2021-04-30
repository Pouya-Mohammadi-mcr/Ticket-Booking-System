package Team5.onlinebookingsystem;

public class SortingStrategyFactory {

    // Private ctor and private field for singleton pattern
    private SortingStrategyFactory(){}
    private static SortingStrategyFactory factory;

    // Lazy Initialization for the Singleton Factory
    // Ensures that only one instance is created and that too only when
    //  an instance is requested(unlike Eager initialization)
    public static SortingStrategyFactory getInstance(){
        if(factory == null){
            factory = new SortingStrategyFactory();
        }
        return factory;
    }

    // Creating and storing only single instances of the two strategies as once created,
    // the same instance can be returned when an instance is required from the factory
    private SortingStrategy ascendingStrategy;
    private SortingStrategy descendingStrategy;

    // Factory returns the appropriate object as per the requested "type"
    public SortingStrategy getStrategy(String type) {
        if (type.equals("Sort by Price Ascending")) {
            // Ensuring only single instance creation
            if(ascendingStrategy == null){
                ascendingStrategy = new SortByPriceAscending();
            }
            return ascendingStrategy;
        }

        else if (type.equals("Sort by Price Descending")) {
            // Ensuring only single instance creation
            if(descendingStrategy == null){
                descendingStrategy = new SortByPriceDescending();
            }
            return descendingStrategy;
        }
        return null;
    }
}
