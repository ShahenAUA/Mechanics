Shahen Hovakimyan
Observations for yor-f-83.stu

1 iteration:
Shift = 1       Min clashes = 8988      at step 1
Shift = 3       Min clashes = 6936      at step 1
Shift = 5       Min clashes = 5228      at step 1
Shift = 7       Min clashes = 3948      at step 1
Shift = 9       Min clashes = 2948      at step 1
Shift = 11      Min clashes = 2314      at step 1
Shift = 13      Min clashes = 1592      at step 1
Shift = 15      Min clashes = 1002      at step 1
Shift = 17      Min clashes = 576       at step 1
Shift = 19      Min clashes = 324       at step 1

2 iterations:
Shift = 1       Min clashes = 8792      at step 2
Shift = 3       Min clashes = 5064      at step 2
Shift = 5       Min clashes = 2814      at step 2
Shift = 7       Min clashes = 1486      at step 2
Shift = 9       Min clashes = 512       at step 2
Shift = 11      Min clashes = 492       at step 2
Shift = 13      Min clashes = 468       at step 2
Shift = 15      Min clashes = 440       at step 2
Shift = 17      Min clashes = 422       at step 2
Shift = 19      Min clashes = 324       at step 1

If we increase the number of iterations for 19 shifts we will still get the same result as for one iterations:
Shift = 19      Min clashes = 324       at step 1 (5 iters)
Shift = 19      Min clashes = 324       at step 1 (8 iters)
Shift = 19      Min clashes = 324       at step 1 (10 iters)

18 shifts:
Shift = 18      Min clashes = 142       at step 8
Shift = 18      Min clashes = 404       at step 1
Shift = 18      Min clashes = 386       at step 2
Shift = 18      Min clashes = 322       at step 3
Shift = 18      Min clashes = 282       at step 4
Shift = 18      Min clashes = 204       at step 5
Shift = 18      Min clashes = 162       at step 6
Shift = 18      Min clashes = 162       at step 6
Shift = 18      Min clashes = 142       at step 8
Shift = 18      Min clashes = 142       at step 8
Shift = 18      Min clashes = 142       at step 8
Shift = 18      Min clashes = 142       at step 8

Even after manually continuing with 18 shifts, we get the following results:
Remaining clashes: 146
Remaining clashes: 176
Remaining clashes: 182
Remaining clashes: 188
Remaining clashes: 176
Remaining clashes: 162
Remaining clashes: 182
Remaining clashes: 200
Remaining clashes: 152
Remaining clashes: 158
Remaining clashes: 142
Remaining clashes: 162
Remaining clashes: 158
Remaining clashes: 144
Remaining clashes: 142
Remaining clashes: 162
Remaining clashes: 144
Remaining clashes: 138
Remaining clashes: 140
Remaining clashes: 178
Remaining clashes: 132
Remaining clashes: 192
Remaining clashes: 122
Remaining clashes: 172
Remaining clashes: 160

So, best we have achieved so far is 122 clashes (by doing 18 shift 8 iters and then manually continuing 23 iterations)

We can achieve 122 clashes also by doing 10 iters with 17 shifts and then manually continuing 13 iterations
Shift = 17      Min clashes = 138       at step 10
Remaining clashes: 162
Remaining clashes: 168
Remaining clashes: 148
Remaining clashes: 156
Remaining clashes: 136
Remaining clashes: 172
Remaining clashes: 180
Remaining clashes: 160
Remaining clashes: 152
Remaining clashes: 190
Remaining clashes: 154
Remaining clashes: 160
Remaining clashes: 122

By doing even number of shifts we notice that results are worse:
Shift = 2       Min clashes = 8014      at step 1
Shift = 4       Min clashes = 5802      at step 1
Shift = 6       Min clashes = 4558      at step 1
Shift = 8       Min clashes = 3384      at step 1
Shift = 10      Min clashes = 2538      at step 1
Shift = 12      Min clashes = 1926      at step 1
Shift = 14      Min clashes = 1308      at step 1
Shift = 16      Min clashes = 770       at step 1
Shift = 18      Min clashes = 404       at step 1

But if we do 10 iterations they are not that bad:
Shift = 2       Min clashes = 1228      at step 10
Shift = 4       Min clashes = 376       at step 10
Shift = 6       Min clashes = 310       at step 10
Shift = 8       Min clashes = 198       at step 10
Shift = 10      Min clashes = 172       at step 9
Shift = 12      Min clashes = 168       at step 7
Shift = 14      Min clashes = 166       at step 9
Shift = 16      Min clashes = 184       at step 8
Shift = 18      Min clashes = 142       at step 8

Just to compare here are 10 iterations of odd numbers of shifts:
Shift = 1       Min clashes = 6308      at step 10
Shift = 3       Min clashes = 594       at step 10
Shift = 5       Min clashes = 408       at step 8
Shift = 7       Min clashes = 228       at step 8
Shift = 9       Min clashes = 210       at step 10
Shift = 11      Min clashes = 118       at step 7
Shift = 13      Min clashes = 172       at step 7
Shift = 15      Min clashes = 144       at step 8
Shift = 17      Min clashes = 138       at step 10
Shift = 19      Min clashes = 324       at step 1

And using all these observations the best result was the 11 shift 7 steps with 118 clashes, so lets try to continue it using different shifts.
I have tried many combinations to continue from that point but all of them only increase the number of clashes.

Shift = 11      Min clashes = 118       at step 7
Remaining clashes: 176
Remaining clashes: 240
Remaining clashes: 272
Shift = 11      Min clashes = 118       at step 7
Remaining clashes: 126
Remaining clashes: 154
Remaining clashes: 198
Remaining clashes: 166
Remaining clashes: 174
Remaining clashes: 150
