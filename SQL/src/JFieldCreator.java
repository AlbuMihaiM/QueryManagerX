
public class JFieldCreator {
	
	public static String spaces (int count) {
		
		String spaces = "";
		
		for(int i = 0; i < count; i++) {
			spaces = spaces.concat(" ");
		}
		
		return spaces;
	}
    
	public static String createAdnotationsField (Pair<Pair<Integer, String>, Pair<Integer, String>> adnotations) {

		if(adnotations.getFirst() == null && adnotations.getSecond() == null) {
			return "";
		}
		
        String field = spaces(8) + "\"annotations\": [";
        
		if(adnotations.getFirst() != null && adnotations.getSecond() == null) {
			field = field.concat("\n" + spaces(12) + "{\n" + spaces(16) + "\"annotator_id\": ");
			field = field.concat(adnotations.getFirst().getFirst().toString() + ",\n" + spaces(16));
			field = field.concat("\"annotation\": " + "\"" + adnotations.getFirst().getSecond() + "\"\n" 
					+ spaces(12) + "}\n"
					+ spaces(8) + "], \n");
			
			return field;
		}

		if(adnotations.getFirst() == null && adnotations.getSecond() != null) {
			field = field.concat("\n" + spaces(12) + "{\n" + spaces(16) + "\"annotator_id\": ");
			field = field.concat(adnotations.getSecond().getFirst().toString() + ",\n" + spaces(16));
			field = field.concat("\"annotation\": " + "\"" + adnotations.getSecond().getSecond() + "\"\n" 
					+ spaces(12) + "}\n"
					+ spaces(8) + "], \n");
			
			return field;
		}
		
		field = field.concat("\n" + spaces(12) + "{\n" + spaces(16) + "\"annotator_id\": ");
		field = field.concat(adnotations.getFirst().getFirst().toString() + ",\n" + spaces(16));
		field = field.concat("\"annotation\": " + "\"" + adnotations.getFirst().getSecond() + "\"\n" 
				+ spaces(12) + "},\n"
				+ spaces(12) + "{\n"
				+ spaces(16));
		field = field.concat("\"annotator_id\": ");
		field = field.concat(adnotations.getSecond().getFirst().toString() + ",\n" + spaces(16));
		field = field.concat("\"annotation\": " + "\"" + adnotations.getSecond().getSecond() + "\"\n" 
				+ spaces(12) + "}\n"
				+ spaces(8) + "], \n");
		
		return field;
	}
}
