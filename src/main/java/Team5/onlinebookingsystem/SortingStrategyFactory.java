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
    private SortingStrategy priceAscendingStrategy;
    private SortingStrategy priceDescendingStrategy;
    private SortingStrategy departureTimeAscendingStrategy;
    private SortingStrategy departureTimeDescendingStrategy;
    private SortingStrategy durationStrategy;

    // Factory returns the appropriate object as per the requested "type"
    public SortingStrategy getStrategy(String type) {
        if (type.equals("Sort by Price Ascending")) {
            // Ensuring only single instance creation
            if(priceAscendingStrategy == null){
                priceAscendingStrategy = new SortByPriceAscending();
            }
            return priceAscendingStrategy;
        }

        else if (type.equals("Sort by Price Descending")) {
            // Ensuring only single instance creation
            if(priceDescendingStrategy == null){
                priceDescendingStrategy = new SortByPriceDescending();
            }
            return priceDescendingStrategy;
        }
        else if (type.equals("Sort by Departure Time Ascending")) {
            // Ensuring only single instance creation
            if(departureTimeAscendingStrategy == null){
                departureTimeAscendingStrategy = new SortByDepartureTimeAscending();
            }
            return departureTimeAscendingStrategy;
        }
        else if (type.equals("Sort by Departure Time Descending")) {
            // Ensuring only single instance creation
            if(departureTimeDescendingStrategy == null){
                departureTimeDescendingStrategy = new SortByDepartureTimeDescending();
            }
            return departureTimeDescendingStrategy;
        }
        else if (type.equals("Sort by Duration")) {
            // Ensuring only single instance creation
            if(durationStrategy == null){
                durationStrategy = new SortByDuration();
            }
            return durationStrategy;
        }
        return null;
    }
}