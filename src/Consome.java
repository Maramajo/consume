import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Consome {

	public static StringBuilder teste = new StringBuilder();
	static ObjectNode node = null;
	static ObjectMapper mapper = new ObjectMapper();
	public static List<Alien> a = new ArrayList<>();
	public String s = "";
	// http://localhost:8080/RESTfulExample/json/product/get
	public static void main(String[] args) {

	  try {

		URL url = new URL("http://localhost:8080/demorest/webapi/aliens");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

		String line = null;
		while ((line = br.readLine()) != null) {
			teste.append(line);
		}
		
		//node = new ObjectMapper().readValue(teste.toString(), ObjectNode.class);
		//a = (List<Alien>) mapper.readValue(teste.toString(), Alien.class);
		Alien[] x = mapper.readValue(teste.toString(), Alien[].class);
		String output;
		System.out.println("Output from Server .... \n");
		for  (Alien aa: x) {
		System.out.println("ID - " + aa.getId());
		System.out.println("Nome - " + aa.getName());
		System.out.println("Nota - " + aa.getPoints());
		}
	//	while ((output = br.readLine()) != null) {
		//	System.out.println(output);
	//	}

		conn.disconnect();

	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	  }

	}

}
