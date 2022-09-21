package za.co.wethinkcode.examples.server;

import za.co.wethinkcode.examples.server.player.Player;
import za.co.wethinkcode.examples.server.world.FirstWorld;

import java.util.ArrayList;
import java.util.Properties;

//manages server variables
//since the program uses multi threads, program need a class that allows for the different threads to access the same data
public class Manager {
    private ArrayList players;
    FirstWorld firstWorld;
    Properties properties;
    boolean bothTeamsPresent = false;
    boolean teamOneWon = false;
    boolean teamTwoWon = false;


    //when a new player joins, he is added to the player list
    public void addPlayers(Player player) {
        this.players.add(player);
    }

    //when the program starts, a world that all users will use must be set
    public void setFirstWorld(FirstWorld firstWorld) {
        this.firstWorld = firstWorld;
    }

    //every user needs to be able to get access to the current running world
    public  FirstWorld getFirstWorld() {
        return  firstWorld;
    }


    //manager needs to store the property file so all classes can access it
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    //classes need to be able to access property variable for configs
    public  Properties getProperties() {
        return  properties;
    }

    //when a player exits or disconnects, he will be removed
    public  void removePlayers(Player player) {
        players.remove(player);
    }

    //get the list of players currently in game
    public  ArrayList getPlayers() {
        return players;
    }

    //set the players list that all the players can access
    public void setServers(ArrayList serve) {
        players = serve;
    }

    public boolean isBothTeamsPresent() {
        return bothTeamsPresent;
    }

    public void setBothTeamsPresent(boolean bothTeamsPresentNew) {
        bothTeamsPresent = bothTeamsPresentNew;
    }


    public boolean isTeamOneHasWon() {
        return teamOneWon;
    }

    public void setTeamOneWon(boolean teamHasWon) {
        this.teamOneWon = teamHasWon;
    }


    public boolean isTeamTwoHasWon() {
        return teamTwoWon;
    }

    public void setTeamTwoWon(boolean teamHasWon) {
        this.teamTwoWon = teamHasWon;
    }

    public void checkTeamsPresent(){
        int teamone= 0;
        int teamtwo=0;
        for(int i = 0; i<players.size();i++){
           Player player = (Player) players.get(i);
           if (player.getTeam().contains("Team_One")){
               teamone = teamone +1;
           }
           else if(player.getTeam().contains("Team_Two")){
               teamtwo = teamtwo +1;
           }
        }

        if (teamone>0 && teamtwo>0){
            setBothTeamsPresent(true);

        }
    }


    public void checkTeamWon(){
        int teamone= 0;
        int teamtwo=0;
        if (bothTeamsPresent) {

            for (int i = 0; i < players.size(); i++) {
                Player player = (Player) players.get(i);
                if (player.getTeam().contains("Team_One")) {
                    teamone = teamone + 1;
                } else if (player.getTeam().contains("Team_Two")) {
                    teamtwo = teamtwo + 1;
                }
            }

            if (teamone >0 && teamone == 0) {
                setTeamOneWon(true);

            }
            else if (teamone ==0 && teamtwo > 0) {
                setTeamTwoWon(true);

            }
        }
    }


}
