package gui;

import game.ComputerAgent;
import game.HumanPlayer;
import game.Player;
import game.TileBag;

import javax.swing.text.DefaultCaret;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dict.DictionaryTable;
import dict.EnglishScrabbleScorer;

import com.google.common.collect.HashBiMap;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.ListIterator;

class TablePanel extends JPanel {
	private static final long serialVersionUID = 6322841196743668800L;
	private Image bg;

	public TablePanel() {
		ImageIcon gameTable = new ImageIcon(this.getClass().getResource("../res/images/table.png"));
		bg = gameTable.getImage();
		Dimension size = new Dimension(650, 334);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(bg, 0, 0, null);
	}
}

public class GameGUI {
	JPanel mainPanel, eventPanel, tablePanel, tilePanel, actionPanel, betPanel;
	Character[] letterArray = new Character[7];
	final ImageIcon blank = new ImageIcon(this.getClass().getResource("../res/images/tiles/blank.jpg"));
	final ImageIcon a = new ImageIcon(this.getClass().getResource("../res/images/tiles/a.jpg"));
	final ImageIcon b = new ImageIcon(this.getClass().getResource("../res/images/tiles/b.jpg"));
	final ImageIcon c = new ImageIcon(this.getClass().getResource("../res/images/tiles/c.jpg"));
	final ImageIcon d = new ImageIcon(this.getClass().getResource("../res/images/tiles/d.jpg"));
	final ImageIcon e = new ImageIcon(this.getClass().getResource("../res/images/tiles/e.jpg"));
	final ImageIcon f = new ImageIcon(this.getClass().getResource("../res/images/tiles/f.jpg"));
	final ImageIcon g = new ImageIcon(this.getClass().getResource("../res/images/tiles/g.jpg"));
	final ImageIcon h = new ImageIcon(this.getClass().getResource("../res/images/tiles/h.jpg"));
	final ImageIcon i = new ImageIcon(this.getClass().getResource("../res/images/tiles/i.jpg"));
	final ImageIcon j = new ImageIcon(this.getClass().getResource("../res/images/tiles/j.jpg"));
	final ImageIcon k = new ImageIcon(this.getClass().getResource("../res/images/tiles/k.jpg"));
	final ImageIcon l = new ImageIcon(this.getClass().getResource("../res/images/tiles/l.jpg"));
	final ImageIcon m = new ImageIcon(this.getClass().getResource("../res/images/tiles/m.jpg"));
	final ImageIcon n = new ImageIcon(this.getClass().getResource("../res/images/tiles/n.jpg"));
	final ImageIcon o = new ImageIcon(this.getClass().getResource("../res/images/tiles/o.jpg"));
	final ImageIcon p = new ImageIcon(this.getClass().getResource("../res/images/tiles/p.jpg"));
	final ImageIcon q = new ImageIcon(this.getClass().getResource("../res/images/tiles/q.jpg"));
	final ImageIcon r = new ImageIcon(this.getClass().getResource("../res/images/tiles/r.jpg"));
	final ImageIcon s = new ImageIcon(this.getClass().getResource("../res/images/tiles/s.jpg"));
	final ImageIcon t = new ImageIcon(this.getClass().getResource("../res/images/tiles/t.jpg"));
	final ImageIcon u = new ImageIcon(this.getClass().getResource("../res/images/tiles/u.jpg"));
	final ImageIcon v = new ImageIcon(this.getClass().getResource("../res/images/tiles/v.jpg"));
	final ImageIcon w = new ImageIcon(this.getClass().getResource("../res/images/tiles/w.jpg"));
	final ImageIcon x = new ImageIcon(this.getClass().getResource("../res/images/tiles/x.jpg"));
	final ImageIcon y = new ImageIcon(this.getClass().getResource("../res/images/tiles/y.jpg"));
	final ImageIcon z = new ImageIcon(this.getClass().getResource("../res/images/tiles/z.jpg"));
	private boolean playGame = true;
	private int initialBank = 500;
	private int bank = initialBank;
	private StringBuilder buildPlayerString = new StringBuilder(7);
	private boolean b1Pressed = false, b2Pressed = false, b3Pressed = false, b4Pressed = false, b5Pressed = false,
			b6Pressed = false, b7Pressed = false;

