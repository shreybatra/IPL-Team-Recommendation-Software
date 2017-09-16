# BEAT - Best And Efficient Auction Of Teams
IPL player recommendation software

PROJECT OVERVIEW :
Our project is based on providing the Team Managers of IPL with a tool to shortlist their squad in the auction event. Our software takes the input from the Team Manager (user), and provides them with the best players in their price range.

ALGORITHMS USED :
1 Self - Enhanced Clustering Algorithm – Lloyd&#39;s Algorithm (k - Means)
2 Greedy Algorithm – 0-1 Knapsack

SOFTWARES USED :
1 Netbeans (Java)
2 Python (for crawling)

USER-DEFINED CLASSES :
1 Team – Contains details of a team, their home-ground and their comparative size of the ground.
2 Player – Contains details of a player – name, its every batting and bowling statistic and his base selling price for the auction.
3 Comparator BowlEcoComparator – to compare the objects of class Player based on their Bowling Economy.
4 Comparator BowlWktComparator – to compare the objects of class Player based on their wickets per innings.
5 Comparator BatFourComparator – to compare the objects of class Player based on the number of Fours they have have scored.
6 Comparator BatAVGComparator – to compare the objects of class Player based on their Batting Average.
7 Kmean – class to define clustering functions based on different parameters.

FUNCTION IN K-MEANS CLASS :
1 Bol1 – It forms clusters based on the wickets per innings on the set of bowlers.
2 Bol2 – It forms clusters based on the economy of the bowlers.
3 Bat1 – It forms clusters based on the number of sixes scored by the batsmen.
4 Bat2 – It forms clusters based on the strike rate of each player.

INPUT FILES :
1 final1.txt – contains the statistics of each T20 player of Australia, Bangladesh, England, India, New Zealand, South Africa, Sri Lanka, West Indies and Zimbabwe.
2 final2.txt – contains the names of each player of the above mentioned teams.
3 teams.txt – contains the names of the different teams of IPL, their home-ground and the size of their home-ground.
4 final1.txt &amp; final2.txt are obtained via crawling in python.

GAME PLAY : The software is started by the START button, which performs reading from the different files and places the data into different data structures. Then, after the user has given the input consisting of his team, the condition of the pitch in that IPL season and the Total Fund that the Franchise can raise, the software forms different clusters and
based on the input provided, selects the best batsmen and bowlers for the team. The players were added in the list as per knapsack
algorithm.

K-MEANS CLUSTERING ALGORITHM :
It takes k centroids (3 in our case) and takes out the distance of each data item from the centroid. The data item is then put in the list in which its distance from the centroid is minimum. This takes place for each of the data Item. Hence the complexity of clustering the data once takes O(kn) time. If in our case, the clustering algorithm runs for i times, the total complexity is O(nki).

Working of each clustering function:
Each function has different parameters to make clusters. The algorithm first makes clusters based on 3 centroids. The first 3 initial
centroids are the first 3 player data on that parameter. After the clusters are made, the algorithm then takes the average of the
parameter in each centroid and compares it with the old centroids. If found same, the loop breaks, else the algorithm runs again on the
newly found centroids.

REFERENCES :
1 www.espncricinfo.com
2 www.iplt20.com
3 m.cricbuzz.com›ipl2017
