package adapter;

/**
 * Created by sRJ Rivera on 1/28/2017.
 */
interface UpdateAuto {
    void updateOptionSetName(int keyToBeEdited,String optSetName, String newName);
    void updateOptionPrice(int keyToBeEdited, String optSetName, String optName, Float newPrice);
}
