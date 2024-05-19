import java.util.Arrays;

public class Autoassociator {
    private int weights[][];
    private int trainingCapacity;

    public Autoassociator(CourseArray courses) {
        int numNeurons = courses.length();
        weights = new int[numNeurons][numNeurons];
        trainingCapacity = (int) (0.14 * (numNeurons + 1));
    }

    public int getTrainingCapacity() {
        return trainingCapacity;
    }

    public void training(int pattern[]) {
        if (pattern.length == weights.length && trainingCapacity > 0) {
            int prod;
            for (int i = 1; i < pattern.length; i++)
                for (int j = 1; j < pattern.length; j++) {
                    if (i != j) {
                        prod = pattern[i] * pattern[j];
                        weights[i][j] += prod;
                        weights[j][i] += prod;
                    }
                }
            trainingCapacity--;
        }
    }

    public void initialTraining() {
        int[][] patterns = {
            {1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, // Slot 0
            {-1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, // Slot 1
            {-1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, // Slot 2
            {-1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, // Slot 4
            {-1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, // Slot 6
            {-1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, // Slot 7
            {-1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, // Slot 8
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, // Slot 9
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1}, // Slot 10
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1}, // Slot 13
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1}, // Slot 14
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1}, // Slot 16
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1}, // Slot 17
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1}, // Slot 18
        };
        for (int[] is : patterns) {
            training(is);
        }
    }

    public void printWeights() {
        System.out.println("Weights:");
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[i].length; j++) {
                System.out.print(weights[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int unitUpdate(int neurons[]) {
        int index = (int) (Math.random() * neurons.length);
        unitUpdate(neurons, index);
        return index;
    }

    public void unitUpdate(int neurons[], int index) {
        int sum = 0;
        for (int i = 0; i < neurons.length; i++) {
            sum += weights[index][i] * neurons[i];
        }
        neurons[index] = sum >= 0 ? 1 : -1;
    }

    public void chainUpdate(int neurons[], int steps) {
        for (int i = 0; i < steps; i++) {
            int index = unitUpdate(neurons);
            unitUpdate(neurons, index);
        }
    }

    public void fullUpdate(int neurons[]) {
        boolean unchanged = false;
        while (!unchanged) {
            int[] oldState = Arrays.copyOf(neurons, neurons.length);
            chainUpdate(neurons, neurons.length);
            unchanged = Arrays.equals(oldState, neurons);
        }
    }
}
