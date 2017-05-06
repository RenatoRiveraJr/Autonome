package adapter;

/**
 * Created by sRJ Rivera on 1/28/2017.
 */
interface UpdateAuto {
    void updateOptionSetName(String modelName, String optSetName, String newName);
    void updateOptionPrice(String modelName, String optSetName, String opt, float newPrice);
}
