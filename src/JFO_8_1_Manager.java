import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class JFO_8_1_Manager {
        private List<JFo_8_Team> teams;
        private List<JFO_8_1_Game> games;
        private int consecutiveColdWeeks;
    
        public JFO_8_1_Manager() {
            teams = new ArrayList<>();
            games = new ArrayList<>();
            consecutiveColdWeeks = 0;
        }
    
        public void addTeam(JFo_8_Team team) {
            teams.add(team);
        }
    
        public void scheduleGames(int temperature) {
            if (temperature <= 32) {
                consecutiveColdWeeks++;
                System.out.println("Too cold to play.");
            } else {
                consecutiveColdWeeks = 0;
                Random random = new Random();
                int numGames = 2;
                for (int i = 0; i < numGames; i++) {
                    JFo_8_Team awayTeam = getRandomTeam();
                    JFo_8_Team homeTeam = getRandomTeam();
                    while (awayTeam == homeTeam) {
                        homeTeam = getRandomTeam();
                    }
                    int awayScore = random.nextInt(6);
                    int homeScore = random.nextInt(6);
                    JFO_8_1_Game game = new JFO_8_1_Game(awayTeam, homeTeam, awayScore, homeScore, temperature);
                    games.add(game);
                }
            }
            if (consecutiveColdWeeks >= 3) {
                System.out.println("Season is over\n");
                printStats();
                System.exit(0);
            }
        }
    
        private JFo_8_Team getRandomTeam() {
            Random random = new Random();
            int randomIndex = random.nextInt(teams.size());
            return teams.get(randomIndex);
        }
    
        public void printStats() {
            System.out.println("*********RESULTS*********\n");
            for (JFo_8_Team team : teams) {
                System.out.println(team.getName());
                System.out.println("Wins: " + team.getWins() + ", Losses: " + team.getLosses() + ", Ties: " + team.getTies());
                System.out.println("Points Scored: " + team.getGoalsScored() + ", Points Allowed: " + team.getGoalsAllowed());
                System.out.println();
            }
            for (JFO_8_1_Game game : games) {
                System.out.println("Game #" + (games.indexOf(game) + 1));
                System.out.println("Temperature: " + game.getTemperature());
                System.out.println("Away Team: " + game.getAwayTeam().getName() + ", " + game.getAwayScore());
                System.out.println("Home Team: " + game.getHomeTeam().getName() + ", " + game.getHomeScore());
                System.out.println();
            }
        }
    
        public static void main(String[] args) {
            JFO_8_1_Manager leagueManager = new JFO_8_1_Manager();
            Scanner input = new Scanner(System.in);
            int week = 1;
            while (true) {
                System.out.println("Enter temperature for Week " + week + ": ");
                int temperature = input.nextInt();
                leagueManager.scheduleGames(temperature);
                week++;
                input.close();
            } 
        } 
}