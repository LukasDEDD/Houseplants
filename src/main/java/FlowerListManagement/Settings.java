package FlowerListManagement;


public final class Settings {

    private static final String ORIGINAL_FILE_PATH = "resources/PlantList.txt";

    private static final String NEW_FILE_PATH = "resources/NewPlantList.txt";

    private static final String DELIMITER = "\t";

    private Settings() {
    }

    public static String getOriginalFilePath() {
        return ORIGINAL_FILE_PATH;
    }

    public static String getNewFilePath() {
        return NEW_FILE_PATH;
    }

    public static String getDelimiter() {
        return DELIMITER;
    }
}