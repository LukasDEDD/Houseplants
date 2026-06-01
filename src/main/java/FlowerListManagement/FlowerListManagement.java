package FlowerListManagement;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class FlowerListManagement {

    private List<Plant> plants = new ArrayList<>();

    public Plant getPlant(int index) {
        return plants.get(index);
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public void removePlant(int index) throws PlantException {
        if (index < 0 || index >= plants.size()) {
            throw new PlantException("Invalid index: " + index);
        }
        plants.remove(index);
        System.out.println("Plant number" + (index + 1) + " was sold");
    }

    public void addAllPlants(List<Plant> plants) {
        this.plants.addAll(plants);
    }

    public List<Plant> getAllPlants() {
        return new ArrayList<>(plants);
    }

    public List<Plant> getPlants() {
        return new ArrayList<>(plants);
    }

    public void readFromTextFile(String filePath, String delimiter) throws PlantException {
        int lineNumber = 0;
        String[] parts = null;
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filePath)))) {
            plants.clear();
            while (scanner.hasNextLine()) {
                lineNumber++;
                String record = scanner.nextLine();
                if (!record.isEmpty()) {
                    parts = record.split(delimiter);
                    try {

                        String name = parts[0].trim();
                        String notes = parts[1].trim();
                        Integer frequency = Integer.parseInt(parts[2].trim());
                        LocalDate watering = LocalDate.parse(parts[3].trim());
                        LocalDate planted = LocalDate.parse(parts[4].trim());

                        Plant plant = new Plant(name, notes, planted, watering, frequency);
                        plants.add(plant);
                    } catch (Exception e) {
                        throw new PlantException("Error on line" + lineNumber + ": " + e.getMessage(), e);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new PlantException("File" + filePath + " cannot be opened:" + e.getLocalizedMessage());
        }
        System.out.println("Line " + lineNumber + ": " + Arrays.toString(parts));
    }

    public void writeToTextFile(String filePath, String delimiter) throws PlantException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {
            for (Plant plant : plants) {
                writer.println(plant.toTextRecord(delimiter));
            }
        } catch (IOException e) {
            throw new PlantException("Error writing to file: " + e.getMessage());
        }
    }

    public List<Plant> getFlowersToWater() {
        List<Plant> flowersToWater = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (Plant flower : plants) {
            LocalDate nextWatering = flower.getWatering().plusDays(flower.getFrequencyOfWatering());
            if (!nextWatering.isAfter(today)) {
                flowersToWater.add(flower);
            }
        }

        return flowersToWater;
    }

    public void sortFlowers() {
        plants.sort(Comparator.comparing(Plant::getName).thenComparing(Plant::getWatering));
    }
}
