************************************************************************
file with basedata            : md347_.bas
initial value random generator: 1771398222
************************************************************************
projects                      :  1
jobs (incl. supersource/sink ):  22
horizon                       :  154
RESOURCES
  - renewable                 :  2   R
  - nonrenewable              :  2   N
  - doubly constrained        :  0   D
************************************************************************
PROJECT INFORMATION:
pronr.  #jobs rel.date duedate tardcost  MPM-Time
    1     20      0       21        5       21
************************************************************************
PRECEDENCE RELATIONS:
jobnr.    #modes  #successors   successors
   1        1          3           2   3   4
   2        3          2           7  11
   3        3          3           6   9  21
   4        3          3           5   6  15
   5        3          3          13  17  19
   6        3          3          10  12  16
   7        3          1           8
   8        3          3          12  14  16
   9        3          3          10  12  15
  10        3          1          13
  11        3          2          13  18
  12        3          1          18
  13        3          1          20
  14        3          3          18  20  21
  15        3          2          17  19
  16        3          1          17
  17        3          1          20
  18        3          1          19
  19        3          1          22
  20        3          1          22
  21        3          1          22
  22        1          0        
************************************************************************
REQUESTS/DURATIONS:
jobnr. mode duration  R 1  R 2  N 1  N 2
------------------------------------------------------------------------
  1      1     0       0    0    0    0
  2      1     5       8    0    5    0
         2     6       0    8    0    2
         3    10       0    3    3    0
  3      1     2       8    0    6    0
         2     7       6    0    0    8
         3    10       6    0    6    0
  4      1     4       0    6    0    6
         2     7       0    6    0    1
         3     7       0    6    4    0
  5      1     6       0    8   10    0
         2     7       0    6   10    0
         3     9       0    4    0    8
  6      1     4       0    1    0    4
         2     5       7    0   10    0
         3     8       4    0    0    4
  7      1     1       0    5    3    0
         2     2       0    3    3    0
         3     3       9    0    3    0
  8      1     2       1    0    0    9
         2     4       0    5    0    2
         3     4       0    3    0    5
  9      1     4       6    0    3    0
         2     6       0    4    0    3
         3    10       0    3    0    2
 10      1     1       0   10    0    3
         2     8       4    0    9    0
         3     8       0    4    9    0
 11      1     7       5    0    5    0
         2     9       0    7    2    0
         3    10       0    6    0   10
 12      1     1       3    0   10    0
         2     3       0   10    0    6
         3     6       0    9   10    0
 13      1     1       0    8    6    0
         2     2       0    7    3    0
         3     2       6    0    5    0
 14      1     3       0    6    0    6
         2     8       4    0    0    5
         3    10       2    0    8    0
 15      1     2       2    0    0    9
         2     3       0    2    7    0
         3     8       0    2    0    9
 16      1     5       4    0    3    0
         2     8       0    6    0   10
         3    10       0    5    0   10
 17      1     3       0    5    8    0
         2     7       0    4    0    1
         3     8       0    3    7    0
 18      1     1       3    0    4    0
         2     4       0    3    2    0
         3    10       2    0    0    3
 19      1     2       2    0    0    5
         2     6       1    0    0    3
         3     7       0   10    0    2
 20      1     5       0    3    6    0
         2     7       7    0    5    0
         3     9       6    0    4    0
 21      1     2      10    0    6    0
         2     5       0    1    6    0
         3     5       0    1    0    3
 22      1     0       0    0    0    0
************************************************************************
RESOURCEAVAILABILITIES:
  R 1  R 2  N 1  N 2
   22   26  113   96
************************************************************************
