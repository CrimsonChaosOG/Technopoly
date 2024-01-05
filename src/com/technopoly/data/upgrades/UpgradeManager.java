package com.technopoly.data.upgrades;

import java.util.ArrayList;

import com.technopoly.data.Constants;
import com.technopoly.data.Player;
import com.technopoly.data.Constants.Upgrades;
import com.technopoly.data.board.BoardSquare;
import com.technopoly.data.effects.Effect;
import com.technopoly.data.effects.EffectType;

public class UpgradeManager {
	
	private static ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();
	
	public static void SetupUpgrades() {
		
		//Junior Team Upgrade
		Upgrade juniorTeam = new Upgrade("Junior Team",UpgradeType.JUNIOR_TEAM,Constants.Upgrades.Costs.JuniorTeam) {
			@Override
			public int onCalculateRent(int input) {
				return input + (input /2);
			};
			
			@Override
			public boolean canBuild(Player p, BoardSquare s) {
				boolean hasSenior = s.HasUpgrade(UpgradeType.SENIOR_TEAM);
				boolean hasMaxJuniors = s.HasUpgrade(UpgradeType.JUNIOR_TEAM, Constants.Upgrades.MaxJuniorTeams);
				
				return (!hasSenior && !hasMaxJuniors);
			}
		};
		upgrades.add(juniorTeam);
		
		//Senior Team Upgrade
		Upgrade seniorTeam = new Upgrade("Senior Team",UpgradeType.SENIOR_TEAM,Constants.Upgrades.Costs.SeniorTeam) {
			@Override
			public int onCalculateRent(int input) {
				return input * 3;
			};
			
			@Override
			public boolean canBuild(Player p, BoardSquare s) {
				//You can only build a senior team if this square has 3 junior teams and there doesn't already exist a senior team
				if(s.HasUpgrade(UpgradeType.JUNIOR_TEAM, Constants.Upgrades.MaxJuniorTeams) && !s.HasUpgrade(UpgradeType.SENIOR_TEAM)) {
					return true;
				}else {
					return false;
				}
			}
		};
		upgrades.add(seniorTeam);
		
		//Innovation Centre Upgrade
		Upgrade innovationCentre = new Upgrade("Innovation Centre", UpgradeType.INNOVATION_CENTRE,Constants.Upgrades.Costs.InnovationCentre) {
			@Override
			public void onPlayerStart(Player p, BoardSquare s) {
				p.AddEffect(EffectType.DOUBLE_UP, Constants.Upgrades.InnovationCentreTurns);
			}
		
		};
		upgrades.add(innovationCentre);
		
		// Data Centre Upgrade
		Upgrade dataCentre = new Upgrade("Data Centre",UpgradeType.DATA_CENTRE,Constants.Upgrades.Costs.DataCentre) {
			@Override
			public void onPlayerEnd(Player p, BoardSquare s) {
				p.AddEffect(EffectType.BIG_DATA, 3);
			}
		};
		upgrades.add(dataCentre);
		
		Upgrade securityTeam = new Upgrade("Security Team",UpgradeType.SECURITY_TEAM,Constants.Upgrades.Costs.SecurityTeam) {
			@Override
			public void onPlayerEnd(Player p, BoardSquare s) {
				p.AddEffect(EffectType.VPN, Constants.Upgrades.SecurityTeamTurns);
			};
			
			@Override
			public boolean canBuild(Player p, BoardSquare s) {
				if(s.HasUpgrade(UpgradeType.JUNIOR_TEAM, Constants.Upgrades.MaxJuniorTeams) && s.HasUpgrade(UpgradeType.SENIOR_TEAM)) {
					return true;
				}else {
					return false;
				}
			}
		};
		upgrades.add(securityTeam);
		
		Upgrade internshipProgram = new Upgrade("Internship Program", UpgradeType.INTERNSHIP_PROGRAM,Constants.Upgrades.Costs.InternshipProgram) {
			@Override
			public void onPlayerEnd(Player p, BoardSquare s) {
				p.AddEffect(EffectType.TECH_SAVVY, Constants.Upgrades.InternshipProgramTurns);
			}
		
		};
		upgrades.add(internshipProgram);
	
}

	
	
	public static ArrayList<Upgrade> GetUpgrades() {
		return upgrades;
	}
	
	public static Upgrade GetUpgrade(UpgradeType type) {
		for (Upgrade u : upgrades) {
			if (u.getType() == type) {
				return u;
			}
		}

		return null;
	}


}
