import java.util.ArrayList;
import java.util.List;

public class Query {

	String description;
	String title;
	String url;
	List<String> comments = new ArrayList<String>();
	String sql;
	String sql_plain;
	Pair<Pair<Integer, String>, Pair<Integer, String>> adnotation;
	String id;
	
	public Query() {
		this.adnotation = new Pair<Pair<Integer, String>, Pair<Integer, String>>();
		this.comments = new ArrayList<String>();
	}
	
	public Query(String description, String title, String url, List<String> comments,
			String sql, String sql_plain, String id) {
		this.description = description;
		this.title = title;
		this.url = url;
		this.comments = comments;
		this.sql = sql;
		this.sql_plain = sql_plain;
		this.adnotation = new Pair<Pair<Integer, String>, Pair<Integer, String>>();
		this.id = id;
	}
	
	public static String sqlEnters (String string) {
		return string.replaceAll(",", ",\n").concat("\n");
	}
	
	public void print() {
		
		System.out.println("\tDescription = " + this.description);
		System.out.println("\tTitle = " + this.title);
		System.out.println("\tUrl = " + this.url);
		System.out.println("\tComments: = \n");
		
		for(int i = 0; i < this.comments.size(); i++) {
			System.out.println("\t\tComment " + (i + 1) + " = " + this.comments.get(i));
		}
		
		System.out.println("\tSql = " + sqlEnters(this.sql));
		System.out.println("\tSql_plain = " + this.sql_plain);
		System.out.println("\tId = " + this.id);

		
	}
}
