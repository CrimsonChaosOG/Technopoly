package com.technopoly.data.board;

import java.util.ArrayList;

import com.technopoly.utils.Vector2;

public class BoardConfigurator {

	public static Board GetBoard() {

		Vector2 boardSize = new Vector2(14, 8);

		BoardConfig config = new BoardConfig(boardSize);
		
		//Top section
		BoardField field1 = new BoardField("Field 1");
		BoardField field2 = new BoardField("Field 2");
		//Right section
		BoardField field3 = new BoardField("Field 3");
		BoardField field4 = new BoardField("Field 4");
		//Bottom section
		BoardField field5 = new BoardField("Field 5");
		BoardField field6 = new BoardField("Field 6");
		//Left Section
		BoardField field7 = new BoardField("Field 7");
		BoardField field8 = new BoardField("Field 8");
		
		// Go Square
		config.AddSquareConfig("START", 200, BoardSquareType.START);

		// Soundcloud
		config.AddSquareConfig("SNDCLD", 30, BoardSquareType.PROPERTY,field1);

		// SAP
		config.AddSquareConfig("SAP", 40, BoardSquareType.PROPERTY,field1);

		// Deezer
		config.AddSquareConfig("DEEZER", 50, BoardSquareType.PROPERTY,field1);

		// Oxford UNI
		config.AddSquareConfig("OXFORD", 75, BoardSquareType.UNIVERSITY);

		// Nokia
		config.AddSquareConfig("NOKIA", 45, BoardSquareType.PROPERTY,field1);

		// Community chest
		config.AddSquareConfig("CHEST", 0, BoardSquareType.COMMUNITY_CHEST);

		// Chance
		config.AddSquareConfig("CHANCE", 0, BoardSquareType.CHANCE);

		// Super cell
		config.AddSquareConfig("SUPCELL", 55, BoardSquareType.PROPERTY,field2);

		// EPFL University
		config.AddSquareConfig("EPFL", 75, BoardSquareType.UNIVERSITY);

		// Spotify
		config.AddSquareConfig("SPOTFY", 60, BoardSquareType.PROPERTY,field2);

		// Jetbrains
		config.AddSquareConfig("JETBRN", 65, BoardSquareType.PROPERTY,field2);

		// Mojang
		config.AddSquareConfig("MOJANG", 70, BoardSquareType.PROPERTY,field2);

		// JAIL
		config.AddSquareConfig("JAIL", 70, BoardSquareType.JAIL);

		// Rockstar
		config.AddSquareConfig("RCKSTR", 50, BoardSquareType.PROPERTY,field3);

		// Kainos
		config.AddSquareConfig("KAINOS", 60, BoardSquareType.PROPERTY,field3);

		// Community chest
		config.AddSquareConfig("CHEST", 0, BoardSquareType.COMMUNITY_CHEST);

		// Chance
		config.AddSquareConfig("CHANCE", 0, BoardSquareType.CHANCE);

		// Allstate
		config.AddSquareConfig("ALLSTE", 65, BoardSquareType.PROPERTY,field4);

		// Softwire
		config.AddSquareConfig("SFTWIRE", 60, BoardSquareType.PROPERTY,field4);

		// Subsidy
		config.AddSquareConfig("SUBSDY", 0, BoardSquareType.SUBSIDY);

		// Lenovo
		config.AddSquareConfig("LENOVO", 60, BoardSquareType.PROPERTY,field5);

		// Tencent
		config.AddSquareConfig("TENCNT", 60, BoardSquareType.PROPERTY,field5);

		// Sony
		config.AddSquareConfig("SONY", 60, BoardSquareType.PROPERTY,field5);

		// MIT University
		config.AddSquareConfig("MIT", 75, BoardSquareType.UNIVERSITY);

		// Vivo
		config.AddSquareConfig("VIVO", 60, BoardSquareType.PROPERTY,field5);

		// Community chest
		config.AddSquareConfig("CHEST", 0, BoardSquareType.COMMUNITY_CHEST);

		// Chance
		config.AddSquareConfig("CHANCE", 0, BoardSquareType.CHANCE);

		// Daewoo
		config.AddSquareConfig("DAEWOO", 50, BoardSquareType.PROPERTY,field6);

		// NTU University
		config.AddSquareConfig("NTU", 50, BoardSquareType.UNIVERSITY);

		// Sega
		config.AddSquareConfig("SEGA", 60, BoardSquareType.PROPERTY,field6);

		// Fujitsu
		config.AddSquareConfig("FUJITSU", 55, BoardSquareType.PROPERTY,field6);

		// Nintendo
		config.AddSquareConfig("NINTEND", 55, BoardSquareType.PROPERTY,field6);

		// Go to jail
		config.AddSquareConfig("GO JAIL", 0, BoardSquareType.GOJAIL);

		// Oracle
		config.AddSquareConfig("ORACLE", 70, BoardSquareType.PROPERTY,field7);

		// IBM
		config.AddSquareConfig("IBM", 60, BoardSquareType.PROPERTY,field7);

		// Community chest
		config.AddSquareConfig("CHEST", 0, BoardSquareType.COMMUNITY_CHEST);

		// Chance
		config.AddSquareConfig("CHANCE", 0, BoardSquareType.CHANCE);

		// Microsoft
		config.AddSquareConfig("MICRO", 90, BoardSquareType.PROPERTY,field8);

		// Google
		config.AddSquareConfig("GOOGLE", 95, BoardSquareType.PROPERTY,field8);

		Board b = BoardFactory.BuildBoard(config);

		return b;

	}

}
