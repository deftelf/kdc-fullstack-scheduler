************************************************************************
file with basedata            : md363_.bas
initial value random generator: 1783027904
************************************************************************
projects                      :  1
jobs (incl. supersource/sink ):  22
horizon                       :  160
RESOURCES
  - renewable                 :  2   R
  - nonrenewable              :  2   N
  - doubly constrained        :  0   D
************************************************************************
PROJECT INFORMATION:
pronr.  #jobs rel.date duedate tardcost  MPM-Time
    1     20      0       23       17       23
************************************************************************
PRECEDENCE RELATIONS:
jobnr.    #modes  #successors   successors
   1        1          3           2   3   4
   2        3          2           5  11
   3        3          2           7   9
   4        3          3           6   8  20
   5        3          3           7  10  21
   6        3          3          10  12  15
   7        3          2          15  17
   8        3          3           9  10  13
   9        3          2          12  14
  10        3          1          17
  11        3          3          12  15  21
  12        3          1          16
  13        3          3          16  17  18
  14        3          2          18  19
  15        3          1          16
  16        3          1          19
  17        3          1          19
  18        3          1          21
  19        3          1          22
  20        3          1          22
  21        3          1          22
  22        1          0        
************************************************************************
REQUESTS/DURATIONS:
jobnr. mode duration  R 1  R 2  N 1  N 2
------------------------------------------------------------------------
  1      1     0       0    0    0    0
  2      1     2       3    0    8    7
         2     3       0    9    8    7
         3     7       0    7    8    6
  3      1     2       0    4    9    7
         2     4       7    0    8    5
         3     8       0    4    8    4
  4      1     5       0    6    4    8
         2     6       8    0    4    8
         3     9       0    1    1    7
  5      1     7       5    0    5    4
         2     9       0    6    3    4
         3    10       4    0    2    3
  6      1     3       6    0    8    6
         2     9       0    7    6    6
         3    10       3    0    5    6
  7      1     4       0    3    7    7
         2     8       0    1    6    3
         3    10       7    0    3    2
  8      1     2       0    5    8    5
         2     2       0    5    9    4
         3     4       8    0    4    2
  9      1     3       0    7    3    8
         2     4      10    0    3    8
         3     7       8    0    3    7
 10      1     4       4    0    7    2
         2     4       0    9    7    2
         3     5       5    0    6    2
 11      1     5       0    5    9    7
         2     6       9    0    6    6
         3     8       6    0    5    5
 12      1     2       0    6    9    9
         2     5       0    4    7    8
         3     9       0    3    4    8
 13      1     2       0    4   10    4
         2     3       0    3    6    3
         3     7       8    0    5    3
 14      1     2       8    0    9    9
         2     6       0    5    9    6
         3     7       8    0    9    4
 15      1     1      10    0    8    5
         2     1       0    5    8    4
         3     6       9    0    8    3
 16      1     3       0    5    5    6
         2     4       7    0    5    3
         3    10       7    0    4    2
 17      1     1       0   10   10    7
         2     4       0    6    9    5
         3    10       0    4    8    5
 18      1     1       0    7    7    2
         2     3       7    0    6    2
         3     7       0    7    5    1
 19      1     6       0    7    4    2
         2     8       8    0    2    2
         3     8       0    7    1    2
 20      1     7       0    2    9    2
         2     8       6    0    8    1
         3     9       6    0    7    1
 21      1     5       7    0    3   10
         2     7       6    0    2    7
         3     9       0    5    1    6
 22      1     0       0    0    0    0
************************************************************************
RESOURCEAVAILABILITIES:
  R 1  R 2  N 1  N 2
   27   24  120   98
************************************************************************
