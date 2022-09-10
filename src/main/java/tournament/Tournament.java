package tournament;

public class Tournament {

    /**
     * @param scorecard the scores in the tournament and is not null; {@code scorecard[i][j]} represents the score of Player i in Game j. Each row in this array has {@code sets * games} entries, and each entry has a value between 0 and 5 (limits inclusive).
     * @param sets the number of sets played in the tournament, {@code sets > 0}
     * @param games the number of games per set, {@code games > 0}
     * @return a list of players in order of their performance (best to worst)
     */
    public static int[] rankPlayers(int[][] scorecard, int sets, int games) {
        int[] playerRankings;
        // TODO: Your implementation goes here
        
        //Setups
        int playerNumber = scorecard.length();
        
        playerRankings = new int [playerNumber];
        for (int i = 0, i < playerNumber, i++) {
            playerRankings[i] = i; 
        }
        
        
        
        int[] playerScoresLastSet;
        playerScoresLastSet = new int [playerNumber];
        
        //go through the last couple of games in the last set, find each player's total score
        for(int i = (sets - 1) * games, i < sets * games, i++) {
            //add the scores one by one into each player's scores
            for(int j = 0, j < playerNumber, j++) {
                
                playerScoresLastSet[j] += scorecard[j][i];     
            }
        }
        

        //sort playerScoresLastSet, make playerRankings follow the sort
        for(int n = 0, n < playerNumber, n++) {   
        
            for(int i = 1, i < playerNumber, i++) {
                if(playerScoresLastSet[i] > playerScoresLastSet[i-1]) {
                    int temporaryScore = playerScoresLastSet[i-1];
                    playerScoresLastSet[i-1] = playerScoresLastSet[i];
                    playerScoresLastSet[i] = temporaryScore;
                    
                    int temporaryRanking = playerRankings[i-1];
                    playerRankings[i-1] = playerRankings[i];
                    playerRankings[i] = temporaryRanking;
                }  
            }
        }
        
        //After the previous step, all players should be ranked based on their last set's total score
        
        //Loop through the overall scores,
        //if find the same score, check their last digits, if the behind one's digit is bigger, swap place, if is smaller, stop comparing
        for(int n = 0, n < playerNumber, n++) {   
        
            for(int i = 1, i < playerNumber, i++) {
                if(playerScoresLastSet[i] == playerScoresLastSet[i-1]) {
                   
                    for(int j = sets * games - 1, j > 0, j--) {
                        if(scoreCard[playerRankings[i]][j] > scoreCard[playerRankings[i-1]][j]) {
                            int temporaryRanking = playerRankings[i-1];
                            playerRankings[i-1] = playerRankings[i];
                            playerRankings[i] = temporaryRanking;
                            
                            break;
                        } else if(scoreCard[playerRankings[i]][j] < scoreCard[playerRankings[i-1]][j]) {
                            break;
                        }
                    }
                    
                }  
            }
            
        }
        


        
        return playerRankings;
    }
    
}
