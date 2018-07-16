
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Sql {
	
	public static List<Query> queries = new ArrayList<Query>();
	public static String lastFileName = null;
	public static String bigString = "";
	public static JSONObject sql;

    JSONParser parser = new JSONParser();
	JSONArray jsonArray = null;
	
    private void run() throws FileNotFoundException{

        JFrame frame = new JFrame("Query Manager");
        frame.setPreferredSize(new Dimension(720,500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        
        Font menuFont = new Font("Tahoma", Font.BOLD, 14);
        
        menu.setFont(menuFont);
        openItem.setFont(menuFont);
        saveItem.setFont(menuFont);
        
        menuBar.add(menu);
        menu.add(openItem);
        menu.add(saveItem);
        
        frame.setJMenuBar(menuBar);
        frame.setResizable(false);
        
        
        JLabel idLabel = new JLabel("ID:");
        JLabel sqlLabel = new JLabel("SQL:");
        JLabel firstAdnLabel = new JLabel("Adnotation 1:");
        JLabel secondAdnLabel = new JLabel("Adnotation 2:");
        JLabel statusUpLabel = new JLabel
        		("<html>(ENTER to view SQL<br>"
        				+ "- ONLY positive integers)");
        JButton previousButton = new JButton("<");
        JButton nextButton = new JButton(">");
        JButton firstButton = new JButton("<<");
        JButton lastButton = new JButton(">>");
        JTextField idField = new JTextField();
        JTextArea sqlText = new JTextArea();
        JTextArea firstAdnText = new JTextArea();
        JTextField secondAdnText = new JTextField();
		JButton dialogBoxButton = new JButton("OK");
        JScrollPane scroll;
        final JFileChooser fc = new JFileChooser();
        JFrame dialog = new JFrame();
    	GridBagConstraints c = new GridBagConstraints();
    	Dimension dimStatusUpLabel = statusUpLabel.getSize();
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        
        frame.setLocation(screenDim.width/4, screenDim.height/4);
        
        dialog.setLayout(new GridBagLayout());
    	
    	c.anchor = GridBagConstraints.NORTHWEST;
    	c.fill = GridBagConstraints.BOTH;
    	c.weightx = 0.1;
    	c.weighty = 0.1;
    	c.gridx = 0;
    	c.gridy = 0;
    	c.gridwidth = 5;
    	frame.add(new JLabel(""), c);

    	c.gridx = 0;
    	c.gridy = 1;
    	c.gridwidth = 1;
    	frame.add(new JLabel(""), c);
    	
    	c.gridx = 1;
    	c.gridy = 1;
    	idLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    	idLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 10));
    	frame.add(idLabel, c);

    	c.gridx = 2;
    	c.gridy = 1;
    	c.gridwidth = 1;
    	c.insets = new Insets(50, 0, 30, 0);
    	frame.add(idField, c);
    	c.ipadx = 0;
    	c.ipady = 0;

    	c.gridx = 3;
    	c.gridy = 1;
    	c.gridwidth = 2;
    	statusUpLabel.setPreferredSize(dimStatusUpLabel);
    	statusUpLabel.setHorizontalAlignment(SwingConstants.LEFT);
    	statusUpLabel.setVerticalAlignment(SwingConstants.CENTER);
    	statusUpLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
    	frame.add(statusUpLabel, c);
    	
    	c.gridx = 0;
    	c.gridy = 2;
    	c.gridwidth = 1;
    	frame.add(new JLabel(""), c);

    	c.gridx = 1;
    	c.gridy = 2;
    	c.insets = new Insets(10, 0, 10, 0);
    	sqlLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    	sqlLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
    	frame.add(sqlLabel, c);
    	
    	c.gridx = 0;
    	c.gridy = 3;
    	c.gridheight = 2;
    	c.gridwidth = 2;
    	frame.add(new JLabel(""), c);

    	c.gridx = 2;
    	c.gridy = 2;
    	c.gridwidth = 2;
    	c.gridheight = 3;
    	
    	sqlText.setEditable(false);
    	sqlText.setFont(new Font("Arial", Font.PLAIN, 14));
    	scroll = new JScrollPane(sqlText,
    			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
    			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    	frame.add(scroll, c);
    	
    	c.gridx = 0;
    	c.gridy = 5;
    	c.gridwidth = 1;
    	c.gridheight = 1;
    	frame.add(new JLabel(""), c);
    	
    	c.gridx = 1;
    	c.gridy = 5;
    	firstAdnLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    	firstAdnLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
    	frame.add(firstAdnLabel, c);
    	
    	c.gridx = 0;
    	c.gridy = 6;
    	frame.add(new JLabel(""), c);
    	
    	c.gridx = 1;
    	c.gridy = 6;
    	secondAdnLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    	secondAdnLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
    	frame.add(secondAdnLabel, c);
    	
    	c.gridx = 0;
    	c.gridy = 7;
    	frame.add(new JLabel(""), c);

    	c.gridx = 2;
    	c.gridy = 5;
    	c.gridwidth = 2;
    	c.gridheight = 1;
    	c.ipady = 20;
    	c.insets = new Insets(10,2,10,2);
    	scroll = new JScrollPane(firstAdnText,
    			JScrollPane.VERTICAL_SCROLLBAR_NEVER,
    			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    	frame.add(scroll, c);
    	firstAdnText.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
    	firstAdnText.setFont(new Font("Arial", Font.PLAIN, 12));
    	
    	c.gridx = 2;
    	c.gridy = 6;
    	c.ipady = 20;
    	c.insets = new Insets(10,2,10,2);
    	scroll = new JScrollPane(secondAdnText,
    			JScrollPane.VERTICAL_SCROLLBAR_NEVER,
    			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    	frame.add(scroll, c);
    	secondAdnText.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
    	secondAdnText.setFont(new Font("Arial", Font.PLAIN, 12));

    	previousButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
    	firstButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
    	
    	c.gridx = 1;
    	c.gridy = 7;
    	c.gridwidth = 1;
    	c.insets = new Insets(20,0,0,0);
    	c.fill = GridBagConstraints.BASELINE_TRAILING;
    	c.anchor = GridBagConstraints.EAST;
    	c.ipadx = 20;
    	frame.add(firstButton, c);
    	c.gridx = 2;
    	c.ipadx = 0;
    	c.fill = GridBagConstraints.HORIZONTAL;
    	frame.add(previousButton, c);

    	nextButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
    	lastButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
    	
    	c.gridx = 3;
    	c.gridy = 7;
    	c.gridwidth = 1;
    	c.insets = new Insets(20,0,0,0);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.anchor = GridBagConstraints.WEST;
    	frame.add(nextButton, c);
    	c.ipadx = 20;
    	c.gridx = 4;
    	c.fill = GridBagConstraints.NONE;
    	frame.add(lastButton, c);
    	c.gridx = 5;
    	frame.add(new Label(""), c);
    	
    	c.gridx = 4;
    	c.gridy = 2;
    	c.gridheight = 6;
    	frame.add(new JLabel(""), c);
    
    	c.gridx = 2;
    	c.gridy = 8;
    	c.gridwidth = 5;
    	c.anchor = GridBagConstraints.WEST;
    	c.fill = GridBagConstraints.NONE;
    	frame.add(new Label(""), c);
    	
        frame.pack();
        frame.setVisible(true);
        
		dialog.setBounds(frame.getX() + frame.getWidth() / 3,
				frame.getY() + frame.getHeight() / 3,
				frame.getWidth() / 2,
				frame.getHeight() / 4);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 9;
		
		JLabel dialogBoxLabel = new JLabel("");
		dialogBoxLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dialog.add(dialogBoxLabel, gbc);
		
		gbc.gridy = 1;
		gbc.gridwidth = 4;
		dialog.add(new JLabel(""), gbc);
		
		gbc.gridx = 4;
		gbc.gridwidth = 1;
		dialogBoxButton.setHorizontalAlignment(SwingConstants.CENTER);
		dialog.add(dialogBoxButton, gbc);
		dialogBoxButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		        dialog.dispose();
		    }
		});
		
		gbc.gridx = 5;
		gbc.gridwidth = 4;
		dialog.add(new JLabel(""), gbc);
		
		gbc.gridy = 2;
		gbc.gridx = 0;
		gbc.gridwidth = 9;
		dialog.add(new JLabel(""), gbc);
        
        idField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
            	
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                	
                	if(lastFileName == null || !lastFileName.endsWith(".json")) {
                		dialogBoxLabel.setText("No .json file uploaded!");
                		dialog.setVisible(true);
                		return;
                	}
                	
                	if(!Utils.isNumber(idField.getText())) {
                		dialogBoxLabel.setText("Insert a positive integer number!");
                		dialog.setVisible(true);
                		return;
                	}
                	
                	if(Integer.parseInt(idField.getText()) > queries.size()) {
                		dialogBoxLabel.setText("<html>"
                				+ "<BOTTOM>Too big value for ID query!</BOTTOM><br>"
                				+ "<CENTER>Redirecting to last query...</CENTER></HTML>");
                		dialog.setVisible(true);
	                	idField.setText(((Integer)queries.size()).toString());
                	}
                	
                	else if(idField.getText().equals("0")) {
                		dialogBoxLabel.setText("First valid ID is 1!");
                		dialog.setVisible(true);
                		idField.setText("1");
                	}
                	
                	sqlText.setText(Query.sqlEnters(queries.get(Integer.parseInt(idField.getText()) - 1).sql));
                }
                
            }

        });
        

        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	if(lastFileName == null || !lastFileName.endsWith(".json")) {
            		dialogBoxLabel.setText("No .json file uploaded!");
            		dialog.setVisible(true);
            		return;
            	}
            	
            	if(!Utils.isNumber(idField.getText())) {
            		dialogBoxLabel.setText("No valid ID number!");
            		dialog.setVisible(true);
            		return;
            	}
            	
                if (e.getSource() == firstButton) {
                	if (idField.getText().length() <= 0) {
                		idField.setText("1");
                    	sqlText.setText(Query.sqlEnters(queries.get(Integer.parseInt(idField.getText()) - 1).sql));
                	}
                	else if (Integer.parseInt(idField.getText()) == 1) {
	            		dialogBoxLabel.setText("First valid ID is 1!");
	            		dialog.setVisible(true);
                	}
                	else if (Integer.parseInt(idField.getText()) < 1) {
                		dialogBoxLabel.setText("<html>"
                				+ "<BOTTOM>First valid ID is 1!</BOTTOM><br>"
                				+ "<CENTER>Redirecting to first query...</CENTER></HTML>");
	            		dialog.setVisible(true);
                    	idField.setText("1");
                    	sqlText.setText(Query.sqlEnters(queries.get(Integer.parseInt(idField.getText()) - 1).sql));
                	}
                	else {
                    	idField.setText("1");
                    	sqlText.setText(Query.sqlEnters(queries.get(0).sql));
                    	firstAdnText.setText("");
                    	secondAdnText.setText("");
                	}
                }
                
                else if (e.getSource() == previousButton) {
                	if (idField.getText().length() <= 0) {
	            		dialogBoxLabel.setText("Insert an ID!");
	            		dialog.setVisible(true);
	            		return;
                	}
                	Integer currentValue = Integer.parseInt(idField.getText());
                	if (currentValue < 1) {
                		dialogBoxLabel.setText("<html>"
                				+ "<BOTTOM>First valid ID is 1!</BOTTOM><br>"
                				+ "<CENTER>Redirecting to first query...</CENTER></HTML>");
	            		dialog.setVisible(true);
                    	idField.setText("1");
                    	sqlText.setText(Query.sqlEnters(queries.get(Integer.parseInt(idField.getText()) - 1).sql));
                	}
                	else if (currentValue == 1) {
	            		dialogBoxLabel.setText("First valid ID is 1!");
	            		dialog.setVisible(true);
                	}
                	else {
                		Integer nextValue = currentValue - 1;
                		idField.setText(nextValue.toString());
                    	sqlText.setText(Query.sqlEnters(queries.get(nextValue - 1).sql));
                    	firstAdnText.setText("");
                    	secondAdnText.setText("");
                	}
                }
                
                else if (e.getSource() == nextButton) {
                	if (idField.getText().length() <= 0) {
	            		dialogBoxLabel.setText("Insert an ID!");
	            		dialog.setVisible(true);
	            		return;
                	}
                	Integer currentValue = Integer.parseInt(idField.getText());
                	if (currentValue == queries.size()) {
                		dialogBoxLabel.setText("There are no more queries!");
                		dialog.setVisible(true);
                	}
                	else if (currentValue > queries.size()) {
                		dialogBoxLabel.setText("<html>"
                				+ "<BOTTOM>Too big value for ID query!</BOTTOM><br>"
                				+ "<CENTER>Redirecting to last query...</CENTER></HTML>");
                		dialog.setVisible(true);
	                	idField.setText(((Integer)queries.size()).toString());
	                	sqlText.setText(Query.sqlEnters(queries.get(Integer.parseInt(idField.getText()) - 1).sql));
                	}
                	else {
                		Integer nextValue = currentValue + 1;
                		idField.setText(nextValue.toString());
                    	sqlText.setText(Query.sqlEnters(queries.get(nextValue - 1).sql));
                    	firstAdnText.setText("");
                    	secondAdnText.setText("");
                	}
                }
                
                else if (e.getSource() == lastButton) {
                	if (idField.getText().length() <= 0) {
                		idField.setText(((Integer)queries.size()).toString());
                    	sqlText.setText(Query.sqlEnters(queries.get(Integer.parseInt(idField.getText()) - 1).sql));
                	}
                	else if (idField.getText().equals(((Integer)queries.size()).toString())) {
	            		dialogBoxLabel.setText("There are no more queries!");
	            		dialog.setVisible(true);
                	}
                	else if (Integer.parseInt(idField.getText()) > queries.size()) {
                		dialogBoxLabel.setText("<html>"
                				+ "<BOTTOM>Too big value for ID query!</BOTTOM><br>"
                				+ "<CENTER>Redirecting to last query...</CENTER></HTML>");
                		dialog.setVisible(true);
	                	idField.setText(((Integer)queries.size()).toString());
	                	sqlText.setText(Query.sqlEnters(queries.get(Integer.parseInt(idField.getText()) - 1).sql));
                	}
                	else {
	                    idField.setText(((Integer)queries.size()).toString());
	                	sqlText.setText(Query.sqlEnters(queries.get(queries.size() - 1).sql));
	                	firstAdnText.setText("");
	                	secondAdnText.setText("");
                	}
                }
               
            }
        };

        ActionListener openAction = new ActionListener() {
    		public void actionPerformed(ActionEvent e) {

                int returnVal = fc.showOpenDialog(dialog);

                if (returnVal == JFileChooser.APPROVE_OPTION) {

                    File fileChosen = fc.getSelectedFile();
                    
                    if(fileChosen != null) {
                    	lastFileName = fileChosen.getName();
                    }

                    if(!fileChosen.getName().endsWith(".json")) {
	            		dialogBoxLabel.setText(
	            				"<HTML>"
	            				+ "<BOTTOM>File type not supported!</BOTTOM><br>"
	            				+ "<CENTER>Please open a .json file!</CENTER></HTML>");
	            		dialog.setVisible(true);
	            		fileChosen = null;
                    	return;
                    }

            		queries.clear();
            		
					try {

						jsonArray = (JSONArray) parser.parse(new FileReader(fileChosen));
						
			    		for (Object obj : jsonArray) {
			    		    sql = (JSONObject) obj;
			    		    
			    		    String description = (String) sql.get("description");
			    		    String title = (String) sql.get("title");
			    		    String url = (String) sql.get("url");
			    		    String query = (String) sql.get("sql");
			    		    String sql_plain = (String) sql.get("sql_plain");
			    		    String id = (String) sql.get("id");
			    		    
			    		    List<String> comments = new ArrayList<String>();
			    		    
			                JSONArray comms = (JSONArray) sql.get("comments");
			                Iterator<String> iterator = comms.iterator();
			                while (iterator.hasNext()) {
			                	comments.add(iterator.next());
			                }
			    		    queries.add(new Query(description, title, url, comments, query, sql_plain, id));
			    		}

					} catch (IOException | ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }

	    	}
    	};
    	
        ActionListener saveAction = new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			
    			if(lastFileName == null || !lastFileName.endsWith(".json")) {
            		dialogBoxLabel.setText(
            				"<HTML><CENTER>Please open a .json file!</CENTER></HTML>");
            		dialog.setVisible(true);
    				return;
    			}
    			
    			if(idField.getText().length() <= 0) {
            		dialogBoxLabel.setText(
            				"<HTML><CENTER>Please enter an ID number!</CENTER></HTML>");
            		dialog.setVisible(true);
    				return;
    			}
        	
	        	if(!Utils.isNumber(idField.getText()) ||
	        			Integer.parseInt(idField.getText()) < 1 ||
	        			Integer.parseInt(idField.getText()) > queries.size()) {
	        		dialogBoxLabel.setText("<HTML><CENTER>No valid ID number!</CENTER></HTML>");
	        		dialog.setVisible(true);
	        		return;
	        	}

        		int id = Integer.parseInt(idField.getText()) - 1;

	        	try {
	    			BufferedReader in = new BufferedReader(new FileReader(fc.getSelectedFile()));
	    			
	    			int count = 0;
	        		int noAdn = Utils.queryAdnNoLines(queries.get(id));
	        		int startLine = Utils.firstQueriesNoLines(id + 1) - 3 - noAdn;
	        		int endLine = startLine + noAdn;
	    			
	    			if(Utils.emptyTextArea(firstAdnText) && Utils.emptyTextField(secondAdnText)){
		        		
		        		queries.get(id).adnotation =
		        				new Pair<Pair<Integer,String>,Pair<Integer,String>>(null, null);
		        	}
		        	
		        	else if(!Utils.emptyTextArea(firstAdnText) && Utils.emptyTextField(secondAdnText)){
		        		
		        		queries.get(id).adnotation =
		        				new Pair<Pair<Integer,String>,Pair<Integer,String>>(
		        						new Pair<Integer, String>(3, firstAdnText.getText()),
		        						null);
		        	}

		        	else if(Utils.emptyTextArea(firstAdnText) && !Utils.emptyTextField(secondAdnText)){
		        		
		        		queries.get(id).adnotation =
		        				new Pair<Pair<Integer,String>,Pair<Integer,String>>(
		        						null,
		        						new Pair<Integer, String>(3, secondAdnText.getText()));
		        	}
		        	
		        	else {
		        		queries.get(id).adnotation =
		        				new Pair<Pair<Integer,String>,Pair<Integer,String>>(
		        						new Pair<Integer, String>(3, firstAdnText.getText()),
		        						new Pair<Integer, String>(3, secondAdnText.getText()));
		        	}
	    			
	    			List<String> lines = new ArrayList<String>();
	    			String line = in.readLine();
	    		    while (line != null) {
	    		    	count++;
	    		        if (count == startLine) {
	    		        	if (line.startsWith(JFieldCreator.spaces(8) + "\"sql\"")) {
	    		        		line = JFieldCreator.createAdnotationsField(queries.get(id).adnotation) + line;
	    		        	}
	    		        	else {
	    		        		line = Utils.withoutLast(JFieldCreator.createAdnotationsField(queries.get(id).adnotation));
	    		        		endLine--;
	    		        		if(line.equals("")) {
	    		        			startLine--;
	    		        		}
	    		        	}
	    		        }
	    		        
	    		        if (count <= startLine || count > endLine) {
	    			        lines.add(line);
	    		        }
	    		        
	    		        line = in.readLine();
	    		    }
	    		    BufferedWriter out = new BufferedWriter(new FileWriter(fc.getSelectedFile()));
	    		    for (String l : lines)
	    		    	out.write(l + "\n");
	    		    in.close();
	    		    out.close();
	    		    
	    		} catch (IOException e1) {
	    			// TODO Auto-generated catch block
	    			e1.printStackTrace();
	    		}
	        	
        		return;
	    	}
    	};

        openItem.addActionListener(openAction);
        saveItem.addActionListener(saveAction);
        
        firstButton.addActionListener(al);
        previousButton.addActionListener(al);
        nextButton.addActionListener(al);
        lastButton.addActionListener(al);
        
    }
    
	public static void main(String[] args)
			throws FileNotFoundException, IOException, ParseException {

		Sql GUI = new Sql();
		GUI.run();
	}
}
