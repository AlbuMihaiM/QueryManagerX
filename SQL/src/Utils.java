import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Utils {

	public static boolean isNumber (String str) {
		
		for(int i = 0; i < str.length(); i++) {
			if(!('0' <= str.charAt(i) && str.charAt(i) <= '9')) {
				return false;
			}
		}
		
		return true;
	}

	public static boolean emptyTextArea (JTextArea ta) {
		
		return ta == null || ta.getText().length() == 0;
	}
	
	public static boolean emptyTextField (JTextField tf) {
		
		return tf == null || tf.getText().length() == 0;
	}
	
	public static int queryAdnNoLines (Query q) {
		
		if(q.adnotation.getFirst() == null && q.adnotation.getSecond() == null) {
			return 0;
		}
		
		if(q.adnotation.getFirst() != null && q.adnotation.getSecond() != null) {
			return 10;
		}
		
		return 6;
	}
	
	public static int queryCommNoLines (Query q) {
		
		if(q.comments.size() == 0) {
			return 1;
		}
		
		return 2 + q.comments.size();
	}
	
	public static int queryNumberLines (Query q) {
		
		return 8 + queryCommNoLines(q) + queryAdnNoLines(q);
	}
	
	public static int firstQueriesNoLines (int number) {
		
		int sum = 1;
		
		for(int i = 0; i < number; i++) {
			sum += queryNumberLines(Sql.queries.get(i));
		}
		
		return sum;
	}
	
	public static String withoutLast (String string) {
		
		if (string.equals("")) {
			return "";
			
		}
		
		return string.substring(0, string.length() - 1);
	}
}