	@SuppressWarnings("boxing")
	public GameGUI() {
		// set up Frame
		JFrame gameFrame = new JFrame();

		gameFrame.setTitle("Texas Scramble");
		gameFrame.setSize(1022, 768);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//gameFrame.setVisible(true);
		gameFrame.setResizable(false);
		gameFrame.setLocationRelativeTo(null);

		// create panels
		mainPanel = new JPanel(new BorderLayout());
		tablePanel = new JPanel();
		tablePanel.setLayout(new BoxLayout(tablePanel, 1));
		tilePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 13, 8));
		betPanel = new JPanel(new GridLayout(2, 2));
		eventPanel = new JPanel(new BorderLayout());

		actionPanel = new JPanel();
		actionPanel.setLayout(new BoxLayout(actionPanel, 1));
		Dimension tb = new Dimension(724, 622);
		Dimension ap = new Dimension(292, 622);
		Dimension tp = new Dimension(1022, 200);

		TablePanel tPanel = new TablePanel();

		// set up panels
		tablePanel.setPreferredSize(tb);
		tablePanel.setBackground(Color.BLACK);

		eventPanel.setPreferredSize(ap);
		actionPanel.setBackground(Color.BLACK);

		tilePanel.setMinimumSize(tp);
		tilePanel.setBackground(Color.BLACK);

		// set up image icons
		ImageIcon callIcon = new ImageIcon(this.getClass().getResource("../res/images/call_small.jpg"));
		ImageIcon checkIcon = new ImageIcon(this.getClass().getResource("../res/images/check_small.jpg"));
		ImageIcon foldIcon = new ImageIcon(this.getClass().getResource("../res/images/fold_small.jpg"));
		ImageIcon raiseIcon = new ImageIcon(this.getClass().getResource("../res/images/raise_small.jpg"));
		ImageIcon submitIcon = new ImageIcon(this.getClass().getResource("../res/images/submit.jpg"));
		
		
		final JTextArea text = new JTextArea();

		DefaultCaret caret = (DefaultCaret) text.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		Font font = new Font("Verdana", Font.BOLD, 12);
		text.setForeground(Color.WHITE);
		text.setBackground(Color.BLACK);
		text.setFont(font);
		text.setEditable(false);

		// create scrollPane for showing game actions
		JScrollPane scrollText = new JScrollPane(text);
		scrollText.setPreferredSize(new Dimension(0, 422));

		// create buttons for gambling actions
		JButton callButton = new JButton(callIcon);
		JButton checkButton = new JButton(checkIcon);
		JButton raiseButton = new JButton(raiseIcon);
		JButton foldButton = new JButton(foldIcon);
		JButton submitButton = new JButton(submitIcon);

		// remove default borders
		callButton.setBorder(null);
		checkButton.setBorder(null);
		raiseButton.setBorder(null);
		foldButton.setBorder(null);
		submitButton.setBorder(null);

		// create initially blank letter buttons
		final JButton letter1 = new JButton(blank);
		final JButton letter2 = new JButton(blank);
		final JButton letter3 = new JButton(blank);
		final JButton letter4 = new JButton(blank);
		final JButton letter5 = new JButton(blank);
		final JButton letter6 = new JButton(blank);
		final JButton letter7 = new JButton(blank);

		// remove default borders
		letter1.setBorder(null);
		letter2.setBorder(null);
		letter3.setBorder(null);
		letter4.setBorder(null);
		letter5.setBorder(null);
		letter6.setBorder(null);
		letter7.setBorder(null);

		// add gambling buttons to action panel
		betPanel.add(callButton);
		betPanel.add(checkButton);
		betPanel.add(raiseButton);
		betPanel.add(foldButton);
		actionPanel.add(submitButton);

		// add letter buttons to tile panel
		tilePanel.add(letter1);
		tilePanel.add(letter2);
		tilePanel.add(letter3);
		tilePanel.add(letter4);
		tilePanel.add(letter5);
		tilePanel.add(letter6);
		tilePanel.add(letter7);

		// add table image pane to table panel
		tablePanel.add(tPanel);

		// add scroll text area and gambling buttons to events panel
		eventPanel.add(scrollText, BorderLayout.NORTH);
		eventPanel.add(betPanel, BorderLayout.CENTER);
		eventPanel.add(actionPanel, BorderLayout.SOUTH);

		// add panels to frame
		gameFrame.add(mainPanel);
		mainPanel.add(tablePanel, BorderLayout.WEST);
		mainPanel.add(eventPanel, BorderLayout.EAST);
		mainPanel.add(tilePanel, BorderLayout.SOUTH);

		gameFrame.setVisible(true);

		// create players
		LinkedList<Player> players = new LinkedList<Player>();
		final HumanPlayer human = new HumanPlayer(initialBank);
		players.add(new ComputerAgent(initialBank, 50, 40));
		players.add(new ComputerAgent(initialBank, 40, 50));
		players.add(new ComputerAgent(initialBank, 30, 20));
		players.add(human);
		ListIterator<Player> itr = players.listIterator();

		// create dictionary
		final DictionaryTable dict = new DictionaryTable();

		// create english scrabble scorer
		final EnglishScrabbleScorer score = new EnglishScrabbleScorer();
		
		// create tile bag
		TileBag bag = new TileBag();

		// HashBiBap to map char value to button icon
		// HashBiMap is a data structure from the google guava library
		final HashBiMap<Character, ImageIcon> charToIcon = HashBiMap.create();

		charToIcon.put('a', a);
		charToIcon.put('b', b);
		charToIcon.put('c', c);
		charToIcon.put('d', d);
		charToIcon.put('e', e);
		charToIcon.put('f', f);
		charToIcon.put('g', g);
		charToIcon.put('h', h);
		charToIcon.put('i', i);
		charToIcon.put('j', j);
		charToIcon.put('k', k);
		charToIcon.put('l', l);
		charToIcon.put('m', m);
		charToIcon.put('n', n);
		charToIcon.put('o', o);
		charToIcon.put('p', p);
		charToIcon.put('q', q);
		charToIcon.put('r', r);
		charToIcon.put('s', s);
		charToIcon.put('t', t);
		charToIcon.put('u', u);
		charToIcon.put('v', v);
		charToIcon.put('w', w);
		charToIcon.put('x', x);
		charToIcon.put('y', y);
		charToIcon.put('z', z);

		// main game loop
		do {
			bag.shuffleTiles();
			char[] pTiles = bag.dealPlayerTiles(4);
			human.pocketTiles(pTiles[0], pTiles[1]);
			char[] flopTiles = bag.flop();
			char turnTile = bag.turn();
			char riverTile = bag.river();

			letter1.setIcon(charToIcon.get(human.getFirstTile()));
			letter2.setIcon(charToIcon.get(human.getSecondTile()));
			letter3.setIcon(charToIcon.get(flopTiles[0]));
			letter4.setIcon(charToIcon.get(flopTiles[1]));
			letter5.setIcon(charToIcon.get(flopTiles[2]));

			if (!itr.hasNext()) { // small blind
				itr.previous();
				itr.previous();
				itr.previous();
				itr.previous();
			}
			itr.next().smallBlind();
			text.append("Small blind posted at x amount\n");

			if (!itr.hasNext()) { // big blind
				itr.previous();
				itr.previous();
				itr.previous();
				itr.previous();
			}
			itr.next().bigBlind();
			text.append("Big blind posted at y amount\n");

			if (!itr.hasNext()) { // first round of betting
				itr.previous();
				itr.previous();
				itr.previous();
				itr.previous();
			}
			text.append(itr.next().takeTurn());

			letter6.setIcon(charToIcon.get(riverTile));
			letter7.setIcon(charToIcon.get(turnTile));
			playGame = false;
		} while (playGame); // non-functional atm

		// action listeners on buttons

		callButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				text.append("You called the bet\n");
				human.call(bank);
			}

		});

		checkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				text.append("You checked\n");
				human.check();
			}

		});

		raiseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				text.append("You raised " + 100 + "\n");
				human.raise(100, 100);
			}

		});

		foldButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//text.append("You fold\n");
				human.fold();
				
				text.append("Your word is: " + buildPlayerString.toString() + "\n");
			}

		});
		
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String word = buildPlayerString.toString();
				text.append("Your word is: " + word + "\n");
				if(dict.containsWord(word)){
					text.append(word + " is a valid word\n");
				} else {
					text.append(word + " is not a valid word\n");
				}
				text.append(word + " is worth " + score.getScore(word) + " points");
			}

		});

		letter1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!b1Pressed) {
					char c = charToIcon.inverse().get(letter1.getIcon());
					buildPlayerString.append(c);
					b1Pressed = true;
				}
			}
		});

		letter2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!b2Pressed) {
					char c = charToIcon.inverse().get(letter2.getIcon());
					buildPlayerString.append(c);
					b2Pressed = true;
				}
			}
		});

		letter3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!b3Pressed) {
					char c = charToIcon.inverse().get(letter3.getIcon());
					buildPlayerString.append(c);
					b3Pressed = true;
				}
			}
		});

		letter4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!b4Pressed) {
					char c = charToIcon.inverse().get(letter4.getIcon());
					buildPlayerString.append(c);
					b4Pressed = true;
				}
			}
		});

		letter5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!b5Pressed) {
					char c = charToIcon.inverse().get(letter5.getIcon());
					buildPlayerString.append(c);
					b5Pressed = true;
				}
			}
		});

		letter6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!b6Pressed) {
					char c = charToIcon.inverse().get(letter6.getIcon());
					buildPlayerString.append(c);
					b6Pressed = true;
				}
			}
		});

		letter7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!b7Pressed) {
					char c = charToIcon.inverse().get(letter7.getIcon());
					buildPlayerString.append(c);
					b7Pressed = true;
					
				}
			}
		});
	}

	public static void main(String[] args) {
		new GameGUI();
	}
}
