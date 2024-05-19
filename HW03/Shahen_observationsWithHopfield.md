ShahenHovakimyan

After implementing Hopfield network and adjusting slot assignment using autoassociator weights, the results become much worse, but that was sort of expected because the autoassociator is not trained initially.
Now 7 iters with 11 shifts result is as follows:
Shift = 11      Min clashes = 8796      at step 1
And there are no clash free-slots.

So lets try to take the best result from initial observations and train the hopfield network on that pattern fully.
For that I will try to convert the following to trainable pattern:
Shift = 11      Min clashes = 118       at step 7
Exam    Slot    Clashes
1       9       2
2       2       1
3       9       2
4       11      0
5       10      0
6       2       0
7       2       1
8       3       1
9       18      3
10      12      0
...
163     17      1
164     9       0
165     16      0
166     8       0
167     17      0
168     3       0
169     16      0
170     14      0
171     9       0
172     5       0
173     15      3
174     6       2
175     7       0
176     18      0
177     13      0
178     11      3
179     18      2
180     18      0
181     2       0

which converted to clash free patterns will look like this:
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

now after manually starting the previous implementation and training the network manually with all the slots, after trained start we still get a worse result:
Shift = 11      Min clashes = 118       at step 7
Training finished with random slot: 0
Training finished with random slot: 1
Training finished with random slot: 2
Training finished with random slot: 4
Training finished with random slot: 6
Training finished with random slot: 7
Training finished with random slot: 8
Training finished with random slot: 9
Training finished with random slot: 10
Training finished with random slot: 13
Training finished with random slot: 14
Training finished with random slot: 16
Training finished with random slot: 17
Training finished with random slot: 18
Shift = 11      Min clashes = 8954      at step 1

In code, the current implementation is that the start button runs the iterations without autoassociator or any training from scratch, the train button trains the given or random slot and train start runs start with autoassociator.

I may assume that there may be code issues but further investigation will require a lot of time which is not achievable by project deadline. We can try to use the knowledge from mechanics, where potential energy reflects how much energy a system has depending on how it's arranged, here in a Hopfield network, the energy of a state is linked to how the network is set up, so we can find clash free patterns and for training, the goal will be to make these stable setups have as little energy as possible since they're the best solutions.
However, this idea still needs further investigations and trial and error processes to go through.

I will try to outline the general ideas that I have strategized that may provide better performance:
1. After each iteration of course slot assignments, we can use the Hopfield network to refine the slot assignments by applying the fullUpdate method to the current state of slot assignments to leverage the network's learned patterns. Also we can try to periodically retrain the Hopfield network with the updated slot assignments to ensure that it captures the evolving patterns of reduced clashes.
2. We can try to implement a feedback loop where the number of clashes after each iteration is analyzed and used to adjust the training patterns or the network’s parameters.
3. And, of course, the training with initial clash free patterns startegy, which I have tried to utilize above. 