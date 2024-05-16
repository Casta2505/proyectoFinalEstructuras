package co.edu.unbosque.SnakesAndLadders.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.ByteBufferOutputStream;
import com.esotericsoftware.kryo.io.Output;

import co.edu.unbosque.SnakesAndLadders.model.Board;
import co.edu.unbosque.SnakesAndLadders.model.Components;
import co.edu.unbosque.SnakesAndLadders.model.Game;
import co.edu.unbosque.SnakesAndLadders.model.GameSave;
import co.edu.unbosque.SnakesAndLadders.model.Player;
import co.edu.unbosque.SnakesAndLadders.repository.GameSaveRepository;
import co.edu.unbosque.SnakesAndLadders.util.graph.Edge;
import co.edu.unbosque.SnakesAndLadders.util.graph.Graph;
import co.edu.unbosque.SnakesAndLadders.util.graph.Vertex;
import co.edu.unbosque.SnakesAndLadders.util.linkedlist.MyLinkedList;
import co.edu.unbosque.SnakesAndLadders.util.linkedlist.Node;

@Controller
@RequestMapping
@SessionAttributes("game")
public class GameController {
	@Autowired
	private GameSaveRepository gameSaveRep;

	@GetMapping("/")
	public String getSucursales() {
		return "menu";
	}

	@ModelAttribute("game")
	public Game setUpUser() {
		Game game = new Game();
		Board board = new Board();
		game.setBoard(board);
		return game;
	}

	@GetMapping("/saveGame")
	public String saveGame(@ModelAttribute("game") Game game, Model model) {
		GameSave gs = new GameSave();
		gs.setBoard(serializeBoard(game.getBoard()));
		gs.setDiceNumber(game.getDiceNumber());
		gs.setDifficulty(game.getDifficulty());
		gs.setPlayerNum(game.getPlayerNum());
		gs.setPlayers(serializeList(game.getPlayers()));
		gs.setPlayerTurn(serializePlayer(game.getPlayerTurn()));
		gs.setTheme(game.getTheme());
		gameSaveRep.save(gs);
		game = new Game();
		game.setBoard(new Board());
		return "menu";
	}

	@GetMapping("/SelectPlayers")
	public String getPlayers(Model model) {
		return "personalize";
	}

	@PostMapping("/updateDifficulty")
	public String updateDifficulty(@ModelAttribute("game") Game game, @RequestParam("difficulty") String difficulty,
			Model model) {
		game.setDifficulty(difficulty);
		return "personalize";
	}

	@PostMapping("/updateThemes")
	public String updateThemes(@ModelAttribute("game") Game game, @RequestParam("themes") String theme, Model model) {
		game.setTheme(theme);
		return "personalize";
	}

	@PostMapping("/updatePlayers")
	public String updatePlayers(@ModelAttribute("game") Game game, @RequestParam("playerSelected") String player,
			@RequestParam("height") int height, @RequestParam("width") int width, Model model) {
		if (game.getTheme() == null || game.getDifficulty() == null) {
			model.addAttribute("mensaje", "Select theme and diffilculty before create players");
			return "personalize";
		}
		game.getBoard().setHeight(height);
		game.getBoard().setWidth(width);
		if (player.equals("two")) {
			game.setPlayerNum(2);
			return "twoPlayers";
		} else if (player.equals("three")) {
			game.setPlayerNum(3);
			return "treePlayers";
		} else {
			game.setPlayerNum(4);
			return "fourPlayers";
		}
	}

	@GetMapping("/StartPlaying")
	public String getPlay(Model model, @ModelAttribute("game") Game game) {
		model.addAttribute("diceNumber", game.getDiceNumber());
		model.addAttribute("theme", game.getTheme());
		if (game.getTheme().equals("green")) {
			return "tableroV";
		} else {
			if (game.getTheme().equals("pink")) {
				return "tableroR";
			} else {
				if (game.getTheme().equals("gray")) {
					return "tableroG";
				}
			}
		}
		return null;
	}

	@GetMapping("/goBackMenu")
	public String goBack(Model model) {
		return "menu";
	}

	@GetMapping("/ResumeGame")
	public String resumeG(Model model) {
		return "ResumeGame";
	}

