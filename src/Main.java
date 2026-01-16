import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        FlowerListManagement manager = new FlowerListManagement();

        try {
            manager.readFromTextFile(Settings.getOriginalFilePath(), Settings.getDelimiter());
            manager.sortFlowers();
            for (Plant plant : manager.getAllPlants()) {
                System.out.println(plant.getWateringInfo());
            }
        } catch (PlantException e) {
            System.err.println("Error: " + e.getMessage());
        }

        try {

            manager.addPlant(new Plant("Rose", "red", LocalDate.of(2025, 8, 14), LocalDate.of(2025, 8, 14), 7));

            for (int i = 1; i <= 10; i++) {
                manager.addPlant(new Plant(
                        "Tulip",
                        "Tulip for sale" + i,
                        LocalDate.now(),
                        LocalDate.now(),
                        14
                ));
            }

            manager.removePlant(2);
            manager.sortFlowers();
            manager.writeToTextFile(Settings.getNewFilePath(), Settings.getDelimiter());

            System.out.println("The writing to the new file was also successful.");

        } catch (PlantException e) {
            System.err.println("Error while writing or editing: " + e.getMessage());
        }
    }
}
