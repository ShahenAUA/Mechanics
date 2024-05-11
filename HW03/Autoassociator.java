import java.util.Arrays;

public class Autoassociator {
    private int weights[][];
    private int trainingCapacity;

    public Autoassociator(CourseArray courses) {
        int numNeurons = courses.length();
        weights = new int[numNeurons][numNeurons];
        trainingCapacity = numNeurons;
    }

    public int getTrainingCapacity() {
        return trainingCapacity;
    }

    public void training(int pattern[]) {
        for (int i = 0; i < pattern.length; i++) {
			for (int j = 0; j < pattern.length; j++) {
				if (i != j) {
					weights[i][j] += pattern[i] * pattern[j];
				}
			}
		}
    }

    public int unitUpdate(int neurons[]) {
        int index = (int) (Math.random() * neurons.length);
        int sum = 0;
        for (int i = 0; i < neurons.length; i++) {
            sum += weights[index][i] * neurons[i];
        }
        neurons[index] = sum >= 0 ? 1 : -1;
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