	@GetMapping("/ganador")
	public String goWinner(Model model) {
		return "ganador";
	}

	@PostMapping("/SelectTwoCharacters")
	public String getCharacters(@ModelAttribute("game") Game game, Model model,
			@RequestParam("player1Name") String player1Name, @RequestParam("player2Name") String player2Name) {
		Player player1 = new Player(player1Name, 1, 1);
		Player player2 = new Player(player2Name, 1, 2);
		game.setPlayers(new ArrayList<>(Arrays.asList(player1, player2)));
		model.addAttribute("players", 2);
		return "characters";
	}

	@PostMapping("/SelectThreeCharacters")
	public String getCharacters(@ModelAttribute("game") Game game, Model model,
			@RequestParam("player1Name") String player1Name, @RequestParam("player2Name") String player2Name,
			@RequestParam("player3Name") String player3Name) {
		Player player1 = new Player(player1Name, 1, 1);
		Player player2 = new Player(player2Name, 1, 2);
		Player player3 = new Player(player3Name, 1, 3);
		game.setPlayers(new ArrayList<>(Arrays.asList(player1, player2,player3)));

		model.addAttribute("players", 3);
		return "characters";
	}

	@PostMapping("/SelectFourCharacters")
	public String getCharacters(@ModelAttribute("game") Game game, Model model,
			@RequestParam("player1Name") String player1Name, @RequestParam("player2Name") String player2Name,
			@RequestParam("player3Name") String player3Name, @RequestParam("player4Name") String player4Name) {
		Player player1 = new Player(player1Name, 1, 1);
		Player player2 = new Player(player2Name, 1, 2);
		Player player3 = new Player(player3Name, 1, 3);
		Player player4 = new Player(player4Name, 1, 4);
		game.setPlayers(new ArrayList<>(Arrays.asList(player1, player2,player3,player4)));
		model.addAttribute("players", 4);
		return "characters";
	}

	public byte[] serializeBoard(Board board) {
	    Kryo kryo = new Kryo();
	    kryo.setReferences(true);
	    kryo.register(Board.class);
	    kryo.register(Graph.class);
	    kryo.register(Node.class);
	    kryo.register(ArrayList.class);
	    kryo.register(MyLinkedList.class);
	    kryo.register(Vertex.class);
	    kryo.register(Edge.class);
	    kryo.register(Player.class);
	    kryo.register(Components.class);

	    try {
	        ByteBuffer byteBuffer = ByteBuffer.allocate(1024*1024*1024);
	        try (Output output = new Output(new ByteBufferOutputStream(byteBuffer))) {
	            kryo.writeObject(output, board);
	            output.flush();
	            byte[] serializedData = output.toBytes();
	            byteBuffer.clear();
	            return serializedData;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}



	public Board deserializeBoard(byte[] serializedBoard) {
		try (ByteArrayInputStream bis = new ByteArrayInputStream(serializedBoard);
				ObjectInputStream ois = new ObjectInputStream(bis)) {
			Board deserializedBoard = (Board) ois.readObject();
			return deserializedBoard;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public byte[] serializePlayer(Player player) {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(bos)) {
			oos.writeObject(player);
			oos.flush();
			byte[] serializedPlayer = bos.toByteArray();
			return serializedPlayer;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Player deserializePlayer(byte[] serializedPlayer) {
		try (ByteArrayInputStream bis = new ByteArrayInputStream(serializedPlayer);
				ObjectInputStream ois = new ObjectInputStream(bis)) {
			Player deserializedPlayer = (Player) ois.readObject();
			return deserializedPlayer;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public byte[] serializeList(List<Player> players) {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(bos)) {
			oos.writeObject(players);
			oos.flush();
			byte[] serializedPlayers = bos.toByteArray();
			return serializedPlayers;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Player> deserializeList(byte[] serializedPlayers) {
		try (ByteArrayInputStream bis = new ByteArrayInputStream(serializedPlayers);
				ObjectInputStream ois = new ObjectInputStream(bis)) {
			List<Player> deserializedPlayers = (List<Player>) ois.readObject();
			return deserializedPlayers;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}